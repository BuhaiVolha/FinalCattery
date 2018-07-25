<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Pedigree type</th>
            <th scope="col">Amount</th>

        </tr>
        </thead>
<c:forEach var="entry" items="${pedigreeCount}">
        <tbody>
        <tr>
            <td data-label="Type"> <c:out value="${entry.key}"/></td>
            <td data-label="Amount"><c:out value="${entry.value}"/></td>
        </tr>

        </tbody>
</c:forEach>
    </table>

</div>

<%@ include file="/jsp/parts/footer.jsp" %>