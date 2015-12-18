<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/background.css" rel="stylesheet">
<my:pagetemplate>
<jsp:attribute name="body">
<body background="${pageContext.request.contextPath}/resources/images/background.jpg">

    <div class="animated fadeInDown" style="position: absolute; top: 450px;">
        <p style=" color: white;">Sporting Events Manager</p>
        <p class="secondary" style=" color: white;">Watch the events, join to competitions and see the results!</p>
        <br/>
        <p class="third" style=" color: white;">To enter sign in or sign up.</p>
        <br/>
        <p class="third" style=" color: white;">Project for Masaryk University 2015</p>
    </div>

</body>
</jsp:attribute>
</my:pagetemplate>
