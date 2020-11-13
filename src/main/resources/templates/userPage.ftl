<#import "blocks/common.ftl" as c>

<@c.page>
<div class="py-5 text-center">
    <h3><div class="mb-1">Список пользователей</div></h3>
    <img src="https://img.icons8.com/color/96/000000/user-menu-female-skin-type-7.png"/>
</div>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Логин</th>
        <th>Роли пользователя</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a class="btn btn-success" href="/user/${user.id}">Изменить</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>