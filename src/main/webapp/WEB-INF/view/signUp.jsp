<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h2>Sign Up:</h2>
<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>
<table>
    <form:form action="/signUpSave" method="post" modelAttribute="signUp">
        <tr>
            <td>Email: <form:input path="email"/></td>
        </tr>
        <tr>
            <td>Password: <form:input path="password" type="password"/></td>
        </tr>
        <tr>
            <td>Confirm password: <form:input path="confirmPassword" type="password"/></td>
        </tr>
        <tr>
            <td>Name: <form:input path="name"/></td>
        </tr>
        <tr>
            <td>Surname: <form:input path="surname"/></td>
        </tr>
        <tr>
            <td>Birthday: <form:input path="birthDay" type="date"/></td>
        </tr>
        <tr>
            <td>Phone: <form:input path="phone"/></td>
        </tr>
        <tr>
            <td>Street: <form:input path="street"/></td>
        </tr>
        <tr>
            <td>Number: <form:input path="number"/></td>
        </tr>
        <tr>
            <td>City: <form:input path="city"/></td>
        </tr>
        <tr>
            <td>Region: <form:input path="region"/></td>
        </tr>
        <tr>
            <td>Zip code: <form:input path="zipCode"/></td>
        </tr>
        <tr>
            <td>Country: <form:input path="country"/></td>
        </tr>
        <tr>
            <td><form:button>Submit</form:button></td>
        </tr>
    </form:form>

    <tr>
        <td>
            <br/>
            Do you already have an account? <a href="/">login here</a>
        </td>
    </tr>
</table>

</body>
</html>