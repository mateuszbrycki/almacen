<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../folder/folder_create_form.jsp"/>

<div style="float: left;">
    <ul class="nav navbar-nav">
        <li type="button" data-dismiss="modal" style="margin: 2px;">
            <button id="folder-create-button" class="btn btn-default" style="margin-top: 8px">
                <span class="glyphicon glyphicon-folder-open"> </span>
                <spring:message code="folder.create"/>
            </button>
        </li>
    </ul>
</div>

<div class="table-responsive col-xs-12 col-md-12" id="logs-list">
<c:choose>
    <c:when test="${fn:length(folders) gt 0}">
        <table class="table table-hover" id="logs-table">

            <c:forEach items="${folders}" var="folder">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_SHOW_FULL%>/${folder.id}">
                                ${folder.folder_name}
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="logs-alert alert alert-info" role="alert">
             <spring:message code="folder.empty"/>
        </div>
    </c:otherwise>
</c:choose>