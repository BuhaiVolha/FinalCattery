<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <div class="row">
        <c:choose>
            <c:when test="${!empty offers}">
                <c:forEach items="${offers}" var="offer">

                    <div class="col-lg-4" style=" background: linear-gradient(#ffebcc, #fff); border-radius: 15px;">
                        <div class="our-team-main">

                            <div class="team-front">
                                <c:choose>
                                    <c:when test="${offer.status == 'AWAIT'}">
                                        <h2 class="stat-await"><c:out value="${offer.status}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'REJCT'}">
                                        <h2 class="stat-reject"><c:out value="${offer.status}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'DISC'}">
                                        <h2 class="stat-disc"><c:out value="${offer.status}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'APRVD'}">
                                        <h2 class="stat-approve"><c:out value="${offer.status}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'SENT'}">
                                        <h2 class="stat-sent"><c:out value="${offer.status}"/></h2>
                                    </c:when>
                                </c:choose>
                                <div class="product-photo">
                                    <c:choose>
                                        <c:when test="${empty offer.photo}">
                                            <img src="/assets/img/uploads/offers/default-offer.jpg"
                                                 style="border-radius:0; margin-bottom:0;" alt="default image"
                                                 class="img-responsive"/>
                                        </c:when>
                                        <c:otherwise>
                                            <a target="_blank" title="Open in new window"
                                               href="/assets/img/uploads/offers/${offer.photo}">
                                                <img src="/assets/img/uploads/offers/${offer.photo}" alt="kitten"
                                                     class="img-responsive"/></a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'EXPERT'}">

                                    <h3>
                                        By:<c:out value="${offer.userMadeOfferName}"/>
                                        <c:out value="${offer.userMadeOfferLastname}"/>
                                    </h3>
                                    <p class="descr">
                                        контактный телефон:</p>
                                    <p>+375 <c:out value="${offer.userMadeOfferPhone}"/>
                                    </p>
                                </c:if>

                            </div>

                            <div class="team-back layer speech-bubble">
	<span>
        <c:if test="${offer.status != 'REJCT' && offer.status != 'DISC'}">
            <p class="descr">описание котенка:</p>
            <p><c:out value="${offer.catDescription}"/></p>
            <hr>
        </c:if>
        <c:choose>
            <c:when test="${not empty offer.expertMessage && sessionScope.role == 'USER' && offer.status == 'REJCT'}">
                <p class="descr">желаемая цена:</p>
                <p><c:out value="${offer.price}"/> долларов </p>
                <hr>
                <p class="decl">пояснение эксперта:</p>
                <p><c:out value="${offer.expertMessage}"/></p>
            </c:when>
            <c:when test="${not empty offer.expertMessage && sessionScope.role == 'USER' && offer.status == 'DISC'}">
                <p class="decl">пояснение эксперта:</p>
                <p><c:out value="${offer.expertMessage}"/></p>
                <hr>
                <p class="decl">предлагаемая цена:</p>
                <p><c:out value="${offer.price}"/> долларов </p>
            </c:when>
            <c:when test="${offer.status == 'SENT' || offer.status == 'APRVD'}">
                <p class="decl">установленная цена:</p>
                <p><c:out value="${offer.price}"/> долларов </p>
            </c:when>
            <c:otherwise>
                <p class="decl">желаемая цена:</p>
                <p><c:out value="${offer.price}"/> долларов </p>
            </c:otherwise>
        </c:choose>
         <c:if test="${not empty offer.expertMessageToAdmin && sessionScope.role == 'ADMIN'}">
             <hr>
             <p class="decl">сообщение от эксперта:</p>
             <p><c:out value="${offer.expertMessageToAdmin}"/></p>
         </c:if>
	</span>
                            </div>

                        </div>

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
                                <c:if test="${offer.status == 'AWAIT'}">
                                    <form style='float: left; padding: 5px;' role="form" method="get"
                                          action="/controller">
                                        <input type="hidden" name="command" value="single_offer"/>
                                        <input type="hidden" name="operation" value="cat-offer-photo"/>
                                        <input type="hidden" name="offerId" value="${offer.id}"/>
                                        <button type="submit" class="btn btn-primary">Edit photo</button>
                                    </form>
                                </c:if>

                                <form style='float: left; padding: 5px;' role="form" method="post"
                                      action="/controller">
                                    <input type="hidden" name="command" value="delete_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <button type="submit" class="btn btn-primary">Delete offer</button>
                                </form>

                            </c:when>
                            <c:when test="${sessionScope.role == 'ADMIN'}">
                                <c:if test="${empty offer.photo}">
                                    <form style='float: left; padding: 5px;' role="form" method="get"
                                          action="/controller">
                                        <input type="hidden" name="command" value="single_offer"/>
                                        <input type="hidden" name="operation" value="cat-offer-photo"/>
                                        <input type="hidden" name="offerId" value="${offer.id}"/>
                                        <button type="submit" class="btn btn-primary">Add photo</button>
                                    </form>
                                </c:if>

                                <form style='float: left; padding: 5px;' role="form" method="get" action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="photo" value="${offer.photo}"/>
                                    <input type="hidden" name="statedPrice" value="${offer.price}"/>
                                    <input type="hidden" name="operation" value="cat-form"/>
                                    <button type="submit" class="btn btn-primary">Add</button>
                                </form>

                            </c:when>
                            <c:when test="${sessionScope.role == 'EXPERT'}">
                                <c:if test="${empty offer.photo}">
                                    <form style='float: left; padding: 5px;' role="form" method="get"
                                          action="/controller">
                                        <input type="hidden" name="command" value="single_offer"/>
                                        <input type="hidden" name="operation" value="cat-offer-photo"/>
                                        <input type="hidden" name="offerId" value="${offer.id}"/>
                                        <button type="submit" class="btn btn-primary">Add photo</button>
                                    </form>
                                </c:if>
                                <form style='float: left; padding: 5px;' role="form" method="get"
                                      action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="operation" value="approve"/>
                                    <button type="submit" class="btn btn-primary">Approve</button>
                                </form>

                                <form style='float: left; padding: 5px;' role="form" method="get"
                                      action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="operation" value="bargain"/>
                                    <button type="submit" class="btn btn-primary">Bargain</button>
                                </form>

                                <form style='float: left; padding: 5px;' role="form" method="get"
                                      action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="operation" value="decline"/>
                                    <button type="submit" class="btn btn-primary">Decline</button>
                                </form>
                            </c:when>
                        </c:choose>
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