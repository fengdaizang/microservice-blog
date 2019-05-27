package com.fdzang.microservice.blog.common.utils;

public class Constant {

    public class Cache{
        public static final long CACHE_ONE_SECOND = 1L;
        public static final long CACHE_ONE_MINUTE = 60L;
        public static final long CACHE_TEN_MINUTE = 600L;
        public static final long CACHE_ONE_HOUR = 3600L;
        public static final long CACHE_ONE_DAY = 86400L;
        public static final long CACHE_THREE_DAY = 259200L;
        public static final long CACHE_FOREVER = -1L;
    }

    public class ServiceName{
        public static final String BLOG_CONSOLE = "blog-console-v1";
        public static final String BLOG_UCENTER = "blog-ucenter-v1";
        public static final String BLOG_ARTICLE = "blog-article-v1";
    }
    public class Session{
        public static final String ARTICLES = "articles";
        public static final String PAGINATIONPAGECOUNT = "paginationPageCount";
        public static final String PAGINATIONPAGENUMS = "paginationPageNums";
        public static final String PAGINATIONPREVIOUSPAGENUM = "paginationPreviousPageNum";
        public static final String PAGINATIONCURRENTPAGENUM = "paginationCurrentPageNum";
        public static final String PAGINATIONNEXTPAGENUM = "paginationNextPageNum";
        public static final String ARTICLECOMMENTS = "articleComments";
        public static final String STATISTICBLOGVIEWCOUNT = "statisticBlogViewCount";
        public static final String ADMINEMAIL = "adminEmail";
        public static final String ADMINUSER = "adminUser";
        public static final String USERNAME = "userName";
        public static final String ISLOGGEDIN = "isLoggedIn";
        public static final String ISINITED = "isInited";
        public static final String MOSTVIEWCOUNTARTICLES = "mostViewCountArticles";
        public static final String MOSTCOMMENTARTICLES = "mostCommentArticles";
        public static final String MOSTUSEDTAGS = "mostUsedTags";
        public static final String LOGINURL = "loginURL";
        public static final String SERVEPATH = "servePath";
        public static final String ARTICLE = "article";
        public static final String TAGS = "tags";
        public static final String TAG = "tag";
        public static final String ONLINEVISITORCOUNT = "onlineVisitorCount";
        public static final String ARCHIVEDATES = "archiveDates";
        public static final String ARCHIVEDATE = "archiveDate";
        public static final String RECENTCOMMENTS = "recentComments";
        public static final String LINKS = "links";
        public static final String USER = "user";
        public static final String USERS = "users";
        public static final String KEYWORD = "keyword";
        public static final String COMMENTS = "comments";
        public static final String PAGE = "page";


    }

    public class Page{
        public static final int PAGESIZE = 10;
    }

    public class IndexHtml {
        public static final String INDEX = "index";
        public static final String LINKS = "links";
        public static final String SEARCH = "search";
        public static final String TAGS = "tags";
        public static final String DYNAMIC = "dynamic";
        public static final String ARTICLE = "article";
        public static final String ARCHIVES = "archives";
        public static final String TAG_ARTICLES = "tag-articles";
        public static final String ARCHIVE_ARTICLES = "archive-articles";
    }

    public class AdminHtml {
        public static final String INDEX = "admin/index";
        public static final String LINK = "admin/link";
        public static final String USER = "admin/user";
        public static final String COMMENT = "admin/comment";
        public static final String ADD_ARTICLE = "admin/addArticle";
        public static final String EDIT_ARTICLE = "admin/updateArticle";
        public static final String ARTICLE = "admin/article";
        public static final String DRAFT = "admin/draft";
        public static final String OPTIONS = "admin/options";
        public static final String OTHERS = "admin/others";
        public static final String REGISTER = "admin/register";
        public static final String LOGIN = "admin/login";
    }

    public class UserRole{
        public static final String ADMIN = "adminRole";
        public static final String DEFAULT = "defaultRole";
        public static final String VISITOR = "visitorRole";
    }

    public class Static{
        public static final String MSG = "msg";
        public static final String DEFAULT_AVATAR = "https://secure.gravatar.com/avatar/a6acd9a25c699aab72bb9e60d1eba97f?s=128";
    }

    public class Article{
        public static final String PUSH = "1";
        public static final String DRAFT = "0";
        public static final String ARTICLE = "article";
        public static final String ARTICLES = "articles";
    }
}
