
<%@ page import="es.uma.proyectotaw.entity.PersonEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PersonEntity companyUser = (PersonEntity) request.getAttribute("companyUser");
%>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<h1>Edit profile</h1>

<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>

<form action="/company/edit_user" method="post">
    <table>
        <tr>
            <td>First name:</td>
            <td>
                <input type="text" name="firstName" value="<%=companyUser.getName()%>">
            </td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td>
                <input type="text" name="surname" value="<%=companyUser.getSurname()%>">
            </td>
        </tr>
        <tr>
            <td>Birthdate:</td>
            <td>
                <input type="text" name="birthdate" value="<%=companyUser.getBirthDate()%>">
            </td>
        </tr>
        <tr>
            <td>Identification number:</td>
            <td>
                <input type="text" name="identificationNumber" value="<%=companyUser.getClientByPersonClient().getIdentificationNumber()%>">
            </td>
        </tr>
        <tr>
            <td>Street:</td>
            <td>
                <input type="text" name="street" value="<%=companyUser.getClientByPersonClient().getAddressByAddress().getStreet()%>">
            </td>
        </tr>
        <tr>
            <td>Number:</td>
            <td>
                <input type="text" name="streetNumber" value="<%=companyUser.getClientByPersonClient().getAddressByAddress().getNumber()%>">
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td>
                <input type="text" name="city" value="<%=companyUser.getClientByPersonClient().getAddressByAddress().getCity()%>">
            </td>
        </tr>
        <tr>
            <td>Region:</td>
            <td>
                <input type="text" name="region" value="<%=companyUser.getClientByPersonClient().getAddressByAddress().getRegion()%>">
            </td>
        </tr>
        <tr>
            <td>Zip code:</td>
            <td>
                <input type="text" name="zip" value="<%=companyUser.getClientByPersonClient().getAddressByAddress().getZipCode()%>">
            </td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <input type="text" name="country" value="<%=companyUser.getClientByPersonClient().getAddressByAddress().getCountry()%>">
            </td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td>
                <input type="text" name="phone" value="<%=companyUser.getClientByPersonClient().getPhone()%>">
            </td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>
                <input type="text" name="email" value="<%=companyUser.getUserByPersonUser().getEmail()%>">
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
    <button>Confirm changes</button>
</form>

</body>
</html>