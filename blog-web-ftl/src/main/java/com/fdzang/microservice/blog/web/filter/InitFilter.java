package com.fdzang.microservice.blog.web.filter;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.OptionsClient;
import com.fdzang.microservice.blog.ucenter.feign.client.UserClient;
import com.fdzang.microservice.blog.web.listener.OnlineUserListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class InitFilter implements Filter {
    @Autowired
    private OptionsClient optionsClient;

    @Autowired
    private TagClient tagClient;

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private HttpSession session;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //更新在线人数
        int onlineVisitorCount= OnlineUserListener.online;
        session.setAttribute(Constant.Session.ONLINEVISITORCOUNT,onlineVisitorCount);
        //从session中取值，判断是否加载基础属性
        boolean isInited=false;
        if(session.getAttribute(Constant.Session.ISINITED)==null){
            isInited=false;
        }else {
            isInited=(boolean)session.getAttribute(Constant.Session.ISINITED);
        }
        if(!isInited) {
            List<OptionsDTO> options =(List<OptionsDTO>) CoventUtils.getApiResultData(optionsClient.getAllOptions());
            List<TagDTO> mostUsedTags =(List<TagDTO>) CoventUtils.getApiResultData(tagClient.getMostUsedTags());
            List<ArticleDTO> mostCommentArticles = (List<ArticleDTO>) CoventUtils.getApiResultData(articleClient.getMostCommentArticles());
            List<ArticleDTO> mostViewCountArticles = (List<ArticleDTO>) CoventUtils.getApiResultData(articleClient.getMostViewCountArticles());

            //更新网站浏览次数
            OptionsDTO viewCount=(OptionsDTO)CoventUtils.getApiResultData(
                    optionsClient.getOptionById(Constant.Static.BLOG_VIEW_COUNT));
            optionsClient.incrementById(Constant.Static.BLOG_VIEW_COUNT,1);
            session.setAttribute(Constant.Static.BLOG_VIEW_COUNT,viewCount.getOptionValue());

            //得到博客所有者信息
            UserDTO adminUser = null;
            for (OptionsDTO option : options) {
                session.setAttribute(option.getId(), option.getOptionValue());
                if (option.getId().equals(Constant.User.ADMINEMAIL)) {
                    String email=option.getOptionValue();
                    adminUser = (UserDTO) CoventUtils.getApiResultData(userClient.getUserByEmail(email));
                    session.setAttribute(Constant.User.ADMINUSER, adminUser);
                    session.setAttribute(Constant.User.USERNAME,adminUser.getUserName());
                }
            }

            session.setAttribute(Constant.Session.SERVEPATH, "");
            session.setAttribute(Constant.User.ISLOGGEDIN, false);
            session.setAttribute(Constant.Session.LOGINURL, "/login?goto=/admin-index.do#main");
            session.setAttribute(Constant.Tag.MOSTUSEDTAGS, mostUsedTags);
            session.setAttribute(Constant.Article.MOSTCOMMENTARTICLES, mostCommentArticles);
            session.setAttribute(Constant.Article.MOSTVIEWCOUNTARTICLES, mostViewCountArticles);

            session.setAttribute(Constant.Session.ISINITED, true);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
