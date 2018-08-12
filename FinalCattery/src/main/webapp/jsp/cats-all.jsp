<%@ include file="/jsp/cats-display.jsp" %>

<div class="col" style="float: right; margin-bottom: 38px">
    <c:url var="searchUri" value="/controller?command=all_cats&page=##" />
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
