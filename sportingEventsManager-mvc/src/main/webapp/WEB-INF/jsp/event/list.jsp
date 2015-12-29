<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Events" backgroundimg="${pageContext.request.contextPath}/resources/images/events.jpg">
    <jsp:attribute name="body">
        <c:if test="${signedUser.admin==true}">
            <form method="get" action="${pageContext.request.contextPath}/event/new">
                <button type="submit" class="btn btn-default btn-event" >Add event</button>
            </form>
        </c:if>
        <br/>
        
        <c:forEach var="event" items="${events}">
            <div class="event_container animated fadeIn">
                
                <c:if test="${signedUser.admin==true}">
                    <div class="event_manipulation">
                        <form method="get" action="${pageContext.request.contextPath}/event/update/${event.idEvent}">
                            <button class="btn btn-default btn-event" type="submit">Edit</button>
                        </form>
                        <form style="margin-top: 10px;" method="post" action="${pageContext.request.contextPath}/event/delete/${event.idEvent}">
                            <button class="btn btn-default btn-event" type="submit">Delete</button>
                        </form>
                    </div>
                </c:if>
                
                <h1 class="event_name">${event.name}</h1>
                
                <div class="event_info">
                    <table class="no-lines-table">
                        <tr>
                            <td><strong>Starts:</strong></td>
                            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.startTime}"/></td>
                        </tr>
                        <tr>
                            <td><strong>Ends:</strong></td>
                            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.endTime}"/></td>
                        </tr>
                        <tr>
                            <td><strong>Description:</strong></td>
                            <td>${event.description}</td>
                        </tr>
                    </table>
                </div>

                <div class="event_sports">
                    
                    <c:if test="${signedUser.admin==true}">
                        <form class="event_sports_add" method="get" action="${pageContext.request.contextPath}/event/${event.idEvent}/addSport">
                            <button type="submit" class="btn btn-default btn-event">Add competition</button>
                        </form>
                    </c:if>
                    
                    <h3>Competitions</h3>
                    
                    <table class="table event_sports_table">
                        <thead>
                            <tr>
                                <th width="40"></th>
                                <th width="200">Name</th>
                                <th></th>
                                <th width="100">Results</th>
                                <c:if test="${signedUser.admin==true}">
                                    <th width="100"></th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="sport" items="${event.sports}">
                            <tr>
                                <td>${sport.idSport}</td>
                                <td>${sport.name}</td>
                                <td>
                                    <c:choose>
                                        <%-- for signed user, show Leave button otherwise show Enter button --%>
                                        <c:when test="${sport.isSportsmanRegistred(signedUser.id)}">
                                            <form method="post" action="${pageContext.request.contextPath}/event/signOut/${sport.idSport}">
                                                <button type="submit" class="btn btn-default btn-event">Leave</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form method="post" action="${pageContext.request.contextPath}/event/signIn/${sport.idSport}">
                                                <button type="submit" class="btn btn-default btn-event">Enter</button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <div class="event_sport_control_butttons">
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}/result/${sport.idSport}">
                                        <button type="submit" class="btn btn-default btn-event">Results</button>
                                    </form>
                                </td>
                                <%-- Delete button, just for the admin role --%>
                                <c:if test="${signedUser.admin==true}">
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/event/deleteSport/${sport.idSport}/${event.idEvent}">
                                        <button type="submit" class="btn btn-default btn-event">Delete</button>
                                    </form>
                                </td>
                                </c:if>
                                </div>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        <br/>
        </c:forEach>
   </jsp:attribute>
</my:pagetemplate>