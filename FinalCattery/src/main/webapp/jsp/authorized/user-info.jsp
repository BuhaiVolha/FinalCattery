<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.cats.colour.body.black" var="catsBodyColourBlack"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blue" var="catsBodyColourBlue"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.red" var="catsBodyColourRed"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.creme" var="catsBodyColourCreme"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blacktortie" var="catsBodyColourBlacktortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.bluetortie" var="catsBodyColourBluetortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.silver" var="catsBodyColourSilver"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.white" var="catsBodyColourWhite"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.golden" var="catsBodyColourGolden"/>
<fmt:message bundle="${loc}" key="local.cabinet.avatar.alt.admin" var="avatarAdmin"/>
<fmt:message bundle="${loc}" key="local.cabinet.avatar.alt.expert" var="avatarExpert"/>
<fmt:message bundle="${loc}" key="local.cabinet.avatar.alt.user" var="avatarUser"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.discount" var="cabinetUserDiscount"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.preference" var="cabinetUserPreference"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.preference.if-empty" var="cabinetUserPreferenceEmpty"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.preference.edit" var="cabinetUserPreferenceEdit"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.preference.set" var="cabinetUserPreferenceSet"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.review" var="cabinetUserReview"/>
<fmt:message bundle="${loc}" key="local.cabinet.phone" var="cabinetPhone"/>
<fmt:message bundle="${loc}" key="local.cabinet.email" var="cabinetEmail"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.review.if-empty" var="cabinetUserReviewEmpty"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.review.leave" var="cabinetUserReviewLeave"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.reservations" var="cabinetUserReservations"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.offer" var="cabinetUserOffers"/>
<fmt:message bundle="${loc}" key="local.cabinet.expert.statistics" var="cabinetExpertStatistics"/>
<fmt:message bundle="${loc}" key="local.cabinet.expert.offers" var="cabinetExpertOffers"/>
<fmt:message bundle="${loc}" key="local.cabinet.expert.pedigree" var="cabinetExpertPedigree"/>
<fmt:message bundle="${loc}" key="local.cabinet.admin.offers" var="cabinetAdminOffers"/>
<fmt:message bundle="${loc}" key="local.cabinet.admin.reservations" var="cabinetAdminReservations"/>
<fmt:message bundle="${loc}" key="local.cabinet.admin.users" var="cabinetAdminUsers"/>
<fmt:message bundle="${loc}" key="local.cabinet.edit-info" var="cabinetEditInfo"/>
<fmt:message bundle="${loc}" key="local.cabinet.admin.add-cat" var="cabinetAdminAddCat"/>
<fmt:message bundle="${loc}" key="local.cabinet.user.offer-cat" var="cabinetUserOfferCat"/>


<div class="container" style="width:1300px;">
    <div class="row">

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title"><c:out value="${requestScope.user.name}"/><c:out value="${requestScope.user.lastname}"/></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center">
                            <c:choose>
                            <c:when test="${sessionScope.role eq 'USER'}">
                            <img alt="${avatarUser}" src="/assets/img/user.png" class="img-responsive"></div>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'EXPERT'}">
                        <img alt="${avatarExpert}" src="/assets/img/expert.png" class="img-responsive"></div>
                    </c:when>
                    <c:when test="${sessionScope.role eq 'ADMIN'}">
                    <img alt="${avatarAdmin}" src="/assets/img/admin.png" class="img-responsive"></div>
                </c:when>
                </c:choose>

                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>

                        <c:if test="${sessionScope.role eq 'USER'}">
                            <tr>
                                <td>${cabinetUserDiscount}</td>
                                <td>${requestScope.user.discount} %</td>
                            </tr>


                            <tr>
                                <td>${cabinetUserPreference}</td>
                                <td><c:choose>
                                    <c:when test="${not empty requestScope.user.colourPreference}">

                                        <c:choose>
                                            <c:when test="${requestScope.user.colourPreference eq 'N'}">
                                                ${catsBodyColourBlack}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'A'}">
                                                ${catsBodyColourBlue}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'D'}">
                                                ${catsBodyColourRed}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'E'}">
                                                ${catsBodyColourCreme}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'F'}">
                                                ${catsBodyColourBlacktortie}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'Q'}">
                                                ${catsBodyColourBluetortie}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'S'}">
                                                ${catsBodyColourSilver}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'W'}">
                                                ${catsBodyColourWhite}
                                            </c:when>
                                            <c:when test="${requestScope.user.colourPreference eq 'Y'}">
                                                ${catsBodyColourGolden}
                                            </c:when>
                                        </c:choose>

                                        <span class="pull-right">
                            <a href="/jsp/user/set-preference.jsp" title="${cabinetUserPreferenceEdit}"
                               data-original-title="colour preference"
                               data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i
                                    class="fas fa-palette"></i></a>
                                    </span>
                                    </c:when>
                                    <c:otherwise>
                                        ${cabinetUserPreferenceEmpty}
                                        <br>
                                        <br>
                                        <span class="text-center">
                            <a href="/jsp/user/set-preference.jsp" title="${cabinetUserPreferenceSet}"
                               data-original-title="colour preference"
                               data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i
                                    class="fas fa-palette"></i></a></span
                                    </c:otherwise>
                                </c:choose>

                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>${cabinetEmail}:</td>
                            <td><a href="mailto:"${requestScope.user.email}><c:out value="${requestScope.user.email}"/></a></td>
                        </tr>
                        <tr>
                            <td>${cabinetPhone}:</td>
                            <td>+375 <c:out value="${requestScope.user.phone}"/></td>
                        </tr>

                        <c:if test="${!requestScope.user.reviewLeft && sessionScope.role eq 'USER'}">
                            <tr>
                                <td>${cabinetUserReview}:</td>
                                <td>
                                        ${cabinetUserReviewEmpty}
                                    <br>
                                    <br>
                                    <span class="text-center">
                            <a href="/jsp/user/review-form.jsp" title="${cabinetUserReviewLeave}"
                               data-original-title="review"
                               data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i
                                    class="fab fa-affiliatetheme"></i></a></span>
                                </td>
                            </tr>
                        </c:if>

                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${sessionScope.role eq 'USER'}">
                            <a href="/controller?command=all_reservations"
                               class="btn btn-primary">${cabinetUserReservations}</a>
                            <a href="/controller?command=all_offers" class="btn btn-primary">${cabinetUserOffers}</a>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'EXPERT'}">
                            <br>
                            <a href="/controller?command=colour_statistics"
                               class="btn btn-primary">${cabinetExpertStatistics}</a>
                            <a href="/controller?command=pedigree" class="btn btn-primary">${cabinetExpertPedigree}</a>
                            <a href="/controller?command=awaiting_offers"
                               class="btn btn-primary">${cabinetExpertOffers}</a>
                        </c:when>

                        <c:when test="${sessionScope.role eq 'ADMIN'}">
                            <a href="/controller?command=all_users" class="btn btn-primary">${cabinetAdminUsers}</a>
                            <a href="/controller?command=all_reservations"
                               class="btn btn-primary">${cabinetAdminReservations}</a>
                            <a href="/controller?command=approved_offers"
                               class="btn btn-primary">${cabinetAdminOffers}</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="panel-footer clearfix">
                        <span class="pull-right">
                            <a href="/controller?command=single_user&operation=edit-user-info"
                               data-original-title="Edit information" data-toggle="tooltip"
                               title="${cabinetEditInfo}" type="button"
                               class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                        </span>

            <c:choose>
                <c:when test="${sessionScope.role eq 'ADMIN'}">
                        <span class="pull-left">
                            <a href="/jsp/authorized/cat-form.jsp" class="btn btn-warning">${cabinetAdminAddCat}</a>
                        </span>
                </c:when>
                <c:when test="${sessionScope.role eq 'USER'}">
                        <span class="pull-left">
                            <a href="/jsp/authorized/offer-form.jsp" class="btn btn-warning">${cabinetUserOfferCat}</a>
                        </span>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
