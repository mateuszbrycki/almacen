<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true" %>


<div id="modal-register" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title"><spring:message code="user.register"/></h4>
            </div>
            <div class="modal-body">
                <div class="col-xs-12 col-md-8 col-md-offset-2" style="margin-top: 25px">
                    <form id='user-register-form'
                          action="${pageContext.request.contextPath}<%=UserUrls.USER_REGISTER_FORM%>"
                          method='POST' class="form-horizontal">

                        <div class="form-group">
                            <div>
                                <input type="text" name="username" placeholder="<spring:message
                                    code="user.username"/>*" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div>
                                <input type="text" name="mail" placeholder="<spring:message
                                    code="user.mail"/>*" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div>
                                <input type="password" name="password" id="password" placeholder="<spring:message
                                    code="user.password"/>*" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div>
                                <input type="password" name="passwordRepeat" placeholder="<spring:message
                                    code="user.repeatPassword"/>*" required/>
                            </div>
                        </div>

                    </form>

                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close" /></button>

                <input type="submit" id="register-user" name="submit"
                       value="<spring:message code="user.register" />" class="btn btn-primary"/></div>
        </div>
    </div>
</div>