<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="User profile">
    <jsp:attribute name="body">

        <h2>
            <c:out value="${userDetail.name}"/>
            <c:out value="${userDetail.surname}"/>
            <c:if test="${userDetail.admin}"><span style="font-size: 70%;">(admin)</span></c:if>
        </h2>

        <table class="no-lines-table">
            <tr>
                <td><strong>Id:</strong></td>
                <td><c:out value="${userDetail.id}"/></td>
            </tr>
            <tr>
                <td><strong>Email:</strong></td>
                <td><a href="mailto:<c:out value='${userDetail.email}'/>"><c:out value="${userDetail.email}"/></a></td>
            </tr>
            <tr>
                <td><strong>Name:</strong></td>
                <td><c:out value="${userDetail.name}"/></td>
            </tr>
            <tr>
                <td><strong>Surname:</strong></td>
                <td><c:out value="${userDetail.surname}"/></td>
            </tr>
        </table>
        <br/>
        <c:if test="${userDetail.id == authenticatedUser.id}">
            <a href="${pageContext.request.contextPath}/user/update/${userDetail.id}"><button type="button" class="btn btn-primary btn-md">Edit details</button></a>
        </c:if>
    </jsp:attribute>
</my:pagetemplate>
