<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Events">
    <jsp:attribute name="body">

        <h1>New event</h1>

        <form:form method="post" action="${pageContext.request.contextPath}/event/create" modelAttribute="newEvent">
            <%-- Event name --%>
            <s:bind path="name">
                <div class="event_form_${status.error ? 'with_errors' : 'without_errors'}">
                    <label class="event_form_labels">Name:</label>
                    <div>
                        <form:input path="name" id="name" type="text" class="event_form_input"
                                    placeholder="name" />
                        <form:errors path="name" />
                    </div>
                </div>
            </s:bind>

            <%-- Event description --%>
            <s:bind path="description">
                <div class="event_form_${status.error ? 'with_errors' : 'without_errors'}">
                    <label class="event_form_labels">Description</label>
                    <div>
                        <form:input path="description" id="description" type="text" class="event_form_input"
                                    placeholder="description"/>
                        <form:errors path="description" />
                    </div>
                </div>
            </s:bind>

            <%-- Start time --%>
            <s:bind path="startTime">
                <div class="event_form_${status.error ? 'with_errors' : 'without_errors'}">
                    <label class="event_form_labels">Starts:</label>
                    <div>
                        <form:input path="startTime" id="startTime" type="text" class="event_form_input"
                                    placeholder="Start date and time"/>
                        <form:errors path="startTime" />
                    </div>
                </div>
            </s:bind>

            <%-- End time --%>
            <s:bind path="endTime">
                <div class="event_form_${status.error ? 'with_errors' : 'without_errors'}">
                    <label class="event_form_labels">Ends:</label>
                    <div>
                        <form:input path="endTime" id="endTime" type="text" class="event_form_input"
                                    placeholder="End date and time"/>
                        <form:errors path="endTime" />
                    </div>
                </div>
            </s:bind>

            <%-- TODO: adding sports --%>

            <button type="submit">Add</button>

        </form:form>

    </jsp:attribute>
</my:pagetemplate>
