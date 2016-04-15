<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.almacen.module.file.FileUrls" %>
USER IS LOGGED.
<div class="modal-body">
    <form method="POST" enctype="multipart/form-data" id="add-folder-form"
          action="${pageContext.request.contextPath}<%=FileUrls.FILE_UPLOAD_FULL%>/" class="form-horizontal"
            style="width: 300px;">

                <input type="file" name="name" />

            <input type="submit" id="add-folder-submit" value="<spring:message code="button.send" />"
                   class="btn btn-primary"/>
    </form>
</div>