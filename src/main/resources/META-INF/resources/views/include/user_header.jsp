<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../controller/file/upload_file_form.jsp"/>
<jsp:include page="../controller/folder/folder_create_form.jsp"/>
<jsp:include page="../controller/folder/folder_edit_form.jsp"/>
<jsp:include page="../controller/folder/folder_delete_form.jsp"/>
<jsp:include page="../controller/folder/folder_share_form.jsp"/>

<header id="header" class="dashboard">
    <div class="container">
        <div class="row">
            <div class="logo">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="app.name"/></a>
            </div>
            <div class="hamburger-button visible-xs">
                <a id="mobile-button" href="#"><i class="fa fa-bars" aria-hidden="true"></i></a>
            </div>
            <div class="button__bar">
                <a id="btn-edit" href="<%=UserUrls.USER_MANAGEMENT_FULL%>" class="button--edit button">
                    <spring:message code="user.account.management"/>
                    <i class="fa fa-cog"
                       aria-hidden="true"></i>
                </a>

                <button id="upload-file-button" href="#" class="button--upload button"><spring:message code="file.upload"/>
                    <i class="fa fa-cloud-upload" aria-hidden="true"></i></button>

                <button id="folder-create-button" href="#" class="button--create button"><spring:message code="folder.create"/>
                    <i class="fa fa-folder" aria-hidden="true"></i></button>

                <a id="btn-logout" href="<%=UserUrls.USER_LOGOUT_FULL%>" class="button--logout button">
                    <spring:message code="button.logout"/>
                </a>
            </div>
        </div>
    </div>
</header>