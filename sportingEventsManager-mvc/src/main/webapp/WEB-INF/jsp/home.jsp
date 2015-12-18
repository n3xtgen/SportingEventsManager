<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Sporting Events Manager" backgroundimg="${pageContext.request.contextPath}/resources/images/background.jpg">
<jsp:attribute name="body">
    
    <div class="animated fadeInDown" style="position: absolute; top: 450px; font-weight: bold; color: white;">
        <p style="font-size: 50px;">Sporting Events Manager</p>
        <p style="font-size: 20px;">Project for Masaryk University 2015</p>
        <br/>
        <p style="font-size: 30px;">Watch the events, join to competitions and see the results!</p>
        <p style="font-size: 20px;">To enter sign in or sign up.</p>
    </div>

</jsp:attribute>
</my:pagetemplate>
