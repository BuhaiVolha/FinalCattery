<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "parts/header.jsp" %>
<div id="header-main" class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="align-center">
                    <h1>${welcome}</h1>
                    <h2 class="subtitle">Nunc velit risus, dapibusvitae fermentum. In vitae nulla lacus. Sed sagittis tortor vel arcu sollicitudin nec tincidunt metus suscipit.Nunc velit risus, dapibus non interdum.Nunc velit risus, dapibus non interdum quis, suscipit nec dolor.</h2>

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
                        <a href="main.jsp" ><img src="/assets/img/imac1.jpg" class="img-responsive" alt="red cat"/></a>
                    </div>
                </div>
                <div class="col-xm-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="right_results" data-uk-scrollspy="{cls:'uk-animation-slide-right', repeat: false,delay:0}">
                        <h2>Hi, we are Milacoon Cattery</h2>
                        <p>and we are a group of professional cat-breeders. Our work has started from 2001 when we sold my first cat to one foreigner at extremely low price. He recommended us to continue breeding cats.</p>
                        <p><b>You can</b></p>
                        <ul>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Buy our cats</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Or offer your own</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Reserve them</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Or just look at them</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Leave feedback for FREE</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>Get a discount</li>
                            <li><i class="fa fa-paw" aria-hidden="true"></i>And much more!</li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file = "parts/footer.jsp" %>
