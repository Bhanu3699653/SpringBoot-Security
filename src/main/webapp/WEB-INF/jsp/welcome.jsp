<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ProductList</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <%-- <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <style>
    .table {
   margin: auto;
   width: 50%; 
}
    </style>
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
	<h1>Product Manager</h1>
	<div>
		<a href="new">Create New Product</a>
	</div>
	
	<br/><br/>	
	<table class="table table-stripped">
		<thead>
			<tr>
				<th>Product ID</th>
				<th>Name</th>
				<th>Brand</th>
				<th>Made In</th>
				<th>Price</th>				
				<th>Actions</th>				
				
			</tr>
		</thead>
		<c:forEach var="product" items="${plist}">
		<tbody>
		<tr>
			<td>${product.id}</td>
			<td>${product.name}</td>
			<td>${product.brand}</td>
			<td>${product.madein}</td>
			<td>${product.price}</td>
			<td><a href="/editproduct/${product.id}">Edit</a> 
            <a href="/deleteproduct/${product.id}">Delete</a></td> 
		</tr>
		</tbody>
		</c:forEach>
	</table>
</div>


</body>
</html>
