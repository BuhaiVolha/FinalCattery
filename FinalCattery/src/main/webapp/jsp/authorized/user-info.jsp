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

                                        <c:choose>
                                            <c:when test="${requestScope.user.colourPreference eq 'N'}">
                                                black
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'A'}">
                                                blue
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'D'}">
                                                red
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'E'}">
                                                creme
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'F'}">
                                                blacktortie
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'Q'}">
                                                bluetortie
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'S'}">
                                                silver
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'W'}">
                                                white
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'Y'}">
                                                golden
                                            </c:when>
                                        </c:choose>

                                        <span class="pull-right">
                            <a href="/jsp/user/set-preference.jsp" title="Edit your colour preference"
                               data-original-title="colour preference"
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
                            <a href="/jsp/user/set-preference.jsp" title="Choose your colour preference"
                               data-original-title="colour preference"
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
                            <a href="/jsp/user/review-form.jsp" title="Leave your review" data-original-title="review"
                               data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i
                                    class="fab fa-affiliatetheme"></i></a></span>
                                </td>
                            </tr>
                        </c:if>

                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${sessionScope.role eq 'USER'}">
                            <a href="/controller?command=all_reservations" class="btn btn-primary">My
                                reservations</a>
                            <a href="/controller?command=all_offers" class="btn btn-primary">My offers</a>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'EXPERT'}">
                            <br>
                            <a href="/controller?command=colour_statistics" class="btn btn-primary">See statistics</a>
                            <a href="/controller?command=pedigree" class="btn btn-primary">Pedigree count</a>
                            <a href="/controller?command=awaiting_offers"
                               class="btn btn-primary">Assess cats</a>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'ADMIN'}">


                            <a href="/controller?command=all_users" class="btn btn-primary">Manage users</a>

                            <a href="/controller?command=all_reservations" class="btn btn-primary">Manage
                                reservations</a>
                            <a href="/controller?command=approved_offers"
                               class="btn btn-primary">Add offers</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="panel-footer clearfix">

            <span class="pull-right">
                            <a href="/controller?command=single_user&operation=edit-user-info"
                               data-original-title="Edit information" data-toggle="tooltip"
                               title="Edit your information" type="button"
                               class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                        </span>
            <c:choose>
                <c:when test="${sessionScope.role eq 'ADMIN'}">
            <span class="pull-left">
                            <a href="/jsp/authorized/cat-form.jsp" class="btn btn-warning">Add a cat</a>
                        </span>
                </c:when>
                <c:when test="${sessionScope.role eq 'USER'}">
<span class="pull-left">
                            <a href="/jsp/authorized/offer-form.jsp" class="btn btn-warning">Offer a cat</a>
                        </span>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
