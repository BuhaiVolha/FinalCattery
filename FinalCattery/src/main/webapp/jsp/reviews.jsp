<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.reviews.title" var="reviewsTitle"/>
<fmt:message bundle="${loc}" key="local.reviews.button.edit" var="reviewsEdit"/>
<fmt:message bundle="${loc}" key="local.reviews.button.delete" var="reviewsDelete"/>

<div class="container">

    <h4 style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${reviewsTitle}</h4><br>
    <div class="row">

        <c:forEach items="${approvedReviews}" var="approvedReview">

            <div class="well">
                <div class="media">
                    <a class="pull-left" href="#">
                        <img class="media-object img-responsive" src="/assets/img/user.png">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">${approvedReview.userLeftLogin}</h4>
                        <p><c:out value="${approvedReview.text}"/></p>
                        <ul class="list-inline list-unstyled">
                            <li><span><i class="glyphicon glyphicon-calendar"></i> <c:out
                                    value="${approvedReview.date}"/></span></li>
                            <li>|</li>

                            <li>
                                <span class="stars" data-rating="${approvedReview.starsCount}"
                                      data-num-stars="5"></span>
                            </li>

                            <c:choose>
                                <c:when test="${sessionScope.userId == approvedReview.userLeftId}">
                                    <li style="float:right;">
                                        <form role="form" method="get" action="/controller">
                                            <input type="hidden" name="command" value="single_review"/>
                                            <input type="hidden" name="reviewId" value="${approvedReview.id}"/>
                                            <button type="submit" class="btn btn-danger">${reviewsEdit}</button>
                                        </form>
                                    </li>
                                </c:when>

                                <c:when test="${sessionScope.role eq 'ADMIN'}">
                                    <li style="float:right;">
                                        <form role="form" method="post" action="/controller">
                                            <input type="hidden" name="command" value="delete_review"/>
                                            <input type="hidden" name="reviewId" value="${approvedReview.id}"/>
                                            <input type="hidden" name="userId" value="${approvedReview.userLeftId}"/>
                                            <button type="submit" class="btn btn-danger">${reviewsDelete}</button>
                                        </form>
                                    </li>
                                </c:when>
                            </c:choose>

                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="col" style="float: right; margin-bottom: 38px">
        <c:url var="searchUri" value="/controller?command=all_reviews&page=##"/>
        <paginator:display maxLinks="10"
                           currPage="${requestScope.page}"
                           totalPages="${requestScope.pageCount}"
                           uri="${searchUri}"/>
    </div>

</div>

<script src="/assets/js/reviewStarDisplay.js"></script>

<%@ include file="/jsp/parts/footer.jsp" %>
