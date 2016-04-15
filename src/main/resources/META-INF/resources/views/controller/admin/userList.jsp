<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ page import="com.almacen.module.admin.AdminUrls" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-xs-12 col-md-8 col-md-offset-2">
    <c:choose>
        <c:when test="${fn:length(userList) gt 0}">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="user.list.title"/></h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover" id="userlist-table">
                        <tr>
                            <th><spring:message code="user.list.id"/></th>
                            <th><spring:message code="user.list.login"/></th>
                            <th><spring:message code="user.list.role"/></th>
                            <th><spring:message code="user.list.roleName"/></th>
                            <th><spring:message code="user.list.mail"/></th>
                            <th><spring:message code="user.list.password"/></th>
                            <th></th>
                        </tr>
                        <div class="row">
                            <c:forEach items="${userList}" var="userlist">
                                <tr>
                                    <td>${userlist.id}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}<%=AdminUrls.ADMIN_EDIT_MANAGEMENT_FULL%>/${userlist.id}">
                                                ${userlist.username}
                                        </a>
                                    </td>
                                    <td>${userlist.role.getId()}</td>
                                    <td>${userlist.role.getRole().toLowerCase()}</td>
                                    <td>${userlist.mail}</td>
                                    <td>${userlist.password}</td>
                                </tr>
                            </c:forEach>
                        </div>
                    </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="logs-alert alert alert-info" role="alert">
                <spring:message code="logger.empty"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
