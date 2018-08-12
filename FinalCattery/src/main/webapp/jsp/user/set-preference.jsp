<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="container">
    <div class="jumbotron" style="background: rgba(200, 54, 54, 0.0);">
        <div class="container-fluid">
        <div class="row">
            <form method="post" name="set-preference" action="/controller">
                <input type="hidden" name="command" value="colour_preference"/>

                <div class="form-group">
                    <div class="row">
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/nblack.jpg" alt="black" class="img-thumbnail img-check"><input type="radio" name="colour" id="item1" value="N" class="hidden" autocomplete="off"></label><p style="text-align: center">Black</p></div>
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/ablue.jpg" alt="blue" class="img-thumbnail img-check"><input type="radio" name="colour" id="item2" value="A" class="hidden" autocomplete="off"></label><p style="text-align: center">Blue</p></div>
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/dred.jpg" alt="red" class="img-thumbnail img-check"><input type="radio" name="colour" id="item3" value="D" class="hidden" autocomplete="off"></label><p style="text-align: center"Red</p></div>
                    </div>
                    <div class="row">
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper" ><img src="/assets/img/pref/ecreme.jpg" alt="creme" class="img-thumbnail img-check"><input type="radio" name="colour" id="item4" value="E" class="hidden" autocomplete="off"></label><p style="text-align: center">Creme</p></div>
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/fblacktortie.png" alt="blacktortie" class="img-thumbnail img-check"><input type="radio" name="colour" id="item5" value="F" class="hidden" autocomplete="off"></label><p style="text-align: center">Blacktortie</p></div>
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/gbluetortie.jpg" alt="bluetortie" class="img-thumbnail img-check"><input type="radio" name="colour" id="item6" value="Q" class="hidden" autocomplete="off"></label><p style="text-align: center">Bluetortie</p></div>
                    </div>
                    <div class="row">
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/ssilver.jpeg" alt="silver" class="img-thumbnail img-check"><input type="radio" name="colour" id="item7" value="S" class="hidden" autocomplete="off"></label><p style="text-align: center">Silver</p></div>
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/wwhite.jpg" alt="white" class="img-thumbnail img-check"><input type="radio" name="colour" id="item8" value="W" class="hidden" autocomplete="off"></label><p style="text-align: center">White</p></div>
                    <div class="col-md-4"><label class="btn btn-primary img-wrapper"><img src="/assets/img/pref/ygolden.jpg" alt="golden" class="img-thumbnail img-check"><input type="radio" name="colour" id="item9" value="Y" class="hidden" autocomplete="off"></label><p style="text-align: center">Golden</p></div>
                </div>
                <div class="clearfix"></div>

                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button id="preferenceSubmit" type="submit" class="btn btn-warning" disabled>Choose your favourite colour <span class="glyphicon glyphicon-plane"></span>
                        </button>
                    </div>
                </div>
                </div>
            </form>
        </div>
        </div>
    </div>
</div>

<script>$(document).ready(function(e){
    $('.img-check').click(function(e) {
        $('.img-check').not(this).removeClass('check')
            .siblings('input').prop('checked',false);
        $(this).addClass('check')
            .siblings('input').prop('checked',true);
        $("#preferenceSubmit").prop("disabled", false);
    });
});</script>

<%@ include file="/jsp/parts/footer.jsp" %>
