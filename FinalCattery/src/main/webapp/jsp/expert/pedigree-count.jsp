<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.reservations.pedigree" var="reservationsPedigree"/>
<fmt:message bundle="${loc}" key="local.pedigree.count.amount" var="pedigreeAmmount"/>
<fmt:message bundle="${loc}" key="local.pedigree.count.type" var="pedigreeType"/>

<div class="container">
    <br>
    <br>
    <br>
    <br>
    <br>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">${reservationsPedigree}</th>
            <th scope="col">${pedigreeAmmount}</th>
        </tr>
        </thead>
        <c:forEach var="entry" items="${requestScope.pedigreeCount}">
            <tbody>
            <tr>
                <td data-label="${pedigreeType}"><c:out value="${entry.key}"/></td>
                <td data-label="${pedigreeAmmount}"><c:out value="${entry.value}"/></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>