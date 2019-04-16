<header>
    <div class="banner">
        <div class="fn-clear wrapper">
            <h1 class="fn-inline">
                <a href="${request.contextPath}" rel="start">
                    ${blogTitle}
                </a>
            </h1>
            <small> &nbsp; ${blogSubtitle}</small>
            <div class="fn-right">
                <#if isLoggedIn>
                    <a href="${request.contextPath}/admin-index.do#main" title="管理">
                        <i class="icon-setting"></i> 管理
                    </a>
                    <a href="${logoutURL}">
                        <i class="icon-logout"></i> 退出
                    </a>
                    <#else>
                    <a href="${request.contextPath}/login">
                        <i class="icon-login"></i> 登录
                    </a>
                    <a href="${request.contextPath}/register">
                        <i class="icon-register"></i> 注册
                    </a>
                </#if>
            </div>
        </div>
    </div>

    <div class="navbar">
        <div class="fn-clear wrapper">
            <nav class="fn-left">
                <a href="${request.contextPath}">
                    <i class="icon-home"></i>
                    首页
                </a>
                <a href="${request.contextPath}/dynamic.html" rel="section">
                    <i class="icon-refresh"></i> 动态
                </a>
                <a href="${request.contextPath}/tags.html" rel="section">
                    <i class="icon-tags"></i> 标签墙
                </a>
                <a href="${request.contextPath}/archives.html">
                    <i class="icon-inbox"></i> 存档
                </a>
                <a rel="archive" href="${request.contextPath}/links.html">
                    <i class="icon-link"></i> 友情链接
                </a>
                <a rel="alternate" href="${request.contextPath}/blog-articles-rss.do" rel="section">
                    <i class="icon-rss"></i> RSS
                </a>
            </nav>
            <div class="fn-right">
                <form class="form" action="${request.contextPath}/search">
                    <input placeholder="搜索" id="search" type="text" name="keyword"/>
                    <button type="submit"><i class="icon-search"></i></button>
                </form>
            </div>
        </div>
    </div>
</header>
<div class="responsive fn-none">
    <i class="icon-list"></i>
    <ul class="list">
        <#if isLoggedIn>
            <li>
                <a href="${request.contextPath}/admin-index.do#main" title="管理">
                    <i class="icon-setting"></i> 管理
                </a>
            </li>
            <li>
                <a href="${logoutURL}">
                    <i class="icon-logout"></i> 退出
                </a>
            </li>
            <#else>
                <li>
                    <a href="${loginURL}">
                        <i class="icon-login"></i> 登录
                    </a>
                </li>
                <li>
                    <a href="${request.contextPath}/register">
                        <i class="icon-register"></i> 注册
                    </a>
                </li>
        </#if>
        <li>
            <a href="${request.contextPath}">
                <i class="icon-home"></i>
                首页
            </a>
        </li>
        <li>
            <a href="${request.contextPath}/dynamic.html" rel="section">
                <i class="icon-refresh"></i> 动态
            </a>
        </li>
        <li>
            <a href="${request.contextPath}/tags.html" rel="section">
                <i class="icon-tags"></i> 标签墙
            </a>
        </li>
        <li>
            <a href="${request.contextPath}/archives.html">
                <i class="icon-inbox"></i> 存档
            </a>
        </li>
        <li>
            <a rel="archive" href="${request.contextPath}/links.html">
                <i class="icon-link"></i> 友情链接
            </a>
        </li>
        <li>
            <a rel="alternate" href="${request.contextPath}/blog-articles-rss.do" rel="section">
                <i class="icon-rss"></i> RSS
            </a>
        </li>
    </ul>
</div>