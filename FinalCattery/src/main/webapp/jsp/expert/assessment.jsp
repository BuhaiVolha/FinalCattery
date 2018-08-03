<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "/jsp/parts/header.jsp" %>
<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="row row-eq-height">
<c:choose>
    <c:when test="${!empty catsByStatus}">
            <c:forEach items="${catsByStatus}" var="catByStatus">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                        <img src="/assets/img/user.png" alt="kitten">

                        <div class="card-body">
                            <p class="card-text">
                            <h5>
                                By: <c:out value="${catByStatus.userMadeOfferName}" />
                                <c:out value="${catByStatus.userMadeOfferLastname}"/>
                            </h5>
                            <p class="card-text">
                            <h5>
                                телефон: +375 <c:out value="${catByStatus.userMadeOfferPhone}" />
                            </h5>
                            <h5>
                                описание котенка: <c:out value="${catByStatus.catDescription}" />
                            </h5>
                            <h5>
                                желаемая цена: <c:out value="${catByStatus.price}" /> долларов
                            </h5>

                            <h5>
                                статус: <c:out value="${catByStatus.status}" />
                            </h5>

                            <form style ='float: left; padding: 5px;' role="form" method="post" action="/controller">
                                <input type="hidden" name="command" value="single_offer"/>
                                <input type="hidden" name="offerId" value="${catByStatus.id}"/>
                                <input type="hidden" name="operation" value="approve"/>
                                <button type="submit" class="btn btn-primary">Approve</button>
                            </form>

                            <form style ='float: left; padding: 5px;' role="form" method="post" action="/controller">
                                <input type="hidden" name="command" value="single_offer"/>
                                <input type="hidden" name="offerId" value="${catByStatus.id}"/>
                                <input type="hidden" name="operation" value="bargain"/>
                                <button type="submit" class="btn btn-primary">Bargain</button>
                            </form>

                            <form style ='float: left; padding: 5px;' role="form" method="post" action="/controller">
                                <input type="hidden" name="command" value="single_offer"/>
                                <input type="hidden" name="offerId" value="${catByStatus.id}"/>
                                <input type="hidden" name="operation" value="decline"/>
                                <button type="submit" class="btn btn-primary">Politely decline</button>
                            </form>

                        </div>
                    </div>
                </div>
            </c:forEach>

    </c:when>
    <c:otherwise>

        <div class="text-center">
            <img src="/assets/img/empty.jpg" class="img-responsive" style="margin:0 auto;" alt="Nothing to show"/>
        </div>
    </c:otherwise>
</c:choose>

        </div>
    </div>
</section>
<%@ include file = "/jsp/parts/footer.jsp" %>

