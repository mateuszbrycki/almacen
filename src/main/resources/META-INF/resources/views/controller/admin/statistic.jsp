<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel='stylesheet' href='/css/progress_bar.css'>
<link rel='stylesheet' href='/css/circle.css'>
<div class="col-xs-12 col-md-8 col-md-offset-2">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="statistic.title"/></h3>
        </div>
        <div class="panel-body">
            <h5><b><spring:message code="statistic.place"/>:</b></h5><br>
            <div id="progress" class="graph">
                <div id="bar" style="width:${percentage}%">
                    <p>${wholeSizeUserFiles}/${maximumUploadSize}MB [${percentage}%]</p></div>
            </div>
            <br>
            <h5><b><spring:message code="statistic.extension"/>:</b></h5><br>
            <script>
                var percentageArray = new Array();
                var answerArray = new Array();
            </script>

            <c:forEach items="${percentageExtension}" var="percentage">
                <script>
                    percentageArray.push(${percentage});
                </script>
            </c:forEach>

            <c:forEach items="${nameExtension}" var="name">
                <script>
                    answerArray.push("${name}");
                </script>
            </c:forEach>


            <div id="live-poll-area">
                <div class="graph-container">
                    <div class="graph"></div>
                </div>
                <div class="answer-list"></div>
            </div>
        </div>

    </div>

</div>
