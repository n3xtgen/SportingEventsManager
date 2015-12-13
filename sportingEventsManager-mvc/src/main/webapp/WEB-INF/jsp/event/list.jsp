<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Events">
    <jsp:attribute name="body">

        <form method="get" action="${pageContext.request.contextPath}/event/new">
            <button type="submit" class="add_event" >Add event</button>
        </form>

        <c:forEach var="event" items="${events}">

            <%-- area for a single event --%>
            <div class="event_container">
                    <%-- area for event info --%>
                <div class="event_info">
                    <table class="single_event_table">
                        <thead>
                        <tr>
                            <th> <h1 class="event_name">${event.name}</h1> </th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td>Starts:</td>
                            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.startTime}"/></td>
                        </tr>
                        <tr>
                            <td>Ends:</td>
                            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.endTime}"/></td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td>${event.description}</td>
                        </tr>
                        </tbody>

                    </table>
                </div>

                    <%-- area for edit, delete buttons --%>
                <div class="event_manipulation">
                    <button class="btn_event_edit" type="submit">Edit</button>
                    <form method="post" action="${pageContext.request.contextPath}/event/delete/${event.idEvent}">
                        <button class="btn_event_delete" type="submit">Delete</button>
                    </form>
                </div>
                    <%-- area for sports inside of an event --%>
                <div class="event_sports">
                    <table class="sport_list_table">
                        <thead>
                        <tr>
                            <th> <h3>Competitions: </h3> </th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="sport" items="${event.sports}">
                            <tr>
                                <td>1.</td>
                                <td>${sport.name}</td>
                                <td><s:url value="" var="" /><button class="btn_sport_signup" onclick="">Sign up</button></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </c:forEach>
   </jsp:attribute>
</my:pagetemplate>