<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "/jsp/parts/header.jsp" %>

<div id="user-welcome" class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="align-center">
                    <h1>Success!</h1>
                    <h2 class="subtitle">${login}, ${role}, you are logged in!</h2>
                    <a class="btn btn-xl btn-outline-light" href="../../jsp/main.jsp">
                        <i class="fa fa-truck mr-2"></i>
                        Go to main page
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file = "/jsp/parts/footer.jsp" %>
