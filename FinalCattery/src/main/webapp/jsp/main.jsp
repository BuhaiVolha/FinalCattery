<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ include file = "parts/header.jsp" %>


<fmt:message bundle="${loc}" key="local.main.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="local.main.welcome.description" var="welcomeDescription"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us" var="aboutUs"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.left" var="aboutUsLeft"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.greetings" var="aboutUsGreetings"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.greetings.after" var="afterGreetings"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.you-can" var="youCan"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.option1" var="option1"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.option2" var="option2"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.option3" var="option3"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.option4" var="option4"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.option5" var="option5"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.option6" var="option6"/>
<fmt:message bundle="${loc}" key="local.main.text.about-us.option7" var="option7"/>

<div id="header-main" class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-6" style="width: 56%;">
                <div class="align-center" style="margin-left: 79px; margin-top: -35px;">
                    <h1 style="font-size: 64px; color: #f7db72">${welcome}</h1>
                    <h2 class="subtitle" style="font-size: 35px">${welcomeDescription}</h2>
                </div>
            </div>
        </div>
    </div>
</div>

<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="about">
            <div class="section_title" data-uk-scrollspy="{cls:'uk-animation-slide-bottom', repeat: false,delay:0}">
                <h2>${aboutUs}</h2>
                <p>${aboutUsLeft}</p>
            </div>
            <div class="row results_section">
                <div class="col-xm-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="left_results" data-uk-scrollspy="{cls:'uk-animation-slide-left', repeat: false,delay:0}">
                        <a href="main.jsp" ><img src="/assets/img/imac1.jpg" class="img-responsive" alt="red cat"/></a>
                    </div>
                </div>
                <div class="col-xm-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="right_results" data-uk-scrollspy="{cls:'uk-animation-slide-right', repeat: false,delay:0}">
                        <h2>${aboutUsGreetings}</h2>
                        <p>${afterGreetings}</p>
                        <p><b>${youCan}</b></p>
                        <ul>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>${option1}</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>${option2}</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>${option3}</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>${option4}</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>${option5}</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>${option6}</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>${option7}</li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file = "parts/footer.jsp" %>
