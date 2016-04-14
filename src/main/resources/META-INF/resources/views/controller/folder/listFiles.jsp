<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="folder.create"/></h3>
    </div>
    <div class="panel-body">
        <form id='folder-create-form' action="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_CREATE_FORM%>"
              method='POST' class="form-horizontal">

            <div class="form-group">
                <label class="control-label col-sm-3" for="folderName"><spring:message code="folder.name"/>:</label>
                <div class="col-sm-5">
                    <input type="text" name="folder_name" class="form-control"/>
                </div>
            </div>
            <input type="hidden" name="parent_folder_id" value="${parent_folder.parent_folder_id}">
            <input type="hidden" name="physical_path" value="${parent_folder.physical_path}">
            <input type="hidden" name="folder_id" value="${parent_folder.id}">
            <input type="submit" id="folder-name" name="submit" value="<spring:message code="button.add" />"
                   class="btn btn-primary"/>
        </form>
    </div>
</div>

<div class="table-responsive col-xs-12 col-md-12" id="logs-list">
<c:choose>
    <c:when test="${fn:length(folders) gt 0}">
        <table class="table table-hover" id="logs-table">
                <%--<tr>--%>
                <%--<th>Twój dysk</th>--%>
                <%--</tr>--%>

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