<#import "base/basepage.ftl" as b>

<@b.basepage "Список сообщений">
<body>
<nav id="menu">
    <ul class="links">
        <li><a href="/messlist" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Список сообщений</a></li>
        <li><a href="/addr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Адресаты</a></li>
        <li><a href="/mess" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Добавить рассылку</a></li>
    </ul>
    <a href="#menu" class="close" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></a>
</nav>
<h1 align="center"> Список сообщений </h1>
<div class="wrapper inner content" align="center">
    <div id="addressess" class='table-wrapper scroll font'
         style="height: 500px;">
        <table class="alt">
            <thead>
            <tr>
<#--                <th class="font">№</th>-->
                <th class="font">Описание</th>
                <th class="font">Сообщение</th>
                <th class="font">Удалить</th>
            </tr>
            </thead>
            <tbody id="emails list">
            <#list lastMessList as mess>
                <tr>
<#--                    <td>${mess.id}</td>-->
                    <input id="id" type="hidden" value="${mess.id}">
                    <td onclick="document.location.href='/mess/${mess.id}'">${mess.subject}</td>
                    <td onclick="document.location.href='/mess/${mess.id}'">${mess.email}</td>
                    <td>
                        <button class="button primary small"
                                onclick="deleteMess(this)">X</button>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
<!-- Scripts -->
<script type="text/javascript" src="../public/js/messageList.js"></script>
</@b.basepage>