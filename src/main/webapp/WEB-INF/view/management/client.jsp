<%@ page import="es.uma.proyectotaw.dto.management.FullPersonDTO" %>
<%@ page import="es.uma.proyectotaw.dto.management.FullCompanyDTO" %>
<%@ page import="es.uma.proyectotaw.dto.management.FullClientDTO" %>
<%@ page import="es.uma.proyectotaw.dto.management.FullAccountDTO" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 28/04/2023
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  /**
   * @author: Ignacio Alba
   */
%>
<%
  FullPersonDTO person = (FullPersonDTO) request.getAttribute("person");
  FullCompanyDTO company = (FullCompanyDTO) request.getAttribute("company");

  FullClientDTO client = person == null? company.getClient() : person.getClient();
%>
<html>
<head>
    <title>Viewing <%=person == null? company.getName() : person.getSurname()%></title>
</head>
<body>
  <%
    if (person != null) {
  %>
    <h3>Personal information</h3>

    <table style="border-spacing: 10px">
      <tr>
        <td><b>Name:</b></td> <td> <%=person.getName()%></td>
      </tr>
      <tr>
        <td><b>Surname:</b></td> <td> <%=person.getSurname()%></td>
      </tr>
      <tr>
        <td><b>Birth date:</b></td> <td> <%=person.getBirthDate()%></td>
      </tr>
      <tr>
        <td><b>Email:</b></td> <td> <%=person.getEmail()%></td>
      </tr>
      <tr>
        <td><b>Phone:</b></td> <td> <%=person.getClient().getPhone()%></td>
      </tr>
    </table>
  <%
    } else {
  %>
    <h3>Business information</h3>

    <table style="border-spacing: 10px">
      <tr>
        <td><b>Name:</b></td> <td> <%=company.getName()%></td>
      </tr>
      <tr>
        <td><b>CIF:</b></td> <td> <%=company.getCif()%></td>
      </tr>
      <tr>
        <td><b>Email:</b></td> <td> <%=company.getEmail()%></td>
      </tr>
      <tr>
        <td><b>Area:</b></td> <td> <%=company.getArea()%></td>
      </tr>
      <tr>
        <td><b>URL:</b></td> <td> <a href="http://<%=company.getUrl()%>"><%=company.getUrl()%></a></td>
      </tr>
      <tr>
        <td><b>Phone:</b></td> <td> <%=company.getClient().getPhone()%></td>
      </tr>
    </table>
  <%
    }
  %>
  <hr/>
  <h3>Address</h3>

  <table style="border-spacing: 10px">
    <tr>
      <td><b>Street:</b></td> <td> <%=client.getAddress().getStreet()%></td>
    </tr>
    <tr>
      <td><b>Number:</b></td> <td> <%=client.getAddress().getNumber()%></td>
    </tr>
    <tr>
      <td><b>Zip code:</b></td> <td> <%=client.getAddress().getZipCode()%></td>
    </tr>
    <tr>
      <td><b>City:</b></td> <td> <%=client.getAddress().getCity()%></td>
    </tr>
    <tr>
      <td><b>Region:</b></td> <td> <%=client.getAddress().getRegion()%></td>
    </tr>
    <tr>
      <td><b>Country:</b></td> <td> <%=client.getAddress().getCountry()%></td>
    </tr>
  </table>

  <hr/>

  <h3>Client information</h3>

  <table style="border-spacing: 10px">
    <tr>
      <td><b>Identification:</b></td> <td> <%=client.getIdentificationNumber()%></td>
    </tr>
    <tr>
      <td><b>Status:</b></td> <td> <%=client.getStatus()%></td>
    </tr>
  </table>

  <hr/>
  <%
    FullAccountDTO account = client.getAccount();
  %>

  <%
    if (account != null) {
  %>
    <h3>Account information</h3>

    <table style="border-spacing: 10px">
      <tr>
        <td><b>IBAN:</b></td> <td> <%=account.getIban()%></td>
      </tr>
      <tr>
        <td><b>Opening date:</b></td> <td> <%=account.getOpeningDate()%></td>
      </tr>
      <tr>
        <td><b>Status:</b></td> <td> <%=account.getStatus()%></td>
      </tr>
      <tr>
        <td><b>Currency:</b></td> <td> <%=account.getCurrency()%></td>
      </tr>
      <tr>
        <td><b>Current balance:</b></td> <td> <%=account.getBalance()%></td>
      </tr>
    </table>

    <hr/>
  <%
  }
  %>


  <%
    if (company != null) {
  %>
    <h3>Partners and authorised people</h3>

    <%
      if (company.getRelatedPeople().size() > 0) {
    %>
    <table border="1px">
      <thead>
      <tr>
        <td><b>Name</b></td>
        <td><b>Surname</b></td>
        <td><b>Email</b></td>
        <td><b>Birth date</b></td>
      </tr>
      </thead>
      <tbody>
      <%
        for (FullPersonDTO p : company.getRelatedPeople()) {
      %>
      <tr>
        <td><%=p.getName()%></td>
        <td><%=p.getSurname()%></td>
        <td><%=p.getEmail()%></td>
        <td><%=p.getBirthDate()%></td>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
    <hr/>
    <%
    } else {
    %>
    <h4 style="color: crimson">This company doesn't have any partners or authorised people registered.</h4>
    <hr/>
    <%
      }
    %>
  <%
    }
  %>

  <%
    if (account == null) {
  %>
    <a href="/clients/pending">Return to pending clients</a>
  <%
  } else {
  %>
    <a href="/clients">Return to the main page</a>
  <%
    }
  %>

</body>
</html>
