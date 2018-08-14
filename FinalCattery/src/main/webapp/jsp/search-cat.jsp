<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.cats.search" var="catsSearch"/>
<fmt:message bundle="${loc}" key="local.cats.gender" var="catsGender"/>
<fmt:message bundle="${loc}" key="local.cats.search.any" var="catsSearchAny"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body" var="catsBodyColour"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.black" var="catsBodyColourBlack"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blue" var="catsBodyColourBlue"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.red" var="catsBodyColourRed"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.creme" var="catsBodyColourCreme"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blacktortie" var="catsBodyColourBlacktortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.bluetortie" var="catsBodyColourBluetortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.silver" var="catsBodyColourSilver"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.white" var="catsBodyColourWhite"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.golden" var="catsBodyColourGolden"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes" var="catsEyesColour"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.green" var="catsEyesColourGreen"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.odd" var="catsEyesColourOdd"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.orange" var="catsEyesColourOrange"/>
<fmt:message bundle="${loc}" key="local.cats.colour.eyes.blue" var="catsEyesColourBlue"/>
<fmt:message bundle="${loc}" key="local.cats.status.reserved" var="catsReserved"/>
<fmt:message bundle="${loc}" key="local.cats.status.available" var="catsAvailable"/>
<fmt:message bundle="${loc}" key="local.cats.status.sold" var="catsSold"/>
<fmt:message bundle="${loc}" key="local.cats.search.status" var="catsStatus"/>
<fmt:message bundle="${loc}" key="local.cats.gender.female" var="catsGenderF"/>
<fmt:message bundle="${loc}" key="local.cats.gender.male" var="catsGenderM"/>
<fmt:message bundle="${loc}" key="local.cats.search.max-price" var="catsSearchMaxPrice"/>
<fmt:message bundle="${loc}" key="local.price.rule" var="priceRule"/>


<div id="searcher" class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-md-offset-3 col-lg-offset-0">
                <div class="well" style="padding: 28px;">
                    <h3 align="center">${catsSearch}</h3>
                    <form class="form-horizontal" method="GET" action="/controller">
                        <input type="hidden" name="command" value="search"/>
                        <div class="form-group">
                            <label for="gender" class="control-label">${catsGender}</label>
                            <select class="form-control" name="gender" id="gender">
                                <option value="">${catsSearchAny}</option>
                                <option value="FEMALE">${catsGenderF}</option>
                                <option value="MALE">${catsGenderM}</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="catstatus" class="control-label">${catsStatus}</label>
                            <select class="form-control" name="status" id="catstatus">
                                <option value="">${catsSearchAny}</option>
                                <option value="AVAIL">${catsAvailable}</option>
                                <option value="RSRV">${catsReserved}</option>
                                <option value="SOLD">${catsSold}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="body" class="control-label">${catsBodyColour}</label>
                            <select class="form-control" name="bodyColour" id="body">
                                <option value="">${catsSearchAny}</option>
                                <option value="A">${catsBodyColourBlue}</option>
                                <option value="D">${catsBodyColourRed}</option>
                                <option value="E">${catsBodyColourCreme}</option>
                                <option value="F">${catsBodyColourBlacktortie}</option>
                                <option value="N">${catsBodyColourBlack}</option>
                                <option value="Q">${catsBodyColourBluetortie}</option>
                                <option value="S">${catsBodyColourSilver}</option>
                                <option value="W">${catsBodyColourWhite}</option>
                                <option value="Y">${catsBodyColourGolden}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="eyes" class="control-label">${catsEyesColour}</label>
                            <select class="form-control" name="eyesColour" id="eyes">
                                <option value="">${catsSearchAny}</option>
                                <option value="F61">${catsEyesColourBlue}</option>
                                <option value="F62">${catsEyesColourOrange}</option>
                                <option value="F63">${catsEyesColourOdd}</option>
                                <option value="F64">${catsEyesColourGreen}</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="priceto" class="control-label">${catsSearchMaxPrice}</label>
                            <div class="input-group">
                                <div class="input-group-addon" id="basic-addon2">$</div>
                                <input type="text" pattern="[0-9]{2,4}"
                                       title="${priceRule}" name="price"
                                       class="form-control" id="priceto" aria-describedby="basic-addon1">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-4 text-left">
                                <button type="reset" class="btn btn-primary glyphicon glyphicon-remove">
                                </button>
                            </div>
                            <label class="col-md-4 control-label"></label>
                            <div class="col-md-4 text-right">
                                <button type="submit" class="btn btn-danger glyphicon glyphicon-search">
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/jsp/parts/footer.jsp" %>
