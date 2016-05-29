<%@ page import="com.almacen.module.file.FileUrls" %>
<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../folder/folder_edit_form.jsp"/>
<jsp:include page="../../include/header.jsp"/>

<section id="object-content-file" class="container">
    <c:forEach items="${files}" var="file">
        <div class="file col-xs-6 col-sm-3 col-md-2">
            <div class="file-ico glyphicon glyphicon-file"></div>
            <h5 style="text-align: center">${file.name}</h5>
            <div class="button-bar">
                <button type="button"
                        href="${pageContext.request.contextPath}<%=FileUrls.Api.FILE_DOWNLOAD_FULL%>/${file.fileId}"
                        class="download-file btn btn-info">
                    <span class="glyphicon glyphicon-save"></span>
                </button>

                <button type="button"
                        href="${pageContext.request.contextPath}<%=FileUrls.Api.FILE_DELETE_FULL%>/${file.fileId}"
                        class="delete-file btn btn-danger">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </div>
        </div>
    </c:forEach>
</section>

<!--<section id="object-content-folder" class="container">
    <c:choose>
        <c:when test="${fn:length(folders) gt 0}">
            <div class="folder col-xs-12 col-sm-6">
                <table class="table table-hover">
                    <c:forEach items="${folders}" var="folder">
                        <c:if test="${folder.folder_name ne folder.user.id}">
                        <tr>
                            <td>

                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}<%=FolderUrls.FOLDER_SHOW_FULL%>/${folder.id}">
                                        ${folder.folder_name}

                                </a>
                            </td>
                        </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="logs-alert alert alert-info" role="alert">
                <spring:message code="folder.empty"/>
            </div>
        </c:otherwise>
    </c:choose>
</section>-->

<section id="object-content-file" class="container clearfix">
<c:choose>
    <c:when test="${fn:length(folders) gt 0}">
        <c:forEach items="${folders}" var="file">
            <div class="folder col-xs-6 col-sm-3 col-md-2">
                <div class="folder-ico glyphicon glyphicon-folder-close"></div>
                <h5 style="text-align: center">${folder.folderName}</h5>
                <div class="button-bar">
                    <button type="button" id="folder-delete-button" href="${folder.id}"
                            class="delete-folder btn btn-danger" style="padding: 2px 2px">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>

                    <button type="button" id="folder-edit-button" href="${folder.id}"
                            class="edit-folder btn btn-default" style="padding: 2px 3px">
                        <span class="glyphicon glyphicon-edit"></span>
                    </button>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="logs-alert alert alert-info" role="alert">
            <spring:message code="folder.empty"/>
        </div>
    </c:otherwise>
</c:choose>
</section>
<jsp:include page="../../include/footer.jsp"/>