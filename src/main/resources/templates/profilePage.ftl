<#include "blocks/security.ftl">
<#import "blocks/common.ftl" as c>

<@c.page>
    <div class="py-5 text-center">
        <h3>
            <div class="mb-5">
                Личный кабинет пользователя
            </div>
        </h3>

        <h5>
            <div class="mt-1">
                ${username}
            </div>
        </h5>
        <img src="https://img.icons8.com/color/96/000000/red-panda.png" alt=""/>

        ${message?ifExists}

        <form method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Пароль:</label>
                <div class="col-sm-6">
                    <label>
                        <input type="password" name="password" class="form-control" placeholder="Пароль" size="45"/>
                    </label>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Электронная почта:</label>
                <div class="col-sm-6">
                    <label>
                        <input type="email" name="email" class="form-control" placeholder="email@example.com"
                               value="${email!''}" size="45"/>
                    </label>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-success" type="submit">
                Сохранить изменения
            </button>
        </form>
    </div>

    <#if !isManager>
        <div class="row">
            <div class="col-md-4">
                <h5>Заселиться в теремок</h5>
                <p> Хочешь хорошо провести время, зверенок? Так чего же ты ждешь?
                    Скорей нажимай эту кнопку и проведи незабываемое время в лучших апартаментах леса! </p>
                <p>
                    <a class="btn btn-secondary" href="/agreementPage" role="button">Заселиться</a>
                </p>
            </div>

            <div class="col-md-4">
                <h5>Выселиться из теремка</h5>
                <p> Очень жалко прощаться с тобой, но без расстований нет и долгожданных встреч. Пока!
                    Возвражайся к нам, мы будем тебя ждать! </p>
                <p>
                    <a class="btn btn-secondary" href="/agreementPage" role="button">Выселиться</a>
                </p>
            </div>

            <div class="col-md-4">
                <h5>Продлить ареду</h5>
                <p>Мы очень рады, что тебе сложно с нами растаться! Оставайся! А для этого всего лишь нажми на это кнопку.
                    Не так много нужно сделать, не правда ли?</p>
                <p>
                    <a class="btn btn-secondary" href="/agreementPage" role="button">
                        Продлить
                    </a>
                </p>
            </div>
        </div>
    </#if>
</@c.page>