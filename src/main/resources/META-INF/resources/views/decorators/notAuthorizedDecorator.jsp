<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../include/header.jsp" />

<div class="container">

    <select id="language-select" onchange="changeLanguage(this.value)">
        <option value="en" data-image="<c:url value="/img/language/en.png" />"></option>
        <option value="pl_PL" data-image="<c:url value="/img/language/pl_PL.png" />"></option>
    </select>

    <h1><a href="${pageContext.request.contextPath}/"><spring:message code="app.name" /></a></h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <span class="sr-only"><spring:message code="error" />:</span>
                ${error}
        </div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
            <span class="sr-only"><spring:message code="success" />:</span>
                ${success}
        </div>
    </c:if>

    <sitemesh:write property='body'/>

</div>
<jsp:include page="../include/footer.jsp" />