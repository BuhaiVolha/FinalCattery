<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.cat-photo.upload.title" var="uploadCatPhotoTitle"/>
<fmt:message bundle="${loc}" key="local.cat-photo.upload.requirements" var="uploadCatPhotoRequirements"/>
<fmt:message bundle="${loc}" key="local.cat-photo.upload.button.submit" var="uploadCatPhotoButtonSubmit"/>

<div class="container">
    <h2 style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px;"
        class="text-center text-uppercase text-secondary mb-0">${uploadCatPhotoTitle}</h2>
    <div class="row">
        <%--<h3 style="text-align: center;">${uploadCatPhotoRequirements}</h3>--%>
    </div>

    <div class="row" style="text-align: center; margin-right: 40px; margin-left: 40px;">
        <div class="widget-area no-padding blank">
            <div class="status-upload">
                <form method="post" action="/imageUploader" enctype="multipart/form-data">
                    <c:choose>
                        <c:when test="${not empty param.offerId}">
                            <input type="hidden" name="command" value="upload_offer_photo"/>
                            <input type="hidden" name="offerId" value="${param.offerId}"/>
                            <div class="form-group"><input type="file" required="required" name="offer" size="60"/>
                            </div>
                        </c:when>

                        <c:when test="${not empty param.catId}">
                            <input type="hidden" name="command" value="upload_cat_photo"/>
                            <input type="hidden" name="catId" value="${param.catId}"/>
                            <div class="form-group"><input type="file" required="required" name="cat" size="60"/>
                            </div>
                        </c:when>
                    </c:choose>
                    <br>
                    <button type="submit" class="btn btn-primary">${uploadCatPhotoButtonSubmit}</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>
