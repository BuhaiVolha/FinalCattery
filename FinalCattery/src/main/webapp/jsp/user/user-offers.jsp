<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>
<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="row">

            <c:forEach items="${catOffers}" var="catOffer">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                        <img src="/jsp/assets/img/user.png" alt="kitten">

                        <div class="card-body">
                            <p class="card-text">

                            <h5>
                            <c:if test="${sessionScope.role eq 'EXPERT'}">
                                <c:out value="${catOffer.userMadeOfferName}"/>
                                <c:out value="${catOffer.userMadeOfferLastname}"/>
                            </c:if>
                            </h5>
                            <p class="card-text">
                            <h5>
                                описание котенка: <c:out value="${catOffer.catDescription}"/>
                            </h5>
                            <h5><c:if test="${catOffer.status == 'DISC'}">предлагаемая</c:if>
                                <c:if test="${catOffer.status != 'DISC'}">желаемая</c:if>
                               цена: <c:out value="${catOffer.price}"/> долларов
                            </h5>
                            <h5>
                                телефон: +375 <c:out value="${catOffer.userMadeOfferPhone}"/>
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
                            <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">Send
                            </button>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</section>
<%@ include file="/jsp/parts/footer.jsp" %>




