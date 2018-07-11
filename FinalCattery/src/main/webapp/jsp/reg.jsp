<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "parts/header.jsp" %>

<div class="container">
    <h2 class="text-center text-uppercase text-secondary mb-0">Registration</h2>
    <hr class="star-dark mb-5">
    <div class="row">
        <div class="col-lg-8 mx-auto">

            <form name="SignUpForm" method="POST" action="/controller">
                <input type="hidden" name="command" value="Registration" />
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <h4>Login</h4>
                        <label>Login</label>
                        <input type="text" name="login" value="" placeholder="login" required="required" data-validation-required-message="Please enter your name." />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>

                ${errorLoginExistsMessage}
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <h4>Password</h4>
                        <label>Password</label>
                        <input type="password" name="password" placeholder="6+ symbols" required="required" value="" />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <h4>Name</h4>
                        <label>Name</label>
                        <input type="text" name="name" placeholder="" required="required" value="" />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <h4>Lastname</h4>
                        <label>Lastname</label>
                        <input type="text" name="lastname" placeholder="" required="required" value="" />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <h4>email</h4>
                        <label>email</label>
                        <input type="email" name="email" placeholder="" required="required" value="" />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <h4>phone</h4>
                        <label>phone</label>
                        <input type="text" name="phone" placeholder="" required="required" value="" />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <br>
                <div id="success"></div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-xl" value="sign-up" id="sendMessageButton">Sign up</button>
                    <a class="btn btn-xl btn-outline-light" href="../index.jsp">
                        <i class="fa fa-truck mr-2"></i>
                        Go to main page
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file = "parts/footer.jsp" %>