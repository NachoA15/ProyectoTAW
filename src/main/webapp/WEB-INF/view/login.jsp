<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 13/03/2023
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<p>Please submit your email and password:</p>

<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>

<form action="/" method="post">
    <table>
        <tr>
            <td>Email:</td> <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>Password:</td> <td><input type="password" name="password"> </td>
        </tr>
    </table>

    <br/>
    <button>Login</button><br/><br/>

    If you don't have an account you can <a href="/signUp">sign up here</a>
    <br/>
    If you want to register company you <a href="/company/register">click here</a>
</form>
</body>
</html>
