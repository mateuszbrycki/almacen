<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.almacen.module.user.UserUrls" %>

<div class="col-xs-12 col-md-8 col-md-offset-2">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="user.change.password"/></h3>
        </div>
        <div class="panel-body">
            <form id='user-change-password-form'
                  action="${pageContext.request.contextPath}<%=UserUrls.ADMIN_PASSWORD_CHANGE_FORM%>" method='POST'
                  class="form-horizontal">
                <input type="hidden" name="userId" id="userIdPassword" value="${userId}"/>

                <div class="form-group">
                    <label class="control-label col-sm-3" for="password"><spring:message code="user.password"/>:</label>
                    <div class="col-sm-5">
                        <input type="password" name="password" id="password" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3" for="password_repeat"><spring:message
                            code="user.repeatPassword"/>:</label>
                    <div class="col-sm-5">
                        <input type="password" name="password_repeat" class="form-control"/>
                    </div>
                </div>

                <input type="submit" id="change-password-user" name="submit"
                       value="<spring:message code="button.save" />" class="btn btn-primary"/>
            </form>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="user.change.username"/></h3>
        </div>
        <div class="panel-body">

            <form id='user-username-password-form'
                  action="${pageContext.request.contextPath}<%=UserUrls.ADMIN_USERNAME_CHANGE_FORM%>" method='POST'
                  class="form-horizontal">
                <input type="hidden" name="userId" id="userIdUsername" value="${userId}"/>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="username"><spring:message code="user.username"/>:</label>
                    <div class="col-sm-5">
                        <input type="text" name="username" class="form-control"/>
                    </div>
                </div>

                <input type="submit" id="username-password-user" name="submit"
                       value="<spring:message code="button.save" />" class="btn btn-primary"/>
            </form>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="user.change.role"/></h3>
        </div>
        <div class="panel-body">
            <form id='user-changerole-form'
                  action="${pageContext.request.contextPath}<%=UserUrls.ADMIN_CHANGE_ROLE_FORM%>" method='POST'
                  class="form-horizontal">
                <input type="hidden" name="userId" id="userIdRole" value="${userId}"/>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="changeRole"><spring:message code="user.role"/>:</label>
                    <div class="col-sm-5">
                        <select class="form-control" name="roleId">
                            <option value="1"><spring:message code="user.change.role3"/></option>
                            <option value="2"><spring:message code="user.change.role1"/></option>
                            <option value="3"><spring:message code="user.change.role2"/></option>
                            <option value="4"><spring:message code="user.change.role4"/></option>
                        </select>
                    </div>
                </div>

                <input type="submit" id="username-changerole-user" name="submit"
                       value="<spring:message code="button.save" />" class="btn btn-primary"/>
            </form>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="user.delete.account"/></h3>
        </div>
        <div class="panel-body">
            <form id='user-delete-form' action="${pageContext.request.contextPath}<%=UserUrls.ADMIN_DELETE_FORM%>"
                  method='POST' class="form-horizontal">
                <input type="hidden" name="userId" id="userIdDelete" value="${userId}"/>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="password"><spring:message code="user.password"/>:</label>
                    <div class="col-sm-5">
                        <input type="password" name="password" class="form-control"/>
                    </div>
                </div>

                <input type="submit" id="user-delete" name="submit" value="<spring:message code="button.delete" />"
                       class=" btn btn-danger"/>
            </form>
        </div>
    </div>
</div>