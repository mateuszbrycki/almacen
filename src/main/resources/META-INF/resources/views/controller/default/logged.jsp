<%@ page import="com.almacen.module.file.FileUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../include/header.jsp"/>

<section id="object-content" class="container">
    <c:forEach items="${files}" var="file">
        <div class="file col-xs-2">
            <div class="file-ico glyphicon glyphicon-file" style="display: block; text-align: center"></div>
            <h5 style="text-align: center">${file.name}
                <button type="button" href="${pageContext.request.contextPath}<%=FileUrls.Api.FILE_DELETE_FULL%>/${file.fileId}"
                        class="delete-file btn btn-danger" style="padding: 2px 4px">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </h5>
        </div>
    </c:forEach>
</section>
<jsp:include page="../../include/footer.jsp"/>