<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="es.uma.proyectotaw.entity.CompanyEntity" %>
<%@ page import="es.uma.proyectotaw.entity.ClientEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Martin Pur
     */
%>
<%
    CompanyEntity company = (CompanyEntity) request.getAttribute("company");
    Boolean isCompanyUser = (Boolean) request.getAttribute("isCompanyUser");
    List<ClientEntity> clients = (List<ClientEntity>) request.getAttribute("clients");
%>
<html>
<head>
    <title>Viewing <%=company.getName()%></title>
</head>
<body>
<h1>Company <%=company.getName()%></h1>

<c:if test="${info != null}" >
    <p style="color:red">
            ${info}
    </p>
</c:if>

<a href="/logout">Log out</a>

<h3>Business information</h3>

<table style="border-spacing: 10px">
    <tr>
        <td><b>Name:</b></td> <td> <%=company.getName()%></td>
    </tr>
    <tr>
        <td><b>CIF:</b></td> <td> <%=company.getCif()%></td>
    </tr>
    <tr>
        <td><b>URL:</b></td> <td> <a href="<%=company.getUrl()%>"><%=company.getUrl()%></a></td>
    </tr>
    <tr>
        <td><b>Area:</b></td> <td> <%=company.getCompanyAreaByArea().getArea()%></td>
    </tr>
    <tr>
        <td><b>Email:</b></td> <td> <%=company.getUserByCompanyUser().getEmail()%></td>
    </tr>
    <tr>
        <td><b>Phone:</b></td> <td> <%=company.getClientByCompanyClient().getPhone()%></td>
    </tr>
    <tr>
        <td><b>Country:</b></td> <td> <%=company.getClientByCompanyClient().getAddressByAddress().getCountry()%></td>
    </tr>
    <tr>
        <td><b>Street:</b></td> <td> <%=company.getClientByCompanyClient().getAddressByAddress().getStreet()%></td>
    </tr>
    <tr>
        <td><b>Street number:</b></td> <td> <%=company.getClientByCompanyClient().getAddressByAddress().getNumber()%></td>
    </tr>
    <tr>
        <td><b>City:</b></td> <td> <%=company.getClientByCompanyClient().getAddressByAddress().getCity()%></td>
    </tr>
    <tr>
        <td><b>Country:</b></td> <td> <%=company.getClientByCompanyClient().getAddressByAddress().getCountry()%></td>
    </tr>
</table>

<hr/>

<h3>Clients of a company</h3>

<table border="1" style="border-spacing: 10px">
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthdate</th>
        <th>City</th>
        <th>Country</th>
    </tr>
    <%
        if(clients != null) {
    %>
        <%
            for (ClientEntity cl : clients) {
        %>
        <tr>
            <td><%=cl.getPersonById().getName()%></td>
            <td><%=cl.getPersonById().getSurname()%></td>
            <td><%=cl.getPersonById().getBirthDate()%></td>
            <td><%=cl.getAddressByAddress().getCity()%></td>
            <td><%=cl.getAddressByAddress().getCountry()%></td>
        </tr>
        <%
            }
        %>
    <%
        }
    %>
</table>

<%
    if (isCompanyUser) {
%>
<a href="register_user">Register new company partner / authorised</a>
<%
    }
%>
<br/>
<a href="edit_company?id=<%=company.getId()%>">Edit company</a>
<br/>

</body>
</html>
