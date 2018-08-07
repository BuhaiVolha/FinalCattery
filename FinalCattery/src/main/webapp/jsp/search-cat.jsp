
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/parts/header.jsp" %>

<div id="searcher" class="header">
<div class="container">
    <div class="row">
        <div class="col-lg-3 col-md-6 col-md-offset-3 col-lg-offset-0">
            <div class="well" style="padding: 28px;">
                <h3 align="center">Search Filter</h3>
                <form class="form-horizontal" method="POST" action="/controller">
                    <input type="hidden" name="command" value="search"/>
                    <div class="form-group">
                        <label for="gender" class="control-label">Gender</label>
                        <select class="form-control" name="gender" id="gender">
                            <option value="">Any</option>
                            <option value="FEMALE">Female</option>
                            <option value="MALE">Male</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="catstatus" class="control-label">Status</label>
                        <select class="form-control" name="status" id="catstatus">
                            <option value="">Any</option>
                            <option value="AVAIL">Available</option>
                            <option value="RSRV">Reserved</option>
                            <option value="SOLD">Sold</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="body" class="control-label">Body colour</label>
                        <select class="form-control" name="body" id="body">
                            <option value="">Any</option>
                            <option value="A">Blue</option>
                            <option value="D">Red</option>
                            <option value="E">Creme</option>
                            <option value="F">Blacktortie</option>
                            <option value="N">Black</option>
                            <option value="Q">Bluetortie</option>
                            <option value="S">Silver</option>
                            <option value="W">White</option>
                            <option value="Y">Golden</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="eyes" class="control-label">Eyes colour</label>
                        <select class="form-control" name="eyes" id="eyes">
                            <option value="">Any</option>
                            <option value="F61">Blue</option>
                            <option value="F62">Orange</option>
                            <option value="F63">Odd</option>
                            <option value="F64">Green</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="priceto" class="control-label">Max Price</label>
                        <div class="input-group">
                            <div class="input-group-addon" id="basic-addon2">$</div>
                            <input type="text"  pattern="[0-9]{3,4}" name="price" class="form-control" id="priceto" aria-describedby="basic-addon1">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4 text-center">
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
