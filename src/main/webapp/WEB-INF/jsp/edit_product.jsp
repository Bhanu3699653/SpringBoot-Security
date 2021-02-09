<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>

</div>
<div align="center">	
		<h1>Update Product</h1>
		<br/>
		<c:url var="action" value="/edit"/>
	<form:form action="${action}" method="post"  modelAttribute="editProduct" novalidate="novalidate">
     <table>
            <tr>
				<td>Product ID:</td>
				<td><form:input path="id" readOnly="readOnly"/></td>
			</tr>
			<tr>
				<td>Product Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Brand:</td>
				<td><form:input path="brand" /></td>
				<%-- <font color="red"><form:errors path="age" /></font> --%>
			</tr>
			<tr>
				<td>Madein:</td>
				<td><form:input path="madein" /></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Edit Product" /></td>
				<td><input type="reset" value="Cancel" /></td>
			</tr>
		</table>

      </form:form>
</div>

</body>
</html>