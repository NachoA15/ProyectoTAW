<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="es.uma.proyectotaw.entity.CompanyEntity" %>
<%@ page import="es.uma.proyectotaw.entity.PersonEntity" %>
<%@ page import="es.uma.proyectotaw.entity.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Martin Pur
     */
%>
<%
    CompanyEntity company = (CompanyEntity) request.getAttribute("company");
    List<PersonEntity> companyUsers = (List<PersonEntity>) request.getAttribute("companyUsers");
    UserEntity companyUser = (UserEntity) request.getAttribute("companyUser");
    UserEntity loggedUser = (UserEntity) request.getAttribute("loggedUser");
%>
<html>
<head>
    <title>Company users</title>
</head>
<body>
<h1><%=company.getName()%></h1>
<h2>Contact: <%=companyUser.getEmail()%></h2>

<h3>Filter by:</h3>
<form:form action="/company/filterCompanyUsers" modelAttribute="filter" method="post">
    Name:
    <form:input path="name"/>
    Surname:
    <form:input path="surname"/>
    Country:
    <form:input path="country"/>
    Role:
    <form:select path="role" >
        <form:option value=""/>
        <form:options items="${roles}" itemLabel="role" itemValue="id"/>
    </form:select>
    <form:button>Filter</form:button>
</form:form>

<h3>List of company users</h3>

<table border="1">
    <tr>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>BIRTHDATE</th>
        <th>EMAIL</th>
        <th>PHONE</th>
        <th>IDENTIFICATION NUMBER</th>
        <th>STREET</th>
        <th>STREET NUMBER</th>
        <th>CITY</th>
        <th>REGION</th>
        <th>ZIP</th>
        <th>COUNTRY</th>
        <th>STATUS</th>
        <th>BLOCK</th>
    </tr>
    <%
        if(companyUsers != null) {
    %>
        <%
            for (PersonEntity p : companyUsers) {
        %>
        <tr>
            <td><%=p.getName()%></td>
            <td><%=p.getSurname()%></td>
            <td><%=p.getBirthDate()%></td>
            <td><%=p.getUserByPersonUser().getEmail()%></td>
            <td><%=p.getClientByPersonClient().getPhone()%></td>
            <td><%=p.getClientByPersonClient().getIdentificationNumber()%></td>
            <td><%=p.getClientByPersonClient().getAddressByAddress().getStreet()%></td>
            <td><%=p.getClientByPersonClient().getAddressByAddress().getNumber()%></td>
            <td><%=p.getClientByPersonClient().getAddressByAddress().getCity()%></td>
            <td><%=p.getClientByPersonClient().getAddressByAddress().getRegion()%></td>
            <td><%=p.getClientByPersonClient().getAddressByAddress().getZipCode()%></td>
            <td><%=p.getClientByPersonClient().getAddressByAddress().getCountry()%></td>
            <td><%=p.getClientByPersonClient().getClientStatusByStatus().getStatus()%></td>
            <%
                if(p.getUserByPersonUser().getId() != loggedUser.getId() && p.getClientByPersonClient().getClientStatusByStatus().getId() == 1) {
            %>
            <td><a href="block_partner?id=<%=p.getUserByPersonUser().getId()%>">Block</a></td>
            <%
                } else {
            %>
            <td>-</td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>
    <%
        }
    %>
</table>
</body>
</html>