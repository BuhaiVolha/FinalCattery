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
                    <th style="width:40%">Cat</th>
                    <th style="width:15%">Buyer</th>
                    <th style="width:10%">Price</th>
                    <th style="width:15%">Date</th>
                    <th style="width:10%">Pedigree</th>
                    <th style="width:22%" class="text-center">Status</th>
                    <th style="width:10%"></th>
                </c:when>
                <c:when test="${sessionScope.role == 'USER'}">
                    <th style="width:30%">Cat</th>
                    <th style="width:10%">Price</th>
                    <th style="width:15%">Date</th>
                    <th style="width:10%">Pedigree</th>
                    <th style="width:22%" class="text-center">Status</th>
                    <th style="width:10%"></th>
                </c:when>
            </c:choose>
        </tr>
        </thead>
        <c:forEach items="${reservations}" var="reservation">
            <tbody>
            <tr>

                <td data-th="Product">
                    <div class="row">
                        <div class="col-sm-2 hidden-xs">
                            <a target="_blank" title="Open in new window" href="/assets/img/uploads/cats/${reservation.catPhoto}">
                                <img src="/assets/img/uploads/cats/${reservation.catPhoto}" alt="Reserved cat"
                                     class="img-responsive" /></a></div>
                        <div class="col-sm-10">
                            <h4 class="nomargin"><c:out value="${reservation.catName}"/> <c:out
                                    value="${reservation.catLastname}"/></h4>

                        </div>
                    </div>
                </td>
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

                <td class="actions" data-th="">

                    <c:if test="${!reservation.expired && sessionScope.role == 'ADMIN'}">

                        <form role="form" method="post" action="/controller">
                            <input type="hidden" name="command" value="sell_cat"/>
                            <input type="hidden" name="reservationId" value="${reservation.id}"/>
                            <button type="submit" class="btn btn-info btn-sm"><i class="fas fa-hand-holding-usd"> Sell</i></button>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.role == 'USER'}">
                        <c:choose>
                            <c:when test="${reservation.status eq 'EXPD'}">

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="renew_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-warning btn-sm"><i class="fab fa-hotjar"> Renew</i></button>
                                </form>

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="delete_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-danger btn-sm"><i class="fab fa-hotjar"> Delete</i></button>
                                </form>
                            </c:when>
                            <c:when test="${reservation.status eq 'NEW'}">

                                <form role="form" method="post" action="/controller">
                                    <input type="hidden" name="command" value="cancel_reservation"/>
                                    <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                    <button type="submit" class="btn btn-info btn-sm"><i class="fas fa-hand-holding-usd"> Cancel</i></button>
                                </form>

                            </c:when>
                        </c:choose>
                    </c:if>

                </td>
            </tr>
            </tbody>
        </c:forEach>
        <tfoot>
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <tr class="visible-xs">
                <td class="text-center"><strong>Total</strong></td>
            </tr>
            <tr>
                <td colspan="2" class="hidden-xs"></td>
                <td colspan="2" class="hidden-xs"></td>
                <td colspan="2" class="hidden-xs"></td>

                <c:if test="${not empty reservations}">
                    <td> <form role="form" method="post" action="/controller">
                        <input type="hidden" name="command" value="decline_expired_reservations"/>
                        <input type="hidden" name="reservationId" value="${reservation.id}"/>
                        <button type="submit" class="btn btn-warning btn-block">Decline all
                            expired <i class="fab fa-hotjar"></i></button>
                    </form>
                    </td>

                </c:if>
            </tr>
        </c:if>
        </tfoot>
    </table>
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