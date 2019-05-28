<aside>
    <section>
        <#if noticeBoard??>
            <div class="ad content-reset">
                ${noticeBoard}
            </div>
        </#if>

        <#if 0 != mostUsedTags?size>
            <div class="module">
                <header><h2>标签</h2></header>
                <main>
                    <#list mostUsedTags as tag>
                        <a rel="tag"
                           href="${request.contextPath}/tags/${tag.tagTitle?url('UTF-8')}"
                           class="tag tooltipped tooltipped-n"
                           aria-label="${tag.tagPublishedRefCount} 篇文章">
                                ${tag.tagTitle}</a>
                    </#list>
                </main>
            </div>
        </#if>

        <div class="module meta">
            <header>
                <h2>${adminUser.userName}</h2>
            </header>
            <main class="fn-clear">
                <img src="${adminUser.userAvatar}" aria-label="${adminUser.userName}"/>
                <div class="fn-right">
                    <a href="${request.contextPath}/archives.html">
                        ${statisticPublishedBlogArticleCount}
                        <span class="ft-gray">文章</span></a><br/>
                    <a href="${request.contextPath}/dynamic.html">
                        ${statisticPublishedBlogCommentCount}
                        <span class="ft-gray">评论</span></a><br/>
                    ${statisticBlogViewCount} <span class="ft-gray">浏览</span><br/>
                    12 <span class="ft-gray">访客</span>
                </div>
            </main>
        </div>

        <#if 0 != mostCommentArticles?size>
            <div class="module">
                <header><h2>评论最多的文章</h2></header>
                <main class="list">
                    <ul>
                        <#list mostCommentArticles as article>
                            <li>
                                <a rel="nofollow" aria-label="${article.articleCommentCount} 评论"
                                   class="tooltipped tooltipped-e"
                                   href="${request.contextPath}${article.articlePermalink}">
                                    ${article.articleTitle}
                                </a>
                            </li>
                        </#list>
                    </ul>
                </main>
            </div>
        </#if>

        <#if 0 != mostViewCountArticles?size>
            <div class="module">
                <header><h2>访问最多的文章</h2></header>
                <main class="list">
                    <ul>
                        <#list mostViewCountArticles as article>
                            <li>
                                <a rel="nofollow" aria-label="${article.articleViewCount} 浏览"
                                   class="tooltipped tooltipped-e"
                                   href="${request.contextPath}${article.articlePermalink}">
                                    ${article.articleTitle}
                                </a>
                            </li>
                        </#list>
                    </ul>
                </main>
            </div>
        </#if>
    </section>
</aside>