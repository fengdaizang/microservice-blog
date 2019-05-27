<#include "macro-head.ftl">
<!DOCTYPE html>
<html>
    <head>
        <@head title="${blogTitle}">
        <meta name="keywords" content="${metaKeywords},存档"/>
        <meta name="description" content="${metaDescription},存档"/>
        </@head>
    </head>
    <body>
        <#include "header.ftl">
        <div class="wrapper">
            <div class="main-wrap">
                <main class="other">
                    <span class="title">
                         <h2><i class="icon-inbox"></i>
                             &nbsp;${statisticPublishedBlogArticleCount} 文章</h2>
                    </span>
                    <#if 0 != archiveDates?size>
                        <ul class="list">
                        <#list archiveDates as archiveDate>
                            <li>
                                <a class="post-title"
                                   href="${request.contextPath}/archives/${archiveDate.archiveDateYear?c}/${archiveDate.archiveDateMonth}">
                                    ${archiveDate.archiveDateYear?c} 年 ${archiveDate.archiveDateMonth} 月(${archiveDate.archivedatePublishedArticleCount})
                                </a>
                            </li>
                        </#list>
                        </ul>
                    </#if>
                </main>
                <#include "side.ftl">
            </div>
        </div>
        <#include "footer.ftl">
    </body>
</html>
