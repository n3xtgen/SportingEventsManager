<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Events">
    <jsp:attribute name="body">

          <c:choose>
              <c:when test="${eventForm['new']}">
                  <h1>New event</h1>
              </c:when>
              <c:otherwise>
                  <h1>Update</h1>
              </c:otherwise>
          </c:choose>

        <form:form method="post" action="${pageContext.request.contextPath}${eventForm['new'] ? '/event/create' : '/event/update'}" modelAttribute="eventForm">

            <%-- Event id, we need this here, otherwise the value would be lost (update) --%>
            <form:hidden path="idEvent" />

            <%-- Event name --%>
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

            <%-- Event description --%>
            <s:bind path="description">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Description</label>
                    <div class="col-sm-10">
                        <form:input path="description" id="description" type="text" class="form-control"
                                    placeholder="description"/>
                        <form:errors path="description" />
                    </div>
                </div>
            </s:bind>

            <%-- Start time --%>
            <s:bind path="startTime">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Starts:</label>
                    <div class="col-sm-10">
                        <fmt:formatDate value="${eventForm.startTime}" type="both" var="startDateFormated" pattern="yyyy/MM/dd HH:mm" />
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
                        <fmt:formatDate value="${eventForm.endTime}" type="both" var="endDateFormated" pattern="yyyy/MM/dd HH:mm" />
                        <form:input path="endTime" value="${endDateFormated}" id="endTime" autocomplete="off" type="text" class="form-control" />
                        <form:errors path="endTime" />
                    </div>
                </div>
            </s:bind>

            <c:choose>
                <c:when test="${eventForm['new']}">
                    <button type="submit" class="btn btn-default">Add</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-default">Update</button>
                </c:otherwise>
            </c:choose>
        </form:form>

    </jsp:attribute>
</my:pagetemplate>
