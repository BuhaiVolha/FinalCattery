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
                        <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..."
                                                             class="img-responsive"/></div>
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
                        <c:otherwise>
                            <span class="label label-success">Bought</span>
                        </c:otherwise>
                    </c:choose>
                </p></td>
                <td class="actions" data-th="">
                    <c:choose>
                        <c:when test="${reservation.status eq 'EXPD'}">
                            <a href="/controller?command=delete_reservation&reservationId=${reservation.id}"
                               class="btn btn-danger btn-sm"><i class="fab fa-hotjar"> Delete</i></a>
                            <a href="/controller?command=renew_reservation&reservationId=${reservation.id}"
                               class="btn btn-warning btn-sm"><i class="fab fa-hotjar"> Renew</i></a>
                        </c:when>
                        <c:when test="${reservation.status eq 'NEW'}">
                            <a href="/controller?command=cancel_reservation&reservationId=${reservation.id}"
                               class="btn btn-info btn-sm"><i class="fas fa-hand-holding-usd"> Cancel</i></a>
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