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
    function push(status) {
        $("#articleIsPublished").val(status);
        $.post("${request.contextPath}/article/push", $("#new_article_form").serialize(), function (data) {
            if (data) {
                alert("发布成功！");
                if(status==1){
                    window.location.href="${request.contextPath}/article/mgr";
                }else{
                    window.location.href="${request.contextPath}/link/search";
                }

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
                    <li class="breadcrumb-item active">工具</li>
                    <li class="breadcrumb-item active">配置管理</li>
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
                                    <form method="post" id="new_article_form" action="${request.contextPath}/options/update">
                                        <input type="hidden" name="articleIsPublished" id="articleIsPublished">
                                        <div class="form-group">
                                            <label for="blogTitle" class="form-control-label">博客标题</label>
                                            <input type="text" name="blogTitle" id="blogTitle" placeholder="博客标题"
                                                   value="${blogTitle}" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="blogSubtitle" class="form-control-label">博客子标题</label>
                                            <input type="text" name="blogSubtitle" id="blogSubtitle" placeholder="博客子标题"
                                                   value="${blogSubtitle}" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="metaKeywords" class="form-control-label">Meta Keywords</label>
                                            <input type="text" name="metaKeywords" id="metaKeywords" placeholder="Meta Keywords"
                                                   value="${metaKeywords}" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="metaDescription" class="form-control-label">Meta Description</label>
                                            <input type="text" name="metaDescription" id="metaDescription" placeholder="Meta Description"
                                                   value="${metaDescription}" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="htmlHead" class="form-control-label">HTML head</label>
                                            <input type="text" name="htmlHead" id="htmlHead" placeholder="HTML head"
                                                   value="${htmlHead}" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="noticeBoard" class="form-control-label">公告</label>
                                            <input type="text" name="noticeBoard" id="noticeBoard" placeholder="公告"
                                                   value="${noticeBoard}" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="footerContent" class="form-control-label">页脚</label>
                                            <input type="text" name="footerContent" id="footerContent" placeholder="页脚"
                                                   value="${footerContent}" class="form-control">
                                        </div>
                                        <div class="line"></div>
                                        <div class="form-group row">
                                            <label for="keyword" class="col-sm-10"></label>
                                            <label for="submit" class="col-sm-2 form-control-label">
                                                <input type="submit" value="更新" class="btn btn-success form-control">
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