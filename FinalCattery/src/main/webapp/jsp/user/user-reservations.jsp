<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <table id="cart" class="table table-hover table-condensed">

        <thead>
        <tr>
            <th style="width:30%">Cat</th>
            <th style="width:10%">Price</th>
            <th style="width:15%">Date</th>
            <th style="width:10%">Pedigree</th>
            <th style="width:22%" class="text-center">Status</th>
            <th style="width:10%"></th>
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
                <td data-th="Price"><c:out value="${reservation.totalCost}"/></td>
                <td data-th="Price"><c:out value="${reservation.dateOfReservation}"/></td>
                <td data-th="Price"><c:out value="${reservation.pedigreeType}"/></td>

                <td data-th="Subtotal" class="text-center"><p class="list-group-item-text">
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
                </p></td>
                <td class="actions" data-th="">
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

                </td>
            </tr>
            </tbody>
        </c:forEach>
        <tfoot>

        </tfoot>
    </table>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>