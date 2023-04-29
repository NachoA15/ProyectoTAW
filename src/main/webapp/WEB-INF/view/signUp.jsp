<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h2>Sign up</h2>
<form action="/register" method="post">
    <table>
        <tr>
            <td>Email:</td> <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>Password:</td> <td><input type="password" name="password"> </td>
        </tr>
        <tr>
            <td>Confirm password:</td> <td><input type="password" name="confirm_password"> </td>
        </tr>
    </table>

    <br/>
    <button>Register</button><br/><br/>

    If you already have an account you can <a href="/">login here</a>
</form>
</body>
</html>