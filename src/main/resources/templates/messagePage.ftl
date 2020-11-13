<#include "blocks/security.ftl">
<#import "blocks/common.ftl" as c>

<@c.page>
<div class="py-5 text-center">
    <h3><div class="mb-3">Заявления</div></h3>
    <img src="https://img.icons8.com/cute-clipart/96/000000/copybook.png"/>
</div>

<#if !isUser>
<h5><div class="mt-3">Поиск заявления по типу</div></h5>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/messagePage" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                   placeholder="Введите тип заявления">
            <button type="submit" class="btn btn-success ml-2">Найти заявление</button>
        </form>
    </div>
</div>
</#if>

<#if !isManager>
<div class="py-5 text-center">
<div class="mt-5">
<a class="btn btn-success" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Добавить новое заявление
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="text"
                       placeholder="Заполните заявление в свободной форме с указанием сроков" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="tag" placeholder="Поселение/Продление/Выселение">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-success">Добавить</button>
            </div>
        </form>
    </div>
</div>
</div>
</#if>

<#if !isUser>
<div class="py-5 text-center">
<h5><div class="mb-2">Список заявлений</div></h5>
<div class="card-columns">
    <#list messages as message>
    <div class="card my-3">
        <div class="m-2">
            <span>${message.text}</span>
            <i>${message.tag}</i>
        </div>
        <div class="card-footer text-muted">
            ${message.authorName}
        </div>
        <div class="p-3 mb-2 bg-secondary text-white">
            ${message.status}
        </div>
        <#if isManager>
            <a class="btn btn-outline-success" href="/messagePage/${message.id}" role="button">Одобрить заявление</a>
        </#if>
    </div>
    <#else>
    No message
    </#list>
</div>
</div>
</#if>
</div>
</@c.page>