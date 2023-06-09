<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.uma.proyectotaw.dto.client.Client_PersonDTO" %>
<%@ page import="es.uma.proyectotaw.dto.client.Client_AccountDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Marina
  Date: 10/04/2023
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Marina Sayago
     */
%>
<%
    Client_PersonDTO client = (Client_PersonDTO) request.getAttribute("client");
    Client_AccountDTO account = (Client_AccountDTO) request.getAttribute("account");
%>
<html>
<head>
    <title>Client</title>
</head>
<body>
<h1>WELCOME, <%=client.getName()%> <%=client.getSurname()%></h1>
<h2>My profile</h2>
<table border="1">
    <tr>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>BIRTHDATE</th>
        <th>IDENTIFICATION NUMBER</th>
        <th>STATUS</th>
        <th>ADDRESS</th>
        <th>PHONE</th>

    </tr>

    <tr>
        <th><%=client.getName()%></th>
        <th><%=client.getSurname()%></th>
        <th><%=client.getBirthDate()%></th>
        <th><%=client.getClientByPersonClient().getIdentificationNumber()%></th>
        <th><%=client.getClientByPersonClient().getClientStatusByStatus()%></th>
        <th><%=client.getClientByPersonClient().getAddressByAddress().getStreet()%>,
            <%=client.getClientByPersonClient().getAddressByAddress().getNumber()%>,
            <%=client.getClientByPersonClient().getAddressByAddress().getCity()%>,
            <%=client.getClientByPersonClient().getAddressByAddress().getRegion()%>,
            <%=client.getClientByPersonClient().getAddressByAddress().getZipCode()%>,
            <%=client.getClientByPersonClient().getAddressByAddress().getCountry()%></th>
        <th><%=client.getClientByPersonClient().getPhone()%></th>
    </tr>
</table>
<h3><a href="/updateProfile?id=<%=client.getClientByPersonClient().getId()%>"> Edit profile</a> </h3>

<h2>My account</h2>
<table border="1">
    <tr>
        <th>IBAN</th>
        <th>ACCOUNT STATUS</th>
        <%if(!account.getAccountStatusByAccountStatus().getStatus().equals("Active")){%>
        <th></th>
        <%
            }
        %>
        <th>BALANCE</th>
        <th>CURRENCY</th>
        <th>OPENING DATE</th>

    </tr>
    <tr>
        <th><%=account.getIban()%></th>
        <th><%=account.getAccountStatusByAccountStatus().getStatus()%></th>
        <%if(account.getAccountStatusByAccountStatus().getStatus().equals("Blocked") ){%>
        <th><a href="/activation?id=<%=account.getId()%>">Request activation</a> </th>
        <%
            }else if(account.getAccountStatusByAccountStatus().getStatus().equals("Pending")){
        %>
        <th>Waiting for activation</th>
        <%
            }
        %>
        <th><%=account.getBalance()%></th>
        <th><%=account.getCurrency()%></th>
        <th><%
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(account.getOpeningDate());
        %><%=strDate%></th>

    </tr>
</table>

<h2>My operations</h2>

<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>

<h3><a href="/seeOperations?id=<%=client.getClientByPersonClient().getId()%>"> See all my operations</a> </h3>

<h3><a href="/transference?id=<%=client.getClientByPersonClient().getId()%>"> Make a transference</a> </h3>

<h3><a href="/currencyChange?id=<%=client.getClientByPersonClient().getId()%>"> Make a currency change</a> </h3>

<h3><a href="/client/chat/<%=client.getId()%>">Chat with assistant</a></h3>

<h3><a href="/logout"> LogOut</a> </h3>

<br/>
<h2><a href="/atm/<%=client.getId()%>"> Go to ATM </a></h2>
</body>
</html>
