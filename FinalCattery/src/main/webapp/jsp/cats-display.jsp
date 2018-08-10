<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>


<div class="container">
<c:choose>
    <c:when test="${!empty cats}">
    <h4>OUR CATS</h4>
    </c:when>
    <c:otherwise><h4>Sorry, nothing was found</h4></c:otherwise>
</c:choose>
    <div class="row">

        <!-- BEGIN PRODUCTS -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="row row-eq-height">
                    <c:choose>
                        <c:when test="${!empty cats}">
                            <c:forEach items="${cats}" var="cat">
                                <div class="col-md-3 col-sm-6 item-wr cat">
                                    <div class="thumbnail">
                                        <c:choose>
                                        <c:when test="${cat.status eq 'AVAIL'}">
                                        <p class="status avail">
                                                <c:out value="AVAILABLE"/>
                                            </c:when>
                                            <c:when test="${cat.status eq 'RSRV'}">
                                        <p class="status book">
                                                <c:out value="RESERVED"/>
                                            </c:when>
                                            <c:otherwise>
                                        <p class="status sold"><c:out value="SOLD"/>
                                            </c:otherwise>
                                            </c:choose>
                                        </p>
                                            <div class="product-photo">

                                                <c:choose>
                                                    <c:when test="${not empty cat.photo}">
                                                        <a target="_blank" title="Open in new window" href="/assets/img/uploads/cats/${cat.photo}">
                                                            <img src="/assets/img/uploads/cats/${cat.photo}" alt="A photo of kitten"
                                                                 class="img-responsive"/></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="/assets/img/uploads/cats/${sessionScope.local}-default-cat.jpg" style="border-radius:0; margin-bottom:0;" alt="default image"
                                                             class="img-responsive"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                    </div>

                                        <h4 style="text-align: center; position: relative;"><c:out value="${cat.name}"/> <c:out value="${cat.lastname}"/></h4>

                                        <h5 style="text-align: center; position: relative;"><c:out value="${cat.age}"/> month, <c:choose><c:when
                                                test="${cat.gender eq 'FEMALE'}">
                                            <i class="fa fa-venus"></i>
                                        </c:when>
                                            <c:otherwise>
                                                <i class="fa fa-mars"></i>
                                            </c:otherwise>
                                        </c:choose></h5>

                                        <hr class="line">
                                        <div class="row">
                                            <div class="col-md-6 col-sm-6">

                                                    <c:choose>
                                                        <c:when test="${cat.priceWithDiscount != cat.price && not empty cat.priceWithDiscount && cat.status ne 'SOLD'}">
                                                            <p class="price">price:
                                                                <del><span>$<c:out value="${cat.price}"/></span></del>
                                                                <span style="margin-left: 19px">$<c:out
                                                                        value="${cat.priceWithDiscount}"/> !!!</span>
                                                            </p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="price">price: <span>$<c:out
                                                                    value="${cat.price}"/></span>
                                                            </p><br>
                                                        </c:otherwise>
                                                    </c:choose>

                                            </div>
                                            <div class="col-md-6 col-sm-6">

                                                <a href="/controller?command=single_cat&catId=${cat.id}&operation=display-cat">

                                                    <button
                                                            class="btn btn-info right">MORE DETAILS
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>

                            <div class="text-center">
                                <img src="/assets/img/not-found.jpg" class="img-responsive" style="margin:0 auto;"
                                     alt="Nothing to show"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>