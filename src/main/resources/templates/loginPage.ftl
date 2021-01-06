<#import "blocks/common.ftl" as c>
<#import "blocks/login.ftl" as l>

<@c.page>
    <div class="py-5 text-center">
        <h3>
            <div class="mb-1">
                Вxод
            </div>
        </h3>
        <img src="https://img.icons8.com/color/96/000000/user-credentials.png" alt=""/>
    </div>

    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>

    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>

    <@l.login "/loginPage" false/>
</@c.page>