<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "parts/header.jsp" %>



<div class="container">
    <div class="row">
        <div class="span12">
            <div class="" id="loginModal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3>Have an Account?</h3>
                </div>
                <div class="modal-body">
                    <div class="well">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#login" data-toggle="tab">Login</a></li>
                            <li><a href="#create" data-toggle="tab">Create Account</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane active in" id="login">
                                <form class="form-horizontal" action='' method="POST">
                                    <fieldset>
                                        <div id="legend">
                                            <legend class="">Login</legend>
                                        </div>
                                        <div class="control-group">
                                            <!-- Username -->
                                            <label class="control-label"  for="username">Username</label>
                                            <div class="controls">
                                                <input type="text" id="username" name="username" placeholder="" class="input-xlarge">
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <!-- Password-->
                                            <label class="control-label" for="password">Password</label>
                                            <div class="controls">
                                                <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
                                            </div>
                                        </div>


                                        <div class="control-group">
                                            <!-- Button -->
                                            <div class="controls">
                                                <button class="btn btn-success">Login</button>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane fade" id="create">
                                <form id="tab">
                                    <label>Username</label>
                                    <input type="text" value="" class="input-xlarge">
                                    <label>First Name</label>
                                    <input type="text" value="" class="input-xlarge">
                                    <label>Last Name</label>
                                    <input type="text" value="" class="input-xlarge">
                                    <label>Email</label>
                                    <input type="text" value="" class="input-xlarge">
                                    <label>Address</label>
                                    <textarea value="Smith" rows="3" class="input-xlarge">
                        </textarea>

                                    <div>
                                        <button class="btn btn-primary">Create Account</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<div class="container">
    <h2 class="text-center text-uppercase text-secondary mb-0">Log in</h2>
    <hr class="star-dark mb-5">
    <div class="row">
        <div class="col-lg-8 mx-auto">

            <form name="LoginForm" method="POST" action="/controller">
                <input type="hidden" name="command" value="Login" />
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <label>Login</label>
                        <input type="text" name="login" value="" placeholder="login" required="required" data-validation-required-message="Please enter your name." />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls mb-0 pb-2">
                        <label>Password</label>
                        <input type="password" name="password" placeholder="6+ symbols" required="required" value="" />
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <br/>
                ${errorLoginPassMessage}
                <br/>
                ${wrongAction}
                <br/>
                ${nullPage}
                <br>
                <div id="success"></div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-xl" value="log-in" id="sendMessageButton">Log in</button>
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
