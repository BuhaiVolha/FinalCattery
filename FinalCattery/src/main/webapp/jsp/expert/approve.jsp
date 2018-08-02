<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <h2 class="text-center text-uppercase text-secondary mb-0">Approve a kitten</h2>
    <div class="row">
        <h3>Please, ${login}, fill in the form</h3>
    </div>

    <div class="row">

        <div class="col-md-6">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form name="CatApproveFrom" method="POST" action="/controller">
                        <input type="hidden" name="command" value="approve"/>
                        <input type="hidden" name="offerId" value="${offer.id}"/>
                        <input type="hidden" name="price" value="${offer.price}"/>
                        <textarea required="required" name="expertMessageToAdmin" placeholder="A note for admin" ></textarea>
                        <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">Send
                        </button>

                    </form>

                </div><!-- Status Upload  -->
            </div><!-- Widget Area -->
        </div>

    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>
