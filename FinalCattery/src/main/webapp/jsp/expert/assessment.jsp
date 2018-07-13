<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "/jsp/parts/header.jsp" %>
<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="row">

            <c:forEach items="${catsByStatus}" var="catByStatus">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                        <img src="/jsp/assets/img/user.png" alt="kitten">

                        <div class="card-body">
                            <p class="card-text">
                            <h5>
                                By: <c:out value="${catByStatus.userMadeOfferName}" />
                                <c:out value="${catByStatus.userMadeOfferLastname}"/>
                            ИД: <c:out value="${catByStatus.id}" />
                            </h5>
                            <p class="card-text">
                            <h5>
                                описание котенка: <c:out value="${catByStatus.catDescription}" />
                            </h5>
                            <h5>
                                желаемая цена: <c:out value="${catByStatus.price}" /> долларов
                            </h5>
                            <h5>
                                телефон: +375 <c:out value="${catByStatus.userMadeOfferPhone}" />
                            </h5>
                            <h5>
                                статус: <c:out value="${catByStatus.status}" />
                            </h5>

                            <a href="/controller?command=single_offer&offerId=${catByStatus.id}&operation=approve" class="btn btn-primary">Approve</a>
                            <a href="/controller?command=single_offer&offerId=${catByStatus.id}&operation=bargain" class="btn btn-primary">Bargain</a>
                            <a href="/controller?command=single_offer&offerId=${catByStatus.id}&operation=decline" class="btn btn-primary">Politely decline</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</section>
<%@ include file = "/jsp/parts/footer.jsp" %>

