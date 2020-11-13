<#import "blocks/common.ftl" as c>
<#import "blocks/login.ftl" as l>

<@c.page>
<div class="py-5 text-center">
    <h3><div class="mb-1">Вxод</div></h3>
    <img src="https://img.icons8.com/color/96/000000/user-credentials.png"/>
</div>
${message?ifExists}
<@l.login "/loginPage" false/>
</@c.page>