<#include "macro-head.ftl">
<#include "macro-comments.ftl">
<!DOCTYPE html>
<html>
    <head>
        <@head title="${article.articleTitle} - ${blogTitle}">
        <meta name="keywords" content="${article.articleTags}" />
        <meta name="description" content="${article.articleAbstract?html}" />
        </@head>
        <#if previousArticlePermalink??>
            <link rel="prev" title="${previousArticleTitle}" href="${request.contextPath}${previousArticlePermalink}">
        </#if>
        <#if nextArticlePermalink??>
            <link rel="next" title="${nextArticleTitle}" href="${request.contextPath}${nextArticlePermalink}">
        </#if>
            <!-- Open Graph -->
            <meta property="og:locale" content="zh_CN"/>
            <meta property="og:type" content="article"/>
            <meta property="og:title" content="${article.articleTitle}"/>
            <meta property="og:description" content="${article.articleAbstract?html}"/>
            <meta property="og:url" content="${request.contextPath}${article.articlePermalink}"/>
            <meta property="og:site_name" content="Solo"/>
            <!-- Twitter Card -->
            <meta name="twitter:card" content="summary"/>
            <meta name="twitter:description" content="${article.articleAbstract?html}"/>
            <meta name="twitter:title" content="${article.articleTitle}"/>
            <meta name="twitter:url" content="${request.contextPath}${article.articlePermalink}"/>
            <meta name="twitter:site" content="@DL88250"/>
            <meta name="twitter:creator" content="@DL88250"/>
    </head>
    <body>
        <#include "header.ftl">
        <div class="wrapper">
            <div class="main-wrap">
                <main>
                    <article class="post">
                        <header>
                            <h1>
                                <a rel="bookmark" href="${request.contextPath}${article.articlePermalink}">
                                    ${article.articleTitle}
                                </a>
                                <#if article.articlePutTop = '1'>
                                    <sup>
                                        置顶！
                                    </sup>
                                </#if>
                                <#if article.articleHadBeenPublished == '1'>
                                    <sup>
                                        有更新！
                                    </sup>
                                </#if>
                            </h1>
                            <div class="meta">
                                <span class="tooltipped tooltipped-n" aria-label="创建日期">
                                    <i class="icon-date"></i>
                                    <time>
                                        ${article.articleCreateDate?string("yyyy-MM-dd")}
                                    </time>
                                </span>
                                                &nbsp; | &nbsp;
                                                <span class="tooltipped tooltipped-n" aria-label="评论数">
                                    <i class="icon-comments"></i>
                                    <a href="${request.contextPath}${article.articlePermalink}#comments">
                                        ${article.articleCommentCount} 评论！</a>
                                </span>
                                                &nbsp; | &nbsp;
                                                <span class="tooltipped tooltipped-n" aria-label="浏览量">
                                    <i class="icon-views"></i>
                                    ${article.articleViewCount} 浏览
                                </span>
                            </div>
                        </header>

                        <div class="content-reset">
                            ${article.articleAbstract}
                        </div>

                        <footer class="tags">
                            <#list article.articleTags?split(",") as articleTag>
                                <a class="tag" rel="tag"
                                   href="${request.contextPath}/tags/${articleTag?url('UTF-8')}">
                                    ${articleTag}
                                </a>
                            </#list>

                            <div class="rel fn-clear">
                                <#if previousArticlePermalink??>
                                    <a href="${request.contextPath}${previousArticlePermalink}" rel="prev"
                                       class="fn-left tooltipped tooltipped-n"
                                       aria-label="${previousArticleTitle}">
                                        上一篇
                                    </a>
                                </#if>
                                <#if nextArticlePermalink??>
                                    <a href="${request.contextPath}${nextArticlePermalink}" rel="next"
                                       class="fn-right tooltipped tooltipped-n"
                                       aria-label="${nextArticleTitle}">
                                        下一篇
                                    </a>
                                </#if>
                            </div>
                        </footer>
                        <@comments commentList=articleComments!'' article=article></@comments>
                    </article>
                </main>
                <#include "side.ftl">
            </div>
        </div>
        <#include "footer.ftl">
    </body>
</html>
