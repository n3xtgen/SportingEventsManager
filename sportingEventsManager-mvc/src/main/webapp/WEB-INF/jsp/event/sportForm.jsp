<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Competition">
    <jsp:attribute name="body">

        <form:form method="get" action="${pageContext.request.contextPath}/event/createSport" modelAttribute="sportForm">

            <form:hidden path="event" />

            <s:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Name:</label>
                    <div class="col-sm-10">
                        <form:input path="name" id="name" type="text" class="form-control"
                                    placeholder="name" />
                        <form:errors path="name" />
                    </div>
                </div>
            </s:bind>


            <button type="submit" class="btn btn-default">Add</button>


        </form:form>

        </jsp:attribute>
    </my:pagetemplate>