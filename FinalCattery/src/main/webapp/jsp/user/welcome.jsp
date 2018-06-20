<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "../parts/header.jsp" %>

<section class="bg-primary text-white mb-0" id="about">
    <div class="container">
        <h2 class="text-center text-uppercase text-white">Success!</h2>
        <hr class="star-light mb-5">
        <h4 class="text-center text-uppercase text-white">${user}, you are logged in!</h4>
        <div class="text-center mt-4">

            <a class="btn btn-xl btn-outline-light" href="controller?command=Logout">
                <i class="fa fa-truck mr-2"></i>
                Logout
            </a>
            <a class="btn btn-xl btn-outline-light" href="../../jsp/main.jsp">
                <i class="fa fa-truck mr-2"></i>
                Go to main page
            </a>
        </div>
    </div>
</section>

<%@ include file = "../parts/footer.jsp" %>
