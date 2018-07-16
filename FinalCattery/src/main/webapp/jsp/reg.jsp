<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="parts/header.jsp" %>

<div class="container">

    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form role="form">
                <h2>Please Sign Up <small>
                    <hr class="colorgraph">
                    <div class="row">
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="form-group">
                                <input type="text" name="login" id="loginVal" required="required"
                                       class="form-control input-lg" placeholder="Login" tabindex="6">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="form-group">
                                <input type="password" name="password" id="password"
                                       required="required" class="form-control input-lg" placeholder="Password"
                                       tabindex="7">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="form-group">
                                <input type="text" name="name" id="name" required="required" class="form-control input-lg" placeholder="First Name" tabindex="1">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="form-group">
                                <input type="text" name="lastname" id="lastname" required="required"
                                       class="form-control input-lg" placeholder="Last Name" tabindex="2">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" id="email" class="form-control input-lg" required="required"
                               placeholder="Email Address" tabindex="4">
                    </div>
                    <div class="form-group">
                        <input type="text" name="phone" id="phone" class="form-control input-lg" required="required"
                               placeholder="Phone" tabindex="5">
                    </div>

                    ${errorLoginExistsMessage}
                    <hr class="colorgraph">
                    <div class="row">
                        <input type="submit" value="Registration" class="btn btn-primary btn-block btn-lg" tabindex="8">
                    </div>

                </form>
            </div>
        </div>
    </div>



<%@ include file="parts/footer.jsp" %>