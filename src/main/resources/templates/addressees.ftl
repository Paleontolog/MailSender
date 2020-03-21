<#import "base/basepage.ftl" as b>

<@b.basepage "Адресаты">
 <body>
    <h1 align="center"> Список адресатов </h1>
    <div class="wrapper inner content" align="center">
        <div id="addressess" class='table-wrapper scroll font' >
            <table class="alt">
                <thead>
                <tr>
                    <th class="font">ID пользователя</th>
                    <th class="font">e-mail</th>
                </tr>
                </thead>
                <tbody id="addressess list">
                </tbody>
            </table>
        </div>
        <ul>
            <input type="button" onclick="getAllAddressees();"
                   class="button primary font" name="allAddresses" value="Список адресатов">
        </ul>

        <ul class="actions fit">
            <li>
                <input type="text" class="font" value="" placeholder="email" id="inputE-MAILAdd">
            </li>
            <li>
                <input type="button" onclick="addAddressees();"
                       class="button primary font" name="delAddresses" value="Добавить адресата">
            </li>
        </ul>
        <ul class="actions fit">
            <li>
                <input type="text" value="" class="font" placeholder="id" id="inputID">
            </li>
            <li>
                <input type="text" value="" class="font" placeholder="email"
                       id="inputE-MAILChange">
            </li>
            <li>
                <input type="button" onclick="changeAddresseesOnId();"
                       class="button primary font" name="changeAddresses" value="Изменить адресата">
            </li>
        </ul>
    </div>
    <script src="../public/js/addressees.js" type="text/javascript"></script>
</@b.basepage>