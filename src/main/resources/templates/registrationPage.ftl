<#import "blocks/common.ftl" as c>
<#import "blocks/login.ftl" as l>

<@c.page>
<div class="py-5 text-center">
    <h3><div class="mb-1">Регистрация</div></h3>
    <img src="https://img.icons8.com/color/96/000000/iris-scan.png"/>
</div>
${message?ifExists}
<@l.login "/registrationPage" true />
</@c.page>