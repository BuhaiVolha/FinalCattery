<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <h2 class="text-center text-uppercase text-secondary mb-0">Add your kitten's photo</h2>
    <div class="row">
        <h3>Upload the photo that satisfies such requirements as ...</h3>
    </div>

    <div class="row">

        <div class="col-md-6">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form method="post" action="/imageUploader" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="upload_offer_photo"/>
                        <input type="hidden" name="offerId" value="${param.offerId}"/>

                            <div class="form-group"><input type="file" required="required" name="file" size="60" />
                            </div><br>
                            <button type="submit" class="btn btn-primary">Add photo of your kitten</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>
