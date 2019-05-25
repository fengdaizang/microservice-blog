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
                window.location.href="${request.contextPath}/link/search";
            }
            else {
                alert("发布失败，请重试！");
                window.location.reload();
            }
        });
    }

    function push() {
        $.post("${request.contextPath}/article/push", $("#new_article_form").serialize(), function (data) {
            if (data) {
                alert("发布成功！");
                window.location.href="${request.contextPath}/link/search";
            }
            else {
                alert("发布失败，请重试！");
                window.location.reload();
            }
        });
    }

    function draft() {
        $.post("${request.contextPath}/article/draft", $("#new_article_form").serialize(), function (data) {
            if (data) {
                alert("发布成功！");
                window.location.href="${request.contextPath}/link/search";
            }
            else {
                alert("发布失败，请重试！");
                window.location.reload();
            }
        });
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
                    <h2 class="no-margin-bottom">新增文章</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${request.contextPath}/admin/index.html">首页</a></li>
                    <li class="breadcrumb-item active">文章管理</li>
                    <li class="breadcrumb-item active">新增文章</li>
                </ul>
            </div>
            <!-- Forms Section-->
            <section class="forms">
                <div class="container-fluid">
                    <div class="row">
                        <!-- Basic Form-->
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header d-flex align-items-center">
                                    <h3 class="h4">填写文章内容</h3>
                                </div>
                                <div class="card-body">
                                    <p>注意：正文和摘要均支持markdown格式</p>
                                    <form method="post" id="new_article_form">
                                        <div class="form-group">
                                            <label for="articleTitle" class="form-control-label">标题</label>
                                            <input type="text" name="articleTitle" id="articleTitle" placeholder="文章的标题" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="articleContent" class="form-control-label">正文</label>
                                            <textarea name="articleContent" class="form-control" id="articleContent" rows="8"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="articleTags" class="form-control-label">标签</label>
                                            <input type="text" name="articleTags" class="form-control" id="articleTags"
                                                   placeholder="文章的标签，多个标签使用英文半角逗号分隔">
                                        </div>
                                        <div class="form-group">
                                            <label for="articleAbstract" class="form-control-label">摘要</label>
                                            <textarea name="articleAbstract" class="form-control" id="articleAbstract" rows="6"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="articlePermalink" class="form-control-label">链接</label>
                                            <input type="text" name="articlePermalink" id="articlePermalink"
                                                   placeholder="自定义链接，不填则自动生成" class="form-control">
                                        </div>
                                        <div class="line"></div>
                                        <div class="form-group row">
                                            <label for="keyword" class="col-sm-8"></label>
                                            <label for="submit" class="col-sm-2 form-control-label">
                                                <input type="button" value="保存为草稿" onclick="draft()" class="btn btn-primary form-control">
                                            </label>
                                            <label for="submit" class="col-sm-2 form-control-label">
                                                <input type="button" value="发布" onclick="push()" class="btn btn-success form-control">
                                            </label>
                                        </div>
                                    </form>
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