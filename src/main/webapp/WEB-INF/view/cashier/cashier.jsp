<%@ page import="es.uma.proyectotaw.dto.client.Client_ClientDTO" %>
<%@ page import="es.uma.proyectotaw.dto.UserDTO" %>
<%@ page import="es.uma.proyectotaw.dto.client.Client_AccountDTO" %><%--
  Created by IntelliJ IDEA.
  User: manuj
  Date: 03/05/2023
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client_ClientDTO client = (Client_ClientDTO) request.getAttribute("client");
    UserDTO user = (UserDTO) request.getAttribute("user");
    Client_AccountDTO account = (Client_AccountDTO) request.getAttribute("account");
%>

<html>
<head>
    <title>ATM</title>
</head>
<body>
    <h3> <a href="/client?id=<%=user.getId()%>"> Back to client </a></h3>

    <h1 align="center">Welcome to the ATM</h1>

    <h3><a href="/editProfileATM/<%=client.getId()%>">Edit profile</a></h3>

    <h2>My account: </h2>
    <table border="1" style="align-content: center">
        <tr>
            <th>IBAN</th>
            <th>BALANCE</th>
            <th>STATUS</th>
            <th>CURRENCY</th>
            <th>OPENING DATE</th>
        </tr>
        <tr>
            <td style="text-align: center"><%=account.getIban()%></td>
            <td style="text-align: center"><%=account.getBalance()%></td>
            <td style="text-align: center">
                <%=account.getAccountStatusByAccountStatus().getStatus()%>

                <% if(account.getAccountStatusByAccountStatus().getStatus().equals("Blocked")) {%>
                    <br/><a href="/activation?id=<%=account.getId()%>">Request activation</a>
                <%
                    }else if(account.getAccountStatusByAccountStatus().getStatus().equals("Pending")) {
                %>
                        <br/>
                        Waiting activation...
                <%
                    }
                %>
            </td>
            <td style="text-align: center"><%=account.getCurrency()%></td>
            <td style="text-align: center"><%=account.getOpeningDate()%></td>
        </tr>
    </table>
    <br/>

    <h1 align="center"><a href="/takeMoney/<%=client.getId()%>"> Take money out</a></h1>
    <h1 align="center"><a href="/currencyChangeATM/<%=client.getId()%>"> Take money in another currency</a></h1>
    <h2>Other operations:</h2>
    <h3><a href="/transferenceATM/<%=client.getId()%>">Transference</a></h3>
    <h3><a href="/showOperationsATM/<%=client.getId()%>">Show my operations</a> </h3>

</body>
</html>
