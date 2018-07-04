<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap-theme.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<!-- siimple style -->
<link href="assets/css/style.css" rel="stylesheet">
<!-- Modal -->


<div class="modal fade" id="login-window1">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title">Login form</h2>
            </div>

            <!-- body (form)-->
            <div class="modal-body">
                <input type="hidden" name="command" value="Login" />
                    <div class="form-group">
                        <label class="image-replace email" for="signin-login">Login</label>
                        <input class="form-control" name="login" id="signin-login" type="text" placeholder="Login" required="required">
                    </div>

                    <div class="form-group">
                        <label class="image-replace password" for="signin-password">Password</label>
                        <input class="form-control"  name="password" id="signin-password" type="password"  placeholder="Password" required="required">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block" value="log-in" id="log-in">Log in</button>
                    </div>
                    <div id="generalErrorMessage" class="error-lbl" style="display:none;"></div>

            </div>

            <!-- footer (button) -->
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function openModal() {
        $('#login-window1').modal('show');
    }
</script>
<!-- AJAX LOGIN (POST) -->
<script type="text/javascript">
    $(document).ready(function() {

        clearError();

        $('#log-in').click(function(event) {

            event.preventDefault();
            var LOGIN = $("#signin-login").val();
            var PASSWORD = $("#signin-password").val();

            $.ajax({
                type : "POST",
                url : "controller",
                data : "LOGIN=" + LOGIN + "&PASSWORD=" + PASSWORD,
                success : function(response) {

                    if(response.status == 'FAIL') {
                        showFormError(response.errorMessageList);
                    } else {
                        //everything is O.K. user logged in successfully.
                        $('#login-window1').modal('hide');
                        window.location.href = "user/welcome.jsp";
                    }
                },
                error : function(ex) {
                    console.log(ex);
                }
            });
        });

        var PasswordField = $('#signin-password');
        var LoginField = $('#signin-login');
        var GeneralErrorField = $('#generalErrorMessage');

        function showFormError(errorVal) {
            //show error messages that comming from backend and change border to red.
            for(var i=0; i < errorVal.length; i++) {
                if(errorVal[i].fieldName === 'LOGIN') {
                    clearForm();
                    LoginField.attr("placeholder", errorVal[i].message).css("border", " 1px solid red");
                }

                else if(errorVal[i].fieldName === 'PASSWORD'){
                    PasswordField.val('');
                    PasswordField.attr("placeholder", errorVal[i].message).css("border", " 1px solid red");
                }
                else if(errorVal[i].fieldName === 'FORM FAIL'){
                    clearForm();
                    GeneralErrorField.css("display", "block").html(errorVal[i].message);
                }
            }
        }
        //remove error warning tags and change tips
        function clearError() {
            //clear all and return it as default.
            $('#login').focus(function() {
                clearForm();
                LoginField.css("border", "1px solid lightgrey");
                LoginField.attr("placeholder", "Email address");
            });
            $('#password').focus(function() {
                PasswordField.val('');
                PasswordField.css("border", "1px solid lightgrey");
                PasswordField.attr("placeholder", "Password");
            });
        }
        //clear fields and hide error tag.
        function clearForm() {
            LoginField.val('');
            PasswordField.val('');
            GeneralErrorField.css("display", "none");
        }

    });
</script>
