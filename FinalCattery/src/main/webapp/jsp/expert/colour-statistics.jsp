<%
    String dataPoints = request.getAttribute("colourStatistics").toString();
%>

<%@ include file="/jsp/parts/header.jsp" %>

<fmt:message bundle="${loc}" key="local.statistics.colour-preference.title" var="preferenceTitle"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.black" var="catsBodyColourBlack"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blue" var="catsBodyColourBlue"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.red" var="catsBodyColourRed"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.creme" var="catsBodyColourCreme"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.blacktortie" var="catsBodyColourBlacktortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.bluetortie" var="catsBodyColourBluetortie"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.silver" var="catsBodyColourSilver"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.white" var="catsBodyColourWhite"/>
<fmt:message bundle="${loc}" key="local.cats.colour.body.golden" var="catsBodyColourGolden"/>
<fmt:message bundle="${loc}" key="local.statistics.colour-preference.code" var="preferenceColourCode"/>
<fmt:message bundle="${loc}" key="local.statistics.colour-preference.meaning" var="preferenceColourCodeMeaning"/>

<script type="text/javascript">
    window.onload = function () {

        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2",
            animationEnabled: true,
            exportFileName: "Colour preference",
            exportEnabled: true,
            title: {
                text: "${preferenceTitle}"
            },
            data: [{
                type: "pie",
                showInLegend: true,
                legendText: "{label}",
                toolTipContent: "{label}: <strong>{y}%</strong>",
                indexLabel: "{label} {y}%",
                dataPoints: <%out.print(dataPoints);%>

            }]
        });
        chart.render();
    }
</script>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div id="chartContainer" style="height: 450px; width: 100%;"></div>
        </div>
        <div class="col-md-6" style="padding-top: 86px; padding-left: 131px;">
            <table style="width: 58%;">
                <tr>
                    <th>${preferenceColourCode}</th>
                    <th>${preferenceColourCodeMeaning}</th>
                </tr>
                <tr>
                    <td>N</td>
                    <td>${catsBodyColourBlack}</td>
                </tr>
                <tr>
                    <td>A</td>
                    <td>${catsBodyColourBlue}</td>
                </tr>
                <tr>
                    <td>D</td>
                    <td>${catsBodyColourRed}</td>
                </tr>
                <tr>
                    <td>E</td>
                    <td>${catsBodyColourCreme}</td>
                </tr>
                <tr>
                    <td>F</td>
                    <td>${catsBodyColourBlacktortie}</td>
                </tr>
                <tr>
                    <td>Q</td>
                    <td>${catsBodyColourBluetortie}</td>
                </tr>
                <tr>
                    <td>S</td>
                    <td>${catsBodyColourSilver}</td>
                </tr>
                <tr>
                    <td>W</td>
                    <td>${catsBodyColourWhite}</td>
                </tr>
                <tr>
                    <td>Y</td>
                    <td>${catsBodyColourGolden}</td>
                </tr>
            </table>
        </div>
    </div>
</div>

<%@ include file="/jsp/parts/footer.jsp" %>