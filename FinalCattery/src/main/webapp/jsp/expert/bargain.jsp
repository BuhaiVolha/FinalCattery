<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <h2 class="text-center text-uppercase text-secondary mb-0">Try to bargain</h2>
    <div class="row">
        <h3>Please, ${login}, fill in the form</h3>
    </div>

    <div class="row">

        <div class="col-md-6">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form name="CatOfferFrom" method="POST" action="/controller">
                        <input type="hidden" name="command" value="bargain"/>
                        <textarea required="required" name="expertMessage" placeholder="Describe reasons for changing a price" ></textarea>
                        <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">Send
                        </button>
                        <label>New price (in dollars)</label>
                        <input type="text" name="price" placeholder="New price" required="required"/>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>
