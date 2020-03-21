<#macro basepage page>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>${page}</title>
    <header id="header">
        <a class="logo"
           href="javascript: document.getElementById('logout').submit()" >${userName}</a>
        <nav>
            <a href="#menu">Menu</a>
        </nav>
    </header>

    <form action="/logout" method="post" id="logout">
        <input id="_csrf" type='hidden' value='${_csrf.token}' name='_csrf'/>
    </form>

    <nav id="menu">
        <ul class="links">
            <li><a href="/messlist" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Список рассылок</a></li>
            <li><a href="/addr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Адресаты</a></li>
            <li><a href="/mess" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Добавить рассылку</a></li>
        </ul>
        <a href="#menu" class="close" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></a>
    </nav>

    <link href="../public/assets/css/main.css" rel="stylesheet" type="text/css"/>
    <link href="../public/assets/css/myCSS.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../public/js/mustache.js"></script>
</head>

    <#nested>

<#--base scripts-->
    <script src="../public/js/jquery.js" type="text/javascript"></script>
    <script src="../public/assets/js/jquery.min.js"></script>
    <script src="../public/assets/js/browser.min.js"></script>
    <script src="../public/assets/js/breakpoints.min.js"></script>
    <script src="../public/assets/js/util.js"></script>
    <script src="../public/assets/js/main.js"></script>
</body>
</html>
</#macro>