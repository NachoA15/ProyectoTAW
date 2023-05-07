<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.CompanyEntity" %>
<%@ page import="es.uma.proyectotaw.entity.OperationEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Martin Pur
     */
%>
<%
    CompanyEntity company = (CompanyEntity) request.getAttribute("company");
    List<OperationEntity> companyOperations = (List<OperationEntity>) request.getAttribute("companyOperations");
%>
<html>
<head>
    <title>Operations</title>
</head>
<body>
<h1><%=company.getName()%></h1>
<h1>List of operations made by related people</h1>

<h3>Filter by:</h3>
<form:form action="/company/filterCompanyOperations" modelAttribute="filter" method="post">
    Date:
    <form:input path="date" type="date"/>
    Origin:
    <form:select path="origin" >
        <form:option value=""/>
        <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
    </form:select>
    Destination:
    <form:select path="destination" >
        <form:option value="" />
        <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
    </form:select>
    Amount:
    <form:input path="amount"/>

    <h3>Sort by:</h3>
    <form:select path="sortBy">
        <form:option value=""/>
        <form:option value="dateAsc" label="Date asc" />
        <form:option value="dateDesc" label="Date desc" />
        <form:option value="originAsc" label="Origin asc" />
        <form:option value="originDesc" label="Origin desc" />
        <form:option value="destinationAsc" label="Destination asc" />
        <form:option value="destinationDesc" label="Destination desc" />
        <form:option value="amountAsc" label="Amount asc" />
        <form:option value="amountDesc" label="Amount desc" />
    </form:select>
    <form:button>Filter</form:button>
</form:form>

<table border="1">
    <tr>
        <th>IBAN ORIGIN</th>
        <th>IBAN DESTINATION</th>
        <th>AMOUNT</th>
        <th>PAYMENT</th>
        <th>DATE</th>
    </tr>

    <%
        if(companyOperations != null) {
    %>
        <%
            for (OperationEntity o : companyOperations) {
        %>
        <tr>
            <td><%=o.getAccountByOrigin().getIban()%></td>
            <td><%=o.getAccountByDestination().getIban()%></td>
            <td><%=o.getAmount()%></td>
            <%
                if(o.getPaymentByPayment() != null) {
            %>
            <td><%=o.getPaymentByPayment().getCurrency()%></td>
            <%
                } else {
            %>
            <td><%=o.getCurrencyChangeByCurrencyChange().getDestinationCurrency()%></td>
            <%
                }
            %>
            <td><%=o.getDate()%></td>
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