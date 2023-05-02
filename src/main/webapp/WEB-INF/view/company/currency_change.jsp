<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Make a currency change</h1>

<form:form action="/company/currency_change" method="post" modelAttribute="operation">
    <form:hidden path="origin"/><br/>
    <form:hidden path="destination"/>
    Current change origin: <form:select path="currentChangeOrigin">
    <form:option value="" label=""/>
    <form:options items="${currency}"/>
</form:select><br/>
    Current change destination: <form:select path="currentChangeDestination">
    <form:option value="" label=""/>
    <form:options items="${currency}"/>
</form:select><br/>
    Amount: <form:input path="amount"/><br/>
    <form:button>Save</form:button>
</form:form>

</body>
</html>