<#import "base/basepage.ftl" as b>

<@b.basepage "Сообщение">
    <body onload="onloadPage()">
    <h1 align="center"> Редактирование сообщения </h1>
    <div class="wrapper inner content" align="center">
        <div class="row gtr-uniform">
            <div class="col-6 col-12-xsmall">
                <div id="addressess" class='table-wrapper font scroll' style="height: 150px" >
                    <table >
                        <thead>
                        <tr></tr>
                        </thead>
                        <tbody id="emailList">
<#--                             <#list emList as em>-->
<#--                                 <tr>-->
<#--                                    <td>${em}</td>-->
<#--                                 <td>-->
<#--                                     <button class="button primary small"-->
<#--                                      onclick='emailDelete(this)'>X</button></td>-->
<#--                                 </tr>-->
<#--                             </#list>-->
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-4 col-12-xsmall">
                <input type="text" name="city" id="inputEmail" list="cityname" placeholder="-Выбрать адресата-"
                       oninput="load(this);">
                <datalist id="cityname">
                </datalist>
            </div>

            <div class="col-2 col-12-xsmall">
                <input type="button" onclick="addEmail()"
                       class="button primary font" name="saveMessage" value="Добавить">
            </div>

            <div class="col-12">
                <input type="text" class="font" value="<#if subject??>${subject} </#if>"
                       placeholder="Тема" id="messageSubject">
            </div>

            <div class="col-12">
                <textarea class="font" name="textarea" id="messageText"
                          placeholder="Введите текст сообщения"
                          style="resize: none" rows="6"><#if text??>${text}</#if></textarea>
            </div>

            <div class="col-12">
                <input type="button" onclick="saveMessage();"
                       class="button primary font" name="saveMessage" value="Сохранить сообщение">
            </div>

        </div>
    </div>
    <!-- Scripts -->
    <script src="../public/js/message.js" type="text/javascript"></script>
</@b.basepage>