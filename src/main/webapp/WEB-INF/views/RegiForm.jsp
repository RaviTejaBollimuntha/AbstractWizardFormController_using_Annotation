<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}
.errorblock{
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding:8px;
	margin:16px;
}
</style>
</head>

<body>
<h2>Page1Form.jsp</h2>

<form:form method="POST" commandName="command">
<form:errors path="*" cssClass="errorblock" element="div"/>
<table>
<tr>
<td>Username : </td>
<td><form:input path="userName" /></td>
<td><form:errors path="userName" cssClass="error" /></td>
</tr>
<tr>

<tr>
<td colspan="3">
<input type="submit" value="Next" name="_page" />
<input type="submit" value="Cancel" name="_cancel" />
</td>
</tr>

</table>
</form:form>

</body>
</html>