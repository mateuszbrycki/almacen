<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ page import="com.almacen.module.admin.AdminUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="app.name"/></a>
        </div>

        <div style="float: left;">
            <ul class="nav navbar-nav">
                <li type="button" style="margin: 2px;">
                    <a href="<%=AdminUrls.ADMIN_LOGS_FULL%>">
                        <span class="glyphicon glyphicon-list-alt"></span>
                        <spring:message code="admin.logs"/>
                    </a>
                </li>
            </ul>
        </div>

        <div style="float: right;">
            <ul class="nav navbar-nav">
                <li type="button" data-dismiss="modal" style="margin: 2px;">
                    <a href="<%=AdminUrls.USER_LIST_FULL%>">
                        <span class="glyphicon glyphicon-cog"></span>
                        <spring:message code="user.edit.management"/>
                    </a>
                </li>
                <li type="button" data-dismiss="modal" style="margin: 2px;">
                    <a href="<%=UserUrls.USER_MANAGEMENT_FULL%>">
                        <span class="glyphicon glyphicon-cog"></span>
                        <spring:message code="user.account.management"/>
                    </a>
                </li>
            </ul>
            </ul>

            <a href="<%=UserUrls.USER_LOGOUT_FULL%>" class="btn btn-primary" style="margin-top: 10px;">
                <spring:message code="button.logout"/>
            </a>

        </div>
    </div>
</nav>