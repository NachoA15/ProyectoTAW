<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.dto.assistant.AssistantChatDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 22/04/2023
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Iván Delgado
     */
%>

<%
    List<AssistantChatDTO> chats = (List<AssistantChatDTO>) request.getAttribute("chats");
%>

<html>
<head>
    <title>Chats</title>
</head>
<body>
    <h1>Chat list:</h1>

    <form:form modelAttribute="filter" action="/assistant/chats/filtrar" method="post">
        Filter by:<br/>
        <p>Client name: <form:input path="text"/></p>
        <p>
        Status:
        <form:radiobutton path="status" value="open"/> Open
        <form:radiobutton path="status" value="closed"/> Closed
        <form:radiobutton path="status" value=""/> Both</p>
        <p><form:button>Filter</form:button></p>
    </form:form>

    <table border="1">
        <tr>
            <th>CLIENT</th>
            <th>TYPE OF ACCOUNT</th>
            <th>STATUS</th>
            <th></th>
        </tr>
        <%
            for (AssistantChatDTO chat: chats) {
        %>
        <tr>
            <td><%= chat.getUser().getName()%></td>
            <%
                if(!chat.getUser().getCompany().equals("")){
            %>
            <td>COMPANY (<%=chat.getUser().getCompany()%>)</td>
            <%
                }else{
            %>
            <td>PARTICULAR</td>
            <%
                }
            %>
            <%
                if(chat.getState().equals("open")){
            %>
            <td>Open chat</td>
            <%
                }else{
            %>
            <td>Closed chat</td>
            <%
                }
            %>
            <td><a href="/assistant/messages/<%=chat.getId()%>"> Messages</a></td>
        </tr>

        <%
            }
        %>
    </table>
    <a href="/logout">Logout</a>
</body>
</html>
