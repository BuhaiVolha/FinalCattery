<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.login-modal.title" var="loginModalTitle"/>
<fmt:message bundle="${loc}" key="local.login-modal.field.login" var="loginField"/>
<fmt:message bundle="${loc}" key="local.login-modal.field.password" var="passwordField"/>
<fmt:message bundle="${loc}" key="local.login-modal.field.login.rule" var="loginRule"/>
<fmt:message bundle="${loc}" key="local.login-modal.field.password.rule" var="passwordRule"/>
<fmt:message bundle="${loc}" key="local.login-modal.button.submit" var="buttonLogin"/>
<fmt:message bundle="${loc}" key="local.login-modal.no-account" var="noAccount"/>

<div class="modal fade" id="login-window">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title" style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${loginModalTitle}</h2>
            </div>
            <div class="modal-body">
                <form role="form" method="POST" action="/controller">
                    <input type="hidden" name="command" value="login"/>

                    <div class="form-group">
                        <label class="image-replace email" for="signin-login1">${loginField}</label>
                        <input class="form-control" name="login" id="signin-login1" type="text"
                               placeholder="${loginField}" required="required" pattern="[0-9a-zA-Zа-яА-Я]{2,10}"
                               title="${loginRule}">
                    </div>

                    <div class="form-group">
                        <label class="image-replace password" for="signin-password1">${passwordField}</label>
                        <input class="form-control" name="password" id="signin-password1" type="password"
                               placeholder="${passwordField}" required="required" pattern=".{7,15}"
                               title="${passwordRule}">
                    </div>

                    <div id="signInError" class="error form-group" style="color: red; text-align: center">${errorLoginPassMessage}</div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block" value="log-in"
                                id="sendMessageButton1">${buttonLogin}</button>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <div class="text-center">
                    <a href="/jsp/reg.jsp" tabindex="5" class="forgot-password">${noAccount}</a>
                </div>
            </div>
        </div>
    </div>
</div>
