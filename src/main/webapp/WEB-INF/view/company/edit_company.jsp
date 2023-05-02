
<%@ page import="java.util.List" %>

<%@ page import="es.uma.proyectotaw.entity.CompanyAreaEntity" %>
<%@ page import="es.uma.proyectotaw.entity.CompanyEntity" %>
<%@ page import="es.uma.proyectotaw.entity.ClientEntity" %>
<%@ page import="es.uma.proyectotaw.entity.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CompanyAreaEntity> companyAreas = (List<CompanyAreaEntity>) request.getAttribute("companyAreas");
    CompanyEntity company = (CompanyEntity) request.getAttribute("company");
    ClientEntity clientCompany = (ClientEntity) request.getAttribute("clientCompany");
    UserEntity userCompany = (UserEntity) request.getAttribute("userCompany");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit company</h1>

<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>

<form action="/company/edit_company" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td>
                <input type="text" name="name" value="<%=company.getName()%>">
            </td>
        </tr>
        <tr>
            <td>Cif:</td>
            <td>
                <input type="text" name="cif" value="<%=company.getCif()%>">
            </td>
        </tr>
        <tr>
            <td>Url:</td>
            <td>
                <input type="text" name="url" value="<%=company.getUrl()%>">
            </td>
        </tr>
        <tr>
            <td>Company area:</td>
            <td>
                <select name="area">
                    <%
                        for (CompanyAreaEntity ca : companyAreas) {
                    %>
                        <%
                            if(ca.getId() == company.getCompanyAreaByArea().getId()) {
                        %>
                        <option value="<%=ca.getId()%>" selected="selected"><%=ca.getArea()%></option>
                        <%
                            } else {
                        %>
                        <option value="<%=ca.getId()%>"><%=ca.getArea()%></option>
                        <%
                            }
                        %>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Identification number:</td>
            <td>
                <input type="text" name="identificationNumber" value="<%=clientCompany.getIdentificationNumber()%>">
            </td>
        </tr>
        <tr>
            <td>Street:</td>
            <td>
                <input type="text" name="street" value="<%=clientCompany.getAddressByAddress().getStreet()%>">
            </td>
        </tr>
        <tr>
            <td>Number:</td>
            <td>
                <input type="text" name="streetNumber" value="<%=clientCompany.getAddressByAddress().getNumber()%>">
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td>
                <input type="text" name="city" value="<%=clientCompany.getAddressByAddress().getCity()%>">
            </td>
        </tr>
        <tr>
            <td>Region:</td>
            <td>
                <input type="text" name="region" value="<%=clientCompany.getAddressByAddress().getRegion()%>">
            </td>
        </tr>
        <tr>
            <td>Zip code:</td>
            <td>
                <input type="text" name="zip" value="<%=clientCompany.getAddressByAddress().getZipCode()%>">
            </td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <input type="text" name="country" value="<%=clientCompany.getAddressByAddress().getCountry()%>">
            </td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td>
                <input type="text" name="phone" value="<%=clientCompany.getPhone()%>">
            </td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>
                <input type="text" name="email" value="<%=userCompany.getEmail()%>">
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