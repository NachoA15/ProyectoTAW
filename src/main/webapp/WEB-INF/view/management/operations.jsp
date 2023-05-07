<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.management.*" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 28/04/2023
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Ignacio Alba
     */
%>
<%
    PartialPersonDTO person = (PartialPersonDTO) request.getAttribute("person");
    PartialCompanyDTO company = (PartialCompanyDTO) request.getAttribute("company");

    List<OperationDTO> operations = (List<OperationDTO>) request.getAttribute("operations");

    PartialAccountDTO account = person == null? company.getClient().getAccount() : person.getClient().getAccount();
%>
<html>
<head>
    <title>Operations by <%=person == null? company.getName() : person.getName() + person.getSurname()%></title>
</head>
<body>
  <h1>Operation history for <%=person == null? company.getName() : person.getName()%> <%=person != null? person.getSurname() : ""%></h1>

  <h3>Account information</h3>

    <table>
        <tr>
            <td><b>IBAN:</b></td>
            <td><%=account.getIban()%></td>
        </tr>
    </table>

    <hr/>

    <% String action = person != null? "/clients/operations/person/" + person.getId() + "/filter" :  "/clients/operations/company/" + company.getId() + "/filter";%>

    <form:form action="<%=action%>" modelAttribute="filter" method="post">
        Filter by... &nbsp;&nbsp;
        Origin: <form:input path="origin" maxlength="25" size="25"/>&nbsp;&nbsp;&nbsp;&nbsp;
        Destination: <form:input path="destination" maxlength="25" size="25"/>&nbsp;&nbsp;&nbsp;&nbsp;
        Order by...&nbsp;&nbsp;
        <form:radiobutton path="order" value="" label="Default"/>
        <form:radiobuttons path="order" items="${orderCriteria}"/>
        <form:button>Filter</form:button>
    </form:form>

    <table border="1px">
        <thead>
            <tr>
                <th>Origin</th>
                <th>Destination</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Currency change</th>
                <th>Payment</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%
                for (OperationDTO operation : operations) {
            %>
                <tr>
                    <td><%=operation.getOrigin().getIban()%></td>
                    <td><%=operation.getDestination().getIban()%></td>
                    <td><%=operation.getAmount()%></td>
                    <td><%=operation.getDate()%></td>
                    <td>
                        <%if (operation.getCurrency() != null) { %>
                            <%=operation.getCurrency().getOriginCurrency()%> to <%=operation.getCurrency().getDestinationCurrency()%>
                        <% } %>
                    </td>
                    <td>
                        <%if (operation.getPayment() != null) { %>
                            <%=operation.getPayment()%>
                        <% } %>
                    </td>
                    <td><%if (operation.getOrigin().getStatus().equals("Suspicious") || operation.getDestination().getStatus().equals("Suspicious")) { %>
                            <p style="color: crimson">SUSPICIOUS OPERATION</p>
                        <% } else {%>
                            OK
                        <% } %></td>
                    <td>
                        <%if (operation.getOrigin().getStatus().equals("Suspicious") || operation.getDestination().getStatus().equals("Suspicious")) { %>
                            <a href="/clients/suspicious/block/<%=person == null? company.getClient().getId() : person.getClient().getId()%>">Block account</a>
                        <% }%>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
