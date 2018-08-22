<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.offers.status.awaiting" var="offersStatusAwaiting"/>
<fmt:message bundle="${loc}" key="local.offers.status.discussed" var="offersStatusDiscussed"/>
<fmt:message bundle="${loc}" key="local.offers.status.rejected" var="offersStatusRejected"/>
<fmt:message bundle="${loc}" key="local.offers.status.approved" var="offersStatusApproved"/>
<fmt:message bundle="${loc}" key="local.offers.status.sent" var="offersStatusSent"/>
<fmt:message bundle="${loc}" key="local.default.photo.alt" var="defaultPhoto"/>
<fmt:message bundle="${loc}" key="local.cats.photo.alt" var="catsPhoto"/>
<fmt:message bundle="${loc}" key="local.new-window" var="newWindow"/>
<fmt:message bundle="${loc}" key="local.offers.by" var="offersBy"/>
<fmt:message bundle="${loc}" key="local.offers.phone" var="offersPhone"/>
<fmt:message bundle="${loc}" key="local.offers.cat-description" var="offersCatDescription"/>
<fmt:message bundle="${loc}" key="local.offers.price-wanted" var="offersPriceWanted"/>
<fmt:message bundle="${loc}" key="local.offers.expert-message-to-user" var="offersMessageToUser"/>
<fmt:message bundle="${loc}" key="local.offers.price-offered" var="offersPriceOffered"/>
<fmt:message bundle="${loc}" key="local.offers.price-stated" var="offersPriceStated"/>
<fmt:message bundle="${loc}" key="local.offers.expert-message-to-admin" var="offersMessageToAdmin"/>
<fmt:message bundle="${loc}" key="local.offers.user.button.accept-price" var="offersUserButtonAccept"/>
<fmt:message bundle="${loc}" key="local.offers.button.edit-photo" var="offersUserButtonEditPhoto"/>
<fmt:message bundle="${loc}" key="local.offers.user.button.delete-offer" var="offersUserButtonDelete"/>
<fmt:message bundle="${loc}" key="local.offers.button.add-photo" var="offersButtonAddPhoto"/>
<fmt:message bundle="${loc}" key="local.offers.expert.button.approve" var="offersExpertButtonApprove"/>
<fmt:message bundle="${loc}" key="local.offers.expert.button.bargain" var="offersExpertButtonBargain"/>
<fmt:message bundle="${loc}" key="local.offers.expert.button.decline" var="offersExpertButtonDecline"/>
<fmt:message bundle="${loc}" key="local.offers.admin.button.add" var="offersAdminButtonAdd"/>
<fmt:message bundle="${loc}" key="local.photo.nothing-to-show" var="nothingToShow"/>


<div class="container">
    <div class="row">
        <c:choose>
            <c:when test="${!empty requestScope.offers}">
                <c:forEach items="${requestScope.offers}" var="offer">

                    <div class="col-lg-4" style=" background: linear-gradient(#ffebcc, #fff); border-radius: 15px;">
                        <div class="our-team-main">

                            <div class="team-front">
                                <c:choose>
                                    <c:when test="${offer.status == 'AWAIT'}">
                                        <h2 class="stat-await"><c:out value="${offersStatusAwaiting}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'REJCT'}">
                                        <h2 class="stat-reject"><c:out value="${offersStatusRejected}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'DISC'}">
                                        <h2 class="stat-disc"><c:out value="${offersStatusDiscussed}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'APRVD'}">
                                        <h2 class="stat-approve"><c:out value="${offersStatusApproved}"/></h2>
                                    </c:when>
                                    <c:when test="${offer.status == 'SENT'}">
                                        <h2 class="stat-sent"><c:out value="${offersStatusSent}"/></h2>
                                    </c:when>
                                </c:choose>
                                <div class="product-photo">
                                    <c:choose>
                                        <c:when test="${empty offer.photo}">
                                            <img src="/assets/img/uploads/offers/default-offer.jpg"
                                                 style="border-radius:0; margin-bottom:0;" alt="${defaultPhoto}"
                                                 class="img-responsive"/>
                                        </c:when>
                                        <c:otherwise>
                                            <a target="_blank" title="${newWindow}"
                                               href="/assets/img/uploads/offers/${offer.photo}">
                                                <img src="/assets/img/uploads/offers/${offer.photo}" alt="${catsPhoto}"
                                                     class="img-responsive"/></a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'EXPERT'}">

                                    <h3>
                                        ${offersBy}: <c:out value="${offer.userMadeOfferName}"/>
                                        <c:out value="${offer.userMadeOfferLastname}"/>
                                    </h3>
                                    <p class="descr">
                                        ${offersPhone}:</p>
                                    <p>+375 <c:out value="${offer.userMadeOfferPhone}"/>
                                    </p>
                                </c:if>
                            </div>
                            <div class="team-back layer speech-bubble">
	<span>
        <c:if test="${offer.status != 'REJCT' && offer.status != 'DISC'}">
            <p class="descr">${offersCatDescription}:</p>
            <p><c:out value="${offer.catDescription}"/></p>
            <hr>
        </c:if>
        <c:choose>
            <c:when test="${not empty offer.expertMessage && sessionScope.role == 'USER' && offer.status == 'REJCT'}">
                <p class="descr">${offersPriceWanted}:</p>
                <p><c:out value="${offer.price}"/> $ </p>
                <hr>
                <p class="decl">${offersMessageToUser}:</p>
                <p><c:out value="${offer.expertMessage}"/></p>
            </c:when>
            <c:when test="${not empty offer.expertMessage && sessionScope.role == 'USER' && offer.status == 'DISC'}">
                <p class="decl">${offersMessageToUser}:</p>
                <p><c:out value="${offer.expertMessage}"/></p>
                <hr>
                <p class="decl">${offersPriceOffered}:</p>
                <p><c:out value="${offer.price}"/> $ </p>
            </c:when>
            <c:when test="${offer.status == 'SENT' || offer.status == 'APRVD'}">
                <p class="decl">${offersPriceStated}:</p>
                <p><c:out value="${offer.price}"/> $ </p>
            </c:when>
            <c:otherwise>
                <p class="decl">${offersPriceWanted}:</p>
                <p><c:out value="${offer.price}"/> $ </p>
            </c:otherwise>
        </c:choose>
         <c:if test="${not empty offer.expertMessageToAdmin && sessionScope.role == 'ADMIN'}">
             <hr>
             <p class="decl">${offersMessageToAdmin}:</p>
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
                                        <button type="submit" class="btn btn-primary">${offersUserButtonAccept}</button>
                                    </form>
                                </c:if>
                                <c:if test="${offer.status == 'AWAIT'}">
                                    <form style='float: left; padding: 5px;' role="form" method="get"
                                          action="/controller">
                                        <input type="hidden" name="command" value="single_offer"/>
                                        <input type="hidden" name="operation" value="upload-cat-photo"/>
                                        <input type="hidden" name="offerId" value="${offer.id}"/>
                                        <button type="submit" class="btn btn-primary">${offersUserButtonEditPhoto}</button>
                                    </form>
                                </c:if>

                                <form style='float: left; padding: 5px;' role="form" method="post"
                                      action="/controller">
                                    <input type="hidden" name="command" value="delete_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <button type="submit" class="btn btn-primary">${offersUserButtonDelete}</button>
                                </form>

                            </c:when>
                            <c:when test="${sessionScope.role == 'ADMIN'}">
                                <c:if test="${empty offer.photo}">
                                    <form style='float: left; padding: 5px;' role="form" method="get"
                                          action="/controller">
                                        <input type="hidden" name="command" value="single_offer"/>
                                        <input type="hidden" name="operation" value="cat-offer-photo"/>
                                        <input type="hidden" name="offerId" value="${offer.id}"/>
                                        <button type="submit" class="btn btn-primary">${offersButtonAddPhoto}</button>
                                    </form>
                                </c:if>

                                <form style='float: left; padding: 5px;' role="form" method="get" action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="photo" value="${offer.photo}"/>
                                    <input type="hidden" name="statedPrice" value="${offer.price}"/>
                                    <input type="hidden" name="operation" value="cat-form"/>
                                    <button type="submit" class="btn btn-primary">${offersAdminButtonAdd}</button>
                                </form>

                            </c:when>
                            <c:when test="${sessionScope.role == 'EXPERT'}">
                                <c:if test="${empty offer.photo}">
                                    <form style='float: left; padding: 5px;' role="form" method="get"
                                          action="/controller">
                                        <input type="hidden" name="command" value="single_offer"/>
                                        <input type="hidden" name="operation" value="cat-offer-photo"/>
                                        <input type="hidden" name="offerId" value="${offer.id}"/>
                                        <button type="submit" class="btn btn-primary">${offersButtonAddPhoto}</button>
                                    </form>
                                </c:if>
                                <form style='float: left; padding: 5px;' role="form" method="get"
                                      action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="operation" value="approve"/>
                                    <button type="submit" class="btn btn-primary">${offersExpertButtonApprove}</button>
                                </form>

                                <form style='float: left; padding: 5px;' role="form" method="get"
                                      action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="operation" value="bargain"/>
                                    <button type="submit" class="btn btn-primary">${offersExpertButtonBargain}</button>
                                </form>

                                <form style='float: left; padding: 5px;' role="form" method="get"
                                      action="/controller">
                                    <input type="hidden" name="command" value="single_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="operation" value="decline"/>
                                    <button type="submit" class="btn btn-primary">${offersExpertButtonDecline}</button>
                                </form>
                            </c:when>
                        </c:choose>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="text-center">
                    <img src="/assets/img/${sessionScope.local}-empty.jpg" class="img-responsive" style="margin:0 auto;"
                         alt="${nothingToShow}"/>
                </div>
            </c:otherwise>
        </c:choose>
    </div>