<#macro login path isRegisterFrom>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                User Name :
            </label>
            <div class="col-sm-4">
                <input type="text" name="username"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="Username"
                       value="<#if user??>${user.username}</#if>"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                Password:
            </label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if isRegisterFrom>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">
                    Password:
                </label>
                <div class="col-sm-4">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}" placeholder="Retype password"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">
                    Email:
                </label>
                <div class="col-sm-4">
                    <input type="email" name="email"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="Email"
                           value="<#if user??>${user.email}</#if>"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>

            </div>
        </#if>
        <#if !isRegisterFrom>
            <a href="/registration">
                Register
            </a>
        </#if>
        <button class="btn primary" type="submit">
            <#if !isRegisterFrom>
                Login
            <#else>
                Create
            </#if>
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn primary" type="submit">
            Sign out
        </button>
    </form>
</#macro>