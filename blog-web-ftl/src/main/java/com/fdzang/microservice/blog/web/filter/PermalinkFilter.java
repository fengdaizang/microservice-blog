package com.fdzang.microservice.blog.web.filter;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class PermalinkFilter implements Filter {
	@Autowired
	private ArticleClient articleClient;

	@Autowired
	private CommentClient commentClient;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 用于文章自定义链接过滤
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		String requestURI=req.getRequestURI();
		String contextPath = req.getContextPath();
		String permalink = StringUtils.substringAfter(requestURI, contextPath);
		ArticleDTO article=(ArticleDTO) CoventUtils.getApiResultData(articleClient.getArticleByPermalink(permalink));
		if(article!=null){
			//更新文章浏览次数
			article.setArticleViewCount(article.getArticleViewCount()+1);
			articleClient.addArticleViewCount(article.getId());
			//得到所有的评论
			List<CommentDTO> articleComments=(List<CommentDTO> )
					CoventUtils.getApiResultData(commentClient.getCommentByArticleId(article.getId()));

			session.setAttribute(Constant.Article.ARTICLE,article);
			session.setAttribute(Constant.Session.ARTICLECOMMENTS,articleComments);

			RequestDispatcher rd = request.getRequestDispatcher("/article");
			try {
				rd.forward(req, resp);
				return;
			}catch(Exception e){}

		}else{
			chain.doFilter(request,response);
		}
	}

	@Override
	public void destroy() {

	}

}