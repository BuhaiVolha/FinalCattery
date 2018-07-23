<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">

    <form class="well form-horizontal" id="contact_form" role="form" method="POST" action="/controller">
        <input type="hidden" name="command" value="add_cat"/>
        <input type="hidden" name="offerId" value="${requestScope.offerId}"/>
        <input type="hidden" name="userMadeOfferId" value="${requestScope.userMadeOfferId}"/>

        <fieldset>
            <!-- Form Name -->
            <legend>Adding a cat...</legend>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Name</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-eye-open"></i></span>
                        <input name="name" required="required" placeholder="First Name" class="form-control"
                               type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Lastname</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-eye-open"></i></span>
                        <input name="lastname" required="required" placeholder="Last Name" class="form-control"
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
                        <input type="text" name="age"
                               value="<fmt:formatDate value="${now}" type="both" pattern="MM/dd/yyyy" />"
                               data-date-format="mm/dd/yyyy" class="datepicker">
                    </div>
                </div>
            </div>

            <script type="text/javascript">
                $(document).ready(function () {
                    $('.datepicker').datepicker({autoclose: true});
                })
            </script>

            <!-- Select Basic -->

            <div class="form-group">
                <label class="col-md-4 control-label">Body colour code</label>
                <div class="col-md-4 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-star-empty"></i></span>
                        <select name="bodyColour" required="required" class="form-control selectpicker">
                            <option value=" ">Body colour code</option>
                            <option>A</option>
                            <option>D</option>
                            <option>E</option>
                            <option>F</option>
                            <option>N</option>
                            <option>Q</option>
                            <option>F</option>
                            <option>S</option>
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
                            <option value=" ">Eyes colour code</option>
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
                        <input name="femaleParent" required="required" placeholder="Female parent" class="form-control"
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
                        <input name="maleParent" required="required" placeholder="Male parent" class="form-control"
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
                        <input name="price" placeholder="Price" value="${requestScope.statedPrice}" required="required" class="form-control" type="text">
                    </div>
                </div>
            </div>
            <!-- Text area -->

            <div class="form-group">
                <label class="col-md-4 control-label">Description</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                        <textarea class="form-control" name="description" placeholder="Cat description"
                                  required="required"></textarea>
                    </div>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
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


