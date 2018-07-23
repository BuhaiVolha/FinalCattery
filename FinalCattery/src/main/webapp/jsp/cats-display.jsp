<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>


<div class="container">
    <h4>OUR CATS</h4>
    <div class="row">

        <!-- BEGIN PRODUCTS -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="row row-eq-height">
                    <c:forEach items="${cats}" var="cat">
                        <div class="col-md-3 col-sm-6 item-wr">
                            <div class="thumbnail">
                                <c:choose><c:when test="${cat.status eq 'AVAIL'}">
                                    <p class="status avail"><c:out value="AVAILABLE"/>
                                </c:when>
                                <c:when test="${cat.status eq 'RSRV'}">
                                    <p class="status book"><c:out value="RESERVED"/>
                                </c:when>
                                    <c:otherwise>
                                    <p class="status sold"><c:out value="SOLD"/>
                                    </c:otherwise>
                                </c:choose>
                                </p>
                                <img src="/jsp/assets/img/user.png" alt="A photo of kitten" class="img-responsive">

                                <h4><c:out value="${cat.name}"/> <c:out value="${cat.lastname}"/></h4>

                                <h5><c:out value="${cat.age}"/> month,  <c:choose><c:when test="${cat.gender eq 'FEMALE'}">
                                    <i class="fa fa-venus"></i>
                                </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-mars"></i>
                                    </c:otherwise>
                                </c:choose></h5>

                                <hr class="line">
                                <div class="row">
                                    <div class="col-md-6 col-sm-6">
                                    <c:if test="${cat.status ne 'SOLD'}">
                                        <c:choose>
                                            <c:when test="${cat.priceWithDiscount != cat.price && not empty cat.priceWithDiscount}">
                                                <p class="price">price:
                                                    <del><span>$<c:out value="${cat.price}"/></span></del>
                                                    <span>$<c:out value="${cat.priceWithDiscount}"/> !!!</span></p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="price">price: <span>$<c:out value="${cat.price}"/></span>
                                                </p>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
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
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>