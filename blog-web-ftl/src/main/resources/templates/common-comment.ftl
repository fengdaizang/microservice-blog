<li id="${comment.id}">
    <div>
        <div class="avatar tooltipped tooltipped-n" aria-label="${comment.commentName}"
             style="background-image: url(${comment.commentThumbnailUrl})"></div>
        <main>
            <div class="fn-clear">
                <#if "http://" == comment.commentUrl>
                ${comment.commentName}
                <#else>
                <a class="user-name" href="${comment.commentUrl}" target="_blank">${comment.commentName}</a>
                </#if>
                <#if comment.replyFlag>
                @<a class="user-name" href="${request.contextPath}${article.permalink}#${comment.commentOriginalCommentId}"
                   onmouseover="page.showComment(this, '${comment.commentOriginalCommentId}', 23);"
                   onmouseout="page.hideComment('${comment.commentOriginalCommentId}')"
                >${comment.commentOriginalCommentName}</a>
                </#if>
                <time class="ft-gray">${comment.commentDate?string("yyyy-MM-dd HH:mm")}</time>

                <#if article.articleCommentable = '1'>
                    <a class="reply-btn" onclick="clearForm(${comment.id})">回复</a>
                </#if>
            </div>
            <div class="content-reset">
                ${comment.commentContent}
            </div>
        </main>
    </div>
</li>