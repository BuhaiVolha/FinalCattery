<%@ include file="/jsp/cats-display.jsp" %>

<div class="col" style="float: right; margin-bottom: 38px">
    <c:url var="searchUri" value="/controller?command=search&gender=${requestScope.searchedCat.gender}&status=${requestScope.searchedCat.status}&body=${requestScope.searchedCat.bodyColour}&eyes=${requestScope.searchedCat.eyesColour}&price=${requestScope.searchedCat.price}&page=##" />
    <paginator:display maxLinks="10"
                       currPage="${requestScope.page}"
                       totalPages="${requestScope.pageCount}"
                       uri="${searchUri}"/>

</div>
</div>
</div>
</div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
