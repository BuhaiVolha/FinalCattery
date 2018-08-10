<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <div class="card">

        <div class="wrapper row">
            <div class="preview col-md-6">

                <div class="preview-pic tab-content">

                        <div class="img-container">
                            <a target="_blank" title="Open in new window" href="/assets/img/uploads/cats/${singleCat.photo}">
                                <img src="/assets/img/uploads/cats/${singleCat.photo}" class="img-responsive"/></a>


                    </div>

                </div>
            </div>

            <div class="details col-md-6 bubble">
                <h3 class="product-title" style="text-align: center;"><c:out value="${requestScope.singleCat.name}"/> <c:out
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
                        <c:when test="${requestScope.singleCat.priceWithDiscount != requestScope.singleCat.price
                        && not empty requestScope.singleCat.priceWithDiscount && requestScope.singleCat.status ne 'SOLD'}">
                        <h4 class="price">current price:
                            <del><span>$<c:out value="${requestScope.singleCat.price}"/></span></del>
                            <span>$<c:out value="${requestScope.singleCat.priceWithDiscount}"/></span></h4>
                        </c:when>
                        <c:otherwise>
                        <h4 class="price">
                            <c:if test="${requestScope.singleCat.status ne 'SOLD'}">current</c:if> price: <span>$<c:out value="${requestScope.singleCat.price}"/></span>
                        </h4>
                        </c:otherwise>
                        </c:choose>

                        <div class="action">
                        <c:choose>
                        <c:when test="${sessionScope.role eq 'USER' && requestScope.singleCat.status eq 'AVAIL'}">
                            <div style="text-align:center;">
                            <form role="form" method="get" action="/controller">
                                <input type="hidden" name="command" value="single_cat"/>
                                <input type="hidden" name="catId" value="${singleCat.id}"/>
                                <input type="hidden" name="operation" value="reserve-cat"/>
                                <button type="submit" class="add-to-cart btn btn-default">Reserve</button>
                            </form>
                            </div>
                        </c:when>
                        <c:when test="${sessionScope.role eq 'ADMIN'}">
                            <br/>

                            <form method="post" action="/imageUploader" enctype="multipart/form-data">
                                <input type="hidden" name="command" value="upload_cat_photo"/>
                                <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
                                <div class="form-inline pull-left">
                                    <div class="form-group"><input type="file" required="required" name="cat" size="60" />
                                    </div>
                                    <button type="submit" class="btn btn-primary">Change photo</button>
                                </div>
                            </form>
                            <br/>  <br/>
                            <div style="text-align:center;">
                            <form role="form" method="post" action="/controller">
                                <input type="hidden" name="command" value="delete_cat"/>
                                <input type="hidden" name="catId" value="${singleCat.id}"/>
                                <button type="submit" title="Think twice before clicking!!!" class="add-to-cart btn btn-default">Delete</button>
                            </form>
                                </div>

                        </c:when>
                        <c:when test="${sessionScope.role eq 'EXPERT'}">
                            <div style="text-align:center;">
                            <form role="form" method="get" action="/controller">
                                <input type="hidden" name="command" value="single_cat"/>
                                <input type="hidden" name="catId" value="${singleCat.id}"/>
                                <input type="hidden" name="operation" value="edit-cat"/>
                                <button type="submit" class="add-to-cart btn btn-default">Edit</button>
                            </form>
                            </div>
                        </c:when>
                        </c:choose>
                        </div>

                        <c:if test="${sessionScope.role ne 'EXPERT' && sessionScope.role ne 'ADMIN' && sessionScope.role ne 'USER' && requestScope.singleCat.status ne 'SOLD'}">
                        <p style="text-align: center;"><i class="fas fa-exclamation mark"> You must <a href="#" onclick="openLoginModal()">log in</a>
                            in in order to make an
                            order</i></p>
                        </c:if>
            </div>

        </div>
    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
