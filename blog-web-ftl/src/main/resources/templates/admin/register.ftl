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
<!-- 编写js代码 -->
<script type="text/javascript">
    function checkUserEmail()
    {
        var email=$("#register-email").val();
        $.ajax({
            type:"get",
            url:"${request.contextPath}/user/isExistEmail",
            data:{"email":email},
            success:function(data)
            {
                if(data){
                    alert("邮箱被占用，请更换！");
                }
            }
        });
    }

    function checkPassword()
    {
        var password=$("#register-password").val();
        if(password.length < 6 || password.length > 20){
            alert("密码为6-20位之间！");
            return;
        }
        var reg=/^[0-9a-zA-Z]*$/;
        if(password!=""&&!reg.test(password)){
            alert("密码只能输入是字母或者数字,请重新输入");
            return;
        }
    }

    function checkRePassword()
    {
        var password=$("#register-password").val();
        var repassword=$("#register-repassword").val();
        if(password!=repassword){
            alert("两次密码不一致！");
            return;
        }
    }
</script>
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
                            <p>欢迎使用本系统，欢迎注册！</p>
                        </div>
                    </div>
                </div>
                <!-- Form Panel    -->
                <div class="col-lg-6 bg-white">
                    <div class="form d-flex align-items-center">
                        <div class="content">
                            <#if msg??>
                                <span style="color: red;margin-bottom: 20px;">${msg }</span>
                            </#if>
                            <form class="form-validate" method="get" action="${request.contextPath}/user/register.html">
                                <div class="form-group">
                                    <input id="register-email" type="email" name="userEmail" required data-msg="请输入合法的邮箱地址"
                                           class="input-material" onblur="checkUserEmail()">
                                    <label for="register-email" class="label-material">邮箱</label>
                                </div>
                                <div class="form-group">
                                    <input id="register-username" type="text" name="userName" required data-msg="请输入用户名"
                                           class="input-material">
                                    <label for="register-username" class="label-material">用户名</label>
                                </div>
                                <div class="form-group">
                                    <input id="register-url" type="text" name="userUrl" class="input-material">
                                    <label for="register-url" class="label-material">链接</label>
                                </div>
                                <div class="form-group">
                                    <input id="register-password" type="password" name="password" required
                                           data-msg="请输入密码" class="input-material" onblur="checkPassword()">
                                    <label for="register-password" class="label-material">密码</label>
                                </div>
                                <div class="form-group">
                                    <input id="register-repassword" type="password" required
                                           data-msg="请输入密码" class="input-material" onblur="checkRePassword()">
                                    <label for="register-repassword" class="label-material">确认密码</label>
                                </div>
                                <div class="form-group">
                                    <button id="regidter" type="submit" name="registerSubmit" class="btn btn-primary">
                                        注册
                                    </button>
                                </div>
                            </form>
                            <small>已有账号?</small>
                            <a href="${request.contextPath}/login.html" class="signup">立即登录</a>
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