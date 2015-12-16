<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Login">
    <jsp:attribute name="body">

        <form:form class="form-horizontal" role="form" method="post" modelAttribute="authenticateUser"  action="${pageContext.request.contextPath}/user/login"
              >
            <div class="form-group">
                <label class="control-label col-sm-2" for="emailInput">Email:</label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" path="email" id="emailInput" placeholder="Enter email" />
                     <form:errors path="email" cssClass="help-block"/>
                </div>
            </div>
            
            
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Password:</label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" class="form-control" id="pwd" />
                     <form:errors path="password" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>
