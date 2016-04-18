<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.almacen.module.file.FileUrls" %>
<section id="object-content" class="container">
    <c:forEach items="${files}" var="file">
        <div class="file col-xs-2">
            <div class="glyphicon glyphicon-file" style="display: block; text-align: center"></div>
            <h6 style="text-align: center">${file.name}
                <button type="button" id="${file.fileId}"><span class="glyphicon glyphicon-remove"></span></button></h6>
        </div>
    </c:forEach>
</section>
<div class="modal-body">
    <form method="POST" enctype="multipart/form-data" id="upload-file-form"
          action="${pageContext.request.contextPath}<%=FileUrls.FILE_UPLOAD_FULL%>/" class="form-horizontal"
            style="width: 300px;">

                <input type="file" name="file" />

            <input type="submit" id="upload-file-submit" value="<spring:message code="button.send" />"
                   class="btn btn-primary"/>
    </form>
</div>