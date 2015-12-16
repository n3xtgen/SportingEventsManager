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

    Pri komunikaci s databazi doslo k chybe.

</jsp:attribute>
</my:pagetemplate>
