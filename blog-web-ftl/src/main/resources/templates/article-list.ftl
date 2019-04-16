<div>
    <#list articles as article>
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
                <#if article.hasUpdated>
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
                        ${article.articleCommentCount} 评论</a>
                </span>
                &nbsp; | &nbsp;
                <span class="tooltipped tooltipped-n" aria-label="浏览数">
                    <i class="icon-views"></i>
                    ${article.articleViewCount} 浏览
                </span>
            </div>
        </header>
        <div class="content-reset">
            ${article.articleAbstract}
        </div>
        <footer class="fn-clear tags">
            <#list article.articleTags?split(",") as articleTag>
                <a class="tag" rel="tag" href="${request.contextPath}/tags/${articleTag?url('UTF-8')}">
                    ${articleTag}</a>
            </#list>
            <a href="${request.contextPath}${article.articlePermalink}#more" rel="contents" class="fn-right">
                阅读全文 &raquo;
            </a>
        </footer>
    </article>
    </#list>


    <#if 0 != paginationPageCount>
        <div class="fn-clear">
            <nav class="pagination fn-right">
                <#if 1 != paginationPageNums?first>
                <a href="${request.contextPath}${path}/${paginationPreviousPageNum}" class="page-number">&laquo;</a>
                    <a class="page-number" href="${request.contextPath}${path}/1">1</a> <span class="page-number">...</span>
                </#if>
                <#list paginationPageNums as paginationPageNum>
                <#if paginationPageNum == paginationCurrentPageNum>
                <span class="page-number current">${paginationPageNum}</span>
                <#else>
                <a class="page-number" href="${request.contextPath}${path}/${paginationPageNum}">${paginationPageNum}</a>
                </#if>
                </#list>
                <#if paginationPageNums?last != paginationPageCount> <span class="page-number">...</span>
                <a href="${request.contextPath}${path}/${paginationPageCount}" class="page-number">${paginationPageCount}</a>
                <a href="${request.contextPath}${path}/${paginationNextPageNum}" class="page-number">&raquo;</a>
                </#if>
            </nav>
        </div>
    </#if>
</div>