<#include "head.ftl">
<!DOCTYPE html>
<html>
<head>
  <@head title="${blogTitle}">
  <#if metaKeywords??>
    <meta name="keywords" content="${metaKeywords}"/>
  </#if>
  <#if metaDescription??>
    <meta name="description" content="${metaDescription}"/>
  </#if>
  </@head>
</head>
<body>
<div class="page login-page">
    <div class="container d-flex align-items-center">
        <div class="form-holder has-shadow">
            <div class="row">
                <div class="col-lg-6">
                    <div class="info d-flex align-items-center">
                        <div class="content">
                            <div class="logo">
                                <h1>个人博客系统</h1>
                            </div>
                            <p>欢迎使用本系统，请先登录！</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 bg-white">
                    <div class="form d-flex align-items-center">
                        <div class="content">
                            <#if msg??>
                                <span style="color: red;margin-bottom: 20px;">${msg }</span>
                            </#if>
                            <form method="post" action="${request.contextPath}/user/login.html" class="form-validate">
                                <div class="form-group">
                                    <input id="login-username" type="email" name="username" required data-msg="邮箱格式不正确！"
                                           class="input-material">
                                    <label for="login-username" class="label-material">邮箱</label>
                                </div>
                                <div class="form-group">
                                    <input id="login-password" type="password" name="password" required
                                           data-msg="密码不能为空！" class="input-material">
                                    <label for="login-password" class="label-material">密码</label>
                                </div>
                                <button id="login" type="submit" class="btn btn-primary">登录</button>
                            </form>
                            <small>还没有账号？</small>
                            <a href="${request.contextPath}/register.html" class="signup">立即注册</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyrights text-center">
        <p>Design by <a class="external">Bootstrapious</a></p>
    </div>
</div>
<#include "foot.ftl">
</body>
</html>