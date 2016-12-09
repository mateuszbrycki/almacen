<%@ page import="com.almacen.module.file.FileUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="upload-file-modal" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title"><spring:message code="file.upload.text"/></h4>
            </div>
            <form method="POST" enctype="multipart/form-data" id="upload-file-form"
                  action="${pageContext.request.contextPath}<%=FileUrls.FILE_UPLOAD_FULL%>/">
                <div class="modal-body">
                    <input type="hidden" name="folderId" value="${parentFolder.id}">
                    <input type="file" name="file" required/>
                    <h4>${error}</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                            code="button.close"/>
                    </button>
                    <button type="submit" id="upload-file-submit" class="btn btn-primary"><spring:message
                            code="button.send"/></button>
                </div>
            </form>
        </div>
    </div>
</div>