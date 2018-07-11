<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru"
                 var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en"
                 var="en_button"/>
    <fmt:message bundle="${loc}" key="local.title" var="title"/>
    <fmt:message bundle="${loc}" key="local.nav.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="local.nav.about" var="about"/>
    <fmt:message bundle="${loc}" key="local.nav.reviews" var="reviews"/>
    <fmt:message bundle="${loc}" key="local.nav.user.reg" var="reg"/>
    <fmt:message bundle="${loc}" key="local.nav.user.logIn" var="logIn"/>
    <fmt:message bundle="${loc}" key="local.nav.user.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="local.main.message.welcome" var="welcome"/>

    <title>${title}</title>
    <!-- Bootstrap -->

    <link href="/jsp/assets/css/bootstrap.css" rel="stylesheet">
    <link href="/jsp/assets/css/style.css" rel="stylesheet">
    <link href="/jsp/assets/css/bootstrap-theme.css" rel="stylesheet">
    <!-- siimple style -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">


</head>


<body onload=openLoginModalNext()>
<!-- Fixed navbar -->
<%@ include file="/jsp/login-modal.jsp" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <div class="navbar-collapse collapse">
                <img src="/jsp/assets/img/logo.png">
            </div>
        </div>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/jsp/main.jsp">${title}</a>
        </div>
        <div class="navbar-header">

            <c:choose>
                <c:when test="${sessionScope.local == 'ru'}">
                    <a class="btn btn-theme1"
                       href="/controller?command=language&from=${pageContext.request.requestURI}&local=en">${en_button}</a>
                </c:when>
                <c:when test="${sessionScope.local == 'en'}">
                    <a class="btn btn-theme1"
                       href="/controller?command=language&from=${pageContext.request.requestURI}&local=ru">${ru_button}</a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-theme1"
                       href="/controller?command=language&from=${pageContext.request.requestURI}&local=en">${en_button}</a>
                </c:otherwise>
            </c:choose>


        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/controller?command=cats">${goods}</a></li>
                <li><a href="/jsp/main.jsp#aboutus">${about}</a></li>
                <li><a href="/controller?command=approved_reviews">${reviews}</a></li>

                <c:choose>
                <c:when test="${sessionScope.role ne 'USER' && sessionScope.role ne 'ADMIN' && sessionScope.role ne 'EXPERT'}">

                    <li><a class="main-nav" href="#" onclick="openLoginModal()">${logIn}</a></li>
                    <li><a class="main-nav" href="/jsp/logReg.jsp">1page login\reg</a></li>
                    <li class="main-nav"><a class="signin" href="#0">${reg}</a></li>
                </c:when>
                <c:otherwise>

                <li>
                    <div class="nav navbar-nav navbar-right">
                        <button class="inset" type="button" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">

                            <c:choose>
                                <c:when test="${sessionScope.role eq 'USER'}">
                                    <img src="/jsp/assets/img/user.png">

                                </c:when>
                                <c:when test="${sessionScope.role eq 'ADMIN'}">

                                    <img src="/jsp/assets/img/admin.png">

                                </c:when>
                                <c:when test="${sessionScope.role eq 'EXPERT'}">

                                    <img src="/jsp/assets/img/expert.png">
                                </c:when>
                            </c:choose>

                        </button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="navbar-login">${sessionScope.login}</a>
                            <div class="dropdown-divider"></div>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/jsp/user/user-info.jsp">You cabinet</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/controller?command=Logout">${logout}</a>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="navbar-login">${sessionScope.login}</a></li>
                </ul>
            </div>
            </c:otherwise>
            </c:choose>


            <script type="text/javascript">
                function openLoginModal() {
                    $('#login-window').modal('show');
                }

                function openLoginModalNext() {
                    var error = '${errorLoginPassMessage}';
                    if (error) {
                        $('#login-window').modal('show');
                        <c:remove var="errorLoginPassMessage" scope="session" />
                    }

                }

                $('#login-window').on('hide.bs.dropdown', function () {
                    <c:remove var="errorLoginPassMessage" scope="session" />
                });
            </script>

        </div>
    </div>
</div>
<div class="user-modal">
    <div class="user-modal-container">
        <ul class="switcher">
            <li><a href="#0">Sign in</a></li>
            <li><a href="#0">New account</a></li>
        </ul>
        <div id="login">
            <form class="form" method="POST" action="/controller">
                <input type="hidden" name="command" value="Login"/>

                <p class="fieldset">
                    <label class="image-replace email" for="signin-login">Login</label>
                    <input class="full-width has-padding has-border" name="login" id="signin-login" type="text"
                           placeholder="Login">
                    <span class="error-message">An account with this login address does not exist!</span>
                    ${errorLoginPassMessage}
                </p>

                <p class="fieldset">
                    <label class="image-replace password" for="signin-password">Password</label>
                    <input class="full-width has-padding has-border" name="password" id="signin-password"
                           type="password" placeholder="Password">
                    <a href="#0" class="hide-password">Show</a>
                    <span class="error-message">Wrong password! Try again.</span>
                </p>
                <p class="fieldset">
                    <input type="checkbox" id="remember-me" checked>
                    <label for="remember-me">Remember me</label>
                </p>
                <p class="fieldset">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-xl" value="log-in" id="sendMessageButton">Log in
                    </button>
                </div>
                </p>
                <div id="success"></div>

            </form>

            <p class="form-bottom-message"><a href="#0">Forgot your password?</a></p>
            <!-- <a href="#0" class="close-form">Close</a> -->
        </div>
        <div id="signup">
            <form class="form" method="POST" action="/controller">
                <input type="hidden" name="command" value="Registration"/>
                <p class="fieldset">
                    <label class="image-replace login" for="signup-login">Login</label>
                    <input class="full-width has-padding has-border" name="login" id="signup-login" type="text"
                           placeholder="Username">
                    <span class="error-message">Your username can only contain numeric and alphabetic symbols!</span>
                </p>
                ${errorLoginExistsMessage}
                <p class="fieldset">
                    <label class="image-replace password" for="signup-password">Password</label>
                    <input class="full-width has-padding has-border" name="password" id="signup-password"
                           type="password" placeholder="Password">
                    <a href="#0" class="hide-password">Show</a>
                    <span class="error-message">Your password has to be at least 6 characters long!</span>
                </p>
                <p class="fieldset">
                    <label class="image-replace name" for="signup-name">Name</label>
                    <input class="full-width has-padding has-border" name="name" id="signup-name" type="text"
                           placeholder="Name">
                    <span class="error-message">Your username can only contain numeric and alphabetic symbols!</span>
                </p>
                <p class="fieldset">
                    <label class="image-replace lastname" for="signup-lastname">Lastname</label>
                    <input class="full-width has-padding has-border" name="lastname" id="signup-lastname" type="text"
                           placeholder="Lastname">
                    <span class="error-message">Your username can only contain numeric and alphabetic symbols!</span>
                </p>
                <p class="fieldset">
                    <label class="image-replace email" for="signup-email">email</label>
                    <input class="full-width has-padding has-border" name="email" id="signup-email" type="text"
                           placeholder="email">
                    <span class="error-message">Wrong email!</span>
                </p>
                <p class="fieldset">
                    <label class="image-replace glyphicon-phone" for="signup-phone">phone</label>
                    <input class="full-width has-padding has-border" name="phone" id="signup-phone" type="text"
                           placeholder="phone">
                    <span class="error-message">Wrong phone!</span>
                </p>
                <p class="fieldset">

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-xl" value="sign-up" id="sendMessageButton2">Sign
                        up
                    </button>
                </div>
                <input class="full-width has-padding" type="submit" value="Create account">
                </p>
            </form>
            <!-- <a href="#0" class="cd-close-form">Close</a> -->
        </div>
        <div id="reset-password">
            <p class="form-message">Lost your password? Please enter your email address.<br> You will receive a link to
                create a new password.</p>
            <form class="form">
                <p class="fieldset">
                    <label class="image-replace email" for="reset-email">E-mail</label>
                    <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
                    <span class="error-message">An account with this email does not exist!</span>
                </p>
                <p class="fieldset">
                    <input class="full-width has-padding" type="submit" value="Reset password">
                </p>
            </form>
            <p class="form-bottom-message"><a href="#0">Back to log-in</a></p>
        </div>
        <a href="#0" class="close-form">Close</a>
    </div>
</div>
