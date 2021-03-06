<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="create-folder-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close create-folder-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="add-folder-modal-title"><spring:message code="folder.create"/></h4>
            </div>
            <div class="modal-body">
                <form id='folder-create-form'
                      action="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_CREATE_FORM%>"
                      method='POST' class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="folderName"><spring:message
                                code="folder.name"/>:</label>
                        <div class="col-sm-5">
                            <input type="text" name="folder_name" class="form-control"/>
                        </div>

                        <input type="hidden" name="physical_path" value="${parentFolder.physicalPath}">
                        <input type="hidden" name="folder_id" value="${parentFolder.id}">
                        <input type="submit" id="folder-name" name="submit" value="<spring:message code="button.add" />"
                               class="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
