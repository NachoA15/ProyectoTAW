<%@ page import="es.uma.proyectotaw.dto.client.Client_ClientDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: manuj
  Date: 03/05/2023
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>

<%-- Author: Manuel Jesús Jerez --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  Client_ClientDTO client = (Client_ClientDTO) request.getAttribute("client");
%>

<html>
<head>
    <title>CurrencyChange</title>
</head>
<body>

  <h3><a href="/atm/<%=client.getId()%>">Back</a></h3>

  <h2>Indicate the amount of money to take out
  and the currency change: </h2>

  <c:if test="${error != null}">
    <p style="color: red">
        ${error}
    </p>
  </c:if>


  <form:form action="/takeMoneyATM/save" method="post" modelAttribute="operation">
    <form:hidden path="origin"/>
    <form:hidden path="destination"/>
    Amount: <form:input path="amount"/>
    <br/><br/>
    Origin currency:
    <form:select path="currentChangeOrigin">
      <form:options items="${currencys}"/>
    </form:select>
    <br/><br/>
    Destination currency:
    <form:select path="currentChangeDestination">
      <form:options items="${currencys}"/>
    </form:select>
    <br/><br/>
    <form:button>OK</form:button>
  </form:form>


</body>
</html>
