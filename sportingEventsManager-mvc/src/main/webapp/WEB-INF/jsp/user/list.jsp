<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="All users" backgroundimg="${pageContext.request.contextPath}/resources/images/events.jpg">
<jsp:attribute name="body">

  <div class="event_container animated fadeIn">
    <table class="table">
        <thead>
        <tr>
            <th width="40">Id</th>
            <th width="200">Name</th>
            <th width="200">Surname</th>
            <th>Email</th>
            <th width="60">Admin</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><a href="${pageContext.request.contextPath}/user/detail/${user.id}"><c:out value="${user.name}"/></a></td>
                <td><c:out value="${user.surname}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td style="text-align: right;"><c:if test="${user.admin}"><span class="glyphicon glyphicon-ok"></span></c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
  </div>
</jsp:attribute>
</my:pagetemplate>
