<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.dto.client.Client_ClientDTO" %>
<%@ page import="es.uma.proyectotaw.dto.client.Client_OperationDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: manuj
  Date: 06/05/2023
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client_ClientDTO client = (Client_ClientDTO) request.getAttribute("client");
    List<Client_OperationDTO> operations = (List<Client_OperationDTO>) request.getAttribute("operations");
%>
<html>
<head>
    <title>Operations</title>
</head>
<body>
    <h3><a href="/atm/<%=client.getId()%>">Back</a></h3>

    <h1> MY OPERATIONS</h1>

    <h2>Filter by:</h2>
    <form:form action="/filterOperationsATM" method="post" modelAttribute="filter">
        <form:hidden path="client"/>
        <form:hidden path="origin"/>
        Account Destination:
        <form:select path="destination">
            <form:option value=""/>
            <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
        </form:select>
        Date:<form:input path="date"/>
        Payment:
        <form:select path="payment">
            <form:option value=""/>
            <form:options items="${currencys}"/>
        </form:select>
        <form:button>OK</form:button>

    </form:form>

    <table border="1">
        <tr>
            <th>ORIGIN ACCOUNT</th>
            <th>DESTINATION ACCOUNT</th>
            <th>DATE</th>
            <th>CURRENCY CHANGE</th>
            <th>PAYMENT</th>
            <th>AMOUNT</th>
        </tr>
        <%
            for(Client_OperationDTO op: operations){
        %>

            <tr>
                <td style="text-align: center"><%=op.getAccountOriginByOperation().getIban()%></td>
                <td style="text-align: center"><%=op.getAccountDestinationByOperation().getIban()%></td>
                <td style="text-align: center"><%=op.getDate()%></td>
                <td style="text-align: center"><%=op.getCurrencyChangeByCurrencyChange() != null ? op.getCurrencyChangeByCurrencyChange().getId() : "" %></td>
                <td style="text-align: center"><%=op.getPaymentByPayment()%></td>
                <td style="text-align: center"><%=op.getAmount()%></td>
            </tr>

        <%
            }
        %>
    </table>
    <br/>
</body>
</html>
