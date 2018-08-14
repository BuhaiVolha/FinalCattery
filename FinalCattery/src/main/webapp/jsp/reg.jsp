<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.reg.title" var="regTitle"/>
<fmt:message bundle="${loc}" key="local.reg.field.login" var="regLogin"/>
<fmt:message bundle="${loc}" key="local.reg.field.password" var="regPassword"/>
<fmt:message bundle="${loc}" key="local.reg.field.password.rule" var="regPasswordRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.login.rule" var="regLoginRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.name" var="regName"/>
<fmt:message bundle="${loc}" key="local.reg.field.name.rule" var="regNameRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.lastname" var="regLastname"/>
<fmt:message bundle="${loc}" key="local.reg.field.lastname.rule" var="regLastnameRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.email" var="regEmail"/>
<fmt:message bundle="${loc}" key="local.reg.field.email.rule" var="regEmailRule"/>
<fmt:message bundle="${loc}" key="local.reg.field.phone" var="regPhone"/>
<fmt:message bundle="${loc}" key="local.reg.field.phone.rule" var="regPhoneRule"/>
<fmt:message bundle="${loc}" key="local.reg.button.submit" var="regSubmit"/>

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form role="form" method="POST" action="/controller">
                <input type="hidden" name="command" value="registration"/>
                <h2 style="text-align: center">${regTitle}
                    <small>
                        <hr class="colorgraph">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="login" id="loginVal" required="required"
                                           class="form-control input-lg" placeholder="${regLogin}" tabindex="6"
                                           pattern="[0-9a-zA-Zа-яА-Я]{2,10}"
                                           title="${regLoginRule}">
                                    <span></span>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password"
                                           required="required" class="form-control input-lg"
                                           placeholder="${regPassword}"
                                           tabindex="7" pattern=".{7,15}" title="${regPasswordRule}"> <span></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="name" id="name" required="required"
                                           class="form-control input-lg" placeholder="${regName}" tabindex="1"
                                           pattern="[a-zA-Zа-яА-Я]{2,20}"
                                           title="${regNameRule}">
                                    <span></span></div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="lastname" id="lastname" required="required"
                                           class="form-control input-lg" placeholder="${regLastname}" tabindex="2"
                                           pattern="[a-zA-Zа-яА-Я]{2,20}"
                                           title="${regLastnameRule}">
                                    <span></span></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" id="email" class="form-control input-lg"
                                   required="required"
                                   placeholder="${regEmail}" tabindex="4"
                                   title="${regEmailRule}"
                                   pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-z]{2,3}$">
                            <span></span></div>

                        <div class="form-group">
                            <input type="text" name="phone" id="phone" class="form-control input-lg" required="required"
                                   placeholder="${regPhone}" tabindex="5" pattern="^[0-9]{2,2}\s[0-9]{7,7}$"
                                   title="${regPhoneRule}">
                            <span></span></div>

                        <p style="text-align: center; color: red">${param.registrationFailedMessage}</p>

                        <hr class="colorgraph">
                        <div class="row">
                            <input type="submit" value="${regSubmit}" class="btn btn-primary btn-block btn-lg"
                                   tabindex="8">
                        </div>
                    </small>
            </form>
        </div>
    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>