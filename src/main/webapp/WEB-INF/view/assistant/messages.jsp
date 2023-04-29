<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.assistant.AssistantMessageDTO" %>
<%@ page import="es.uma.proyectotaw.dto.assistant.AssistantChatDTO" %>
<%@ page import="es.uma.proyectotaw.dto.assistant.AssistantMessageDTO" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 22/04/2023
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author: Iván Delgado
     */
%>
<%
  List<AssistantMessageDTO> messages = (List<AssistantMessageDTO>) request.getAttribute("messages");
  AssistantChatDTO chat = (AssistantChatDTO) request.getAttribute("chat");
%>

<html>
<head>
    <title>Messages</title>
</head>
<body>
    <h1>List of messages:</h1>
    <table border="1">
        <tr>
            <th>Sender</th>
            <th>Text</th>
            <th>Date</th>
        </tr>
        <%
            for(AssistantMessageDTO assistantMessageDTO : messages){
        %>
        <tr>
            <td><%=assistantMessageDTO.getWriter()%></td>
            <td><%=assistantMessageDTO.getText()%></td>
            <td><%=assistantMessageDTO.getDate().toString().substring(0,19)%></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        if(chat.getState().equals("open")){
    %>
    <p><a href="/assistant/newMessage/<%=chat.getId()%>">New message</a></p>
    <%
        }
    %>

    <p><a href="/assistant">Return</a></p>
</body>
</html>
