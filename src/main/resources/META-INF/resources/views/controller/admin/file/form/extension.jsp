<%@ page import="com.almacen.module.admin.AdminUrls" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>

<c:set var="prepared" value="${fn:split(blockedExtensions.propertyValue, ';')}" />


<script type="text/javascript">
  $(document).ready(function () {
  <c:forEach items="${prepared}" var="item">
    $('#blocked-extensions-input').tagsinput('add', '${item}');
  </c:forEach>
  });
</script>

<div class="col-xs-12 col-md-8 col-md-offset-2">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h3 class="panel-title"><spring:message code="file.extension.blocked" /></h3>
    </div>
    <div class="panel-body">
      <form id='blocked-extension-form' action="${pageContext.request.contextPath}<%=AdminUrls.Api.ADMIN_FILE_EXTENSION_FULL%>" method='POST' class="form-horizontal">

        <div class="form-group">
          <label class="control-label col-sm-3" for="blockedExtensions"><spring:message code="file.extension.blocked" />:</label>
          <div class="col-sm-5">

            <input type="text"
                   id="blocked-extensions-input"
                   name="blocked-extensions"
                   class="form-control"
            />

          </div>
        </div>

        <input type="submit" id="blocked-extensions" name="submit" value="<spring:message code="button.save" />" class="btn btn-primary"/>
      </form>

    </div>
  </div>
</div>


<div class="col-xs-12 col-md-8 col-md-offset-2">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="file.max_size.text" /></h3>
        </div>
        <div class="panel-body">
            <form id='max-size-form' action="${pageContext.request.contextPath}<%=AdminUrls.Api.ADMIN_MAXIMUM_SIZE_FILE_FULL%>" method='POST' class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-3" for="maximumUploadSizeFile"><spring:message code="file.max_size.text" />:</label>
                    <div class="col-sm-5">
                        <input type="number" min="1" max="100" step="1" value="1" name="max-size"/>
                    </div>
                </div>
                <input type="submit" id="max-size" name="submit" value="<spring:message code="button.save" />" class="btn btn-primary"/>
            </form>

        </div>
    </div>
</div>

