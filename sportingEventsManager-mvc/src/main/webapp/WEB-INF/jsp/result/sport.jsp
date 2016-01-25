<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Competition results" backgroundimg="${pageContext.request.contextPath}/resources/images/events.jpg">
    <jsp:attribute name="body">
        
        <div class="event_container animated fadeIn">
            <h2 class="sport_name">${sport.name}</h2>
            
            <%-- Pokud je uzivatel admin, vypsat seznam s moznosti zmeny vysledku --%>
            <c:if test="${signedUser.admin == true}">
                <form:form method="post" action="${pageContext.request.contextPath}/result/${sport.idSport}/update" modelAttribute="sport">
                    <form:hidden path="idSport" />
                    
                    <table class="table event_sports_table">
                        <thead>
                            <tr>
                                <th width="100">Pos.</th>
                                <th width="200">Name</th>
                                <th width="200">Surname</th>
                                <th style="text-align: right">Result</th>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${sport.entries}" var="entry" varStatus="loop">
                            <tr>
                                <td>${entry.position}</td>
                                <td>${entry.usr.name}</td>
                                <td>${entry.usr.surname}</td>

                                <td>
                                    <form:hidden path="entries[${loop.index}].idEntry" />
                                    <form:radiobutton path="entries[${loop.index}].entryState" value="REGISTERED"/>
                                    Not finished
                                    /
                                    <form:radiobutton path="entries[${loop.index}].entryState" value="FINISHED"/>
                                    Finished
                                    <form:input type="text" path="entries[${loop.index}].time" />
                                    /
                                    <form:radiobutton path="entries[${loop.index}].entryState" value="DISQUALIFIED"/>
                                    Disqualified
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    
                    <button type="submit" class="btn btn-default btn-event">Save results</button>
                </form:form>
            </c:if>
            
            <%-- Pokud uzivatel neni admin, tak jen vypsat seznam --%>
            <c:if test="${signedUser.admin != true}">
                <table class="table event_sports_table">
                    <thead>
                        <tr>
                            <th width="100">Pos.</th>
                            <th width="200">Name</th>
                            <th width="200">Surname</th>
                            <th>Time</th>
                        </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${sport.entries}" var="entry" varStatus="loop">
                        <tr>
                            <td>${entry.position}</td>
                            <td>${entry.usr.name}</td>
                            <td>${entry.usr.surname}</td>
                            <fmt:formatDate value="${entry.time}" var="timeString" pattern="HH:mm:ss"/>
                            <td>${timeString}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
            
    </jsp:attribute>
</my:pagetemplate>
