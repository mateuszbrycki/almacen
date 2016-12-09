<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="create-folder-modal" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title"><spring:message code="folder.create"/></h4>
            </div>
            <form id='folder-create-form'
                  action="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_CREATE_FORM%>"
                  method='POST' class="form-horizontal">
                <div class="modal-body">
                    <input type="text" name="folder_name" placeholder="<spring:message
                                code="folder.name"/>*" required/>
                    <input type="hidden" name="physical_path" value="${parentFolder.physicalPath}">
                    <input type="hidden" name="folder_id" value="${parentFolder.id}">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close" />
                    </button>
                    <button type="submit" class="btn btn-primary"><spring:message code="button.add" /></button>
                </div>
            </form>
        </div>
    </div>
</div>