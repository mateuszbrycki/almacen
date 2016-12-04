<%@ page import="com.almacen.module.file.FileUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="upload-file-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close upload-file-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="add-folder-modal-title"><spring:message code="file.upload.text"/></h4>
            </div>
            <div class="modal-body">
                <form method="POST" enctype="multipart/form-data" id="upload-file-form"
                      action="${pageContext.request.contextPath}<%=FileUrls.FILE_UPLOAD_FULL%>/" class="form-horizontal"
                      style="width: 300px; margin-bottom: 0px">
                    <input type="hidden" name="folderId" value="${parentFolder.id}">
                    <input type="file" name="file" />
                    <h4>${error}</h4>
                    <input type="submit" id="upload-file-submit" value="<spring:message code="button.send" />"
                           class="btn btn-primary" style="margin-top: 10px"/>
                </form>
            </div>
        </div>
    </div>
</div>