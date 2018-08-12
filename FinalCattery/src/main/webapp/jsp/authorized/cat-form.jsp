<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <form class="well form-horizontal" id="contact_form" role="form" method="POST" action="/controller">
        <c:choose>
            <c:when test="${not empty requestScope.singleCat}">
                <input type="hidden" name="command" value="edit_cat"/>
                <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
            <fieldset>
                <!-- Form Name -->
                <legend style="text-align: center">Editing a cat...</legend>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="command" value="add_cat"/>
                <input type="hidden" name="offerId" value="${requestScope.offer.id}"/>
                <input type="hidden" name="userMadeOfferId" value="${requestScope.offer.userMadeOfferId}"/>
                <input type="hidden" name="photo" value="${requestScope.offer.photo}"/>
                <fieldset>
                    <!-- Form Name -->
                    <legend  style="text-align: center">Adding a cat...</legend>
            </c:otherwise>
        </c:choose>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Name</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-eye-open"></i></span>
                        <input name="name" required="required" placeholder="First Name" pattern="[a-zA-Zа-яА-Я]{2,20}"
                               title="No less than 2 and no more than 20 characters such as letters only"
                               class="form-control"
                               <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.name}"</c:if>
                               type="text" >
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Lastname</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-eye-open"></i></span>
                        <input name="lastname" required="required" placeholder="Last Name" pattern="[a-zA-Zа-яА-Я]{2,20}(\s[a-zA-Zа-яА-Я]{0,20})?"
                               title="No less than 2 and no more than 20 characters such as letters only. If lastname consists of 2 parts, it should be separated with a space. Same rules applies to the second part" class="form-control"
                               <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.lastname}"</c:if>
                               type="text">
                    </div>
                </div>
            </div>

            <!-- radio checks -->
            <div class="form-group">
                <label class="col-md-4 control-label">Gender</label>
                <div class="col-md-4">
                    <div class="radio">
                        <label>
                            <input type="radio" required="required" name="gender" value="MALE"/> Male
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" required="required" name="gender" value="FEMALE"/> Female
                        </label>
                    </div>
                </div>
            </div>

            <!-- date pic -->

            <div class="form-group">
                <label class="col-md-4 control-label">Birth Date</label>
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

            <!-- Select Basic -->

            <div class="form-group">
                <label class="col-md-4 control-label">Body colour code</label>
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
            <!-- Select Basic -->

            <div class="form-group">
                <label class="col-md-4 control-label">Eyes colour code</label>
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

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label">Female parent</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-heart-empty"></i></span>
                        <input name="femaleParent" required="required" placeholder="Female parent" class="form-control" pattern="[a-zA-Zа-яА-Я]{2,20}\s[a-zA-Zа-яА-Я]{0,20}(\s[a-zA-Zа-яА-Я]{0,20})?"
                               title="No less than 2 and no more than 20 characters such as letters only for both name and lastname which should be separated via space"
                               <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.femaleParent}"</c:if>
                               type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Male parent</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-heart"></i></span>
                        <input name="maleParent" required="required" placeholder="Male parent" class="form-control" pattern="[a-zA-Zа-яА-Я]{2,20}\s[a-zA-Zа-яА-Я]{0,20}(\s[a-zA-Zа-яА-Я]{0,20})?"
                               title="No less than 2 and no more than 20 characters such as letters only for both name and lastname which should be separated via space"
                               <c:if test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.maleParent}"</c:if>
                               type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label">Price</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-gift"></i></span>
                        <input name="price" placeholder="Price"
                               title="A reasonable price will have 2 or 4 digits" required="required" class="form-control" pattern="[0-9]{2,4}\.?0?"
                              <c:choose>
                                  <c:when test="${not empty requestScope.singleCat}">value="${requestScope.singleCat.price}"</c:when>
                                <c:otherwise>value="${requestScope.offer.price}"</c:otherwise>
                        </c:choose>
                               type="text">
                    </div>
                </div>
            </div>
            <!-- Text area -->

            <div class="form-group">
                <label class="col-md-4 control-label">Description</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                        <textarea style="resize: none;" class="form-control" name="description"
                                 <c:choose> <c:when test="${not empty requestScope.singleCat}">placeholder="${requestScope.singleCat.description}"</c:when>
                                 <c:otherwise>placeholder="Cat description"</c:otherwise></c:choose>
                                  required="required"></textarea>
                    </div>
                </div>
            </div>
            <p style="text-align: center; color: red;">${param.sendCatFormFailedMessage}</p>
            <!-- Button -->
            <div class="form-group" style="text-align: center">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-warning">Send <span class="glyphicon glyphicon-plane"></span>
                    </button>
                </div>
            </div>

        </fieldset>
    </form>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>


