<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>

<div class="col-xs-12 col-md-8 col-md-offset-2">
  <div class="panel">
    <div class="panel-body">
      <form id='user-register-form' action="${pageContext.request.contextPath}<%=UserUrls.USER_REGISTER_FORM%>" method='POST' class="form-horizontal">

        <div class="form-group">
          <label class="control-label col-sm-3" for="userName"><spring:message code="user.username" />:</label>
          <div>
            <input type="text" name="username" class="form-control"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-3" for="userLogin"><spring:message code="user.mail" />:</label>
          <div >
            <input type="text" name="mail" class="form-control"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-3" for="userPassword"><spring:message code="user.password" />:</label>
          <div >
            <input type="password" name="password" id="password" class="form-control"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-3" for="userPasswordRepeat"><spring:message code="user.repeatPassword" />:</label>
          <div >
            <input type="password" name="passwordRepeat" class="form-control"/>
          </div>
        </div>

        <input type="submit" id="register-user" name="submit" value="<spring:message code="user.register" />" class="btn btn-primary"/>
      </form>

    </div>
  </div>
</div>


