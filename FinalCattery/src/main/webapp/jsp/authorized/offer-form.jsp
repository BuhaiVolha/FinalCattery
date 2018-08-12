<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
<c:choose>
    <c:when test="${offer == null}">
        <h2 class="text-center text-uppercase text-secondary mb-0">Offer a kitten</h2>
    </c:when>
    <c:when test="${sessionScope.role == 'EXPERT'}">
        <c:choose>
            <c:when test="${operation eq 'bargain'}">
                <h2 class="text-center text-uppercase text-secondary mb-0">Try to bargain</h2>
            </c:when>
            <c:when test="${operation eq 'approve'}">
                <h2 class="text-center text-uppercase text-secondary mb-0">Approve an offer</h2>
            </c:when>
            <c:when test="${operation eq 'decline'}">
                <h2 class="text-center text-uppercase text-secondary mb-0">Decline an offer</h2>
            </c:when>
        </c:choose>
    </c:when>
</c:choose>

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
                                    <textarea required="required" name="expertMessage" placeholder="Describe reasons for changing a price" ></textarea>
                                    <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">Send
                                    </button>
                                    <label>New price (in dollars) </label>
                                    <input type="text" name="price" pattern="[0-9]{2,4}" placeholder="Old price: ${offer.price}" required="required" title="A reasonable price will have 2 or 4 digits"/>
                                </c:when>
                                <c:when test="${operation eq 'approve'}">
                                    <input type="hidden" name="command" value="approve"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="price" value="${offer.price}"/>
                                    <textarea required="required" name="expertMessageToAdmin" placeholder="A note for admin" ></textarea>
                                    <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">Send</button>
                                </c:when>
                                <c:when test="${operation eq 'decline'}">
                                    <input type="hidden" name="command" value="decline_offer"/>
                                    <input type="hidden" name="command" value="decline_offer"/>
                                    <input type="hidden" name="offerId" value="${offer.id}"/>
                                    <input type="hidden" name="price" value="${offer.price}"/>
                                    <textarea required="required" name="expertMessage" placeholder="Describe the reason for your decision" ></textarea>
                                    <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">Send</button>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${offer == null}">
                            <input type="hidden" name="command" value="offer_cat"/>
                            <textarea required="required" name="catDescription" placeholder="Describe characteristics of your cat, so it were easy for our expert to make a decision" ></textarea>
                            <button type="submit" class="btn btn-primary btn-xl"  id="sendMessageButton">Send</button>
                            <label>Price (in dollars) </label>
                            <input type="text" name="price" pattern="[0-9]{2,4}" placeholder="Price you want to ask" required="required" title="A reasonable price will have 2 or 4 digits"/>
                        </c:when>
                        </c:choose>
                    </form>
                </div>
            </div>

    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>