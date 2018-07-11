<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <h2 class="text-center text-uppercase text-secondary mb-0">Decline a kitten</h2>
    <div class="row">
        <h3>Please, ${login}, fill in the form ${sessionScope.kittenId}</h3>
    </div>

    <div class="row">

        <div class="col-md-6">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form name="KittenDeclineFrom" method="POST" action="/controller">
                        <input type="hidden" name="command" value="decline_offer"/>
                        <input type="hidden" name="offerId" value="${kittenId}"/>
                        <textarea required="required" name="expertMessage" placeholder="Describe the reason for the decision" ></textarea>
                        <button type="submit" class="btn btn-primary btn-xl" value="log-in" id="sendMessageButton">Send
                        </button>

                    </form>

                </div><!-- Status Upload  -->
            </div><!-- Widget Area -->
        </div>

    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>
