<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "/jsp/parts/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >

            <c:forEach items="${cats}" var="cat">
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                            <img src="/jsp/assets/img/user.png" alt="kitten">

                        <div class="card-body">
                            <p class="card-text">
                            <h5>
                                <c:out value="${cat.name}" />
                                <c:out value="${cat.lastname}"/>
                            </h5>
                            <p class="card-text">
                            <h5>
                            пол: <c:out value="${cat.gender}" />
                            </h5>
                            <h5>
                                возраст (в месяцах): <c:out value="${cat.ageMonth}" />
                            </h5>
                            <h5>
                                описание: <c:out value="${cat.description}" />
                            </h5>
                            <h5>
                                окрас: <c:out value="${cat.bodyColor}" />
                            </h5>
                            <h5>
                                цвет глаз: <c:out value="${cat.eyesColor}" />
                            </h5>
                            <h5>
                               цена: <c:out value="${cat.price}" /> долларов
                            </h5>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file = "/jsp/parts/footer.jsp" %>
