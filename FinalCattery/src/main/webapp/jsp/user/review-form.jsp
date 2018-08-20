<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.reviews.editing" var="reviewsEditing"/>
<fmt:message bundle="${loc}" key="local.reviews.message" var="reviewsMessage"/>
<fmt:message bundle="${loc}" key="local.reviews.rating" var="reviewsRating"/>
<fmt:message bundle="${loc}" key="local.reviews.placeholder" var="reviewsPlaceholder"/>
<fmt:message bundle="${loc}" key="local.reviews.writing" var="reviewsWriting"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.star" var="reviewsRatingStar"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.stars" var="reviewsRatingStars"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.stars5" var="reviewsRatingStars5"/>
<fmt:message bundle="${loc}" key="local.reviews.button.submit" var="reviewsButtonSubmit"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.1" var="reviewsRating1"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.2" var="reviewsRating2"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.3" var="reviewsRating3"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.4" var="reviewsRating4"/>
<fmt:message bundle="${loc}" key="local.reviews.rating.5" var="reviewsRating5"/>

<fmt:message bundle="${mess}" key="message.inputinvalid" var="inputInvalid"/>

<div class="container">
    <div class="row">

        <c:choose>
        <c:when test="${requestScope.review != null}">
        <h2 style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${reviewsEditing}</h2>
<br>
<br>
        <form class="form-horizontal" role="form" method="POST" action="/controller">
            <input type="hidden" name="command" value="edit_review"/>
            <fieldset>
                <br>
                <br>

                <div class="form-group">
                    <label class="col-md-3 control-label" for="message1">${reviewsMessage}</label>
                    <div class="col-md-9">
                                    <textarea style="margin: 0;  resize: none;" class="form-control" required="required"
                                              id="message1" name="message"
                                              placeholder="${reviewsPlaceholder}"
                                              rows="5">${requestScope.review.text}</textarea>
                    </div>
                </div>
                </c:when>
                <c:otherwise>
                <h2 style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${reviewsWriting}</h2>
                <br>
                <br>
                <form class="form-horizontal" role="form" method="POST" action="/controller">
                    <input type="hidden" name="command" value="write_review"/>
                    <fieldset>
                        <br>
                        <br>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="message">${reviewsMessage}</label>
                            <div class="col-md-9">
                                    <textarea style="margin: 0; resize: none;" class="form-control" required="required"
                                              id="message" name="message"
                                              placeholder="${reviewsPlaceholder}" rows="5"></textarea>
                            </div>
                        </div>
                        </c:otherwise>
                        </c:choose>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="rating">${reviewsRating}</label>
                            <div class="rating" id="rating">
                                <input type="radio" required="required" id="star5" name="rating" value="5"/><label
                                    for="star5" title="${reviewsRating5}">5 ${reviewsRatingStars5}</label>
                                <input type="radio" required="required" id="star4" name="rating" value="4"/><label
                                    for="star4" title="${reviewsRating4}">4 ${reviewsRatingStars}</label>
                                <input type="radio" required="required" id="star3" name="rating" value="3"/><label
                                    for="star3" title="${reviewsRating3}">3 ${reviewsRatingStars}</label>
                                <input type="radio" required="required" id="star2" name="rating" value="2"/><label
                                    for="star2" title="${reviewsRating2}">2 ${reviewsRatingStars}</label>
                                <input type="radio" required="required" id="star1" name="rating" value="1"/><label
                                    for="star1" title="${reviewsRating1}">1 ${reviewsRatingStar}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-center">

                                <c:if test="${param.writeReviewFailedMessage eq 'inputInvalid'}">
                                    <p style="text-align: center; color: red">${inputInvalid}</p>
                                </c:if>

                                <button type="submit" id="ratingSubmit" class="btn btn-primary btn-md" disabled>${reviewsButtonSubmit}
                                </button>
                            </div>
                        </div>
                    </fieldset>
                </form>
    </div>
</div>

<script src="/assets/js/reviewStarSet.js"></script>

<%@ include file="/jsp/parts/footer.jsp" %>

