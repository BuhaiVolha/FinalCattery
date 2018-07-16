<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>


<div class="container">
    <h4>OUR CATS</h4>
    <div class="row">

        <!-- BEGIN PRODUCTS -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="row">
                    <c:forEach items="${cats}" var="cat">
                        <div class="col-md-3 col-sm-6 item-wr">
    		<div class="thumbnail">
      			<img src="/jsp/assets/img/user.png" alt="..." class="img-responsive">
      			<h4> <c:out value="${cat.name}"/> <c:out value="${cat.lastname}"/></h4>
      			<h5> <c:out value="${cat.age}"/> month, <c:out value="${cat.gender}"/></h5>
      			<hr class="line">
      			<div class="row">
      				<div class="col-md-6 col-sm-6">
      					<p class="price">$<c:out value="${cat.price}"/></p>
      				</div>
      				<div class="col-md-6 col-sm-6">
      				 <a href="/controller?command=single_cat&catId=${cat.id}">	<button
                             class="btn btn-info right">MORE DETAILS</button></a>
      				</div>
      			</div>
    		</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/jsp/parts/footer.jsp" %>