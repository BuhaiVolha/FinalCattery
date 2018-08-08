<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>


<div class="col-lg-4 col-lg-offset-4">
    <input type="search" id="search" value="" class="form-control" placeholder="Search...">
</div>

<br>
<div class="container">
    <table id="cart" class="table table-hover table-condensed">

        <thead>
        <tr>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <th style="width:15%; text-align: center">Cat</th>
                    <th style="width:20%; text-align: center">Name</th>
                    <th style="width:15%; text-align: center">Buyer</th>
                    <th style="width:10%">Price</th>
                    <th style="width:15%">Date</th>
                    <th style="width:10%">Pedigree</th>
                    <th style="width:22%" class="text-center">Status</th>
                    <th>Cheque</th>
                    <th style="width:10%"></th>
                    <th style="width:10%"></th>
                </c:when>
                <c:when test="${sessionScope.role == 'USER'}">
                    <th style="width:15%; text-align: center">Cat</th>
                    <th style="width:20%;">Name</th>
                    <th style="width:10%">Price</th>
                    <th style="width:15%">Date</th>
                    <th style="width:10%">Pedigree</th>
                    <th style="width:22%" class="text-center">Status</th>
                    <th></th>
                    <th style="width:10%"></th>
                    <th style="width:10%"></th>
                </c:when>
            </c:choose>
        </tr>
        </thead>
        <c:forEach items="${reservations}" var="reservation">
            <tbody>
            <tr>
                <td data-th="Product">

                    <a target="_blank" title="Open in new window"
                       href="/assets/img/uploads/cats/${reservation.catPhoto}">
                        <img src="/assets/img/uploads/cats/${reservation.catPhoto}" alt="Reserved cat"
                             class="img-responsive"/></a>

                </td>
                <td><c:out value="${reservation.catName}"/> <c:out
                        value="${reservation.catLastname}"/></td>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <td data-th="Price"><c:out value="${reservation.userMadeReservationName}"/> <c:out
                            value="${reservation.userMadeReservationLastname}"/></td>
                </c:if>
                <td data-th="Price"><c:out value="${reservation.totalCost}"/></td>
                <td data-th="Price"><c:out value="${reservation.dateOfReservation}"/></td>
                <td data-th="Price"><c:out value="${reservation.pedigreeType}"/></td>

                <td data-th="Subtotal" class="text-center"><p class="list-group-item-text">

                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <c:choose>
                            <c:when test="${reservation.expired}">
                                <span class="label label-danger">Expired</span>
                                <c:set var="showExpiredButton" value="true"/>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-success">Active</span>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${sessionScope.role == 'USER'}">
                        <c:choose>
                            <c:when test="${reservation.status eq 'EXPD'}">
                                <span class="label label-danger">Expired</span>
                            </c:when>
                            <c:when test="${reservation.status eq 'NEW'}">
                                <span class="label label-success">Active</span>
                            </c:when>
                            <c:when test="${reservation.status eq 'DONE'}">
                                <span class="label label-success">Bought</span>
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
                            <a target="_blank" title="Open in new window"
                               href="/assets/img/uploads/cheques/${reservation.chequePhoto}">
                                <img src="/assets/img/uploads/cheques/${reservation.chequePhoto}" alt="Payment"
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
                                    <div class="form-group"><input type="file" required="required" name="file"
                                                                   size="60"/>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Attach cheque photo</button>
                                </div>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${reservation.status eq 'NEW' && not empty reservation.chequePhoto && sessionScope.role == 'USER'}">
                        <td style="text-align: center">Paid</td>
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
                            <button type="submit" class="btn btn-info btn-sm"><i class="fas fa-hand-holding-usd">
                                Sell</i></button>
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.role == 'USER'}">
                        <c:choose>
                            <c:when test="${reservation.status eq 'EXPD'}">

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="renew_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-warning btn-sm"><i class="fab fa-hotjar">
                                        Renew</i></button>
                                </form>

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="delete_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-danger btn-sm"><i class="fab fa-hotjar">
                                        Delete</i></button>
                                </form>
                            </c:when>
                            <c:when test="${reservation.status eq 'NEW' && empty reservation.chequePhoto}">

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="cancel_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-info btn-sm"><i
                                            class="fas fa-hand-holding-usd"> Cancel</i></button>
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
                <c:if test="${sessionScope.role == 'USER'}"><td colspan="2" class="hidden-xs"></td></c:if>

                <c:if test="${sessionScope.role == 'ADMIN'}">
                <c:choose>
                    <c:when test="${not empty reservations && showExpiredButton}">

                        <td>
                            <form role="form" method="post" action="/controller">
                                <input type="hidden" name="command" value="decline_expired_reservations"/>
                                <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                <button type="submit" class="btn btn-warning btn-block">Decline all
                                    expired <i class="fab fa-hotjar"></i></button>
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

    <div class="col" style="float: right; margin-bottom: 38px">
        <c:url var="searchUri" value="/controller?command=all_reservations&page=##" />
        <paginator:display maxLinks="10"
                           currPage="${requestScope.page}"
                           totalPages="${requestScope.pageCount}"
                           uri="${searchUri}"/>
    </div>

</div>
<script>
    $(function () {
        $('#cart').searchable({
            striped: true,
            oddRow: {'background-color': '#f5f5f5'},
            evenRow: {'background-color': '#fff'},
            searchType: 'fuzzy'
        });
    });
</script>
<%@ include file="/jsp/parts/footer.jsp" %>
