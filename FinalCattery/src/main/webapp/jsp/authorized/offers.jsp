<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="row row-eq-height">
            <c:choose>
                <c:when test="${!empty offers}">
                    <c:forEach items="${offers}" var="offer">
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">

                                <img src="/assets/img/uploads/offers/${offer.photo}" alt="kitten">

                                <div class="card-body">
                                    <p class="card-text">

                                        <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'EXPERT'}">
                                    <h5>
                                        By: <c:out value="${offer.userMadeOfferName}"/>
                                        <c:out value="${offer.userMadeOfferLastname}"/>
                                    </h5>
                                    <p class="card-text">
                                    <h5>
                                        контактный телефон: +375 <c:out value="${offer.userMadeOfferPhone}"/>
                                    </h5>
                                    </c:if>

                                    <h5>
                                        описание котенка: <c:out value="${offer.catDescription}"/>
                                    </h5>
                                    <h5>
                                        <c:choose>
                                            <c:when test="${offer.status == 'DISC'}">
                                                предлагаемая
                                            </c:when>
                                            <c:when test="${offer.status == 'SENT' || offer.status == 'APRVD'}">
                                                установленная
                                            </c:when>
                                            <c:otherwise>
                                                желаемая
                                            </c:otherwise>
                                        </c:choose>
                                        цена: <c:out value="${offer.price}"/> долларов
                                    </h5>

                                    <h5>
                                        статус: <c:out value="${offer.status}"/>
                                    </h5>
                                    <c:choose>
                                        <c:when test="${not empty offer.expertMessage && sessionScope.role == 'USER'}">
                                            <h5>
                                                пояснение: <c:out value="${offer.expertMessage}"/>
                                            </h5>
                                        </c:when>
                                        <c:when test="${not empty offer.expertMessageToAdmin && sessionScope.role == 'ADMIN'}">
                                            <h5>
                                                сообщение от эксперта: <c:out value="${offer.expertMessageToAdmin}"/>
                                            </h5>
                                        </c:when>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${sessionScope.role == 'USER'}">
                                            <c:if test="${offer.status == 'DISC'}">

                                                <form style='float: left; padding: 5px;' role="form" method="post"
                                                      action="/controller">
                                                    <input type="hidden" name="command" value="accept_price"/>
                                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                                    <input type="hidden" name="price" value="${offer.price}"/>
                                                    <button type="submit" class="btn btn-primary">Accept</button>
                                                </form>
                                            </c:if>
                                            <form style='float: left; padding: 5px;' role="form" method="post"
                                                  action="/controller">
                                                <input type="hidden" name="command" value="delete_offer"/>
                                                <input type="hidden" name="offerId" value="${offer.id}"/>
                                                <button type="submit" class="btn btn-primary">Delete offer</button>
                                            </form>
                                            <c:if test="${offer.status == 'AWAIT'}">

                                                <form style='float: left; padding: 5px;' role="form" method="post"
                                                      action="/controller">
                                                    <input type="hidden" name="command" value="single_offer"/>
                                                    <input type="hidden" name="operation" value="cat-offer-photo"/>
                                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                                    <button type="submit" class="btn btn-primary">Edit photo</button>
                                                </form>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${sessionScope.role == 'ADMIN'}">
                                            <form role="form" method="post" action="/controller">
                                                <input type="hidden" name="command" value="single_offer"/>
                                                <input type="hidden" name="offerId" value="${offer.id}"/>
                                                <input type="hidden" name="photo" value="${offer.photo}"/>
                                                <input type="hidden" name="statedPrice" value="${offer.price}"/>
                                                <input type="hidden" name="operation" value="add-cat"/>
                                                <button type="submit" class="btn btn-primary">Add</button>
                                            </form>
                                        </c:when>
                                        <c:when test="${sessionScope.role == 'EXPERT'}">
                                            <form style='float: left; padding: 5px;' role="form" method="post"
                                                  action="/controller">
                                                <input type="hidden" name="command" value="single_offer"/>
                                                <input type="hidden" name="offerId" value="${offer.id}"/>
                                                <input type="hidden" name="operation" value="approve"/>
                                                <button type="submit" class="btn btn-primary">Approve</button>
                                            </form>

                                            <form style='float: left; padding: 5px;' role="form" method="post"
                                                  action="/controller">
                                                <input type="hidden" name="command" value="single_offer"/>
                                                <input type="hidden" name="offerId" value="${offer.id}"/>
                                                <input type="hidden" name="operation" value="bargain"/>
                                                <button type="submit" class="btn btn-primary">Bargain</button>
                                            </form>

                                            <form style='float: left; padding: 5px;' role="form" method="post"
                                                  action="/controller">
                                                <input type="hidden" name="command" value="single_offer"/>
                                                <input type="hidden" name="offerId" value="${offer.id}"/>
                                                <input type="hidden" name="operation" value="decline"/>
                                                <button type="submit" class="btn btn-primary">Politely decline</button>
                                            </form>
                                        </c:when>
                                    </c:choose>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>

                    <div class="text-center">
                        <img src="/assets/img/empty.jpg" class="img-responsive" style="margin:0 auto;"
                             alt="Nothing to show"/>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</section>
<%@ include file="/jsp/parts/footer.jsp" %>



