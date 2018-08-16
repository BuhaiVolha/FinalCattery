<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.new-window" var="newWindow"/>
<fmt:message bundle="${loc}" key="local.cats.photo.alt" var="catsPhoto"/>
<fmt:message bundle="${loc}" key="local.default.photo.alt" var="defaultPhoto"/>
<fmt:message bundle="${loc}" key="local.cats.status.reserved" var="catsReserved"/>
<fmt:message bundle="${loc}" key="local.cats.status.available" var="catsAvailable"/>
<fmt:message bundle="${loc}" key="local.cats.status.sold" var="catsSold"/>
<fmt:message bundle="${loc}" key="local.cats.parents" var="catsParents"/>
<fmt:message bundle="${loc}" key="local.cats.age" var="catsAge"/>
<fmt:message bundle="${loc}" key="local.cats.age.month" var="catsAgeMonth"/>
<fmt:message bundle="${loc}" key="local.and" var="andWord"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body" var="catsBodyColour"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.black" var="catsBodyColourBlack"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blue" var="catsBodyColourBlue"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.red" var="catsBodyColourRed"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.creme" var="catsBodyColourCreme"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blacktortie" var="catsBodyColourBlacktortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.bluetortie" var="catsBodyColourBluetortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.silver" var="catsBodyColourSilver"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.white" var="catsBodyColourWhite"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.golden" var="catsBodyColourGolden"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes" var="catsEyesColour"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.green" var="catsEyesColourGreen"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.odd" var="catsEyesColourOdd"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.orange" var="catsEyesColourOrange"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.blue" var="catsEyesColourBlue"/>
<fmt:message bundle="${loc}" key="local.cats.price" var="catsPrice"/>
<fmt:message bundle="${loc}" key="local.cats.current-price" var="catsCurrentPrice"/>
<fmt:message bundle="${loc}" key="local.cats.current" var="catsCurrent"/>
<fmt:message bundle="${loc}" key="local.cats.button.reserve" var="catsButtonReserve"/>
<fmt:message bundle="${loc}" key="local.cats.button.change-photo" var="catsButtonChangePhoto"/>
<fmt:message bundle="${loc}" key="local.cats.button.delete" var="catsButtonDelete"/>
<fmt:message bundle="${loc}" key="local.cats.button.edit" var="catsButtonEdit"/>
<fmt:message bundle="${loc}" key="local.button.delete.warn" var="buttonDeleteWarn"/>
<fmt:message bundle="${loc}" key="local.must-log-in.message1" var="mustLogInMessage1"/>
<fmt:message bundle="${loc}" key="local.must-log-in.message2" var="mustLogInMessage2"/>
<fmt:message bundle="${loc}" key="local.must-log-in.message3" var="mustLogInMessage3"/>
<fmt:message bundle="${loc}" key="local.cats.single-cat.gender-female" var="singleCatFemale"/>
<fmt:message bundle="${loc}" key="local.cats.single-cat.gender-male" var="singleCatMale"/>


<div class="container">
    <div class="card">

        <div class="wrapper row">
            <div class="preview col-md-6">

                <div class="preview-pic tab-content">

                    <div class="img-container">

                        <c:choose>
                            <c:when test="${not empty requestScope.singleCat.photo}">
                                <a target="_blank" title="${newWindow}"
                                   href="/assets/img/uploads/cats/${requestScope.singleCat.photo}">
                                    <img src="/assets/img/uploads/cats/${requestScope.singleCat.photo}"
                                         alt="${catsPhoto}"
                                         class="img-responsive"/></a>
                            </c:when>
                            <c:otherwise>
                                <img src="/assets/img/uploads/cats/${sessionScope.local}-default-cat.jpg"
                                     style="border-radius:0; margin-bottom:0;" alt="${defaultPhoto}"
                                     class="img-responsive"/>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
            </div>

            <div class="details col-md-6 bubble">
                <h3 class="product-title" style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;"><c:out value="${requestScope.singleCat.name}"/>
                    <c:out
                            value="${requestScope.singleCat.lastname}"/></h3>

                <c:choose>
                <c:when test="${requestScope.singleCat.status eq 'AVAIL'}">
                <h3 class="status avail">${catsAvailable}
                    </c:when>
                    <c:when test="${requestScope.singleCat.status eq 'RSRV'}">
                    <h3 class="status book">${catsReserved}
                        </c:when>
                        <c:otherwise>
                        <h3 class="status sold">${catsSold}
                            </c:otherwise>
                            </c:choose>
                        </h3>
                        <div style="text-align: center">

                        <h5 class="colors">
                            <strong>
                                <c:choose><c:when test="${requestScope.singleCat.gender eq 'FEMALE'}">
                                    ${singleCatFemale}
                                    <i class="fa fa-venus"></i>
                                </c:when>
                                    <c:otherwise>
                                        ${singleCatMale}
                                        <i class="fa fa-mars"></i>
                                    </c:otherwise>
                                </c:choose>
                            </strong>
                        </h5>
                            <h5 class="sizes">${catsParents}:
                                <span><c:out value="${requestScope.singleCat.femaleParent}"/> ${andWord}
                        <span><c:out value="${requestScope.singleCat.maleParent}"/>
                            </h5>
                        <p class="product-description" style="font-style: italic"><c:out value="${requestScope.singleCat.description}"/></p>

                        <h5 class="colors">${catsAge}:
                            <c:out value="${requestScope.singleCat.age}"/> ${catsAgeMonth}
                        </h5>
                        <h5 class="colors">${catsBodyColour}:

                            <c:choose>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'N'}">
                                    ${catsBodyColourBlack}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'A'}">
                                    ${catsBodyColourBlue}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'D'}">
                                    ${catsBodyColourRed}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'E'}">
                                    ${catsBodyColourCreme}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'F'}">
                                    ${catsBodyColourBlacktortie}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'Q'}">
                                    ${catsBodyColourBluetortie}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'S'}">
                                    ${catsBodyColourSilver}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'W'}">
                                    ${catsBodyColourWhite}
                                </c:when>
                                <c:when test="${requestScope.singleCat.bodyColour eq 'Y'}">
                                    ${catsBodyColourGolden}
                                </c:when>
                            </c:choose>
                        </h5>
                        <h5 class="colors">${catsEyesColour}:
                            <c:choose>
                                <c:when test="${requestScope.singleCat.eyesColour eq 'F61'}">
                                    ${catsEyesColourBlue}
                                </c:when>
                                <c:when test="${requestScope.singleCat.eyesColour eq 'F62'}">
                                    ${catsEyesColourOrange}
                                </c:when>
                                <c:when test="${requestScope.singleCat.eyesColour eq 'F63'}">
                                    ${catsEyesColourOdd}
                                </c:when>
                                <c:when test="${requestScope.singleCat.eyesColour eq 'F64'}">
                                    ${catsEyesColourGreen}
                                </c:when>
                            </c:choose>
                        </h5>
                        </div>
                        <c:choose>
                        <c:when test="${requestScope.singleCat.priceWithDiscount != requestScope.singleCat.price
                        && not empty requestScope.singleCat.priceWithDiscount && requestScope.singleCat.status ne 'SOLD'}">
                        <h4 class="price" style="text-align: center">${catsCurrentPrice}:
                            <del><span>$<c:out value="${requestScope.singleCat.price}"/></span></del>
                            <span>$<c:out value="${requestScope.singleCat.priceWithDiscount}"/></span></h4>
                        </c:when>
                        <c:otherwise>
                        <h4 class="price" style="text-align: center">
                            <c:if test="${requestScope.singleCat.status ne 'SOLD'}">${catsCurrent}</c:if> ${catsPrice}:
                            <span>$<c:out
                                    value="${requestScope.singleCat.price}"/></span>
                        </h4>
                        </c:otherwise>
                        </c:choose>

                        <div class="action">
                            <c:choose>
                                <c:when test="${sessionScope.role eq 'USER' && requestScope.singleCat.status eq 'AVAIL'}">
                                    <div style="text-align:center;">
                                        <form role="form" method="get" action="/controller">
                                            <input type="hidden" name="command" value="single_cat"/>
                                            <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
                                            <input type="hidden" name="operation" value="reserve-cat"/>
                                            <button type="submit"
                                                    class="add-to-cart btn btn-default">${catsButtonReserve}</button>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${sessionScope.role eq 'ADMIN'}">
                                    <br/>

                                    <form method="post" action="/imageUploader" enctype="multipart/form-data">
                                        <input type="hidden" name="command" value="upload_cat_photo"/>
                                        <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
                                        <div class="form-inline pull-left">
                                            <div class="form-group"><input type="file" required="required" name="cat"
                                                                           size="60"/>
                                            </div>
                                            <button type="submit"
                                                    class="btn btn-primary">${catsButtonChangePhoto}</button>
                                        </div>
                                    </form>
                                    <br/> <br/>
                                    <br/>
                                    <div style="text-align:center;">
                                        <form role="form" method="post" action="/controller">
                                            <input type="hidden" name="command" value="delete_cat"/>
                                            <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
                                            <button type="submit" title="${buttonDeleteWarn}"
                                                    class="add-to-cart btn btn-default">${catsButtonDelete}
                                            </button>
                                        </form>
                                    </div>

                                </c:when>
                                <c:when test="${sessionScope.role eq 'EXPERT'}">
                                    <div style="text-align:center;">
                                        <form role="form" method="get" action="/controller">
                                            <input type="hidden" name="command" value="single_cat"/>
                                            <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
                                            <input type="hidden" name="operation" value="cat-form"/>
                                            <button type="submit"
                                                    class="add-to-cart btn btn-default">${catsButtonEdit}</button>
                                        </form>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>

                        <c:if test="${sessionScope.role ne 'EXPERT'
                        && sessionScope.role ne 'ADMIN'
                        && sessionScope.role ne 'USER'
                        && requestScope.singleCat.status ne 'SOLD'}">

                        <p style="text-align: center;"><i class="fas fa-exclamation mark"> ${mustLogInMessage1} <a href="#" onclick="openLoginModal()">
                            ${mustLogInMessage2}</a> ${mustLogInMessage3}</i></p>

                        </c:if>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
