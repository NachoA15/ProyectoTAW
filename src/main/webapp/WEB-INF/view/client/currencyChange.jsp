<%@ page import="es.uma.proyectotaw.dto.UserDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Marina
  Date: 17/04/2023
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  UserDTO userDTO = (UserDTO) request.getAttribute("user");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3><a href="/client?id=<%=userDTO.getId()%>">Back</a></h3>
<form:form action="/currencyChange/save" method="post" modelAttribute="operation">
  <form:hidden path="client"/>
  <form:hidden path="origin"/>
  <form:hidden path="destination"/>
  Current change origin: <form:select path="currentChangeOrigin">
  <form:option value="" label=""/>
  <form:options items="${currency}"/>
</form:select><br/>
  Current change destination: <form:select path="currentChangeDestination">
  <form:option value="" label=""/>
  <form:options items="${currency}"/>
</form:select><br/>
  Amount: <form:input path="amount"/><br/>
  <form:button>Guardar</form:button>
</form:form>


</body>
</html>
