<#macro baseLog head action>
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>${head}</title>
    <link href="../public/assets/css/main.css" rel="stylesheet" type="text/css"/>
    <link href="../public/assets/css/myCSS.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper inner content" align="center">
    <h1>${head}</h1>
    <div class="row gtr-uniform">
        <div class="col-4 col-12-xsmall"></div>
        <div class="col-4 col-12-xsmall">
            <form method="post" action="/${action}" >
                <input name="username" type="text" placeholder="Username" />
                <input name="password" type="password" placeholder="Password"/>
                <input id="_csrf" type='hidden' value='${_csrf.token}' name='_csrf'/>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>

    <#nested >

</div>
</body>
</html>
</#macro>
