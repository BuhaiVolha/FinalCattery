<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.offer-form.title.bargain" var="offerFormTitleBargain"/>
<fmt:message bundle="${loc}" key="local.offer-form.title.offer-cat" var="offerFormTitleOfferCat"/>
<fmt:message bundle="${loc}" key="local.offer-form.title.approve" var="offerFormTitleApprove"/>
<fmt:message bundle="${loc}" key="local.offer-form.title.decline" var="offerFormTitleDecline"/>
<fmt:message bundle="${loc}" key="local.offer-form.price-offered" var="offerFormPriceOffered"/>
<fmt:message bundle="${loc}" key="local.offer-form.price" var="offerFormPrice"/>
<fmt:message bundle="${loc}" key="local.offer-form.price-old" var="offerFormPriceOld"/>
<fmt:message bundle="${loc}" key="local.offer-form.bargain.message.placeholder" var="offerFormBargainPlaceholder"/>
<fmt:message bundle="${loc}" key="local.offer-form.decline.message.placeholder" var="offerFormDeclinePlaceholder"/>
<fmt:message bundle="${loc}" key="local.button.send" var="buttonSend"/>
<fmt:message bundle="${loc}" key="local.price.rule" var="priceRule"/>
<fmt:message bundle="${loc}" key="local.offer-form.expert-message-to-admin" var="offerFormExpertMessageToAdmin"/>
<fmt:message bundle="${loc}" key="local.offer-form.price.placeholder" var="offerFormPricePlaceholder"/>
<fmt:message bundle="${loc}" key="local.offer-form.offer-cat.message.placeholder" var="offerFormOfferCatPlaceholder"/>

<div class="container">
<c:choose>
    <c:when test="${offer == null}">
        <h2 class="text-center text-uppercase text-secondary mb-0"
            style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${offerFormTitleOfferCat}</h2>
    </c:when>
    <c:when test="${sessionScope.role == 'EXPERT'}">
        <c:choose>
            <c:when test="${operation eq 'bargain'}">
                <h2 class="text-center text-uppercase text-secondary mb-0"
                    style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${offerFormTitleBargain}</h2>
            </c:when>
            <c:when test="${operation eq 'approve'}">
                <h2 class="text-center text-uppercase text-secondary mb-0"
                    style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${offerFormTitleApprove}</h2>
            </c:when>
            <c:when test="${operation eq 'decline'}">
                <h2 class="text-center text-uppercase text-secondary mb-0"
                    style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${offerFormTitleDecline}</h2>
            </c:when>
        </c:choose>
    </c:when>
</c:choose>
<br>
<br>
    <div class="row" style="text-align: center; margin-right: 40px; margin-left: 40px;">

            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form name="answerToOfferForm" method="POST" action="/controller">
                        <c:choose>
                        <c:when test="${sessionScope.role == 'EXPERT'}">
                            <c:choose>
                                <c:when test="${operation eq 'bargain'}">
                                    <input type="hidden" name="command" value="bargain"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <textarea required="required" name="expertMessage" placeholder="${offerFormBargainPlaceholder}" ></textarea>
                                    <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">${buttonSend}</button>
                                    <label>${offerFormPriceOffered} </label>
                                    <input type="text" name="price" pattern="[0-9]{2,4}" placeholder="${offerFormPriceOld}: ${offer.price}" required="required" title="${priceRule}"/>
                                </c:when>
                                <c:when test="${operation eq 'approve'}">
                                    <input type="hidden" name="command" value="approve"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="price" value="${offer.price}"/>
                                    <textarea required="required" name="expertMessageToAdmin" placeholder="${offerFormExpertMessageToAdmin}" ></textarea>
                                    <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">${buttonSend}</button>
                                </c:when>
                                <c:when test="${operation eq 'decline'}">
                                    <input type="hidden" name="command" value="decline_offer"/>
                                    <input type="hidden" name="command" value="decline_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="price" value="${offer.price}"/>
                                    <textarea required="required" name="expertMessage" placeholder="${offerFormDeclinePlaceholder}" ></textarea>
                                    <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">${buttonSend}</button>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${offer == null}">
                            <input type="hidden" name="command" value="offer_cat"/>
                            <textarea required="required" name="catDescription" placeholder="${offerFormOfferCatPlaceholder}" ></textarea>
                            <button type="submit" class="btn btn-primary btn-xl"  id="sendMessageButton">${buttonSend}</button>
                            <label>${offerFormPrice} </label>
                            <input type="text" name="price" pattern="[0-9]{2,4}" placeholder="${offerFormPricePlaceholder}" required="required" title="${priceRule}"/>
                        </c:when>
                        </c:choose>
                    </form>
                </div>
            </div>

    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>