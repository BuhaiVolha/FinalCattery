<
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <div class="card">

        <div class="wrapper row">
            <div class="preview col-md-6">

                <div class="preview-pic tab-content">
                    <div class="tab-pane active" id="pic-1"><img src="http://placekitten.com/400/252"
                                                                 class="img-responsive"/></div>

                </div>

            </div>
            <div class="details col-md-6">
                <h3 class="product-title"><c:out value="${requestScope.singleCat.name}"/> <c:out
                        value="${requestScope.singleCat.lastname}"/></h3>

                <c:choose>
                <c:when test="${requestScope.singleCat.status eq 'AVAIL'}">
                <h3 class="status avail">AVAILABLE
                    </c:when>
                    <c:when test="${requestScope.singleCat.status eq 'RSRV'}">
                    <h3 class="status book">RESERVED
                        </c:when>
                        <c:otherwise>
                        <h3 class="status sold">SOLD
                            </c:otherwise>
                            </c:choose>
                        </h3>
                        <h5 class="colors">
                            <strong>
                                <c:out value="${requestScope.singleCat.gender}"/>
                                <c:choose><c:when test="${requestScope.singleCat.gender eq 'FEMALE'}">
                                    <i class="fa fa-venus"></i>
                                </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-mars"></i>
                                    </c:otherwise>
                                </c:choose>
                            </strong>
                        </h5>
                        <p class="product-description"><c:out value="${requestScope.singleCat.description}"/></p>
                        <c:if test="${requestScope.singleCat.status ne 'SOLD'}">
                        <c:choose>
                        <c:when test="${requestScope.singleCat.priceWithDiscount != requestScope.singleCat.price && not empty requestScope.singleCat.priceWithDiscount}">
                        <h4 class="price">current price:
                            <del><span>$<c:out value="${requestScope.singleCat.price}"/></span></del>
                            <span>$<c:out value="${requestScope.singleCat.priceWithDiscount}"/></span></h4>
                        </c:when>
                        <c:otherwise>
                        <h4 class="price">current price: <span>$<c:out value="${requestScope.singleCat.price}"/></span>
                        </h4>
                        </c:otherwise>
                        </c:choose>
                        </c:if>
                        <h5 class="sizes">parents:
                            <span><c:out value="${requestScope.singleCat.femaleParent}"/> и
                        <span><c:out value="${requestScope.singleCat.maleParent}"/>
                        </h5>
                        <h5 class="colors">age:
                            <c:out value="${requestScope.singleCat.age}"/> month
                        </h5>
                        <h5 class="colors">body color:
                            <c:out value="${requestScope.singleCat.bodyColour}"/>
                        </h5>
                        <h5 class="colors">eyes color:
                            <c:out value="${requestScope.singleCat.eyesColour}"/>
                        </h5>
                        <c:choose>
                        <c:when test="${sessionScope.role eq 'USER' && requestScope.singleCat.status eq 'AVAIL'}">
                        <div class="action"><a
                                href="/controller?command=single_cat&catId=${singleCat.id}&operation=reserve-cat">
                            <button class="add-to-cart btn btn-default" type="button">Reserve</button>
                        </a></div>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'ADMIN'}">
                        <div class="action"><a href="/controller?command=delete_cat&catId=${singleCat.id}">
                            <button class="add-to-cart btn btn-default" title="Think twice before clicking!!!"
                                    type="button">Delete
                            </button>
                        </a>
                        </div>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'EXPERT'}">
                        <div class="action"><a
                                href="/controller?command=single_cat&catId=${singleCat.id}&operation=edit-cat">
                            <button class="add-to-cart btn btn-default" type="button">Edit</button>
                        </a>
                        </div>
                        </c:when>
                        </c:choose>

                        <c:if test="${sessionScope.role ne 'EXPERT' && sessionScope.role ne 'ADMIN' && sessionScope.role ne 'USER' && requestScope.singleCat.status ne 'SOLD'}">
                        <i class="fas fa-exclamation mark"> You must <a href="#" onclick="openLoginModal()">log in</a>
                            in in order to make an
                            order</i>
                        </c:if>
            </div>

        </div>
    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
