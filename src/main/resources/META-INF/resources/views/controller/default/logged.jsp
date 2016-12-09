<%@ page import="com.almacen.module.file.FileUrls" %>
<%@ page import="com.almacen.module.storage.StorageUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../folder/folder_edit_form.jsp"/>
<jsp:include page="../folder/folder_share_form.jsp"/>
<jsp:include page="../../include/header.jsp"/>

<jsp:include page="../../include/user_header.jsp"/>
<!--<select id="language-select" onchange="changeLanguage(this.value)">
    <option value="en" data-image="<c:url value="/img/language/en.png" />"></option>
    <option value="pl_PL" data-image="<c:url value="/img/language/pl_PL.png" />"></option>
</select>-->
<section id="herobanner" class="dashboard">
    <div class="herobanner__header">
        <h1><spring:message code="dashboard.header" /></h1>
    </div>
    <div class="herobanner__mask"></div>
</section>

<section id="folders">
    <div class="container">
        <div class="folders--header header">
            <h2><spring:message code="folders.header" /></h2>
            <hr/>
        </div>
        <div class="folders-group group">
        <c:choose>
            <c:when test="${fn:length(folders) gt 0}">
                <c:forEach items="${folders}" var="folder">
                    <div class="folder col-xs-6 col-sm-3 col-md-3">
                        <a href="${pageContext.request.contextPath}<%=StorageUrls.Api.FOLDER_CONTENT%>/${folder.id}"
                           class="folder-ico glyphicon">
                            <i class="fa fa-folder-open" aria-hidden="true"></i>
                        </a>
                        <h5 style="text-align: center">${folder.folderName}</h5>

                        <div class="button-bar">

                            <button type="button" id="folder-edit-button" href="${folder.id}"
                                    class="edit-folder btn btn-default">
                                <span class="glyphicon glyphicon-edit"></span>
                            </button>

                            <button type="button" id="folder-delete-button" href="${folder.id}"
                                    class="delete-folder btn btn-danger">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>

                            <button type="button" id="folder-share-button" href="${folder.id}"
                                    class="share-folder btn btn-success">
                                <span class="glyphicon glyphicon-share"></span>
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
        </div>
    </div>
</section>

<section id="files">
    <div class="container">
        <div class="files--header header">
            <h2><spring:message code="files.header" /></h2>
            <hr/>
        </div>
        <div class="files-group group">
            <c:forEach items="${files}" var="file">
                <div class="file col-xs-6 col-sm-3 col-md-3">
                    <div class="file-ico glyphicon">
                        <i class="fa fa-file" aria-hidden="true"></i>
                    </div>
                    <h5 style="text-align: center">${file.name}</h5>

                    <div class="button-bar">
                        <button type="button"
                                href="${pageContext.request.contextPath}<%=FileUrls.Api.FILE_DOWNLOAD_FULL%>/${file.fileId}/${parentFolder.id}"
                                class="download-file btn btn-info">
                            <span class="glyphicon glyphicon-save"></span>
                        </button>

                        <button type="button"
                                href="${pageContext.request.contextPath}<%=FileUrls.Api.FILE_DELETE_FULL%>/${file.fileId}/${parentFolder.id}"
                                class="delete-file btn btn-danger">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
</body>
</html>