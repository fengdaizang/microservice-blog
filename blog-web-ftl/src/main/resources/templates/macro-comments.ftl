<#macro comments commentList article>
<header class='title'><h2>评论</h2></header>
<ul class="comments" id="comments">
    <#list commentList as comment>
        <#include 'common-comment.ftl'/>
    </#list>
</ul>
<#if article.articleCommentable = '1'>
    <header class='title'><h2>发表评论</h2></header>
        <table id="commentForm" class="form">
            <tbody>
                <#if !isLoggedIn>
                <tr>
                    <td>
                        <input placeholder="姓名" type="text" class="normalInput" id="commentName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input placeholder="邮箱" type="email" class="normalInput" id="commentEmail"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input placeholder="URL" type="url" id="commentURL"/>
                    </td>
                </tr>
                </#if>
                <tr>
                    <td id="emotions" class="emotions">
                        <span class="em00" title=":smile:"></span>
                        <span class="em01" title=":joy:"></span>
                        <span class="em02" title=":stuck_out_tongue_winking_eye:"></span>
                        <span class="em03" title=":persevere:"></span>
                        <span class="em04" title=":sob:"></span>
                        <span class="em05" title=":cold_sweat:"></span>
                        <span class="em06" title=":rage:"></span>
                        <span class="em07" title=":triumph:"></span>
                        <span class="em08" title=":eyes:"></span>
                        <span class="em09" title=":scream:"></span>
                        <span class="em10" title=":sunglasses:"></span>
                        <span class="em11" title=":yum:"></span>
                        <span class="em12" title=":heart:"></span>
                        <span class="em13" title=":broken_heart:"></span>
                        <span class="em14" title=":smiling_imp:"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <textarea rows="5" cols="96" id="comment"></textarea>
                    </td>
                </tr>
                <#if !isLoggedIn>
                <tr>
                    <td>
                        <input style="width:50%" placeholder="验证码" type="text" class="normalInput" id="commentValidate"/>
                        <img class="captcha" id="captcha" alt="validate" src="${request.contextPath}/captcha.do" />
                    </td>
                </tr>
                </#if>
                <tr>
                    <td colspan="2" align="right">
                        <span class="error-msg" id="commentErrorTip"></span>
                        <button id="submitCommentButton" onclick="page.submitComment();">提交评论</button>
                    </td>
                </tr>
            </tbody>
        </table>
</#if>
</#macro>

<#macro comment_script id>
<script type="text/javascript" src="/js/page.js" charset="utf-8"></script>
<script type="text/javascript">
                        var page = new Page({
                            "nameTooLongLabel": "姓名只能为 2 到 20 个字符！",
                            "mailCannotEmptyLabel": "邮箱不能为空！",
                            "mailInvalidLabel": "邮箱格式不正确！",
                            "commentContentCannotEmptyLabel": "评论内容只能为 2 到 500 个字符！",
                            "captchaCannotEmptyLabel": "验证码不能为空！",
                            "loadingLabel": "载入中....",
                            "oId": "${id}",
                            "skinDirName": "91PHP",
                            "blogHost": "localhost",
                            "randomArticles1Label": "随机阅读：",
                            "externalRelevantArticles1Label": "相关站外阅读："
                        });
                        var replyTo = function (id) {
                            var commentFormHTML = "<table class='form comment-reply' id='replyForm'>";
                            page.addReplyForm(id, commentFormHTML);
                        };
                        (function () {
                            page.load();
                            Skin.initArticle("文章目录", "站点概要");
                            // emotions
                            page.replaceCommentsEm("#comments .content-reset");
                            <#nested>
                        })();
</script>
</#macro>