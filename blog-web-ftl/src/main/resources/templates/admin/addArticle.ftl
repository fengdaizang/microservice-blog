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
                     	            <span>您好， ${user.userName }<br> 欢迎您使用本风险评估系统！</span>
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
                                    <h2 class="h3">酒店安全风险评估系统</h2>
                                    <div class="badge badge-rounded bg-green">介绍</div>
                                </div>
                                <div class="card-body no-padding">
                                    <div class="item d-flex align-items-center">
                                        <div class="text">
                                            <h3 class="h5">酒店管理人员可以根据系统所提供的可视化的酒店安全检查体系</h3><br/>
                                            <h3 class="h5">用户可以根据自身实际情况进行检查后将自己的完成情况进行检查并将检查结果通过选择的方式输入到系统中</h3><br/>
                                            <h3 class="h5">系统根据每一项目的赋值比例通过算法计算出整体的得分，并评级别</h3><br/>
                                            <h3 class="h5">并对于现在所有模块的风险大小进行排序</h3><br/>
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