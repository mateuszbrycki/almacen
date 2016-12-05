<%@ page import="com.almacen.module.user.UserUrls" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<%--	<div class="col-sm-5 col-md-6" style="top: 25%; text-align: center;" >
		<a href="<spring:eval expression="@environment.getProperty('cas.login.url')" />" class="btn btn-primary">
			<spring:message code="user.login" />
		</a>
	</div>
	<div class="col-sm-5 col-sm-offset-2 col-md-6 col-md-offset-0" style="top: 25%; text-align: center;">
		<a href="${pageContext.request.contextPath}<%=UserUrls.USER_REGISTER_FORM%>" class="btn btn-primary">
			<spring:message code="user.register" />
		</a>
	</div>--%>

<header id="header">
    <div class="container">
        <div class="row">
            <div class="logo">
                <a href="${pageContext.request.contextPath}/"><spring:message code="app.name" /></a>
            </div>
            <div class="button__bar">
                <a id="btn-login" href="<spring:eval expression="@environment.getProperty('cas.login.url')" />" class="button--login button"><spring:message code="user.login" /></a>
                <a id="btn-register" href="#" class="button--register button"><spring:message code="user.register" /></a>
            </div>
        </div>
    </div>
</header>
<section id="herobanner">
    <div class="herobanner__header">
        <h1><spring:message code="herobanner.title" /></h1>
        <div class="button--pulse button">
            <a href="#about"><i class="fa fa-chevron-down" aria-hidden="true"></i></a>
        </div>
    </div>
    <div class="herobanner__mask"></div>
</section>
<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-xs-12 column--text column">
                <h2><spring:message code="about.title" /></h2>
                <p><spring:message code="about.content-first"/></p>
                <p><spring:message code="about.content-second"/></p>
            </div>
            <div class="col-md-6 col-xs-12 column--image column">
                <img src="img/cloud.jpg"/>
            </div>
        </div>
    </div>
</section>
<section id="meeting" class="clearfix">
    <div class="meeting--mask">
        <div class="container">
            <div class="meeting__header">
                <h3><spring:message code="meeting.title" /></h3>
                <p><spring:message code="meeting.content" /></p>
            </div>
        </div>
    </div>
</section>
<section id="text">
    <div class="container">
        <h3><spring:message code="text.title" /></h3>
        <p><spring:message code="text.content" /></p>
    </div>
</section>
<section id="contact" class="section">
    <div class="container">
        <div class="row">
            <div class="column__left column col-md-4 col-xs-12">
                <div class="contact__text contact__info">
                    <h2><spring:message code="contact.title" /></h2>
                    <p><spring:message code="contact.description" /></p>
                </div>
                <div class="contact__data contact__info">
                    <h2><spring:message code="contact.form.contact-date" />:</h2>
                    <a class="contact__email contact__single" href="mailto:kontakt@almacen.pl">
                        <i class="fa fa-envelope" aria-hidden="true"></i> kontakt@almacen.pl
                    </a>
                    <a class="contact__linkedin contact__single" href="http://linkedin.com">
                        <i class="fa fa-linkedin" aria-hidden="true"></i>
                    </a>
                    <a class="contact__facebook contact__single" href="http://facebook.pl">
                        <i class="fa fa-facebook" aria-hidden="true"></i>
                    </a>
                </div>
            </div>
            <div class="column__right column col-md-8 col-xs-12">
                <div id="form">
                    <h2><spring:message code="contact.form.title" /></h2>
                    <form>
                        <input type="text" placeholder="<spring:message code="contact.form.name-surname" />*" class="input input__text">
                        <input type="email" placeholder="<spring:message code="contact.form.mail" />*" class="input input__text">
                        <textarea placeholder="<spring:message code="contact.form.message" />*" class="input input__text"></textarea>
                        <input type="reset" value="<spring:message code="button.clear"/>" class="input input__button">
                        <input type="submit" value="<spring:message code="button.send"/>" class="input input__button">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../../controller/user/modal/register.jsp" />
</body>
</html>
