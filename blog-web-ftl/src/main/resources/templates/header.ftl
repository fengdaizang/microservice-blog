<header>
    <!-- 编写js代码 -->
    <script type="text/javascript">
        function search() {
            var keyword = $("#keyword").val();
            $.get("${request.contextPath}/search", {"keyword": keyword}, function (data) {
                window.location.reload();
            });
        }
    </script>
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
                    <a href="${request.contextPath}/admin/index.html" title="管理">
                        <i class="icon-setting"></i> 管理
                    </a>
                    <a href="${request.contextPath}/logut.html">
                        <i class="icon-logout"></i> 退出
                    </a>
                    <#else>
                    <a href="${request.contextPath}/login.html">
                        <i class="icon-login"></i> 登录
                    </a>
                    <a href="${request.contextPath}/register.html">
                        <i class="icon-register"></i> 注册
                    </a>
                </#if>
            </div>
        </div>
    </div>

    <div class="navbar">
        <div class="fn-clear wrapper">
            <nav class="fn-left">
                <a href="${request.contextPath}/index.html">
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
            </nav>
            <div class="fn-right">
                <form class="form">
                    <input placeholder="搜索" id="keyword" type="text" name="keyword"/>
                    <button type="button" onclick="search()"><i class="icon-search"></i></button>
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
                <a href="${request.contextPath}/admin/index.html" title="管理">
                    <i class="icon-setting"></i> 管理
                </a>
            </li>
            <li>
                <a href="${request.contextPath}/logout.html">
                    <i class="icon-logout"></i> 退出
                </a>
            </li>
            <#else>
                <li>
                    <a href="${request.contextPath}/login.html">
                        <i class="icon-login"></i> 登录
                    </a>
                </li>
                <li>
                    <a href="${request.contextPath}/register.html">
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
    </ul>
</div>