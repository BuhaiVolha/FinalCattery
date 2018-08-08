<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/parts/header.jsp" %>

<div class="col-lg-4 col-lg-offset-4">
    <input type="search" id="search" value="" class="form-control" placeholder="Search...">
</div>

<div class="header">
    <div class="container">
        <div class="col-md-10" id="content-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <div class="clearfix">
                    </div>
                    <div class="row">

                        <div class="col-lg-12">
                            <div class="main-box clearfix">

                                <div class="table-responsive">
                                    <table class="table user-list table-hover table-sm" id="users">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th style="width:6%">&nbsp;</th>
                                            <th style="width:15%"><span>User</span></th>
                                            <th><span>Role</span></th>
                                            <th><span>Login</span></th>
                                            <th class="text-center"><span>Status</span></th>
                                            <th><span>Email</span></th>
                                            <th><span>Phone</span></th>
                                            <th><span>Discount</span></th>

                                            <th>&nbsp;</th>
                                        </tr>
                                        </thead>
                                        <c:forEach items="${users}" var="user">
                                            <tbody>
                                            <tr>

                                                <td>
                                                    <c:choose>
                                                        <c:when test="${user.role eq 'USER'}">
                                                            <img src="/assets/img/user.png" width="50" height="36"
                                                                 alt="User image"/>

                                                        </c:when>
                                                        <c:when test="${user.role eq 'ADMIN'}">

                                                            <img src="/assets/img/admin.png" width="50" height="36"
                                                                 alt="Admin image"/>

                                                        </c:when>
                                                        <c:when test="${user.role eq 'EXPERT'}">

                                                            <img src="/assets/img/expert.png" width="50" height="36"
                                                                 alt="Expert image"/>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a href="#" class="user-link" title="ID ${user.id}"><c:out
                                                            value="${user.name}"/>
                                                        <c:out value="${user.lastname}"/></a>
                                                </td>
                                                <td>
                                                    <span class="user-subhead"><c:out value="${user.role}"/></span>
                                                </td>
                                                <td>
                                                    <c:out value="${user.login}"/>
                                                </td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${user.banned}">
                                                            <span class="label label-danger">Banned</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="label label-success">Active</span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <a href="mailto:"${user.email}><c:out value="${user.email}"/></a>
                                                </td>
                                                <td>
                                                    <c:out value="${user.phone}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${user.discount}"/> %
                                                    <c:if test="${user.role eq 'USER' && !user.banned}">
                                                    <span class="fa-stack table-link" style="color: #c6d550;">
																<i class="fa fa-square fa-stack-2x"></i>
																<i class="fas fa-coins fa-stack-1x fa-inverse"></i>
															</span>

                                                        <form role="form" title="Make Discount" method="POST"
                                                              action="/controller">
                                                            <input type="hidden" name="command" value="discount"/>
                                                            <input type="hidden" name="userId" value="${user.id}"/>
                                                            <select name="discount">
                                                                <c:if test="${user.discount ne '0'}">
                                                                    <option value=0>0%</option>
                                                                </c:if>
                                                                <option value=5>5%</option>
                                                                <option value=10>10%</option>
                                                            </select>
                                                            <input type="submit" value="Submit">
                                                        </form>
                                                    </c:if>
                                                </td>
                                                <td style="width: 50%;">


                                                    <c:if test="${user.id ne sessionScope.userId}">
                                                        <c:choose>
                                                            <c:when test="${user.banned}">

                                                                <a href="/controller?command=unban&userId=${user.id}"
                                                                   class="table-link dangergone" title="Forgive">

															<span class="fa-stack">
																<i class="fa fa-square fa-stack-2x"></i>
																<i class="fas fa-poo fa-stack-1x fa-inverse"></i>
															</span>
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>

                                                                <a href="/controller?command=ban&userId=${user.id}"
                                                                   class="table-link danger" title="BAN!!!">
															<span class="fa-stack">
																<i class="fa fa-square fa-stack-2x"></i>
																<i class="fas fa-angry fa-stack-1x fa-inverse"></i>
															</span>
                                                                </a>


                                                                <c:if test="${user.role ne 'EXPERT'}">
                                                                    <a href="/controller?command=make_expert&userId=${user.id}"
                                                                       class="table-link power"
                                                                       title="Make Expert">
															<span class="fa-stack">
																<i class="fa fa-square fa-stack-2x"></i>
																<i class="fas fa-crown fa-stack-1x fa-inverse"></i>
															</span>
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${user.role eq 'EXPERT'}">
                                                                    <a href="/controller?command=unmake_expert&userId=${user.id}"
                                                                       class="table-link nopower"
                                                                       title="Deprive of the Power">
															<span class="fa-stack">
																<i class="fa fa-square fa-stack-2x"></i>
																<i class="fa fa-bolt fa-stack-1x fa-inverse"></i>
															</span>
                                                                    </a>
                                                                </c:if>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:if>
                                                </td>
                                            </tr>

                                            </tbody>
                                        </c:forEach>
                                    </table>
                                </div>

                                <div class="col" style="float: right; margin-bottom: 38px">
                                    <c:url var="searchUri" value="/controller?command=all_users&page=##" />
                                    <paginator:display maxLinks="10"
                                                       currPage="${requestScope.page}"
                                                       totalPages="${requestScope.pageCount}"
                                                       uri="${searchUri}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<script>
    $(function () {
        $('#users').searchable({
            striped: true,
            oddRow: {'background-color': '#f5f5f5'},
            evenRow: {'background-color': '#fff'},
            searchType: 'fuzzy'
        });
    });
</script>
<%@ include file="/jsp/parts/footer.jsp" %>
