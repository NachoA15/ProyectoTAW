<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 23/04/2023
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Iván Delgado
     */
%>
<html>
<head>
    <title>New message</title>
</head>
<body>
<form:form method="post" action="/assistant/newMessage" modelAttribute="newMessage">
    <form:hidden path="id"/>
    <form:hidden path="writer"/>
    <form:hidden path="chat"/>
    <form:hidden path="date"/>
    Text: <br/><form:textarea path="text" cols="30" rows="10"/><br/><br/>
    <form:button>Send</form:button>
</form:form>
</body>
</html>
