<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.reservations.title" var="reservationsTitle"/>
<fmt:message bundle="${loc}" key="local.reservations.pedigree" var="reservationsPedigree"/>
<fmt:message bundle="${loc}" key="local.reservations.pedigree.simple" var="reservationsPedigreeSimple"/>
<fmt:message bundle="${loc}" key="local.reservations.pedigree.gold" var="reservationsPedigreeGold"/>
<fmt:message bundle="${loc}" key="local.reservations.pedigree.deluxe" var="reservationsPedigreeDeluxe"/>
<fmt:message bundle="${loc}" key="local.reservations.pedigree.simple.description" var="reservationsSimpleDescription"/>
<fmt:message bundle="${loc}" key="local.reservations.pedigree.deluxe.description" var="reservationsDeluxeDescription"/>
<fmt:message bundle="${loc}" key="local.reservations.pedigree.gold.description" var="reservationsGoldDescription"/>
<fmt:message bundle="${loc}" key="local.button.back" var="buttonBack"/>
<fmt:message bundle="${loc}" key="local.button.forward" var="buttonForward"/>
<fmt:message bundle="${loc}" key="local.reservations.total" var="reservationsTotal"/>
<fmt:message bundle="${loc}" key="local.reservations.note" var="reservationsNote"/>
<fmt:message bundle="${loc}" key="local.reservations.button.submit" var="reservationsButtonSubmit"/>

<div class="container cat_reserve_box">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="well well-sm">
                <form class="form-horizontal" role="form" method="POST" action="/controller">
                    <input type="hidden" name="command" value="reserve_cat"/>
                    <input type="hidden" name="catId" value="${requestScope.singleCat.id}"/>
                    <input type="hidden" id="total" name="total"/>
                    <fieldset>
                        <br>
                        <legend class="text-center" style="text-align: center; color: #fd680e; font-weight: bold; font-size: 32px; padding-top: 17px">
                            ${reservationsTitle}
                        </legend>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="pedigree_type">${reservationsPedigree}</label>
                            <div class="col-md-9">
                                <select class="form-control selectpicker" id="pedigree_type" name="pedigreeType"
                                        required="required" onchange="finalCost()">
                                    <option name="0" value="Simple">${reservationsPedigreeSimple}</option>
                                    <option name="10" value="Deluxe">${reservationsPedigreeDeluxe}</option>
                                    <option name="20" value="Gold">${reservationsPedigreeGold}</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div id="myCarousel" class="carousel slide" data-ride="carousel">

                            <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>

                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="/assets/img/pedigree/simple.jpg" class="img-responsive"
                                         alt="${reservationsPedigreeSimple}">
                                    <div class="carousel-caption">
                                        <h3 class="shadow">${reservationsPedigreeSimple}</h3>
                                        <p class="shadow">${reservationsSimpleDescription}</p>
                                    </div>
                                </div>

                                <div class="item">
                                    <img src="/assets/img/pedigree/deluxe.jpg" class="img-responsive"
                                         alt="${reservationsPedigreeDeluxe}">
                                    <div class="carousel-caption">
                                        <h3 class="shadow">${reservationsPedigreeDeluxe}</h3>
                                        <p class="shadow">${reservationsDeluxeDescription}</p>
                                    </div>
                                </div>

                                <div class="item">
                                    <img src="/assets/img/pedigree/gold.jpg" class="img-responsive"
                                         alt="${reservationsPedigreeGold}">
                                    <div class="carousel-caption">
                                        <h3 class="shadow">${reservationsPedigreeGold}</h3>
                                        <p class="shadow">${reservationsGoldDescription}</p>
                                    </div>
                                </div>
                            </div>

                            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                                <span class="sr-only">${buttonBack}</span>
                            </a>
                            <a class="right carousel-control" style="border-bottom: 0" href="#myCarousel"
                               data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                                <span class="sr-only">${buttonForward}</span>
                            </a>
                        </div>
                        <br>
                        <br>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="result">${reservationsTotal} ($): </label>
                            <div class="col-md-9">
                                <span id="result"
                                      style="background-color: #7527b0;color: #fff;padding: 6px 70px;font-weight: 600;font-size: 18px; margin-left: 10px;border-radius: 5px;">${requestScope.singleCat.priceWithDiscount}</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-left">
                                <p style="color: red; font-size: 14px; text-align: center">${reservationsNote}</p>
                            </div>
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        onclick="finalCost()">${reservationsButtonSubmit}</button>
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

        var pedigreeType = $("#pedigree_type option:selected").attr('name');
        <c:set var="price" value="${requestScope.singleCat.priceWithDiscount}"/>

        var price = "${price}";

        var total = parseFloat(price) + parseFloat(pedigreeType);

        document.getElementById("result").innerHTML = total.toFixed(1);
        document.getElementById("total").value = total.toFixed(1);
    }
</script>

<%@ include file="/jsp/parts/footer.jsp" %>
