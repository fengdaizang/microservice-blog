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
        $.get("${request.contextPath}/article/search",{"keyword":keyword}, function(data){
            window.location.reload();
        });
    }

    function pushTop(id,isTop)
    {
        $.get("${request.contextPath}/article/pushTop",{"id":id,"isTop":isTop}, function(data){
            if(data){
                alert("操作成功！");
                window.location.reload();
            }else{
                alert("操作失败！");
                window.location.reload();
            }
        });
    }

    function deleteArticle(id){
        if(confirm('确实要删除该文章吗?')){
            $.get("${request.contextPath}/article/delete",{"id":id}, function(data){
                if(data){
                    alert("文章删除成功！");
                    window.location.reload();
                }else{
                    alert("文章删除失败！");
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
                    <h2 class="no-margin-bottom">文章管理</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${request.contextPath}/admin/index.html">首页</a></li>
                    <li class="breadcrumb-item active">文章</li>
                    <li class="breadcrumb-item active">文章管理</li>
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
                        <!-- Recent Updates-->
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <span class="card-title">文章管理</span>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>标题</th>
                                                <th>作者</th>
                                                <th>评论</th>
                                                <th>浏览</th>
                                                <th>日期</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if page??>
                                                <#list page.result as article>
                                                    <tr>
                                                        <td scope="row">${article_index+1}</td>
                                                        <td><a href="${request.contextPath}${article.articlePermalink}">
                                                                ${article.articleTitle}
                                                            </a>
                                                        </td>
                                                        <td>${article.articleAuthorEmail }</td>
                                                        <td>${article.articleCommentCount }</td>
                                                        <td>${article.articleViewCount }</td>
                                                        <td>${article.articleCreateDate?string["yyyy-MM-dd HH:mm:ss"] }</td>
                                                        <td><a href="${request.contextPath}/article/edit?id=${article.id}.html"
                                                               class="btn btn-primary btn-xs">更新</a>
                                                            <a href="#" class="btn btn-danger btn-xs"
                                                               onclick="deleteArticle(${article.id})">删除</a>
                                                            <#if article.articlePutTop == '0' >
                                                                <a href="#" class="btn btn-success btn-xs"
                                                                   onclick="pushTop(${article.id},1)">置顶</a>
                                                            <#else >
                                                                <a href="#" class="btn btn-success btn-xs"
                                                                   onclick="pushTop(${article.id},0)">取消置顶</a>
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#list>
                                                <#else>
                                                    <tr>
                                                        <td colspan="7">该列表很懒，什么都没有留下。。。</td>
                                                    </tr>
                                                </#if>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <#if page??>
                    <div class="row clearfix">
                        <div class="fn-clear">
                            <nav class="pagination fn-right">
                                <#if 1 != page.pages?first>
                                    <a href="${request.contextPath}${path}${page.previous}" class="page-number">&laquo;</a>
                                    <a class="page-number" href="${request.contextPath}${path}1">1</a>
                                <span class="page-number">...</span>
                                </#if>
                                <#list page.pages as pageNum>
                                    <#if pageNum == page.pageNo>
                                    <span class="page-number current">${pageNum}</span>
                                    <#else>
                                    <a class="page-number" href="${request.contextPath}${path}${pageNum}">${pageNum}</a>
                                    </#if>
                                </#list>
                                <#if page.pages?last != page.totalPage>
                                <span class="page-number">...</span>
                                <a href="${request.contextPath}${path}${page.totalPage}" class="page-number">${page.totalPage}</a>
                                <a href="${request.contextPath}${path}${page.next}" class="page-number">&raquo;</a>
                                </#if>
                            </nav>
                        </div>
                    </div>
                    </#if>
                </div>
            </section>
          <#include "footer.ftl">
        </div>
    </div>
</div>
<#include "foot.ftl">
</body>
</html>