<%@ include file="/jsp/authorized/offers.jsp" %>

<c:if test="${not empty offers}">
    <div class="col" style="float: right; margin-bottom: 38px">
        <c:url var="searchUri" value="/controller?command=approved_offers&page=##" />
        <paginator:display maxLinks="10"
                           currPage="${requestScope.page}"
                           totalPages="${requestScope.pageCount}"
                           uri="${searchUri}"/>
    </div>
</c:if>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>