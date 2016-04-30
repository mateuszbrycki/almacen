<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ page import="com.almacen.module.folder.FolderUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../controller/file/upload_file_form.jsp"/>
<jsp:include page="../controller/folder/folder_create_form.jsp"/>

<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="app.name"/></a>
        </div>


        <div style="float: right;">
            <ul class="nav navbar-nav">
                <li type="button" data-dismiss="modal" style="margin: 2px;">
                    <button id="upload-file-button" class="btn btn-default" style="margin-top: 8px">
                        <span class="glyphicon glyphicon-file"></span>
                        <spring:message code="file.upload"/>
                    </button>
                </li>
                <div style="float: left;">
                    <ul class="nav navbar-nav">
                        <li type="button" data-dismiss="modal" style="margin: 2px;">
                            <button id="folder-create-button" class="btn btn-default" style="margin-top: 8px">
                                <span class="glyphicon glyphicon-folder-open"> </span>
                                <spring:message code="folder.create"/>
                            </button>
                        </li>
                    </ul>
                </div>
                <li type="button" data-dismiss="modal" style="margin: 2px;">
                    <a href="<%=UserUrls.USER_MANAGEMENT_FULL%>">
                        <span class="glyphicon glyphicon-cog"></span>
                        <spring:message code="user.account.management"/>
                    </a>
                </li>
            </ul>

            <a href="<%=UserUrls.USER_LOGOUT_FULL%>" class="btn btn-primary" style="margin-top: 10px;">
                <spring:message code="button.logout"/>
            </a>

        </div>
    </div>
</nav>