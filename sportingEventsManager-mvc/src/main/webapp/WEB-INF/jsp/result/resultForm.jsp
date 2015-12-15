<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Results">
    <jsp:attribute name="body">



        <form:form method="get" action="${pageContext.request.contextPath}/result/update" modelAttribute="resultForm">

            <form:hidden path="idEntry" />
            <form:hidden path="sport"/>
            <form:hidden path="sport.idSport" />
            <form:hidden path="sport.name" />
            <form:hidden path="sportsman" />
            <form:hidden path="entryState"/>

            <s:bind path="position">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Position:</label>
                    <div class="col-sm-10">
                        <form:input path="position" id="position" type="text" class="form-control"
                                    placeholder="position" />
                        <form:errors path="position" />
                    </div>
                </div>
            </s:bind>

            <s:bind path="time">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Time:</label>
                    <div class="col-sm-10">
                        <form:input path="time" id="time" type="text" class="form-control"
                                    placeholder="HH:mm:SS" />
                        <form:errors path="time" />
                    </div>
                </div>
            </s:bind>


            <button type="submit" class="btn btn-default">Add</button>


        </form:form>
    </jsp:attribute>
</my:pagetemplate>
