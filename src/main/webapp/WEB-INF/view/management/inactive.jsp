<%@ page import="es.uma.proyectotaw.dto.management.PartialPersonDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.management.PartialCompanyDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 06/05/2023
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Ignacio Alba
     */
%>
<%
    List<PartialPersonDTO> inactivePersons = (List<PartialPersonDTO>) request.getAttribute("inactivePersons");
    List<PartialCompanyDTO> inactiveCompanies = (List<PartialCompanyDTO>) request.getAttribute("inactiveCompanies");
%>
<html>
<head>
    <title>Inactive clients</title>
</head>
<body>
    <h2>List of clients who haven't been active in 30 days</h2>
    <hr/>
    <h3>Individual inactive clients</h3>

    <%
        if (inactivePersons.size() == 0) {
    %>
    <h3 style="color: crimson">There are currently no inactive individual clients in the system.</h3>
    <%
    } else {
    %>
    <table border="1px">
        <thead>
        <tr style="text-align: center">
            <th><b>Name</b></th>
            <th><b>Surname</b></th>
            <th><b>Phone</b></th>
            <th><b>City</b></th>
            <th><b>Country</b></th>
            <th><b>Client number</b></th>
            <th><b>Status</b></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (PartialPersonDTO p : inactivePersons) {
        %>
        <tr>
            <td><%=p.getName()%></td>
            <td><%=p.getSurname()%></td>
            <td><%=p.getClient().getPhone()%></td>
            <td><%=p.getClient().getCity()%></td>
            <td><%=p.getClient().getCountry()%></td>
            <td><%=p.getClient().getIdentificationNumber()%></td>
            <td><%=p.getClient().getStatus()%></td>
            <td><a href="/clients/view/person/<%=p.getId()%>">View full info</a></td>
            <td><a href="/clients/block/<%=p.getClient().getId()%>">Block account</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>

    <hr/>
    <h3>Inactive companies</h3>

    <%
        if (inactiveCompanies.size() == 0) {
    %>
    <h3 style="color: crimson">There are currently no inactive companies to be registered in the system.</h3>
    <%
    } else {
    %>
    <table border="1px">
        <thead>
        <tr style="text-align: center">
            <th><b>Name</b></th>
            <th><b>Area</b></th>
            <th><b>Phone</b></th>
            <th><b>City</b></th>
            <th><b>Country</b></th>
            <th><b>Client number</b></th>
            <th><b>Status</b></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (PartialCompanyDTO c : inactiveCompanies) {
        %>
        <tr>
            <td><%=c.getName()%></td>
            <td><%=c.getArea()%></td>
            <td><%=c.getClient().getPhone()%></td>
            <td><%=c.getClient().getCity()%></td>
            <td><%=c.getClient().getCountry()%></td>>
            <td><%=c.getClient().getIdentificationNumber()%></td>
            <td><%=c.getClient().getStatus()%></td>
            <td><a href="/clients/view/company/<%=c.getId()%>">View full info</a></td>
            <td><a href="/clients/block/<%=c.getClient().getId()%>">Block account</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>

    <hr/>

    <a href="/clients">Return</a>
</body>
</html>

