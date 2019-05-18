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
<!-- 编写js代码 -->
<script type="text/javascript">
    function search()
    {
        var keyword=$("#keyword").val();
        $.post("${request.contextPath}/user/search",{"keyword":keyword}, function(data){
            if(data)
            {
                window.location.reload();
            }
            else
            {
                alert("查找失败！");
                window.location.reload();
            }
        });
    }

    function clearUser()
    {
        $("#new_username").val("");
        $("#new_avatar").val("");
        $("#new_password").val("");
        $("#new_email").val("");
        $("#new_url").val("");
    }

    function addUser()
    {
        $.post("${request.contextPath}/user/add",$("#new_user_form").serialize(),function(data){
            if(data)
            {
                alert("新增用户成功！");
                window.location.reload();
            }
            else
            {
                alert("新增用户失败！");
                window.location.reload();
            }
        });
    }

    function editUser(id)
    {
        $.ajax({
            type:"get",
            url:"${request.contextPath}/user/getUserById",
            data:{"id":id},
            success:function(data)
            {
                $("#edit_id").val(data.id);
                $("#edit_avatar").val(data.userAvatar);
                $("#edit_url").val(data.userURL);
                $("#edit_email").val(data.userEmail);
                $("#edit_password").val(data.password);
                $("#edit_username").val(data.userName);
            }
        });
    }

    function updateLink()
    {
        $.post("${request.contextPath}/user/update",$("#edit_link_form").serialize(),function(data){
            if(data){
                alert("用户信息更新成功！");
                window.location.reload();
            }else{
                alert("用户信息更新失败！");
                window.location.reload();
            }
        });
    }

    function deleteUser(id){
        if(confirm('确实要删除该用户吗?')){
            $.post("${request.contextPath}/user/delete",{"id":id}, function(data){
                if(data){
                    alert("用户删除成功！");
                    window.location.reload();
                }else{
                    alert("用户司机失败！");
                    window.location.reload();
                }
            });
        }
    }

    function changeUser(id)
    {
        $.post("${request.contextPath}/user/change",{"id":id}, function(data){
            if(data){
                alert("角色修改成功！");
                window.location.reload();
            }else{
                alert("角色修改失败！");

            }
        });
    }
</script>
<div class="page">
    <#include "header.ftl">
    <div class="page-content d-flex align-items-stretch">
        <#include "side.ftl">
        <div class="content-inner">
            <header class="page-header">
                <div class="container-fluid">
                    <h2 class="no-margin-bottom">首页</h2>
                </div>
            </header>
            <section class="updates no-padding-top">
                <div class="container-fluid">
                    <div class="row">
                        <!-- Recent Updates-->
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">搜索</div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group form-inline">
                                        <div class="col-md-6">
                                            <input type="text" class="form-control input-full" id="keyword" name="keyword" placeholder="关键字">
                                        </div>
                                        <div class="col-md-3">
                                            <button class="btn btn-success" onclick="search()">搜索</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <a href="#" class="btn btn-primary" data-toggle="modal"
                                       data-target="#newUserDialog" onclick="clearUser()">新建</a>
                                    <span class="card-title">用户管理</span>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>用户名</th>
                                                <th>邮箱</th>
                                                <th>角色</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#list users as user>
                                                    <tr>
                                                        <td scope="row">${user_index}</td>
                                                        <td>${user.userName }</td>
                                                        <td>${user.userEmail }</td>
                                                        <td>
                                                            <#if user.userRole=="defaultRole">
                                                                一般用户
                                                            <#elseif user.userRole=="visitorRole">
                                                                访客用户
                                                            <#else>
                                                                管理员
                                                            </#if>
                                                        </td>
                                                        <td>
                                                            <#if user.userRole=="adminRole">
                                                                <a href="#" class="btn btn-primary btn-xs"
                                                                   data-toggle="modal" data-target="#editUserDialog"
                                                                   onclick="editUser(${user.id})">修改</a>
                                                            <#else>
                                                                <a href="#" class="btn btn-primary btn-xs"
                                                                   data-toggle="modal" data-target="#editUserDialog"
                                                                   onclick="editUser(${user.id})">修改</a>
                                                                <a href="#" class="btn btn-danger btn-xs"
                                                                   onclick="deleteUser(${user.id})">删除</a>
                                                                <a href="#" class="btn btn-danger btn-xs"
                                                                    onclick="changeUser(${user.id})">删除</a>
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#list>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
          <#include "footer.ftl">
        </div>
    </div>

    <div class="modal fade" id="newUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">添加用户</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" method="post" id="new_user_form" enctype="multipart/form-data">
                        <div class="form-group form-inline">
                            <label for="new_username" class="col-sm-4 control-label">
                                姓名
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="new_username" placeholder="姓名"
                                       required data-msg="请输入用户名" name="userName" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="new_email" class="col-sm-4 control-label">
                                邮箱
                            </label>
                            <div class="col-sm-8">
                                <input type="email" class="form-control" id="new_email" placeholder="姓名"
                                       required data-msg="请输入合法的邮箱地址" name="userEmail" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="new_url" class="col-sm-4 control-label">
                                链接
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="new_url" placeholder="URL"
                                       required data-msg="请输入链接" name="userUrl" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="new_password" class="col-sm-4 control-label">
                                密码
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="new_password" placeholder="链接描述"
                                       required data-msg="请输入密码" name="password" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="new_avatar" class="col-sm-4 control-label">
                                头像
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="new_avatar" placeholder="链接描述" name="userAvatar" />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="addUser()">新增</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="editUserDialog" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">更新用户信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="edit_link_form">
                        <input type="hidden" id="edit_id" name="id"/>
                        <div class="form-group form-inline">
                            <label for="edit_username" class="col-sm-4 control-label">
                                姓名
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="edit_username" placeholder="姓名"
                                       required data-msg="请输入用户名" name="userName" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="edit_email" class="col-sm-4 control-label">
                                邮箱
                            </label>
                            <div class="col-sm-8">
                                <input type="email" class="form-control" id="edit_email" placeholder="姓名"
                                       required data-msg="请输入合法的邮箱地址" name="userEmail" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="edit_url" class="col-sm-4 control-label">
                                链接
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="edit_url" placeholder="URL"
                                       required data-msg="请输入链接" name="userUrl" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="edit_password" class="col-sm-4 control-label">
                                密码
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="edit_password" placeholder="链接描述"
                                       required data-msg="请输入密码" name="password" />
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="edit_avatar" class="col-sm-4 control-label">
                                头像
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="edit_avatar" placeholder="链接描述" name="userAvatar" />
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateUser()">修改</button>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "foot.ftl">
</body>
</html>