<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "parts/header.jsp" %>
<div id="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="align-center">
                    <h1>Welcome to Milacoon cattery!</h1>
                    <h2 class="subtitle">Nunc velit risus, dapibus non interdum quis, suscipit nec dolor. Vivamus tempor tempus mauris vitae fermentum. In vitae nulla lacus. Sed sagittis tortor vel arcu sollicitudin nec tincidunt metus suscipit.Nunc velit risus, dapibus non interdum.Nunc velit risus, dapibus non interdum quis, suscipit nec dolor.</h2>
                    <form class="form-inline signup" role="form">
                        <div class="form-group">
                            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter your email address">
                        </div>
                        <button type="submit" class="btn btn-theme">Get it now</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<section id="aboutus" class="area_padding about_area">
    <div class="container">
        <div class="about">
            <div class="section_title" data-uk-scrollspy="{cls:'uk-animation-slide-bottom', repeat: false,delay:0}">
                <h2>ABOUT US</h2>
                <p>In pulvinar neque mollis, gravida justo eu, volutpat sem. Aliquam vel leo est. Donec at odio condimentum, bibendum mauris<br/> sit amet Morbi mollis nibh et nibh elementum, eget fermentum enim ultrices</p>
            </div>
            <div class="row results_section">
                <div class="col-xm-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="left_results" data-uk-scrollspy="{cls:'uk-animation-slide-left', repeat: false,delay:0}">
                        <a href="main.jsp" ><img src="assets/img/imac1.jpg" class="img-responsive" alt=""/></a>
                    </div>
                </div>
                <div class="col-xm-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="right_results" data-uk-scrollspy="{cls:'uk-animation-slide-right', repeat: false,delay:0}">
                        <h2>Hi, we are Painter Studio</h2>
                        <p>and we are a group of professional painters. Our work has started from 2001 when we sold my first painting to one foreigner at extremely low price. He recommended us to continue painting.</p>
                        <p><b>We can</b></p>
                        <ul>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Draw high quality paintings</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Finish work in less than 2 weeks</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Make your painting available online</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Have paintings stored in Cloud</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Do daily backup for paintings</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Deliver paintings in place for FREE</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Recommend you as a loyal and professional customer</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>And much more!</li>
                        </ul>
                        <p>You can contact me anytime to make order of your paintings by selecting pricing order or by clicking button below</p>
                        <a href="#contact" class="btn btn-default">Get started now</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file = "parts/footer.jsp" %>
