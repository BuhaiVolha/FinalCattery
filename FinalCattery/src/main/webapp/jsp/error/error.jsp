<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file = "/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.error-page.title" var="errorPageTitle"/>
<fmt:message bundle="${loc}" key="local.error-page.message" var="errorPageMessage"/>

<div id="not-found" class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-6" style="padding-top: 198px;">
                <div class="align-center">
                    <h1>${errorPageTitle}</h1>
                    <h2 class="subtitle">${errorPageMessage}</h2>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file = "/jsp/parts/footer.jsp" %>
