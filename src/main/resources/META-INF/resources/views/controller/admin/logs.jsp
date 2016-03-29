<%@ page import="com.almacen.module.admin.AdminUrls" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="table-responsive col-xs-12 col-md-12" id="logs-list">
<c:choose>
    <c:when test="${fn:length(messages) gt 0}">

        <table class="table table-hover" id="logs-table">
            <tr>
                <th><spring:message code="logger.status"/></th>
                <th><spring:message code="logger.user"/></th>
                <th><spring:message code="logger.message"/></th>
                <th><spring:message code="logger.date"/></th>
            </tr>

            <c:forEach items="${messages}" var="message">
                <tr class="logs-element-${message.status}" message-id="${message.id}">
                    <td class="logs-status-${message.status}"></td>
                    <td>
                        <a href="${pageContext.request.contextPath}<%=AdminUrls.ADMIN_LOGS_FULL%>/${message.user.id}">
                            ${message.user.username}
                        </a>
                    </td>
                    <td>${message.message}</td>
                    <td><fmt:formatDate value="${message.date}" pattern="d.MM.YYYY hh:mm:ss"/></td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="logs-alert alert alert-info" role="alert">
            <spring:message code="logger.empty"/>
        </div>
    </c:otherwise>
</c:choose>