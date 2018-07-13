<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="by.epam.cattery.entity.User"%>
<jsp:useBean id="user" class="by.epam.cattery.entity.User"/>
<jsp:setProperty property="*" name="user"/>

<%@ include file = "../parts/header.jsp" %>

<div class="container">
    <div class="row">

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${sessionScope.userName}  ${sessionScope.userLastname}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center">
                            <c:choose>
                                <c:when test="${sessionScope.role eq 'USER'}">
                                <img alt="User pic" src="/jsp/assets/img/user.png" class="img-responsive"> </div>
                                </c:when>
                                <c:when test="${sessionScope.role eq 'EXPERT'}">
                        <img alt="Expert pic" src="/jsp/assets/img/expert.png" class="img-responsive"> </div>
                                </c:when>
                                <c:when test="${sessionScope.role eq 'ADMIN'}">
                    <img alt="Admin pic" src="/jsp/assets/img/admin.png" class="img-responsive"> </div>
                                </c:when>
                            </c:choose>

                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td>Some info:</td>
                                    <td>------</td>
                                </tr>
                                <tr>
                                    <td>Some date:</td>
                                    <td>06/23/2013</td>
                                </tr>
                                <tr>
                                    <td>Date of the order maybe?</td>
                                    <td>01/24/1988</td>
                                </tr>

                                <tr>
                                <tr>
                                    <td>Discount</td>
                                    <td>${sessionScope.discount} %</td>
                                </tr>
                                <tr>
                                    <td>Colour preference</td>
                                    <td>${sessionScope.colorPreference}</td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td><a href="mailto:"${sessionScope.email}>${sessionScope.email}</a></td>
                                </tr>
                                <tr>
                                <td>Phone Number:</td>
                                <td>+375 ${sessionScope.phone}</td>
                                </tr>
                                <tr>
                                    <td>Banned</td>
                                    <td>${sessionScope.banned}</td>
                                </tr>

                                </tbody>
                            </table>
                            <c:choose>
                                <c:when test="${sessionScope.role eq 'USER'}">
                                    <a href="/jsp/user/cat-offer.jsp" class="btn btn-primary">Offer a cat</a>
                                    <a href="/controller?command=all_offers" class="btn btn-primary">See offer status</a>
                                </c:when>
                                <c:when test="${sessionScope.role eq 'EXPERT'}">
                                    <a href="#" class="btn btn-primary">See statistics</a>
                                    <a href="/controller?command=offers_by_status&status=await&path=assessment" class="btn btn-primary">Assess cats</a>
                                </c:when>
                                <c:when test="${sessionScope.role eq 'ADMIN'}">
                                    <a href="/jsp/admin/add-cat.jsp" class="btn btn-primary">Add a cat</a>
                                    <a href="/controller?command=offers_by_status&status=aprvd&path=manage-offers" class="btn btn-primary">See offers</a>
                                    <a href="#" class="btn btn-primary">Manage users</a>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                    <span class="pull-right">
                            <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                </div>

            </div>
        </div>
    </div>
</div>

<%@ include file = "../parts/footer.jsp" %>
