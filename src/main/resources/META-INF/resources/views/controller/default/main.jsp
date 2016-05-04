<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
	<div class="col-sm-5 col-md-6" style="top: 25%; text-align: center;" >
		<a href="<spring:eval expression="@environment.getProperty('cas.login.url')" />" class="btn btn-primary">
			<spring:message code="user.login" />
		</a>
	</div>
	<div class="col-sm-5 col-sm-offset-2 col-md-6 col-md-offset-0" style="top: 25%; text-align: center;">
		<a href="${pageContext.request.contextPath}<%=UserUrls.USER_REGISTER_FORM%>" class="btn btn-primary">
			<spring:message code="user.register" />
		</a>
	</div>
</body>
</html>

</body>
</html>