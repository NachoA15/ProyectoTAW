
<%@ page import="es.uma.proyectotaw.entity.CompanyEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CompanyEntity company = (CompanyEntity) request.getAttribute("company");
%>
<html>
<head>
    <title>Register user</title>
</head>
<body>
<h1>Register company user / authorised</h1>

<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>

<form action="/company/register_user" method="post">
    <table>
        <tr>
            <td>First name:</td>
            <td>
                <input type="text" name="firstName">
            </td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td>
                <input type="text" name="surname">
            </td>
        </tr>
        <tr>
            <td>Birthdate:</td>
            <td>
                <input type="text" name="birthdate" required>
            </td>
        </tr>
        <tr>
            <td>Identification number:</td>
            <td>
                <input type="text" name="identificationNumber">
            </td>
        </tr>
        <tr>
            <td>Street:</td>
            <td>
                <input type="text" name="street">
            </td>
        </tr>
        <tr>
            <td>Number:</td>
            <td>
                <input type="text" name="streetNumber">
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td>
                <input type="text" name="city">
            </td>
        </tr>
        <tr>
            <td>Region:</td>
            <td>
                <input type="text" name="region">
            </td>
        </tr>
        <tr>
            <td>Zip code:</td>
            <td>
                <input type="text" name="zip">
            </td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <input type="text" name="country">
            </td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td>
                <input type="text" name="phone">
            </td>
        </tr>
        <tr>
            <td>Role:</td>
            <td>
                <select name="role">
                    <option value="4">Company partner</option>
                    <option value="5">Company authorised</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>
                <input type="text" name="email">
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" name="password">
            </td>
        </tr>
        <tr>
            <td>Confirm password:</td>
            <td>
                <input type="password" name="confirm_password">
            </td>
        </tr>
    </table>

    <br/>
    <button>Register</button><br/><br/>
</form>

</body>
</html>
