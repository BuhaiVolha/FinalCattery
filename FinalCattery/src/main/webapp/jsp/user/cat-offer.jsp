<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <h2 class="text-center text-uppercase text-secondary mb-0">Offer a kitten</h2>
    <div class="row">
        <h3>Please, ${login}, fill in the form</h3>
    </div>

    <div class="row">

        <div class="col-md-6">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form name="CatOfferFrom" method="POST" action="/controller">
                        <input type="hidden" name="command" value="offer_cat"/>
                        <textarea required="required" name="catDescription" placeholder="Describe characteristics of your cat, so it were easy for our expert to make a decision" ></textarea>
                        <button type="submit" class="btn btn-primary btn-xl"  id="sendMessageButton">Send
                        </button>
                        <label>Price (in dollars)</label>
                        <input type="text" name="price" placeholder="Price you want to ask" required="required"/>
                    </form>

                </div><!-- Status Upload  -->
            </div><!-- Widget Area -->
        </div>

    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>