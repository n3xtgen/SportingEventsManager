<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Competition" backgroundimg="${pageContext.request.contextPath}/resources/images/events.jpg">
    <jsp:attribute name="body">

         <div class="event_container animated fadeIn" id="sports_container">
            <form:form method="post" action="${pageContext.request.contextPath}/event/createSport" modelAttribute="sportForm">
                <form:hidden path="event" />
                <form:hidden path="event.idEvent" />
                <form:hidden path="event.name" />
                <form:hidden path="event.description" />
                <fmt:formatDate value="${sportForm.event.startTime}" type="both" var="evtStartDateFormated" pattern="yyyy/MM/dd HH:mm" />
                <form:hidden path="event.startTime" value="${evtStartDateFormated}" />
                <fmt:formatDate value="${sportForm.event.endTime}" type="both" var="evtEndDateFormated" pattern="yyyy/MM/dd HH:mm" />
                <form:hidden path="event.endTime" value="${evtEndDateFormated}"/>

                <table class="no-lines-table">
                    <tr>
                        <td><strong>Event starts:</strong></td>
                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${sportForm.event.startTime}"/></td>
                    </tr>
                    <tr>
                        <td><strong>Event ends:</strong></td>
                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${sportForm.event.endTime}"/></td>
                    </tr>
                </table>

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

                <%-- Start time --%>
                <s:bind path="startTime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="control-label col-sm-2">Starts:</label>
                        <div class="col-sm-10">
                            <fmt:formatDate value="${sportForm.startTime}" type="both" var="startDateFormated" pattern="yyyy/MM/dd HH:mm" />
                            <form:input path="startTime" value="${startDateFormated}" id="startTime" autocomplete="off" type="text" class="form-control" />
                            <form:errors path="startTime" />
                        </div>
                    </div>
                </s:bind>

                <%-- End time --%>
                <s:bind path="endTime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="control-label col-sm-2">Ends:</label>
                        <div class="col-sm-10">
                            <fmt:formatDate value="${sportForm.endTime}" type="both" var="endDateFormated" pattern="yyyy/MM/dd HH:mm" />
                            <form:input path="endTime" value="${endDateFormated}" id="endTime" autocomplete="off" type="text" class="form-control" />
                            <form:errors path="endTime" />
                        </div>
                    </div>
                </s:bind>

                <button type="submit" class="btn btn-default">Add</button>

            </form:form>
        </div>

    </jsp:attribute>
</my:pagetemplate>