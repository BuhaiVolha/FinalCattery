<%
    //String dataPoints = request.getSession().getAttribute("colourStatistics").toString();
    String dataPoints = request.getAttribute("colourStatistics").toString();
%>
<%@ include file="/jsp/parts/header.jsp" %>

<script type="text/javascript">
    window.onload = function () {

        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2",
            animationEnabled: true,
            exportFileName: "Colour preference",
            exportEnabled: true,
            title: {
                text: "Colour preference among users"
            },
            data: [{
                type: "pie",
                showInLegend: true,
                legendText: "{label}",
                toolTipContent: "{label}: <strong>{y}%</strong>",
                indexLabel: "{label} {y}%",
                dataPoints: <%out.print(dataPoints);%>

            }],
        });

        chart.render();

    }
</script>
<div class="container">
    <div class="row">


        <div id="chartContainer" style="height: 450px; width: 100%;"></div>
       <!-- <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script> -->

    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>