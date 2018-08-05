
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
                        <br>

                        <!-- Name input-->
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="pedigree_type">Pedigree type</label>
                            <div class="col-md-9">
                                <select class="form-control selectpicker" id="pedigree_type" name="pedigreeType"
                                        required="required" onchange="finalCost()">
                                    <option name="0" value="Simple">Simple</option>
                                    <option name="10" value="Deluxe">Deluxe</option>
                                    <option name="20" value="Gold">Gold</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="http://bootstrap-ecommerce.com/main/images/banners/slide2.jpg" class="img-responsive" alt="Los Angeles">
                                    <div class="carousel-caption">
                                        <h3>Simple</h3>
                                        <p>Classic and for free!</p>
                                    </div>
                                </div>

                                <div class="item">
                                    <img src="http://bootstrap-ecommerce.com/main/images/banners/slide3.jpg" class="img-responsive" alt="Chicago">
                                    <div class="carousel-caption">
                                        <h3>Deluxe</h3>
                                        <p>Screams sophistication. 10$ only!</p>
                                    </div>
                                </div>

                                <div class="item">
                                    <img src="http://bootstrap-ecommerce.com/main/images/banners/slide1.jpg" class="img-responsive" alt="New York">
                                    <div class="carousel-caption">
                                        <h3>Gold</h3>
                                        <p>Practically magic. 20$ only!</p>
                                    </div>
                                </div>
                            </div>

                            <!-- Left and right controls -->
                            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" style="border-bottom: 0" href="#myCarousel" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                                <span class="sr-only">Next</span>
                            </a>
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
                            <div class="col-md-12 text-left">
                                <p style="color: red; font-size: 14px">* Note that you must pay in 3 day term or the reservation will be called off!</p>
                            </div>
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-primary btn-lg" onclick="finalCost()">Send</button>
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

        document.getElementById("result").innerHTML = total.toFixed(1);
        document.getElementById("total").value = total.toFixed(1);
    }
</script>
<%@ include file="/jsp/parts/footer.jsp" %>
