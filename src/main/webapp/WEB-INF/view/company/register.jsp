
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.CompanyAreaEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Martin Pur
     */
%>
<%
    List<CompanyAreaEntity> companyAreas = (List<CompanyAreaEntity>) request.getAttribute("companyAreas");
%>
<html>
<head>
    <title>Register company</title>
</head>
<body>
<h1>Register company</h1>
<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>
<form action="/company/register" method="post">
    <table>
        <tr>
            <td colspan="2">
                <h3>Company registration part</h3>
            </td>
        </tr>
        <tr>
            <td>Company name:</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>Cif:</td>
            <td>
                <input type="text" name="cif">
            </td>
        </tr>
        <tr>
            <td>Url:</td>
            <td>
                <input type="text" name="url">
            </td>
        </tr>
        <tr>
            <td>Company area:</td>
            <td>
                <select name="area">
                    <%
                        for (CompanyAreaEntity ca : companyAreas) {
                    %>
                    <option value="<%=ca.getId()%>"><%=ca.getArea()%></option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Company identification number:</td>
            <td>
                <input type="text" name="identificationNumberCompany">
            </td>
        </tr>
        <tr>
            <td>Street:</td>
            <td>
                <input type="text" name="streetCompany">
            </td>
        </tr>
        <tr>
            <td>Number:</td>
            <td>
                <input type="text" name="streetNumberCompany">
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td>
                <input type="text" name="cityCompany">
            </td>
        </tr>
        <tr>
            <td>Region:</td>
            <td>
                <input type="text" name="regionCompany">
            </td>
        </tr>
        <tr>
            <td>Zip code:</td>
            <td>
                <input type="text" name="zipCompany">
            </td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <input type="text" name="countryCompany">
            </td>
        </tr>
        <tr>
            <td>Company phone:</td>
            <td>
                <input type="text" name="phoneCompany">
            </td>
        </tr>
        <tr>
            <td>Company email:</td>
            <td>
                <input type="text" name="emailCompany">
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" name="passwordCompany">
            </td>
        </tr>
        <tr>
            <td>Confirm password:</td>
            <td>
                <input type="password" name="confirm_passwordCompany">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h3>Company partner registration part</h3>
            </td>
        </tr>
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
                <input type="date" name="birthdate" required>
            </td>
        </tr>
        <tr>
            <td>Partner identification number:</td>
            <td>
                <input type="text" name="identificationNumberPartner">
            </td>
        </tr>
        <tr>
            <td>Street:</td>
            <td>
                <input type="text" name="streetPartner">
            </td>
        </tr>
        <tr>
            <td>Number:</td>
            <td>
                <input type="text" name="streetNumberPartner">
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td>
                <input type="text" name="cityPartner">
            </td>
        </tr>
        <tr>
            <td>Region:</td>
            <td>
                <input type="text" name="regionPartner">
            </td>
        </tr>
        <tr>
            <td>Zip code:</td>
            <td>
                <input type="text" name="zipPartner">
            </td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <input type="text" name="countryPartner">
            </td>
        </tr>
        <tr>
            <td>Partner phone:</td>
            <td>
                <input type="text" name="phonePartner">
            </td>
        </tr>
        <tr>
            <td>Partner email:</td>
            <td>
                <input type="text" name="emailPartner">
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" name="passwordPartner">
            </td>
        </tr>
        <tr>
            <td>Confirm password:</td>
            <td>
                <input type="password" name="confirm_passwordPartner">
            </td>
        </tr>
    </table>

    <br/>
    <button>Register</button><br/><br/>

    If you already have an account you can <a href="/">login here</a>
</form>
</body>
</html>
