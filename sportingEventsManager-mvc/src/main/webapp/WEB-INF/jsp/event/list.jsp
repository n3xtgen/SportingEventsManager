<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/background.css" rel="stylesheet">

<my:pagetemplate title="Events">
    <jsp:attribute name="body">
<body background="${pageContext.request.contextPath}/resources/images/events.jpg">
        <c:if test="${signedUser.admin==true}">
            <form method="get" action="${pageContext.request.contextPath}/event/new">
                <button type="submit" class="add_event" >Add event</button>
            </form>
        </c:if>
        <br/>
        <c:forEach var="event" items="${events}">

            <%-- area for a single event --%>
            <div class="event_container animated fadeIn">
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

                 <c:if test="${signedUser.admin==true}">
                    <%-- area for edit, delete buttons --%>
                    <div class="event_manipulation">
                        <table>
                            <td>
                                <form method="get" action="${pageContext.request.contextPath}/event/update/${event.idEvent}">
                                    <button class="btn_event_edit" type="submit">Edit</button>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/event/delete/${event.idEvent}">
                                    <button class="btn_event_delete" type="submit">Delete</button>
                                </form>
                            </td>
                        </table>

                        <br/>

                    </div>
                </c:if>

                    <%-- area for sports inside of an event --%>
                <div class="event_sports">
                    <table class="sport_list_table">
                        <thead>
                        <tr>
                            <th> <h3>Competitions: </h3> </th>
                            <c:if test="${signedUser.admin==true}">
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}/event/${event.idEvent}/addSport">
                                        <button type="submit" class="btn_sport_add">Add competition</button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="sport" items="${event.sports}">
                                <td>${sport.idSport}</td>
                                <td>${sport.name}</td>
                                <c:if test="${signedUser.admin==false}">
                                <%-- Sign up button --%>
                                    <td>
                                        <c:choose>
                                            <c:when test="${sport.isSportsmanRegistred(signedUser.id)}">
                                                <form method="post" action="${pageContext.request.contextPath}/event/signOut/${sport.idSport}">
                                                    <button type="submit" class="btn_sport_signup">Sign out</button>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <form method="post" action="${pageContext.request.contextPath}/event/signIn/${sport.idSport}">
                                                    <button type="submit" class="btn_sport_signup">Sign in</button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:if>
                                <c:if test="${signedUser.admin==true}">
                                    <td>
                                        <form method="get" action="${pageContext.request.contextPath}/event/results/${sport.idSport}">
                                            <button type="submit" class="btn_sport_results">Results</button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        <br/>
        </c:forEach>
</body>
   </jsp:attribute>
</my:pagetemplate>