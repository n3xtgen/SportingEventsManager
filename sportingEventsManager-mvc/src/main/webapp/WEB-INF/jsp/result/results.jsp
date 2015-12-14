<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Results">
    <jsp:attribute name="body">


        <form method="get" action="${pageContext.request.contextPath}/result/show_resultForm">
            <button type="submit" class="add_result" >Add result</button>
        </form>

        <div class="event_container">
            <table class="single_sport_table">
                <thead>
                <tr>
                    <th> <h2 class="sport_name">${sport.name}</h2> </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="entry" items="${entries}">
                <tr>
                    <td>${entry.position}</td>
                    <td>${entry.sportsman.name}</td>
                    <td>&emsp;${entry.sportsman.surname}</td>
                    <td>&emsp;${entry.time}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>



    </jsp:attribute>
</my:pagetemplate>
