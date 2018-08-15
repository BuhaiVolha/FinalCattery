<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.cat-form.adding.title" var="catFormAddingTitle"/>
<fmt:message bundle="${loc}" key="local.cat-form.editing.title" var="catFormEditingTitle"/>
<fmt:message bundle="${loc}" key="local.cats.name" var="catsName"/>
<fmt:message bundle="${loc}" key="local.cats.name.rule" var="catsNameRule"/>
<fmt:message bundle="${loc}" key="local.cats.lastname" var="catsLastame"/>
<fmt:message bundle="${loc}" key="local.cats.lastname.rule" var="catsLastameRule"/>
<fmt:message bundle="${loc}" key="local.cats.gender" var="catsGender"/>
<fmt:message bundle="${loc}" key="local.cats.gender.female" var="catsGenderF"/>
<fmt:message bundle="${loc}" key="local.cats.gender.male" var="catsGenderM"/>
<fmt:message bundle="${loc}" key="local.cats.birthdate" var="catsBirthDate"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body" var="catsBodyColour"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes" var="catsEyesColour"/>
<fmt:message bundle="${loc}" key="local.cats.parent.female" var="catsParentFemale"/>
<fmt:message bundle="${loc}" key="local.cats.parent.male" var="catsParentMale"/>
<fmt:message bundle="${loc}" key="local.cats.parents.rule" var="catsParentsRule"/>
<fmt:message bundle="${loc}" key="local.cats.price" var="catsPrice"/>
<fmt:message bundle="${loc}" key="local.cats.description" var="catsDescription"/>
<fmt:message bundle="${loc}" key="local.button.send" var="buttonSend"/>


<div class="container">
    <form class="well form-horizontal" id="contact_form" role="form" method="POST" action="/controller">
        <c:choose>
        <c:when test="${not empty requestScope.singleCat}">
        <input type="hidden" name="command" value="edit_cat"/>
        <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
        <fieldset>

            <legend style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${catFormEditingTitle}</legend>
            </c:when>
            <c:otherwise>
            <input type="hidden" name="command" value="add_cat"/>
            <input type="hidden" name="offerId" value="${requestScope.offer.id}"/>
            <input type="hidden" name="userMadeOfferId" value="${requestScope.offer.userMadeOfferId}"/>
            <input type="hidden" name="photo" value="${requestScope.offer.photo}"/>
            <fieldset>

                <legend style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;">${catFormAddingTitle}</legend>
                </c:otherwise>
                </c:choose>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsName}</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-eye-open"></i></span>
                            <input name="name" required="required" placeholder="${catsName}"
                                   pattern="[a-zA-Zа-яА-Я]{2,20}"
                                   title="${catsNameRule}"
                                   class="form-control"
                                   <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.name}"</c:if>
                                   type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsLastame}</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-eye-open"></i></span>
                            <input name="lastname" required="required" placeholder="${catsLastame}"
                                   pattern="[a-zA-Zа-яА-Я]{2,20}(\s[a-zA-Zа-яА-Я]{0,20})?"
                                   title="${catsLastameRule}"
                                   class="form-control"
                                   <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.lastname}"</c:if>
                                   type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsGender}</label>
                    <div class="col-md-4">
                        <div class="radio">
                            <label>
                                <input type="radio" required="required" name="gender" value="MALE"/> ${catsGenderM}
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" required="required" name="gender" value="FEMALE"/> ${catsGenderF}
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsBirthDate}</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                            <span class="input-group date">
                        <input type="text" name="age"
                               value="<fmt:formatDate value="${now}" type="both" pattern="dd/MM/yyyy" />"
                               data-date-format="dd/mm/yyyy" required="required" class="datepicker" readonly></span>
                        </div>
                    </div>
                </div>

                <script type="text/javascript">
                    $(document).ready(function () {
                        $('.datepicker').datepicker({autoclose: true});
                    });
                </script>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsBodyColour}</label>
                    <div class="col-md-4 selectContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-star-empty"></i></span>
                            <select name="bodyColour" required="required" class="form-control selectpicker">
                                <option>A</option>
                                <option>D</option>
                                <option>E</option>
                                <option>F</option>
                                <option>N</option>
                                <option>Q</option>
                                <option>S</option>
                                <option>W</option>
                                <option>Y</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsEyesColour}</label>
                    <div class="col-md-4 selectContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-star"></i></span>
                            <select name="eyesColour" required="required" class="form-control selectpicker">
                                <option>F61</option>
                                <option>F62</option>
                                <option>F63</option>
                                <option>F64</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsParentFemale}</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-heart-empty"></i></span>
                            <input name="femaleParent" required="required" placeholder="${catsParentFemale}"
                                   class="form-control"
                                   pattern="[a-zA-Zа-яА-Я]{2,20}\s[a-zA-Zа-яА-Я]{0,20}(\s[a-zA-Zа-яА-Я]{0,20})?"
                                   title="${catsParentsRule}"
                                   <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.femaleParent}"</c:if>
                                   type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsParentMale}</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-heart"></i></span>
                            <input name="maleParent" required="required" placeholder="${catsParentFemale}" class="form-control"
                                   pattern="[a-zA-Zа-яА-Я]{2,20}\s[a-zA-Zа-яА-Я]{0,20}(\s[a-zA-Zа-яА-Я]{0,20})?"
                                   title="${catsParentsRule}"
                                   <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.maleParent}"</c:if>
                                   type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsPrice}</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-gift"></i></span>
                            <input name="price" placeholder="${catsPrice}"
                                   title="A reasonable price will have 2 or 4 digits" required="required"
                                   class="form-control" pattern="[0-9]{2,4}\.?0?"
                            <c:choose>
                                   <c:when test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.price}"</c:when>
                                   <c:otherwise>value="${requestScope.offer.price}"</c:otherwise>
                            </c:choose>
                                   type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">${catsDescription}</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                            <textarea style="resize: none;" class="form-control" name="description"
                                    <c:choose> <c:when
                                            test="${not empty requestScope.singleCat}">placeholder="${requestScope.singleCat.description}"</c:when>
                                        <c:otherwise>placeholder="${catsDescription}"</c:otherwise></c:choose>
                                      required="required" rows="7"></textarea>
                        </div>
                    </div>
                </div>
                <p style="text-align: center; color: red;">${param.sendCatFormFailedMessage}</p>

                <div class="form-group" style="text-align: center">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-warning">${buttonSend} <span
                                class="glyphicon glyphicon-plane"></span>
                        </button>
                    </div>
                </div>

            </fieldset>
    </form>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>


