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
    function deleteComment(id){
        if(confirm('确实要删除该评论吗?')){
            $.get("${request.contextPath}/comment/delete",{"id":id}, function(data){
                if(data){
                    alert("评论删除成功！");
                    window.location.reload();
                }else{
                    alert("评论删除失败！");
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
            <header class="page-header">
                <div class="container-fluid">
                    <h2 class="no-margin-bottom">评论管理</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${request.contextPath}/admin/index.html">首页</a></li>
                    <li class="breadcrumb-item active">评论管理</li>
                </ul>
            </div>
            <section class="tables">
                <div class="container-fluid">
                    <div class="row">
                        <!-- Recent Updates-->
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <span class="card-title">评论管理</span>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>评论内容</th>
                                                <th>文章</th>
                                                <th>作者</th>
                                                <th>时间</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if comments??>
                                                <#list comments as comment>
                                                    <tr>
                                                        <td scope="row">${comment_index+1}</td>
                                                        <td>${comment.commentContent }</td>
                                                        <td><a href="${request.contextPath}${comment.article.articlePermalink}">
                                                                ${comment.article.articleTitle}
                                                            </a>
                                                        </td>
                                                        <td>${comment.commentName }</td>
                                                        <td>${comment.commentDate?string["yyyy-MM-dd HH:mm:ss"] }</td>
                                                        <td><a href="#" class="btn btn-danger btn-xs"
                                                               onclick="deleteComment(${comment.id})">删除</a>
                                                        </td>
                                                    </tr>
                                                </#list>
                                                <#else>
                                                        <tr>
                                                            <td colspan="6">该列表很懒，什么都没有留下。。。</td>
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
</div>
<#include "foot.ftl">
</body>
</html>