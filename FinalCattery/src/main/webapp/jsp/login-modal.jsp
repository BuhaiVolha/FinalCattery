<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="login-window">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title">Login form</h2>
            </div>

            <!-- body (form)-->
            <div class="modal-body">
                <form role="form"  method="POST" action="/controller">
                    <input type="hidden" name="command" value="Login" />

                    <div class="form-group">
                        <label class="image-replace email" for="signin-login1">Login</label>
                        <input class="form-control" name="login" id="signin-login1" type="text" placeholder="Login" required="required" pattern="[0-9a-zA-Zа-яА-Я]{2,10}"
                               title="No less than 2 and no more than 10 characters such as letters and digits only">
                    </div>

                    <div class="form-group">
                        <label class="image-replace password" for="signin-password1">Password</label>
                        <input class="form-control"  name="password" id="signin-password1" type="password"  placeholder="Password" required="required" pattern=".{7,}" title="Seven or more characters">
                    </div>

                    <div id="signInError" class="error form-group">${errorLoginPassMessage}</div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block" value="log-in" id="sendMessageButton1">Log in</button>
                    </div>
                </form>
            </div>
            <!-- footer (button) -->
            <div class="modal-footer">
                <div class="text-center">
                    <a href="/jsp/reg.jsp" tabindex="5" class="forgot-password">Don't have an account?</a>
                </div>
            </div>
        </div>
    </div>
</div>
