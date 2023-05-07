<%@ page import="es.uma.proyectotaw.dto.UserDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Marina
  Date: 10/04/2023
  Time: 15:09
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
    <title>Profile</title>
</head>
<body>
<h1>MY PROFILE</h1>
<h3><a href="/client?id=<%=userDTO.getId()%>">Back</a></h3>
    <form:form action="/saveProfile" method="post" modelAttribute="profileAux">
        <form:hidden path="idClient"/>
        Name: <form:input path="name"/><br/>
        Surname: <form:input path="surname"/><br/>
        BirthDate: <form:input path="birthDate" type="date"/><br/>
        Identification number: <form:input path="identificationNumber"/><br/>
        Phone: <form:input path="phone"/><br/>
        <form:label path="street">Address: </form:label><br/>
        Street: <form:input path="street"/><br/>
        Number: <form:input path="number"/><br/>
        City: <form:input path="city"/><br/>
        Region: <form:input path="region"/><br/>
        Zip_code: <form:input path="zip_code"/><br/>
        Country: <form:input path="country"/><br/>
        <form:button>Save</form:button>
    </form:form>

</body>
</html>
