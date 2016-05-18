<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="edit-folder-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close edit-folder-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="add-folder-modal-title"><spring:message code="folder.share"/></h4>
            </div>
            <div class="modal-body">
                <form id='folder-edit-form' action="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_EDIT_FORM%>"
                      method='POST' class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="folderName"><spring:message
                                code="folder.name"/>:</label>
                        <div class="col-sm-5">
                            <input type="text" name="folder_name" class="form-control"/>
                        </div>
                        <input type="hidden" id="folder_edit_id" name="folder_id" value="">
                        <input type="submit" id="folder-name" name="submit"
                               value="<spring:message code="button.send" />"
                               class="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
