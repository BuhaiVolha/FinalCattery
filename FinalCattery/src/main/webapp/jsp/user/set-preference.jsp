<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <div class="jumbotron" style="background: rgba(200, 54, 54, 0.0);">
    <div class="row">
        <form method="post" name="set-preference" action="/controller">
            <input type="hidden" name="command" value="colour_preference"/>

            <div class="form-group">
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/nblack.jpg" alt="black" class="img-thumbnail img-check"><input type="radio" name="colour" id="item1" value="N" class="hidden" autocomplete="off"></label><p>Black</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/ablue.jpg" alt="blue" class="img-thumbnail img-check"><input type="radio" name="colour" id="item2" value="A" class="hidden" autocomplete="off"></label><p>Blue</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/dred.jpg" alt="red" class="img-thumbnail img-check"><input type="radio" name="colour" id="item3" value="D" class="hidden" autocomplete="off"></label><p>Red</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper" ><img src="/jsp/assets/img/pref/ecreme.jpg" alt="creme" class="img-thumbnail img-check"><input type="radio" name="colour" id="item4" value="E" class="hidden" autocomplete="off"></label><p>Creme</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/fblacktortie.png" alt="blacktortie" class="img-thumbnail img-check"><input type="radio" name="colour" id="item5" value="F" class="hidden" autocomplete="off"></label><p>Blacktortie</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/gbluetortie.jpg" alt="bluetortie" class="img-thumbnail img-check"><input type="radio" name="colour" id="item6" value="Q" class="hidden" autocomplete="off"></label><p>Bluetortie</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/ssilver.jpeg" alt="silver" class="img-thumbnail img-check"><input type="radio" name="colour" id="item7" value="S" class="hidden" autocomplete="off"></label><p>Silver</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/wwhite.jpg" alt="white" class="img-thumbnail img-check"><input type="radio" name="colour" id="item8" value="W" class="hidden" autocomplete="off"></label><p>White</p></div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12"><label class="btn btn-primary img-wrapper"><img src="/jsp/assets/img/pref/ygolden.jpg" alt="golden" class="img-thumbnail img-check"><input type="radio" name="colour" id="item9" value="Y" class="hidden" autocomplete="off"></label><p>Golden</p></div>
            </div>
            <div class="clearfix"></div>

            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-warning">Choose your favourite colour <span class="glyphicon glyphicon-plane"></span>
                    </button>
                </div>
            </div>
        </form>
    </div>
    </div>
</div>
<script>$(document).ready(function(e){

    $('.img-check').click(function(e) {
        $('.img-check').not(this).removeClass('check')
            .siblings('input').prop('checked',false);
        $(this).addClass('check')
            .siblings('input').prop('checked',true);
    });

});</script>
<%@ include file="/jsp/parts/footer.jsp" %>
