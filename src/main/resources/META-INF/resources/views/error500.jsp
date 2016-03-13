<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="include/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>

                <h2>
                    500 Internal server error</h2>

                <div class="error-details">
                    Sorry, an error has occured, internal server error.
                </div>
                <div class="error-actions">
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-lg"><span
                            class="glyphicon glyphicon-home"></span>
                        Take Me Home </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>