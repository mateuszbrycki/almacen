<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.almacen.module.admin.AdminUrls" %>
<link rel='stylesheet' href='/css/progress_bar.css'>
<div class="col-xs-12 col-md-8 col-md-offset-2">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="statistic.title"/></h3>
        </div>
        <div class="panel-body">
            <h5><b><spring:message code="statistic.place"/>:</b></h5><br>
            <div id="progress" class="graph"><div id="bar" style="width:${percentage}%">
                <p>${wholeSizeUserFiles}/${maximumUploadSize}MB [${percentage}%]</p></div></div>
        </div>

    </div>

</div>
