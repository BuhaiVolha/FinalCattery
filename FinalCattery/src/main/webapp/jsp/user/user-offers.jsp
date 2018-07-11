<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "/jsp/parts/header.jsp" %>
<section id="aboutus" class="area_padding about_area">
<div class="container">
    <div class="row">

        <c:forEach items="${kittensOffers}" var="kittensOffer">
            <div class="col-md-4">
                <div class="card mb-4 box-shadow">

                    <img src="/jsp/assets/img/user.png" alt="kitten">

                    <div class="card-body">
                        <p class="card-text">
                        <h5>
                            <c:out value="${kittensOffer.userMadeOfferName}" />
                            <c:out value="${kittensOffer.userMadeOfferLastname}"/>
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
                        <c:if test="${not empty kittensOffer.expertMessage}">
                            <h5>
                                пояснение: <c:out value="${kittensOffer.expertMessage}" />
                            </h5>
                        </c:if>

                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
</section>
<%@ include file = "/jsp/parts/footer.jsp" %>




