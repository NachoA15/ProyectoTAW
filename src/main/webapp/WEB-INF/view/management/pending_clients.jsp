<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.management.PartialPersonDTO" %>
<%@ page import="es.uma.proyectotaw.dto.management.PartialCompanyDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 06/04/2023
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Ignacio Alba
     */
%>
<%
    List<PartialPersonDTO> pendingPersons = (List<PartialPersonDTO>) request.getAttribute("pendingPersons");
    List<PartialCompanyDTO> pendingCompanies = (List<PartialCompanyDTO>) request.getAttribute("pendingCompanies");
%>

<html>
<head>
    <title>Pending clients</title>
</head>
<body>
    <h2>List of requests of new clients</h2>
    <hr/>
    <h3>New individual clients pending for confirmation</h3>

    <%
        if (pendingPersons.size() == 0) {
    %>
        <h3 style="color: crimson">There are currently no pending new individual clients to be registered in the system.</h3>
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
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (PartialPersonDTO p : pendingPersons) {
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
                        <td><a href="/clients/confirm/<%=p.getClient().getId()%>">Confirm</a></td>
                        <td><a href="/clients/delete/<%=p.getClient().getId()%>">Deny</a></td>
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
    <h3>New companies pending for confirmation</h3>

    <%
        if (pendingCompanies.size() == 0) {
    %>
    <h3 style="color: crimson">There are currently no pending new companies to be registered in the system.</h3>
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
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (PartialCompanyDTO c : pendingCompanies) {
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
            <td><a href="/clients/confirm/<%=c.getClient().getId()%>">Confirm</a></td>
            <td><a href="/clients/delete/<%=c.getClient().getId()%>">Deny</a></td>
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
