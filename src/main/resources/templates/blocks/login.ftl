<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="py-5 text-center">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Логин :</label>
                <div class="col-sm-6">
                    <label>
                        <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                               class="form-control ${(usernameError??)?string('is-invalid', '')}"
                               placeholder="Введите логин" size="45"/>
                    </label>

                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>

                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Пароль:</label>
                <div class="col-sm-6">
                    <label>
                        <input type="password" name="password"
                               class="form-control ${(passwordError??)?string('is-invalid', '')}"
                               placeholder="Введите пароль" size="45"/>
                    </label>

                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>

            <#if isRegisterForm>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"> Пароль:</label>
                    <div class="col-sm-6">
                        <label>
                            <input type="password" name="password2"
                                   class="form-control ${(password2Error??)?string('is-invalid', '')}"
                                   placeholder="Повторите пароль" size="45"/>
                        </label>

                        <#if password2Error??>
                            <div class="invalid-feedback">
                                ${password2Error}
                            </div>
                        </#if>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"> Электорнная почта:</label>
                    <div class="col-sm-6">
                        <label>
                            <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                                   class="form-control ${(emailError??)?string('is-invalid', '')}"
                                   placeholder="email@example.com" size="45"/>
                        </label>

                        <#if emailError??>
                            <div class="invalid-feedback">
                                ${emailError}
                            </div>
                        </#if>

                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="g-recaptcha" data-sitekey="6Len9OIZAAAAAOhPCogAB_z1Tng6StWBnPiUyEWP"></div>
                    <#if captchaError??>
                        <div class="alert alert-danger" role="alert">
                            ${captchaError}
                        </div>
                    </#if>
                </div>
            </#if>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-success" type="submit">
                <#if isRegisterForm>
                    Зарегистрироваться
                <#else>
                    Войти
                </#if>
            </button>
            <#if !isRegisterForm>
                <a class="btn btn-outline-success" href="/registrationPage">Регистрация</a>
            </#if>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-success" type="submit">Войти/Выйти</button>
    </form>
</#macro>