<%@ page import="com.almacen.module.file.FileUrls" %>
<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ page import="com.almacen.module.storage.StorageUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../folder/folder_edit_form.jsp"/>
<jsp:include page="../../include/header.jsp"/>

<section id="object-content-file" class="container">
    <c:forEach items="${files}" var="file">
        <div class="file col-xs-6 col-sm-3 col-md-2">
            <div class="file-ico glyphicon glyphicon-file"></div>
            <h5 style="text-align: center">${file.name}</h5>
            <div class="button-bar">
                <button type="button"
                        href="${pageContext.request.contextPath}<%=FileUrls.Api.FILE_DOWNLOAD_FULL%>/${file.fileId}/${parentFolder.id}"
                        class="download-file btn btn-info">
                    <span class="glyphicon glyphicon-save"></span>
                </button>

                <button type="button"
                        href="${pageContext.request.contextPath}<%=FileUrls.Api.FILE_DELETE_FULL%>/${file.fileId}/${parentFolder.id}"
                        class="delete-file btn btn-danger">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </div>
        </div>
    </c:forEach>
</section>

<section id="object-content-folder" class="container clearfix">
<c:choose>
    <c:when test="${fn:length(folders) gt 0}">
        <c:forEach items="${folders}" var="folder">
            <div class="folder col-xs-6 col-sm-3 col-md-2">
                <a href="${pageContext.request.contextPath}<%=StorageUrls.Api.FOLDER_CONTENT%>/${folder.id}"
                   class="folder-ico glyphicon glyphicon-folder-close">
                </a>
                <h5 style="text-align: center">${folder.folderName}</h5>
                <h5>${parentFolder.folderName}</h5>
                <div class="button-bar">
                    <button type="button" id="folder-delete-button" href="${folder.id}"
                            class="delete-folder btn btn-danger" style="padding: 2px 2px">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>

                    <button type="button" id="folder-edit-button" href="${folder.id}"
                            class="edit-folder btn btn-default" style="padding: 2px 3px">
                        <span class="glyphicon glyphicon-edit"></span>
                    </button>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="logs-alert alert alert-info" role="alert">
            <spring:message code="folder.empty"/>
        </div>
    </c:otherwise>
</c:choose>
</section>
<jsp:include page="../../include/footer.jsp"/>