<%@ page import="es.uma.proyectotaw.entity.posiacaso.Company" %>
<%@ page import="es.uma.proyectotaw.entity.posiacaso.Account" %>
<%@ page import="es.uma.proyectotaw.entity.posiacaso.Person" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 07/04/2023
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Company company = (Company) request.getAttribute("company");
    List<Person> relatedPersons = (List<Person>) company.getPeopleById();
    //Assistant
    Person company_partner = (Person) request.getAttribute("company_partner");
    //Person company_user = (Person) request.getAttribute("company_user");
%>
<html>
<head>
    <title>Viewing <%=company.getName()%></title>
</head>
<body>
<h1>Company <%=company.getName()%></h1>
<h3>Business information</h3>

<table style="border-spacing: 10px">
    <tr>
        <td><b>Name:</b></td> <td> <%=company.getName()%></td>
    </tr>
    <tr>
        <td><b>CIF:</b></td> <td> <%=company.getCif()%></td>
    </tr>
    <tr>
        <td><b>URL:</b></td> <td> <a href="<%=company.getUrl()%>"><%=company.getUrl()%></a></td>
    </tr>
    <tr>
        <td><b>Area:</b></td> <td> <%=company.getArea().getArea()%></td>
    </tr>
</table>

<hr/>

<h3>Partners and authorised people</h3>

<%
    if (relatedPersons.size() > 0) {
%>
<table border="1px">
    <thead>
    <tr>
        <td><b>Name</b></td>
        <td><b>Surname</b></td>
        <td><b>Email</b></td>
        <td><b>Birth date</b></td>
        <td><b>Role</b></td>
    </tr>
    </thead>
    <tbody>
    <%
        for (Person p : relatedPersons) {
    %>
    <tr>
        <td><%=p.getName()%></td>
        <td><%=p.getSurname()%></td>
        <td><%=p.getUserByPersonUser().getEmail()%></td>
        <td><%=p.getBirthDate()%></td>
        <td><%=p.getUserByPersonUser().getRoleUserByRole().getRole()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
} else {
%>
<h4 style="color: crimson">This company doesn't have any partners or authorised people registered.</h4>
<%
    }
%>

<hr/>

<h3>Clients of company</h3>

<table style="border-spacing: 10px">
    <tr>
        <td><b>Name:</b></td> <td> Client1 </td>
    </tr>
</table>

<a href="users?id=<%=company.getId()%>">Show company users</a>
<br/>
<a href="operations?id=<%=company.getId()%>">Show company operations</a>
<br/>

<!-- Assistant -->

<%
    if(company_partner != null){
        if(company_partner.getChatsById().isEmpty()){
%>
<a href="/newChat/<%=company_partner.getId()%>">Chat with an assistant</a>
<%
        }else{
%>
<a href="/client/chat/<%=company_partner.getId()%>">Chat with an assistant</a>
<%
        }
    //}else if(company_user != null){
        //if(company_user.getChatsById().isEmpty()){
%>
<!--<a href="/newChat/<%//=company_user.getId()%>">Chat with an assistant</a>-->
<%
        //}else{
%>
<!--<a href="/client/chat/<%//=company_user.getId()%>">Chat with an assistant</a>-->
<%
        //}
    }
%>
</body>
</html>
