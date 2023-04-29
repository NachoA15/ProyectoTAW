<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 15/3/23
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${empty user}">
  <jsp:forward page="/" />
</c:if>


<table style="border: 0px; width: 100% ">
  <tr>
    <td>User ID: ${pageContext.session.id}</td>
  </tr>
  <tr>
    <td>Bienvenido, ${user.email} </td>
  </tr>
  <tr>
    <td><a href="/logout">Log out</a></td>
  </tr>
</table>
<p>


</p>