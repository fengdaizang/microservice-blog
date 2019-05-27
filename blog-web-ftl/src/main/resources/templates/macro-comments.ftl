<#macro comments commentList article>
<header class='title'><h2>评论</h2></header>
<ul class="comments" id="comments">
    <#if commentList?has_content >
        <#list commentList as comment>
            <#include 'common-comment.ftl'/>
        </#list>
    <#else >
        <li>还没有评论哟！快来抢沙发吧！</li>
    </#if>
</ul>

<div class="modal fade" id="commentReplyDialog" style="display: none">
    <header class='title'><h2>回复评论</h2></header>
    <table id="commentForm" class="form">
        <input type="hidden" id="commentIdReply">
        <tbody>
            <#if !isLoggedIn>
            <tr>
                <td>
                    <input placeholder="姓名" type="text" class="normalInput" id="commentNameReply">
                </td>
            </tr>
            <tr>
                <td>
                    <input placeholder="邮箱" type="email" class="normalInput" id="commentEmailReply">
                </td>
            </tr>
            <tr>
                <td>
                    <input placeholder="URL" type="url" id="commentURLReply">
                </td>
            </tr>
            </#if>
            <tr>
                <td>
                    <textarea rows="5" cols="96" id="commentReply"></textarea>
                </td>
            </tr>
            <#if !isLoggedIn>
            <tr>
                <td>
                    <input style="width:50%" placeholder="验证码" type="text" class="normalInput"
                           id="commentValidateReply">
                    <a onclick="changeReplyImage()">
                        <img class="captcha" id="captchaReply" alt="validate" src="${request.contextPath}/replyCaptcha.do?${.now}">
                    </a>
                </td>
            </tr>
            </#if>
            <tr>
                <td colspan="2" align="right">
                    <span class="error-msg" id="commentReplyErrorTip"></span>
                    <button id="hideReplyForm" onclick="hideForm()">取消</button>
                    <button id="submitCommentButtonReply" onclick="submitReply()">提交评论</button>
                </td>
            </tr>
        </tbody>
    </table>
</div>

<div class="modal fade" id="commentDialog" style="display: inline">
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
                <td>
                    <textarea rows="5" cols="96" id="comment"></textarea>
                </td>
            </tr>
            <#if !isLoggedIn>
            <tr>
                <td>
                    <input style="width:50%" placeholder="验证码" type="text" class="normalInput" id="commentValidate"/>
                    <a onclick="javascript:changeImage();">
                        <img class="captcha" id="captcha" alt="validate" src="${request.contextPath}/captcha.do?${.now}" />
                    </a>
                </td>
            </tr>
            </#if>
            <tr>
                <td colspan="2" align="right">
                    <span class="error-msg" id="commentErrorTip"></span>
                    <button id="submitCommentButton" onclick="submitComment()">提交评论</button>
                </td>
            </tr>
        </tbody>
    </table>
</div>



<script type="text/javascript">
    window.onload=function(){
        $("#commentEmail").val(Cookie.readCookie("commentEmail"));
        $("#commentURL").val(Cookie.readCookie("commentURL"));
        $("#commentName").val(Cookie.readCookie("commentName"));
        $("#commentEmailReply").val(Cookie.readCookie("commentEmail"));
        $("#commentURLReply").val(Cookie.readCookie("commentURL"));
        $("#commentNameReply").val(Cookie.readCookie("commentName"));
    }

    function changeImage() {
        var time = new Date();
        $('#captcha').attr('src','${request.contextPath}/captcha.do?timestamp=' + time.getTime());
    }

    function changeReplyImage() {
        var time = new Date();
        $("#captchaReply").attr('src','${request.contextPath}/replyCaptcha.do?timestamp=' + time.getTime());
    }

    function clearForm(id){
        $("#commentReplyDialog").show();
        $("#commentDialog").hide();

        $("#commentIdReply").val(id);
        $("#commentReply").val("");
        $("#commentValidateReply").val("");
    }

    function hideForm(){
        $("#commentReplyDialog").hide();
        $("#commentDialog").show();
    }

    function submitComment() {
        var isLoggedIn="${isLoggedIn?string}";
        var articleId=${article.id};
        if(isLoggedIn=='false'){
            var commentName=$('#commentName').val();
            var commentEmail=$('#commentEmail').val();
            var commentValidate=$('#commentValidate').val();
            var comment=$('#comment').val();
            var commentURL=$('#commentURL').val();

            if(commentName.length<2||commentName.length>20){
                alert("姓名只能为 2 到 20 个字符！");
                return;
            }

            var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
            if(commentEmail.length<1){
                alert("邮箱不能为空！");
                return;
            }
            if(!reg.test(commentEmail)){
                alert("邮箱格式不正确！");
                return;
            }

            if(commentValidate.length!=4){
                alert("验证码格式错误！");
                return;
            }

            if(comment.length<2||comment.length>20){
                alert("姓名只能为 2 到 20 个字符！");
                return;
            }

            Cookie.createCookie("commentName", commentName, 365);
            Cookie.createCookie("commentEmail", commentEmail, 365);
            Cookie.createCookie("commentURL", commentURL.replace(/(^\s*)|(\s*$)/g, ""), 365);

            $.get("${request.contextPath}/comment/submitNoLogin",
                    {"articleId":articleId,"commentName":commentName,
                        "commentEmail":commentEmail,"comment":comment
                        ,"commentURL":commentURL,"commentValidate":commentValidate}, function(data){
                        if(data == 'ok'){
                            alert("操作成功！");
                            window.location.reload();
                        }else{
                            alert(data);
                            window.location.reload();
                        }
                    });
        }else{
            var comment=$('#comment').val();
            if(comment.length<2||comment.length>500){
                alert("评论内容只能为 2 到 500 个字符！");
                return;
            }

            $.get("${request.contextPath}/comment/submit",
                    {"articleId":articleId,"comment":comment}, function(data){
                        if(data == 'ok'){
                            alert("操作成功！");
                            window.location.reload();
                        }else{
                            alert(data);
                            window.location.reload();
                        }
                    });
        }
    }

    function submitReply() {
        var isLoggedIn="${isLoggedIn?string}";
        var commentId=$('#commentIdReply').val();
        if(isLoggedIn=='false'){
            var commentName=$('#commentNameReply').val();
            var commentEmail=$('#commentEmailReply').val();
            var commentValidate=$('#commentValidateReply').val();
            var comment=$('#commentReply').val();
            var commentURL=$('#commentURLReply').val();

            if(commentName.length<2||commentName.length>20){
                alert("姓名只能为 2 到 20 个字符！");
                return;
            }

            var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
            if(commentEmail.length<1){
                alert("邮箱不能为空！");
                return;
            }
            if(!reg.test(commentEmail)){
                alert("邮箱格式不正确！");
                return;
            }

            if(commentValidate.length!=4){
                alert("验证码格式错误！");
                return;
            }

            if(comment.length<2||comment.length>20){
                alert("姓名只能为 2 到 20 个字符！");
                return;
            }

            Cookie.createCookie("commentName", commentName, 365);
            Cookie.createCookie("commentEmail", commentEmail, 365);
            Cookie.createCookie("commentURL", commentURL.replace(/(^\s*)|(\s*$)/g, ""), 365);

            $.get("${request.contextPath}/comment/replyNoLogin",
                    {"commentId":commentId,"commentName":commentName,
                        "commentEmail":commentEmail,"comment":comment
                        ,"commentURL":commentURL,"commentValidate":commentValidate}, function(data){
                        if(data == 'ok'){
                            alert("操作成功！");
                            window.location.reload();
                        }else{
                            alert(data);
                            window.location.reload();
                        }
                    });
        }else{
            var comment=$('#commentReply').val();
            if(comment.length<2||comment.length>500){
                alert("评论内容只能为 2 到 500 个字符！");
                return;
            }

            $.get("${request.contextPath}/comment/reply",
                    {"commentId":commentId,"comment":comment}, function(data){
                        if(data == 'ok'){
                            alert("操作成功！");
                            window.location.reload();
                        }else{
                            alert(data);
                            window.location.reload();
                        }
                    });
        }
    }

</script>
</#macro>

<#macro comment_script id>
<script type="text/javascript" src="/js/page.js" charset="utf-8"></script>
<script type="text/javascript">
                        var page = new Page({
                            "nameTooLongLabel": "",
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