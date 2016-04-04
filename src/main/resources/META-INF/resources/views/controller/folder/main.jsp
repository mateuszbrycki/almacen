<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.almacen.module.user.UserUrls" %>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="folder.create" /></h3>
    </div>
    <div class="panel-body">
        <form id='user-username-password-form' action="${pageContext.request.contextPath}<%=UserUrls.FOLDER_CREATE_FORM%>" method='POST' class="form-horizontal">

            <div class="form-group">
                <label class="control-label col-sm-3" for="folder"><spring:message code="folder.name" />:</label>
                <div class="col-sm-5">
                    <input type="text" name="folder" class="form-control"/>
                </div>
            </div>

            <input type="submit" id="username-password-user" name="submit" value="<spring:message code="button.add" />" class="btn btn-primary"/>
        </form>
    </div>
</div>