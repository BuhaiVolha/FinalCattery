<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "/jsp/parts/header.jsp" %>
<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="row">

            <c:forEach items="${awaitingKittenOffers}" var="kittensOffer" varStatus="row">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                        <img src="/jsp/assets/img/user.png" alt="kitten">

                        <div class="card-body">
                            <p class="card-text">
                            <h5>
                                By: <c:out value="${kittensOffer.userMadeOfferName}" />
                                <c:out value="${kittensOffer.userMadeOfferLastname}"/>
                            ИД: <c:out value="${kittensOffer.id}" />
                            </h5>
                            <p class="card-text">
                            <h5>
                                описание котенка: <c:out value="${kittensOffer.catDescription}" />
                            </h5>
                            <h5>
                                желаемая цена: <c:out value="${kittensOffer.price}" /> долларов
                            </h5>
                            <h5>
                                телефон: +375 <c:out value="${kittensOffer.userMadeOfferPhone}" />
                            </h5>
                            <h5>
                                статус: <c:out value="${kittensOffer.status}" />
                            </h5>

                            <a href="/jsp/user/kitten-offer.jsp" class="btn btn-primary">Approve</a>
                            <a href="/jsp/user/kitten-offer.jsp" class="btn btn-primary">Bargain</a>
                            <a href="/jsp/expert/decline.jsp" class="btn btn-primary">Politely decline</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</section>
<%@ include file = "/jsp/parts/footer.jsp" %>

