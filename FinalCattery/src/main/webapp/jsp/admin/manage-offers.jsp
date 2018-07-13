<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>
<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="row">

            <c:forEach items="${catsByStatus}" var="catByStatus">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                        <img src="/jsp/assets/img/user.png" alt="kitten">

                        <div class="card-body">
                            <p class="card-text">
                            <h5>
                                By: <c:out value="${catByStatus.userMadeOfferName}"/>
                                <c:out value="${catByStatus.userMadeOfferLastname}"/>

                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button"
                                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                        Make discount
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="#">5 %</a>
                                        <a class="dropdown-item" href="#">10 %</a>
                                        <a class="dropdown-item" href="#">15 %</a>
                                    </div>
                                </div>
                            </h5>
                            <p class="card-text">
                            <h5>
                                описание котенка: <c:out value="${catByStatus.catDescription}"/>
                            </h5>
                            <h5>
                                установленная цена: <c:out value="${catByStatus.price}"/> долларов
                            </h5>
                            <h5>
                                телефон: +375 <c:out value="${catByStatus.userMadeOfferPhone}"/>
                            </h5>
                            <h5>
                                статус: <c:out value="${catByStatus.status}"/>
                            </h5>
                            <c:if test="${not empty catByStatus.expertMessageToAdmin}">
                                <h5>
                                    статус: <c:out value="${catByStatus.expertMessageToAdmin}"/>
                                </h5>
                            </c:if>
                            <a href="/jsp/admin/form.jsp"
                               class="btn btn-primary">Make a discount</a>
                            <a href="/controller?command=single_offer&offerId=${catByStatus.id}&operation=add-cat"
                               class="btn btn-primary">Add</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</section>
<%@ include file="/jsp/parts/footer.jsp" %>


