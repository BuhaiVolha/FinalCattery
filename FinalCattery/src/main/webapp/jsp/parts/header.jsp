<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/taglib/paginator.tld"%>
<jsp:useBean id="now" class="java.util.Date" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/assets/img/favicon1.ico" type="image/x-icon">

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

    <link href="/assets/css/bootstrap.css" rel="stylesheet">
    <link href="/assets/css/style.css" rel="stylesheet">
    <link href="/assets/css/bootstrap-theme.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
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

                <li>
                    <div class="dropdown" id="left-nav-dropdown">
                        <a href="#" class="main-nav" id="cat-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${goods}
                        </a>
                        <div class="dropdown-menu left-nav-style" aria-labelledby="cat-dropdown">
                            <a class="dropdown-item" href="/controller?command=all_cats">All cats</a>
                            <a class="dropdown-item" href="/controller?command=available_cats">Available cats</a>
                            <a class="dropdown-item" href="/jsp/search-cat.jsp">Search for a cat</a>
                        </div>
                    </div>
                </li>



                <li><a href="/jsp/main.jsp#aboutus">${about}</a></li>
                <li><a href="/controller?command=all_reviews">${reviews}</a></li>

                <c:choose>
                <c:when test="${sessionScope.role ne 'USER' && sessionScope.role ne 'ADMIN' && sessionScope.role ne 'EXPERT'}">

                    <li><a class="main-nav" href="#" onclick="openLoginModal()">${logIn}</a></li>

                </c:when>
                <c:otherwise>

                <li>
                    <div class="nav navbar-nav navbar-right">
                        <button class="inset" type="button" data-toggle="dropdown" id="user-dropdown" aria-haspopup="true"
                                aria-expanded="false">

                            <c:choose>
                                <c:when test="${sessionScope.role eq 'USER'}">
                                    <img src="/assets/img/user.png">

                                </c:when>
                                <c:when test="${sessionScope.role eq 'ADMIN'}">

                                    <img src="/assets/img/admin.png">

                                </c:when>
                                <c:when test="${sessionScope.role eq 'EXPERT'}">

                                    <img src="/assets/img/expert.png">
                                </c:when>
                            </c:choose>

                        </button>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="user-dropdown">
                            <a class="navbar-login">${sessionScope.login}</a>
                            <div class="dropdown-divider"></div>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/controller?command=single_user&operation=user-info">You cabinet</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/controller?command=logout">${logout}</a>
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
                    $("#signInError").css("display", "none");
                    $('#login-window').modal('show');
                }

                function openLoginModalNext() {
                    var error = '${errorLoginPassMessage}';
                    if (error) {
                        $("#signInError").css("display", "");
                        $('#login-window').modal('show');
                        <c:remove var="errorLoginPassMessage" scope="session" />
                    }
                }
            </script>

        </div>
    </div>

</div>
