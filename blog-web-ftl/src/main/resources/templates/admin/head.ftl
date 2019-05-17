<#macro head title>
<meta charset="utf-8" />
<title>${title}</title>
<#nested>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="author" content="${blogTitle?html}" />
<meta name="generator" content="Solo" />
<meta name="owner" content="B3log Team" />
<meta name="revised" content="${blogTitle?html}" />
<meta name="copyright" content="B3log" />
<meta http-equiv="Window-target" content="_top" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="robots" content="all,follow">
<link rel="stylesheet" href="/admin/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/admin/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/admin/css/fontastic.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/resource/css?family=Poppins:300,400,700">
<link rel="stylesheet" href="/admin/css/style.default.css" id="theme-stylesheet">
<link rel="stylesheet" href="/admin/css/custom.css">
<link rel="icon" type="image/png" href="/favicon.png" />
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
${htmlHead}
</#macro>