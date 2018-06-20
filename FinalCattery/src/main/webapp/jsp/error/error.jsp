<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "../parts/header.jsp" %>

<section class="bg-primary text-white mb-0" id="about">
    <div class="container">
        <h2 class="text-center text-uppercase text-white">Error page!</h2>
        <hr class="star-light mb-5">
        <h4 class="text-center text-uppercase text-white">Something wrong!</h4>
        <div class="text-center mt-4">
            <img class="img-fluid mb-5 d-block mx-auto" src="../../static/img/error.png" alt="">
            <a class="btn btn-xl btn-outline-light" href="../../jsp/main.jsp">
                <i class="fa fa-frown-o mr-2 mr-2"></i>
                Go to main page
            </a>
        </div>
    </div>
</section>

<%@ include file = "../parts/footer.jsp" %>
