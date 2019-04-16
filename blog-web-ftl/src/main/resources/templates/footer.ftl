<footer class="footer fn-clear">
    &copy; 2019
    ${footerContent}
    <a href="${request.contextPath}">${blogTitle}</a>  &nbsp;   • &nbsp;
    <a href="https://solo.b3log.org" target="_blank">Solo</a> ${version}  <br/>

    Powered by <a href="https://b3log.org" target="_blank">B3log</a> 开源 &nbsp;
    <span class="ft-warn">&heartsuit;</span>
    Theme <a rel="friend" href="https://github.com/b3log/solo-skins" target="_blank">9IPHP</a> by <a href="https://github.com/9IPHP/9IPHP" target="_blank">9IPHP</a> & <a href="http://vanessa.b3log.org" target="_blank">Vanessa</a>
</footer>
<div class="icon-up" onclick="Util.goTop()"></div>

<script type="text/javascript" src="${request.contextPath}/static/js/lib/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${request.contextPath}/static/91PHP/js/common.js" charset="utf-8"></script>
<script type="text/javascript">
    var latkeConfig = {
        "servePath": "${request.contextPath}",
        "staticServePath": "${request.contextPath}",
        "isLoggedIn": "${isLoggedIn?string}",
        "userName": "${userName}"
    };

    var Label = {
        "skinDirName": "91PHP",
        "em00Label": ":smile:",
        "em01Label": ":joy:",
        "em02Label": ":stuck_out_tongue_winking_eye:",
        "em03Label": ":persevere:",
        "em04Label": ":sob:",
        "em05Label": ":cold_sweat:",
        "em06Label": ":rage:",
        "em07Label": ":triumph:",
        "em08Label": ":eyes:",
        "em09Label": ":scream:",
        "em10Label": ":sunglasses:",
        "em11Label": ":yum:",
        "em12Label": ":heart:",
        "em13Label": ":broken_heart:",
        "em14Label": ":smiling_imp:"
    };

    Util.parseMarkdown('content-reset');
</script>
