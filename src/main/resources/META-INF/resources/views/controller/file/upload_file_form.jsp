<%@ page import="com.almacen.module.file.FileUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="upload-file-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close upload-file-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="add-folder-modal-title"><spring:message code="file.upload.text"/></h4>
            </div>
            <div class="modal-body">
                <form method="GET" id="add-folder-form"
                      action="${pageContext.request.contextPath}<%=FileUrls.FILE_UPLOAD_FULL%>/" class="form-horizontal">

                    <div class="form-group">
                        <div class="col-sm-5">
                            <input type="file" name="name" class="form-control"/>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default add-folder-form-close" data-dismiss="modal">
                            <spring:message code="button.close"/></button>
                        <input type="submit" id="add-folder-submit" value="<spring:message code="button.send" />"
                               class="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>