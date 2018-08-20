<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.footer.copyright" var="copyright"/>

<div id="footer">
        <div class="container text-center">
            <div class="row">
                <div class="col-sm-6 col-md-6 col-lg-6">
                    <div class="wow fadeInRight" data-wow-delay="0.1s">
                        <div class="text-right">
                            <div id="credits">
                                <p class="foot" style="color: white; margin-right: -187px; margin-top: 10px;">${copyright}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>

<script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/jquery-1.10.2.min.js"></script>
<script src="/assets/js/bootstrap-datepicker.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/canvasjs.min.js"></script>
<script src="/assets/js/jquery.searchable-1.0.0.min.js"></script>
</body>
</html>
