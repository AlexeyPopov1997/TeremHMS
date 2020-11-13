<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="py-5 text-center">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Логин :</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Введите логин" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Введите пароль" />
            </div>
        </div>
        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Электорнная почта:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="email@example.com" />
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-success" type="submit"><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
        <#if !isRegisterForm><a class="btn btn-outline-success" href="/registrationPage">Регистрация</a></#if>
    </div>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-success" type="submit">Войти/Выйти</button>
</form>
</#macro>