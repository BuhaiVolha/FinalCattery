<%@ page import="by.epam.cattery.entity.Cat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>


<div class="container cat_reserve_box">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="well well-sm">
                <form class="form-horizontal" role="form" method="POST" action="/controller">
                    <input type="hidden" name="command" value="reserve_cat"/>
                    <input type="hidden" name="catId" value="${singleCat.id}"/>
                    <input type="hidden" id="total" name="total" />
                    <fieldset>
                        <legend class="text-center">Cat Reservation Form</legend>
                        <br><br>
                        <!-- Name input-->
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="pedigree_type">Pedigree type</label>
                            <div class="col-md-9">
                                <select class="form-control selectpicker" id="pedigree_type" name="pedigreeType"
                                        required="required" onchange="finalCost()">
                                    <option value="">Please select a pedigree type</option>
                                    <option name="0" value="Simple">Simple</option>
                                    <option name="10" value="Deluxe">Deluxe</option>
                                    <option name="20" value="Gold">Gold</option>
                                </select>
                            </div>
                        </div>

                        <!-- Message body -->

                        <div class="col-md-9">
                            You must pay in 3 day term or the reservation will be called off
                        </div>

                        <br>
                        <br>
                        <!-- Total -->
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="result">Total Cost ($): </label>
                            <div class="col-md-9">
                                <span id="result"
                                      style="background-color: #7527b0;color: #fff;padding: 6px 70px;font-weight: 600;font-size: 18px; margin-left: 10px;border-radius: 5px;">${singleCat.priceWithDiscount}</span>
                            </div>
                        </div>

                        <!-- Form actions -->
                        <div class="form-group">
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>


<script>

    function finalCost() {

        var pedigreeType =  $("#pedigree_type option:selected").attr('name');
        <c:set var="price" value="${singleCat.priceWithDiscount}"/>

        var price = "${price}";

        var total = parseFloat(price) + parseFloat(pedigreeType);

        document.getElementById("result").innerHTML = total;
        document.getElementById("total").value = total;
    }
</script>
<%@ include file="/jsp/parts/footer.jsp" %>
