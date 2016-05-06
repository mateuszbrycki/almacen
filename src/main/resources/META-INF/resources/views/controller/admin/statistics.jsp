<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ page import="com.almacen.module.admin.AdminUrls" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel='stylesheet' href='/css/circle_widget.css'>

<div class="col-xs-12 col-md-8 col-md-offset-2">
    <c:choose>
        <c:when test="${fn:length(userList) gt 0}">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="statistic.list.title"/></h3>
                </div>
                <div class="panel-body">
                        <selection class="container">
                        <div class="row">
                            <c:forEach items="${userList}" var="userlist">
                                <div class="col-sm-6 col-md3" style="padding-bottom: 15px">
                                <div class="thumbnail">
                                    <div class="caption">
                                        <center>
                                        <h3><a href="${pageContext.request.contextPath}<%=AdminUrls.ADMIN_SPECIFIC_STATISTIC_FULL%>/${userlist.id}">
                                                ${userlist.username}
                                        </a>
                                        </h3>
                                        </center>
                                </div>
                                </div>
                                </div>
                            </c:forEach>
                        </div>
                        </selection>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="logs-alert alert alert-info" role="alert">
                <spring:message code="statistic.empty"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
