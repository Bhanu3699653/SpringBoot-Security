<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new Product</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <%-- <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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
		<h1>Create New Product</h1>
		<br/>
	<form:form action="save" method="post"  modelAttribute="productForm" novalidate="novalidate">
     <table>
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
				<td><input type="submit" value="Add Product" /></td>
				<td><input type="reset" value="Cancel" /></td>
			</tr>
		</table>

      </form:form>
</div>

</body>
</html>