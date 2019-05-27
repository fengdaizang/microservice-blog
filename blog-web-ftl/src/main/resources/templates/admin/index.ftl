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
            <section class="dashboard-counts no-padding-bottom">
                <div class="container-fluid">
                    <div class="row bg-white has-shadow">
                        <div class="col-xl-6 col-sm-6">
                            <div class="item d-flex align-items-center">
                                <div class="title">
                     	            <span>您好， ${user.userName }<br> 欢迎您使用本个人博客系统！</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-sm-6">
                            <div class="item d-flex align-items-center">
                                <div class="number">
                                    <strong>当前时间： ${.now?string["yyyy-MM-dd HH:mm:ss"]}</strong>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <br/>
            <section class="updates no-padding-top">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="articles card">
                                <div class="card-header d-flex align-items-center">
                                    <h2 class="h3">个人博客系统系统</h2>
                                    <div class="badge badge-rounded bg-green">介绍</div>
                                </div>
                                <div class="card-body no-padding">
                                    <div class="item d-flex align-items-center">
                                        <div class="text">
                                            <h3 class="h5">本系统的用户有个人博主、一般用户、访客用户、游客。</h3><br/>
                                            <h3 class="h5">游客和访客用户可以查看文章、评论文章、按标签或存档查找文章等</h3><br/>
                                            <h3 class="h5">一般用户可以进入后台管理页面，发表文章，管理自己文章的评论</h3><br/>
                                            <h3 class="h5">个人博主进入后台管理界面管理文章、管理评论、管理用户、管理友链以及其他系统管理</h3><br/>
                                        </div>
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