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
    function search() {
        var keyword = $("#keyword").val();
        $.get("${request.contextPath}/link/search", {"keyword": keyword}, function (data) {
            if (data) {
                window.location.reload();
            }
            else {
                alert("未查找的数据！");
                window.location.reload();
            }
        });
    }

    function clearLink() {
        $("#new_address").val("");
        $("#new_description").val("");
        $("#new_title").val("");
    }

    function addLink() {
        $.post("${request.contextPath}/link/add", $("#new_link_form").serialize(), function (data) {
            if (data) {
                alert("新增友链成功！");
                window.location.reload();
            }
            else {
                alert("新增友链失败！");
                window.location.reload();
            }
        });
    }

    function editLink(id) {
        $.ajax({
            type: "get",
            url: "${request.contextPath}/link/getLinkById",
            data: {"id": id},
            success: function (data) {
                $("#edit_address").val(data.linkAddress);
                $("#edit_description").val(data.linkDescription);
                $("#edit_title").val(data.linkTitle);
                $("#edit_id").val(data.id);
            }
        });
    }

    function updateLink() {
        $.post("${request.contextPath}/link/update", $("#edit_link_form").serialize(), function (data) {
            if (data) {
                alert("友链信息更新成功！");
                window.location.reload();
            } else {
                alert("友链信息更新失败！");
                window.location.reload();
            }
        });
    }

    function deleteLink(id) {
        if (confirm('确实要删除该友链吗?')) {
            $.get("${request.contextPath}/link/delete", {"id": id}, function (data) {
                if (data) {
                    alert("友链删除成功！");
                    window.location.reload();
                } else {
                    alert("友链司机失败！");
                    window.location.reload();
                }
            });
        }
    }
</script>
<div class="page">
    <#include "header.ftl">
    <div class="page-content d-flex align-items-stretch">
        <#include "side.ftl">
        <div class="content-inner">
            <!-- Page Header-->
            <header class="page-header">
                <div class="container-fluid">
                    <h2 class="no-margin-bottom">友链管理</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=path%>/index.action">首页</a></li>
                    <li class="breadcrumb-item active">工具</li>
                    <li class="breadcrumb-item active">友链管理</li>
                </ul>
            </div>
            <section class="tables">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header d-flex align-items-center">
                                    <h3 class="h4">关键词搜索</h3>
                                </div>
                                <div class="card-body form-inline">
                                    <div class="form-group">
                                        <label for="keyword" class="mr-4">关键字: </label>
                                    </div>
                                    <div class="form-group">
                                        <label for="keyword" class="sr-only">关键字:</label>
                                        <input id="keyword" type="text" name="keyword"
                                               placeholder="关键字" class="mr-5 form-control">
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-success" onclick="search()">搜索</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <a href="#" class="btn btn-primary" data-toggle="modal"
                                       data-target="#newLinkDialog" onclick="clearLink()">新增</a>&nbsp;
                                    <span class="card-title">友链管理</span>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>链接标题</th>
                                                <th>URL</th>
                                                <th>链接描述</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if links?? && (links?size > 0) >
                                                <#list links as link>
                                                    <tr>
                                                        <td scope="row">${link_index + 1}</td>
                                                        <td>${link.linkTitle }</td>
                                                        <td>${link.linkAddress }</td>
                                                        <td>${link.linkDescription }</td>
                                                        <td>
                                                            <a href="#" class="btn btn-primary btn-xs"
                                                               data-toggle="modal" data-target="#editLinkDialog"
                                                               onclick="editLink(${link.id})">修改</a>
                                                            <a href="#" class="btn btn-danger btn-xs"
                                                               onclick="deleteLink(${link.id})">删除</a>
                                                        </td>
                                                    </tr>
                                                </#list>
                                                <#else>
                                                    <tr>
                                                        <td colspan="5">该列表很懒，什么都没有留下。。。</td>
                                                    </tr>
                                                </#if>
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
    <div class="modal fade" id="newLinkDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">添加友链</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" method="post" id="new_link_form" enctype="multipart/form-data">
                        <div class="form-group form-inline">
                            <label for="new_name" class="col-sm-4 control-label">
                                链接标题
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="new_title" placeholder="链接标题"
                                       name="linkTitle"/>
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="new_age" class="col-sm-4 control-label">
                                URL
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="new_address" placeholder="URL"
                                       name="linkAddress"/>
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="new_driverid" class="col-sm-4 control-label">
                                链接描述
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="new_description" placeholder="链接描述"
                                       name="linkDescription"/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="addLink()">新增</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="editLinkDialog" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">更新友链信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="edit_link_form">
                        <input type="hidden" id="edit_id" name="id"/>
                        <div class="form-group form-inline">
                            <label for="edit_name" class="col-sm-4 control-label">
                                链接标题
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="edit_title" placeholder="链接标题"
                                       name="linkTitle"/>
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="edit_age" class="col-sm-4 control-label">
                                URL
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="edit_address" placeholder="URL"
                                       name="linkAddress"/>
                            </div>
                        </div>
                        <div class="form-group form-inline">
                            <label for="edit_firstlicensetime" class="col-sm-4 control-label">
                                链接描述
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="edit_description" placeholder="链接描述"
                                       name="linkDescription"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateLink()">修改</button>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "foot.ftl">
</body>
</html>