<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.uma.proyectotaw.entity.PersonEntity" %>
<%@ page import="es.uma.proyectotaw.entity.AccountEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Martin Pur
     */
%>
<%
    PersonEntity company_person = (PersonEntity) request.getAttribute("company_person");
    AccountEntity account = (AccountEntity) request.getAttribute("account");
%>
<html>
<head>
    <title>Company person</title>
</head>
<body>
<h1>My profile</h1>

<c:if test="${info != null}" >
    <p style="color:red">
            ${info}
    </p>
</c:if>

<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>

<br/>
<a href="/logout">Log out</a>

<h2>Personal informations</h2>
<table border="1">
    <tr>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>BIRTHDATE</th>
        <th>EMAIL</th>
        <th>PHONE</th>
        <th>STREET</th>
        <th>STREET NUMBER</th>
        <th>CITY</th>
        <th>COUNTRY</th>
    </tr>
    <tr>
        <td><%=company_person.getName()%></td>
        <td><%=company_person.getSurname()%></td>
        <td><%=company_person.getBirthDate()%></td>
        <td><%=company_person.getUserByPersonUser().getEmail()%></td>
        <td><%=company_person.getClientByPersonClient().getPhone()%></td>
        <td><%=company_person.getClientByPersonClient().getAddressByAddress().getStreet()%></td>
        <td><%=company_person.getClientByPersonClient().getAddressByAddress().getNumber()%></td>
        <td><%=company_person.getClientByPersonClient().getAddressByAddress().getCity()%></td>
        <td><%=company_person.getClientByPersonClient().getAddressByAddress().getCountry()%></td>
    </tr>
</table>
<%
    if(account != null){
%>
<h2>Account</h2>
<table border="1">
    <tr>
        <th>IBAN</th>
        <th>ACCOUNT STATUS</th>
        <%if(!account.getAccountStatusByAccountStatus().getState().equals("Active")){%>
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
        <th><%=account.getAccountStatusByAccountStatus().getState()%></th>
        <%if(!account.getAccountStatusByAccountStatus().getState().equals("Active") &&
                !account.getAccountStatusByAccountStatus().getState().equals("Pending") ){%>
        <th><a href="/activation?id=<%=account.getId()%>">Request activation</a> </th>
        <%
        }else if(account.getAccountStatusByAccountStatus().getState().equals("Pending")){
        %>
        <th>Waiting for activation</th>
        <%
            }
        %>
        <th><%=account.getBalance()%></th>
        <th><%=account.getCurrency()%></th>
        <th><%
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(account.getOpeningDate());
        %><%=strDate%></th>

    </tr>
</table>
<%
    }
%>
<br/>
<a href="company?id=<%=company_person.getCompanyByRelatedCompany().getId()%>">Go to company</a>
<br/>
<a href="edit_user?id=<%=company_person.getUserByPersonUser().getId()%>">Edit my profile</a>
<%
    if (company_person.getUserByPersonUser().getRoleUserByRole().getId() == 4) {
%>
<br/>
<a href="users?id=<%=company_person.getCompanyByRelatedCompany().getId()%>">Show company users</a>
<br/>
<a href="transfer?id=<%=company_person.getUserByPersonUser().getId()%>">Make a transfer</a>
<br/>
<a href="currency_change?id=<%=company_person.getUserByPersonUser().getId()%>">Make a currency change</a>
<br/>
<a href="operations?id=<%=company_person.getCompanyByRelatedCompany().getId()%>">Show company operations</a>
<%
    }
%>
<%
    if(company_person.getClientByPersonClient().getClientStatusByStatus().getId() == 2) {
%>
<br/>
<a href="request_activation?id=<%=company_person.getUserByPersonUser().getId()%>">Request a profile activation</a>
<%
    }
%>

<!-- ASSISTANT: Ivan Delgado -->
<br/>
<a href="/client/chat/<%=company_person.getId()%>">Chat with assistant</a>
</body>
</html>
