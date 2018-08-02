<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ include file="/jsp/parts/header.jsp" %>

<div class="container" style="width:1300px;">
    <div class="row">

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${requestScope.user.name} ${requestScope.user.lastname}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center">
                            <c:choose>
                            <c:when test="${sessionScope.role eq 'USER'}">
                            <img alt="User pic" src="/assets/img/user.png" class="img-responsive"></div>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'EXPERT'}">
                        <img alt="Expert pic" src="/assets/img/expert.png" class="img-responsive"></div>
                    </c:when>
                    <c:when test="${sessionScope.role eq 'ADMIN'}">
                    <img alt="Admin pic" src="/assets/img/admin.png" class="img-responsive"></div>
                </c:when>
                </c:choose>

                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>

                        <c:if test="${sessionScope.role eq 'USER'}">
                        <tr>
                            <td>Discount</td>
                            <td>${requestScope.user.discount} %</td>
                        </tr>


                        <tr>
                            <td>Colour preference</td>
                            <td><c:choose>
                                <c:when test="${not empty requestScope.user.colourPreference}">
                                    ${requestScope.user.colourPreference}
                                    <span class="pull-right">
                            <a href="/jsp/user/preference.jsp" title="Edit your colour preference" data-original-title="colour preference"
                               data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i
                                    class="fas fa-palette"></i></a>
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    Мы собираем информацию о том, каких котят желают видеть наши пользователи.
                                    Пожалуйста, укажите какой окрас Вы предпочитаете, чтобы мы размножили именно этих котов.
                                <br>
                                <br>
                                    <span class="text-center">
                            <a href="/jsp/user/preference.jsp" title="Choose your colour preference" data-original-title="colour preference"
                               data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i
                                    class="fas fa-palette"></i></a></span
                                </c:otherwise>
                            </c:choose>

                            </td>
                        </tr>
</c:if>
                        <tr>
                            <td>Email:</td>
                            <td><a href="mailto:"${requestScope.user.email}>${requestScope.user.email}</a></td>
                        </tr>
                        <tr>
                            <td>Phone:</td>
                            <td>+375 ${requestScope.user.phone}</td>
                        </tr>

                        <c:if test="${!requestScope.user.reviewLeft && sessionScope.role eq 'USER'}">
                            <tr>
                                <td>Review:</td>
                                <td>
                                    Пожалуйста, оставьте отзыв! Мы работаем для вас и нам важно ваше мнение.
                                    <br>
                                    <br>
                                    <span class="text-center">
                            <a href="/jsp/user/write-review.jsp" title="Leave your review" data-original-title="review"
                               data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i
                                    class="fab fa-affiliatetheme"></i></a></span>
                                </td>
                            </tr>
                        </c:if>

                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${sessionScope.role eq 'USER'}">
                            <a href="/controller?command=all_reservations&operation=user" class="btn btn-primary">My reservations</a>
                            <a href="/controller?command=all_offers" class="btn btn-primary">My offers</a>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'EXPERT'}">
                            <br>
                            <a href="/controller?command=statistics" class="btn btn-primary">See statistics</a>
                            <a href="/controller?command=pedigree" class="btn btn-primary">Pedigree count</a>
                            <a href="/controller?command=awaiting_offers"
                               class="btn btn-primary">Assess cats</a>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'ADMIN'}">

                            <a href="/controller?command=approved_offers"
                               class="btn btn-primary" >See offers</a>

                            <a href="/controller?command=all_users"  class="btn btn-primary">Manage users</a>

                            <a href="/controller?command=all_reservations&operation=admin"  class="btn btn-primary">Manage reservations</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="panel-footer clearfix">

            <span class="pull-right">
                            <a href="/jsp/authorized/edit-user-info.jsp" data-original-title="Edit information" data-toggle="tooltip" title="Edit your information" type="button"
                               class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                        </span>
            <c:choose>
<c:when test="${sessionScope.role eq 'ADMIN'}">
            <span class="pull-left">
                            <a href="/jsp/admin/add-cat.jsp" class="btn btn-warning">Add a cat</a>
                        </span>
</c:when>
                <c:when test="${sessionScope.role eq 'USER'}">
<span class="pull-left">
                            <a href="/jsp/user/cat-offer.jsp" class="btn btn-warning">Offer a cat</a>
                        </span>
                </c:when>
            </c:choose>
        </div>

    </div>
</div>
</div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
