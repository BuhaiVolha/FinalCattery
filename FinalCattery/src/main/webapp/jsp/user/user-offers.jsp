<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<section id="offer" class="area_padding about_area">
    <div class="container">

            <h4>MY OFFERS</h4>

        <div class="row row-eq-height">
        <c:choose>
<c:when test="${!empty catOffers}">
            <c:forEach items="${catOffers}" var="catOffer">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                        <img src="/jsp/assets/img/user.png" alt="kitten">

                        <div class="card-body">
                            <p class="card-text">

                            <p class="card-text">
                            <h5>
                                описание котенка: <c:out value="${catOffer.catDescription}"/>
                            </h5>
                            <h5><c:if test="${catOffer.status == 'DISC'}">предлагаемая</c:if>
                                <c:if test="${catOffer.status != 'DISC'}">желаемая</c:if>
                               цена: <c:out value="${catOffer.price}"/> долларов
                            </h5>

                            <h5>
                                статус: <c:out value="${catOffer.status}"/>
                            </h5>
                            <c:if test="${not empty catOffer.expertMessage}">
                                <h5>
                                    пояснение: <c:out value="${catOffer.expertMessage}"/>
                                </h5>
                            </c:if>
                            <c:if test="${catOffer.status == 'DISC'}">
                                <a href="/controller?command=accept_price&offerId=${catOffer.id}" class="btn btn-primary">Accept</a>
                            </c:if>
                            <a href="/controller?command=delete_offer&offerId=${catOffer.id}" class="btn btn-primary">Delete offer</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
</c:when>
            <c:otherwise>

                <div class="text-center">
                    <img src="/jsp/assets/img/empty.jpg" class="img-responsive" style="margin:0 auto;" alt="Nothing to show"/>
                </div>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</section>
<%@ include file="/jsp/parts/footer.jsp" %>




