<nav class="side-navbar">
    <div class="sidebar-header d-flex align-items-center">
        <div class="avatar"><img src="${user.userAvatar}" alt="..." class="img-fluid rounded-circle"></div>
        <div class="title">
            <h1 class="h4">${user.userName}</h1>
            <p>${user.userRole}</p>
        </div>
    </div>
    <ul class="list-unstyled">
        <li class="active"><a href="${request.contextPath}/admin/index.html"> <i class="icon-home"></i>后台首页</a></li>
        <li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>文章</a>
            <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                <li><a href="#">发布文章</a></li>
                <li><a href="#">文章管理</a></li>
                <li><a href="#">草稿夹</a></li>
            </ul>
        </li>
        <li><a href="${request.contextPath}/article.html"> <i class="icon-grid"></i>评论管理</a></li>
        <li class="active"><a href="${request.contextPath}/admin/index.html"> <i class="icon-home"></i>工具</a></li>
        <li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>文章</a>
            <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                <li><a href="#">链接管理</a></li>
                <li><a href="#">用户管理</a></li>
                <li><a href="#">全局配置</a></li>
                <li><a href="#">其他</a></li>
            </ul>
        </li>
    </ul>
</nav>