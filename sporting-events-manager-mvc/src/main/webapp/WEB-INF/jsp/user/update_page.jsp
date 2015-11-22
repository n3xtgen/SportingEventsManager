<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit">
    <jsp:attribute name="body">

        <form:form class="form-horizontal" role="form" method="post" modelAttribute="sportsmanUpdate"  action="${pageContext.request.contextPath}/user/update/${sportsmanUpdate.idSportsman}"
                   >

            <div class="form-group">
                <label class="control-label col-sm-2" for="nameInput">Name:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name"  value="${sportsmanUpdate.name}"  id="nameInput"  />
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="surnameInput">Surname:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control"  path="surname"  value="${sportsmanUpdate.surname}" id="surnameInput"  />
                    <form:errors path="surname" cssClass="help-block"/>
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