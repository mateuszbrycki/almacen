<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="delete-folder-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog" style="width: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close delete-folder-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="add-folder-modal-title"><spring:message code="folder.edit"/></h4>
            </div>
            <div class="modal-body">
                <form id='folder-delete-form'
                      action="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_DELETE_FULL%>"
                      method='POST' class="form-horizontal">

                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="control-label" for="folderName"><spring:message
                                    code="folder.message.info"/></label><br/><br/></div>
                        <div class="col-sm-12" style="text-align: right">
                            <input type="hidden" id="folder_delete_id" name="folder_id" value="">
                            <input type="submit" id="folder-name" name="submit"
                                   value="<spring:message code="button.yes" />"
                                   class="btn btn-primary"/>
                            <input type="button" class="btn btn-primary" data-dismiss="modal"
                                   value="<spring:message code="button.no" />"
                                   aria-hidden="true"/></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
