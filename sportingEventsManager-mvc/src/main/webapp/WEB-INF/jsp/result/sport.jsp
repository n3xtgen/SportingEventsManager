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
                                <td><c:if test="${entry.position != 0}">${entry.position}</c:if></td>
                                <td>${entry.usr.name}</td>
                                <td>${entry.usr.surname}</td>

                                <td style="text-align: right">
                                    <form:hidden path="entries[${loop.index}].idEntry" />
                                    <form:radiobutton class="result_radio" path="entries[${loop.index}].entryState" value="REGISTERED" onchange="disableTime(this)"/>
                                    No result
                                    &nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;
                                    <form:radiobutton class="result_radio radio_finished" path="entries[${loop.index}].entryState" value="FINISHED" onchange="enableTime(this)"/>
                                    Finished:
                                    <form:input style="text-align: right; padding-right: 8px;" size="14" class="result_time" type="text" path="entries[${loop.index}].time" />
                                    &nbsp;&nbsp;/&nbsp;&nbsp;
                                    <form:radiobutton class="result_radio" path="entries[${loop.index}].entryState" value="DISQUALIFIED" onchange="disableTime(this)"/>
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
                            <th style="text-align: right">Result</th>
                        </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${sport.entries}" var="entry" varStatus="loop">
                        <tr>
                            <td><c:if test="${entry.position != 0}">${entry.position}</c:if></td>
                            <td>${entry.usr.name}</td>
                            <td>${entry.usr.surname}</td>
                            <c:choose>
                                <c:when test="${entry.entryState eq 'REGISTERED'}">
                                    <td style="text-align: right; color: grey">No result yet</td>
                                </c:when>
                                <c:when test="${entry.entryState eq 'FINISHED'}">
                                    <fmt:formatDate value="${entry.time}" var="timeString" pattern="HH:mm:ss.SSS"/>
                                    <td style="text-align: right; font-size: 110%">${timeString}</td>
                                </c:when>
                                <c:when test="${entry.entryState eq 'DISQUALIFIED'}">
                                    <td style="text-align: right; color: red;">Disqualified</td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
            
        <script type="text/javascript">
            function disableTime(obj) {
                var timeEntry = obj.parentNode.getElementsByClassName("result_time")[0];
                timeEntry.value="";
                timeEntry.disabled = true;
            }
            function enableTime(obj) {
                var timeEntry = obj.parentNode.getElementsByClassName("result_time")[0];
                timeEntry.value="00:00:00.000";
                timeEntry.disabled = false;
            }

            var finishedRadios = document.getElementsByClassName("radio_finished");
            for (var i = 0, len = finishedRadios.length; i < len; i++) {
                if (!finishedRadios[i].checked) {
                    disableTime(finishedRadios[i]);
                }
            }
        </script>
            
    </jsp:attribute>
</my:pagetemplate>
