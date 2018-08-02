<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <h4>REVIEWS</h4>
    <div class="row">
<c:forEach items="${approvedReviews}" var="approvedReview">
    <div class="well">
        <div class="media">
            <a class="pull-left" href="#">
                <img class="media-object" src="http://placekitten.com/150/150">
            </a>
            <div class="media-body">
                <h4 class="media-heading">${approvedReview.userLeftLogin}</h4>
                <p>${approvedReview.text}</p>
                <ul class="list-inline list-unstyled">
                    <li><span><i class="glyphicon glyphicon-calendar"></i> <c:out value="${approvedReview.date}"/></span></li>
                    <li>|</li>

                    <li>
                        <span class="stars" data-rating="${approvedReview.starsCount}" data-num-stars="5" ></span>
                    </li>
                    <c:choose>
                    <c:when test="${sessionScope.userId == approvedReview.userLeftId}">

                        <li style="float:right;">
                        <a href="/controller?command=single_review&reviewId=${approvedReview.id}" class="btn btn-primary">Edit</a>
                    </li>
                    </c:when>
                        <c:when test="${sessionScope.role eq 'ADMIN'}">
                            <li style="float:right;">
                                <a href="/controller?command=delete_review&reviewId=${approvedReview.id}&userId=${approvedReview.userLeftId}" class="btn btn-danger">Delete</a>
                            </li>
                        </c:when>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</c:forEach>
</div>
</div>

<script>
    $.fn.stars = function() {
        return $(this).each(function() {

            var rating = $(this).data("rating");

            var numStars = $(this).data("numStars");

            var fullStar = new Array(Math.floor(rating + 1)).join('<i class="glyphicon glyphicon-star"></i>');

            var halfStar = ((rating%1) !== 0) ? '<i class="fa fa-star-half-empty"></i>': '';

            var noStar = new Array(Math.floor(numStars + 1 - rating)).join('<i class="glyphicon glyphicon-star-empty"></i>');

            $(this).html(fullStar + halfStar + noStar);

        });
    }

    $('.stars').stars();
</script>

<%@ include file="/jsp/parts/footer.jsp" %>
