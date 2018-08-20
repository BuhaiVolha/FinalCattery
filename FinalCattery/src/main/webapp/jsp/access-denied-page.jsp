<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.access-denied-page.title" var="accessDeniedTitle"/>

<div id="access-denied" class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <h1 style="color: red; font-size: 65px; margin-top: 100px;">${accessDeniedTitle}</h1>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
