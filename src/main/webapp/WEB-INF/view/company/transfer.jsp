<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make a transfer</title>
</head>
<body>
<h1>Make a transfer</h1>

<c:if test="${error != null}" >
    <p style="color:red">
            ${error}
    </p>
</c:if>

<form:form action="/company/transfer" method="post" modelAttribute="operation">
    <form:hidden path="origin"/><br/>
    Destination account: <form:select path="destination"><br/>
    <form:option value=""/>
    <form:options items="${accounts}" itemLabel="iban" itemValue="id"/>
</form:select><br/>
    Amount: <form:input path="amount"/><br/>
    Payment: <form:select path="payment">
    <form:option value="" label=""/>
    <form:options items="${currency}"/>
</form:select><br/>
    <form:button>Save</form:button>
</form:form>

</body>
</html>