<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.filter.search" var="filterSearch"/>
<fmt:message bundle="${loc}" key="local.reservations.column.cat" var="reservationsCat"/>
<fmt:message bundle="${loc}" key="local.reservations.column.name" var="reservationsName"/>
<fmt:message bundle="${loc}" key="local.reservations.column.buyer" var="reservationsBuyer"/>
<fmt:message bundle="${loc}" key="local.reservations.column.price" var="reservationsPrice"/>
<fmt:message bundle="${loc}" key="local.reservations.column.date" var="reservationsDate"/>
<fmt:message bundle="${loc}" key="local.reservations.column.pedigree" var="reservationsPedigree"/>
<fmt:message bundle="${loc}" key="local.reservations.column.status" var="reservationsStatus"/>
<fmt:message bundle="${loc}" key="local.reservations.column.cheque" var="reservationsCheque"/>
<fmt:message bundle="${loc}" key="local.reservations.cat.photo.alt" var="reservationsPhotoAlt"/>
<fmt:message bundle="${loc}" key="local.new-window" var="newWindow"/>
<fmt:message bundle="${loc}" key="local.reservations.expired" var="reservationsExpired"/>
<fmt:message bundle="${loc}" key="local.reservations.active" var="reservationsActive"/>
<fmt:message bundle="${loc}" key="local.reservations.bought" var="reservationsBought"/>
<fmt:message bundle="${loc}" key="local.reservations.cheque.photo.alt" var="reservationsChequePhotoAlt"/>
<fmt:message bundle="${loc}" key="local.reservations.user.button.attach-cheque" var="reservationsUserAttachCheque"/>
<fmt:message bundle="${loc}" key="local.reservations.paid" var="reservationsPaid"/>
<fmt:message bundle="${loc}" key="local.reservations.admin.button.sell" var="reservationsAdminSell"/>
<fmt:message bundle="${loc}" key="local.reservations.user.button.renew" var="reservationsUserRenew"/>
<fmt:message bundle="${loc}" key="local.reservations.user.button.delete" var="reservationsUserDelete"/>
<fmt:message bundle="${loc}" key="local.reservations.user.button.cancel" var="reservationsUserCancel"/>
<fmt:message bundle="${loc}" key="local.reservations.admin.button.cancel-expired" var="reservationsAdminCancelExpired"/>


<div class="col-lg-4 col-lg-offset-4">
    <input type="search" id="search" value="" class="form-control" placeholder="${filterSearch}...">
</div>
<br>

<div class="container">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <th style="width:15%; text-align: center">${reservationsCat}</th>
                    <th style="width:15%;">${reservationsName}</th>
                    <th style="width:15%;">${reservationsBuyer}</th>
                    <th style="width:10%">${reservationsPrice}</th>
                    <th style="width:15%">${reservationsDate}</th>
                    <th style="width:10%">${reservationsPedigree}</th>
                    <th style="width:22%" class="text-center">${reservationsStatus}</th>
                    <th>${reservationsCheque}</th>
                    <th style="width:10%"></th>
                    <th style="width:10%"></th>
                </c:when>
                <c:when test="${sessionScope.role == 'USER'}">
                    <th style="width:15%; text-align: center">${reservationsCat}</th>
                    <th style="width:20%;">${reservationsName}</th>
                    <th style="width:10%">${reservationsPrice}</th>
                    <th style="width:15%">${reservationsDate}</th>
                    <th style="width:10%">${reservationsPedigree}</th>
                    <th style="width:22%" class="text-center">${reservationsStatus}</th>
                    <th></th>
                    <th style="width:10%"></th>
                    <th style="width:10%"></th>
                </c:when>
            </c:choose>
        </tr>
        </thead>
        <c:forEach items="${requestScope.reservations}" var="reservation">
            <tbody>
            <tr>
                <td data-th="Product">
                    <a target="_blank" title="${newWindow}"
                       href="/assets/img/uploads/cats/${reservation.catPhoto}">
                        <img src="/assets/img/uploads/cats/${reservation.catPhoto}" alt="${reservationsPhotoAlt}"
                             class="img-responsive"/></a>
                </td>
                <td><c:out value="${reservation.catName}"/> <c:out
                        value="${reservation.catLastname}"/></td>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <td data-th="Price"><c:out value="${reservation.userMadeReservationName}"/> <c:out
                            value="${reservation.userMadeReservationLastname}"/></td>
                </c:if>
                <td data-th="Price"><c:out value="${reservation.totalCost}"/></td>
                <td data-th="Price"><fmt:formatDate pattern = "dd.MM.yyyy  H:m" value = "${reservation.dateOfReservation}" /></td>
                <td data-th="Price"><c:out value="${reservation.pedigreeType}"/></td>

                <td data-th="Subtotal" class="text-center"><p class="list-group-item-text">

                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <c:choose>
                            <c:when test="${reservation.expired}">
                                <span class="label label-danger">${reservationsExpired}</span>
                                <c:set var="showExpiredButton" value="true"/>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-success">${reservationsActive}</span>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${sessionScope.role == 'USER'}">
                        <c:choose>
                            <c:when test="${reservation.status eq 'EXPD'}">
                                <span class="label label-danger">${reservationsExpired}</span>
                            </c:when>
                            <c:when test="${reservation.status eq 'NEW'}">
                                <span class="label label-success">${reservationsActive}</span>
                            </c:when>
                            <c:when test="${reservation.status eq 'DONE'}">
                                <span class="label label-success">${reservationsBought}</span>
                            </c:when>
                        </c:choose>
                    </c:if>
                </p></td>

                <c:choose>
                    <c:when test="${empty reservation.chequePhoto || sessionScope.role != 'ADMIN'}">
                        <td></td>
                    </c:when>
                    <c:otherwise>
                        <td data-th="Product">
                            <a target="_blank" title="${newWindow}"
                               href="/assets/img/uploads/cheques/${reservation.chequePhoto}">
                                <img src="/assets/img/uploads/cheques/${reservation.chequePhoto}"
                                     alt="${reservationsChequePhotoAlt}"
                                     class="img-responsive"/></a>
                        </td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${reservation.status eq 'NEW' && empty reservation.chequePhoto && sessionScope.role == 'USER'}">
                        <td>
                            <form method="post" action="/imageUploader" enctype="multipart/form-data">
                                <input type="hidden" name="command" value="upload_cheque_photo"/>
                                <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                <div class="form-inline pull-left">
                                    <div class="form-group"><input type="file" required="required" name="cheque"
                                                                   size="60"/></div>
                                    <button type="submit"
                                            class="btn btn-primary"> ${reservationsUserAttachCheque}</button>
                                </div>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${reservation.status eq 'NEW' && not empty reservation.chequePhoto && sessionScope.role == 'USER'}">
                        <td style="text-align: center">${reservationsPaid}</td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                    </c:otherwise>
                </c:choose>
                <td class="actions" data-th="">

                    <c:if test="${!reservation.expired && sessionScope.role == 'ADMIN'
                    || not empty reservation.chequePhoto && sessionScope.role == 'ADMIN'}">

                        <form role="form" method="post" action="/controller">
                            <input type="hidden" name="command" value="sell_cat"/>
                            <input type="hidden" name="reservationId" value="${reservation.id}"/>
                            <button type="submit" class="btn btn-info btn-sm">
                                <i class="fas fa-hand-holding-usd"> ${reservationsAdminSell}</i></button>
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.role == 'USER'}">
                        <c:choose>
                            <c:when test="${reservation.status eq 'EXPD'}">

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="renew_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-warning btn-sm">
                                        <i class="fab fa-hotjar"> ${reservationsUserRenew}</i></button>
                                </form>

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="delete_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-danger btn-sm">
                                        <i class="fab fa-hotjar"> ${reservationsUserDelete}</i></button>
                                </form>
                            </c:when>
                            <c:when test="${reservation.status eq 'NEW' && empty reservation.chequePhoto}">

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="cancel_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="fas fa-hand-holding-usd"> ${reservationsUserCancel}</i></button>
                                </form>

                            </c:when>
                            <c:when test="${reservation.status eq 'DONE' && sessionScope.role == 'USER'}">

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="delete_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-danger btn-sm">
                                        <i class="fab fa-hotjar"> ${reservationsUserDelete}</i></button>
                                </form>
                            </c:when>
                        </c:choose>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </c:forEach>
        <tfoot>

        <tr class="visible-xs">
            <td class="text-center"><strong>Total</strong></td>
        </tr>
        <tr>
            <td colspan="2" class="hidden-xs"></td>
            <td colspan="2" class="hidden-xs"></td>
            <td colspan="2" class="hidden-xs"></td>
            <td colspan="2" class="hidden-xs"></td>
            <c:if test="${sessionScope.role == 'USER'}">
                <td colspan="2" class="hidden-xs"></td>
            </c:if>

            <c:if test="${sessionScope.role == 'ADMIN'}">
                <c:choose>
                    <c:when test="${not empty requestScope.reservations && showExpiredButton}">

                        <td>
                            <form role="form" method="post" action="/controller">
                                <input type="hidden" name="command" value="decline_expired_reservations"/>
                                <input type="hidden" name="reservationId" value="${requestScope.reservation.id}"/>
                                <button type="submit"
                                        class="btn btn-warning btn-block">${reservationsAdminCancelExpired} <i
                                        class="fab fa-hotjar"></i></button>
                            </form>
                        </td>
                        <td colspan="2" class="hidden-xs"></td>
                    </c:when>
                    <c:otherwise>
                        <td colspan="2" class="hidden-xs"></td>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </tr>
        </tfoot>
    </table>
    <c:if test="${not empty requestScope.reservations}">
        <div class="col" style="float: right; margin-bottom: 38px">
            <c:url var="searchUri" value="/controller?command=all_reservations&page=##"/>
            <paginator:display maxLinks="10"
                               currPage="${requestScope.page}"
                               totalPages="${requestScope.pageCount}"
                               uri="${searchUri}"/>
        </div>
    </c:if>
</div>

<script src="/assets/js/reservationsSearch.js"></script>

<%@ include file="/jsp/parts/footer.jsp" %>
