<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Users">
    <jsp:attribute name="body">


        <h2>Registred sportsman</h2>

        <table>

             <tr>
                <td><strong>email:</strong></td>
                <td><c:out value="${sportsmanDetail.email}"/></td>


            </tr>
            <tr>
                <td><strong>name:</strong></td>
                <td><c:out value="${sportsmanDetail.name}"/></td>          

            </tr>
            <tr>
                <td><strong>surname:</strong></td>
                <td><c:out value="${sportsmanDetail.surname}"/></td>
            </tr>
            <tr>
                <td><strong>citizen identification number:</strong></td>
                <td><c:out value="${sportsmanDetail.citizenIdNumber}"/></td>


            </tr>
            
            

        </table>

    </jsp:attribute>
</my:pagetemplate>