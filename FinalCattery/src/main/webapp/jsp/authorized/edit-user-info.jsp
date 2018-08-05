<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">

    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form role="form" method="POST" action="/controller">
                <input type="hidden" name="command" value="edit_personal_info"/>
                <h2>Edit your personal information
                    <small>
                        <hr class="colorgraph">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="name" id="name" required="required"
                                           class="form-control input-lg" value="${requestScope.user.name}" tabindex="1"
                                           pattern="[a-zA-Zа-яА-Я]{2,20}"
                                           title="No less than 2 and no more than 20 letters">
                                    <span></span></div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="lastname" id="lastname" required="required"
                                           class="form-control input-lg" value="${requestScope.user.lastname}" tabindex="2"
                                           pattern="[a-zA-Zа-яА-Я]{2,20}"
                                           title="No less than 2 and no more than 20 letters">
                                    <span></span></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" id="email" class="form-control input-lg"
                                   required="required"
                                   value="${requestScope.user.email}" tabindex="4"
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                            <span></span></div>
                        <div class="form-group">
                            <input type="text" name="phone" id="phone" class="form-control input-lg" required="required"
                                   value="${requestScope.user.phone}" tabindex="5" pattern="^[0-9]{2,2}\s[0-9]{7,7}$"
                                   title="valid format:XX XXXXXXX">
                            <span></span></div>
                        <div class="form-group">
                            <input type="password" name="password" id="password"
                                   required="required" class="form-control input-lg" placeholder="Password"
                                   tabindex="7" pattern=".{7,}" title="Seven or more characters">
                            <span></span></div>

                        ${errorLoginExistsMessage}
                        ${errorEmailExistsMessage}
                        <hr class="colorgraph">
                        <div class="row">
                            <input type="submit" value="Edit" class="btn btn-primary btn-block btn-lg"
                                   tabindex="8">
                        </div>

            </form>
        </div>
    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>