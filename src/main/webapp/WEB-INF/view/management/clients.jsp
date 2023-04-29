<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.management.PartialPersonDTO" %>
<%@ page import="es.uma.proyectotaw.dto.management.PartialCompanyDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 05/04/2023
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Ignacio Alba
     */
%>
<%
    List<PartialPersonDTO> persons = (List<PartialPersonDTO>) request.getAttribute("persons");
    List<PartialCompanyDTO> companies = (List<PartialCompanyDTO>) request.getAttribute("companies");
%>
<html>
<head>
    <title>Management - Clients</title>
</head>
<body>
    <h2>Registered clients list</h2>
    <a href="/clients/pending">View requests of new clients to be registered</a>
    <hr/>
        <h3>Filter by:</h3>
        <form:form action="/clients/filter" modelAttribute="filter" method="post">
            Text (name, surname, email): <form:input path="text"/>&nbsp;&nbsp;&nbsp;&nbsp;
            Client status:
                <form:select multiple="true" path="listClientStatus" size="5">
                    <form:option value="" label="---------"/>
                    <form:options items="${clientStatuses}" itemLabel="status" itemValue="status"/>
                </form:select>&nbsp;&nbsp;&nbsp;&nbsp;
            Account status:
                <form:select multiple="true" path="listAccountStatus" size="5">
                    <form:option value="" label="---------"/>
                    <form:options items="${accountStatuses}" itemLabel="status" itemValue="status"/>
                </form:select>&nbsp;&nbsp;&nbsp;&nbsp;
            Areas (Companies):
                <form:select multiple="true" path="listAreas" size="5">
                    <form:option value="" label="---------"/>
                    <form:options items="${companyAreas}" itemLabel="area" itemValue="area"/>
                </form:select>&nbsp;&nbsp;&nbsp;&nbsp;
            <button>Filter</button>
        </form:form>
    <hr/>

    <h3>Individual clients</h3>

    <%
        if (persons.size() == 0) {
    %>
        <h3 style="color: crimson">There are currently no results of individual clients registered in the system.</h3>
    <%
        } else {
    %>
        <table border="1px">
            <thead>
            <tr>
                <td colspan="6" style="text-align: center"><b>Personal information</b></td>
                <td colspan="4" style="text-align: center"><b>Client information</b></td>
                <td></td>
                <td></td>
            </tr>
            <tr style="text-align: center">
                <td><b>Name</b></td>
                <td><b>Surname</b></td>
                <td><b>Email</b></td>
                <td><b>Phone</b></td>
                <td><b>City</b></td>
                <td><b>Country</b></td>
                <td><b>Client number</b></td>
                <td><b>Client status</b></td>
                <td><b>IBAN</b></td>
                <td><b>Account status</b></td>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <%
                for (PartialPersonDTO p : persons) {
            %>
            <tr>
                <td><%=p.getName()%></td>
                <td><%=p.getSurname()%></td>
                <td><%=p.getEmail()%></td>
                <td><%=p.getClient().getPhone()%></td>
                <td><%=p.getClient().getCity()%></td>
                <td><%=p.getClient().getCountry()%></td>
                <td><%=p.getClient().getIdentificationNumber()%></td>
                <td><%=p.getClient().getStatus()%></td>
                <td><%=p.getClient().getAccount().getIban()%></td>
                <td><%=p.getClient().getAccount().getStatus()%></td>
                <td><a href="/clients/view/person/<%=p.getId()%>">View more info</a></td>
                <td><a href="/clients/view/operations/<%=p.getClient().getId()%>">View operations</a></td>
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

    <h3>Companies</h3>

    <%
        if (companies.size() == 0) {
    %>
        <h3 style="color: crimson">There are currently no results of companies registered in the system.</h3>
    <%
        } else {
    %>
        <table border="1px">
            <thead>
            <tr style="text-align: center">
                <td colspan="6" style="text-align: center"><b>Company information</b></td>
                <td colspan="4" style="text-align: center"><b>Client information</b></td>
                <td></td>
                <td></td>
            </tr>
            <tr style="text-align: center">
                <td><b>Name</b></td>
                <td><b>Email</b></td>
                <td><b>Phone</b></td>
                <td><b>Area</b></td>
                <td><b>City</b></td>
                <td><b>Country</b></td>
                <td><b>Client number</b></td>
                <td><b>Client status</b></td>
                <td><b>IBAN</b></td>
                <td><b>Account status</b></td>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <%
                for (PartialCompanyDTO c : companies) {
            %>
            <tr>
                <td><%=c.getName()%></td>
                <td><%=c.getEmail()%></td>
                <td><%=c.getClient().getPhone()%></td>
                <td><%=c.getArea()%></td>
                <td><%=c.getClient().getCity()%></td>
                <td><%=c.getClient().getCountry()%></td>
                <td><%=c.getClient().getIdentificationNumber()%></td>
                <td><%=c.getClient().getStatus()%></td>
                <td><%=c.getClient().getAccount().getIban()%></td>
                <td><%=c.getClient().getAccount().getStatus()%></td>
                <td><a href="/clients/view/company/<%=c.getId()%>">View more info</a></td>
                <td><a href="/clients/view/operations/<%=c.getClient().getId()%>">View operations</a></td>
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
    <a href="/logout">Logout</a>
</body>
</html>
