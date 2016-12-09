<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.almacen.module.user.UserUrls" %>

<header id="header" class="dashboard">
    <div class="container">
        <div class="row">
            <div class="logo">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="app.name"/></a>
            </div>
        </div>
    </div>
</header>
<section id="herobanner" class="dashboard" style="margin-bottom: 40px;">
    <div class="herobanner__header">
        <h1><spring:message code="management.header" /></h1>
    </div>
    <div class="herobanner__mask"></div>
</section>
<div class="container">
    <div class="col-xs-12">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="user.change.password"/></h3>
            </div>
            <div class="panel-body">
                <form id='user-change-password-form'
                      action="${pageContext.request.contextPath}<%=UserUrls.USER_PASSWORD_CHANGE_FORM%>" method='POST'
                      class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="password_old"><spring:message
                                code="user.oldPassword"/>:</label>

                        <div class="col-sm-5">
                            <input type="password" name="password_old" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="password"><spring:message
                                code="user.password"/>:</label>

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
                      action="${pageContext.request.contextPath}<%=UserUrls.USER_USERNAME_CHANGE_FORM%>" method='POST'
                      class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="userName"><spring:message
                                code="user.username"/>:</label>

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
                <h3 class="panel-title"><spring:message code="user.delete.account"/></h3>
            </div>
            <div class="panel-body">
                <form id='user-delete-form' action="${pageContext.request.contextPath}<%=UserUrls.USER_DELETE_FORM%>"
                      method='POST' class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="password"><spring:message
                                code="user.password"/>:</label>

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
</div>