<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.UserDTO" %>
<%@ page import="es.uma.proyectotaw.dto.client.Client_OperationDTO" %><%--
  Created by IntelliJ IDEA.
  User: Marina
  Date: 10/04/2023
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Client_OperationDTO> listOperations = (List<Client_OperationDTO>) request.getAttribute("operations");
    UserDTO userDTO = (UserDTO) request.getAttribute("user");
%>
<html>
<head>
    <title>Operations</title>
</head>
<body>
<h3>Filter by:</h3>
<h3><a href="/client?id=<%=userDTO.getId()%>">Back</a></h3>
    <form:form action="/client/filter" modelAttribute="filter" method="post">
        Account Origin: <form:select path="origin" >
            <form:option value=""/>
            <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
        </form:select>
        Account Destination: <form:select path="destination" >
            <form:option value="" />
            <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
        </form:select>
        Date: <form:input path="date" type="date"/>
        <form:hidden path="client"/>
        Currency Change: <form:select path="currency" >
            <form:option value="" />
            <form:options items="${currencyPayment}"/>
        </form:select>
        Payment: <form:select path="payment" >
            <form:option value="" />
            <form:options items="${currencyPayment}"/>
        </form:select>
        Amount: <form:input path="amount" size="6"/>
        <form:button>Filtrar</form:button>
    </form:form>
<table border="1">
    <tr>
        <th width="150">ORIGIN</th>
        <th width="150">DESTINATION</th>
        <th width="150">DATE</th>
        <th width="150">CURRENCY CHANGE</th>
        <th width="150">PAYMENT</th>
        <th width="150">AMOUNT</th>
    </tr>
    <%
        for(Client_OperationDTO operation : listOperations){
    %>
        <tr>
            <th width="150"><%=operation.getAccountOriginByOperation().getIban()%></th>
            <th width="150"><%=operation.getAccountDestinationByOperation().getIban()%></th>
            <th width="150"><%=operation.getDate()%></th>
            <th width="150">
                <%if(operation.getCurrencyChangeByCurrencyChange() != null){%>
                <%=operation.getCurrencyChangeByCurrencyChange().getOriginCurrency()%>,
                <%=operation.getCurrencyChangeByCurrencyChange().getDestinationCurrency()%>
                <%}%>
            </th>
            <th width="150">
                <%if(operation.getPaymentByPayment() != null){%>
                <%=operation.getPaymentByPayment()%>
                <%}%>
            </th>
            <th width="150"><%=operation.getAmount()%></th>
        </tr>
    <%
        }
    %>
</table>
</body>
</html>
