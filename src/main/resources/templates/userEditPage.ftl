<#import "blocks/common.ftl" as c>

<@c.page>
    <div class="py-5 text-center">
        <h3>
            <div class="mb-2">
                Редактирование прав пользователя
            </div>
        </h3>

        <div class="mb-5">
            <img src="https://img.icons8.com/color/96/000000/computer-support.png" alt=""/>
        </div>

        <form action="/user" method="post">
            <label>
                <input type="text" name="username" value="${user.username}">
            </label>
            <#list roles as role>
                <div class="form-check">
                    <div class="mt-2">
                        <label>
                            <input class="form-check-input" type="checkbox" name="${role}"
                                    ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                        </label>
                    </div>
                </div>
            </#list>

            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <div class="mt-5">
                <button type="submit" class="btn btn-success">
                    Сохранение изменений
                </button>
            </div>
        </form>
    </div>
</@c.page>