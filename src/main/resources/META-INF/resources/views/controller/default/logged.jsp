<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../include/header.jsp"/>

<section id="object-content" class="container">
    <c:forEach items="${files}" var="file">
        <div class="file col-xs-2">
            <div class="glyphicon glyphicon-file" style="display: block; text-align: center"></div>
            <h5 style="text-align: center">${file.name}
                <button type="button" id="${file.fileId}" class="btn btn-danger" style="padding: 2px 4px">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </h5>
        </div>
    </c:forEach>
</section>

<section id="object-content" class="container">
    <div class="table-responsive col-xs-12 col-md-12" id="logs-list">
        <table class="table table-hover" id="logs-table">
            <c:forEach items="${folders}" var="folder">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_SHOW_FULL%>/${folder.id}">
                                ${folder.folder_name}

                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
<jsp:include page="../../include/footer.jsp"/>