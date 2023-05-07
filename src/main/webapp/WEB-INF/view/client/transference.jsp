<%@ page import="es.uma.proyectotaw.dto.UserDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Marina
  Date: 12/04/2023
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Marina Sayago
     */
%>
<%
    UserDTO userDTO = (UserDTO) request.getAttribute("user");
%>
<html>
<head>
    <title>Transferencia</title>
</head>
<body>
<h3><a href="/client?id=<%=userDTO.getId()%>">Back</a></h3>
<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>
<form:form action="/transference/save" method="post" modelAttribute="operation">
    <form:hidden path="origin"/><br/>
    Destination account: <form:select path="destination"><br/>
        <form:option value=""/>
        <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
    </form:select><br/>
    Amount: <form:input path="amount"/><br/>
    Payment: <form:select path="payment">
        <form:option value="" label=""/>
        <form:options items="${currency}"/>
    </form:select><br/>
    <form:button>Save</form:button>
</form:form>
</body>
</html>
