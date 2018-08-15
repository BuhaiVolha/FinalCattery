<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file = "/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.success-page.title" var="successPageTitle"/>
<fmt:message bundle="${loc}" key="local.success-page.description" var="successPageDescription"/>

<div id="user-welcome" class="header">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
                <div class="align-center">
                    <h1>${successPageTitle},</h1>
                    <h2 class="subtitle">${successPageDescription}!</h2>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file = "/jsp/parts/footer.jsp" %>
