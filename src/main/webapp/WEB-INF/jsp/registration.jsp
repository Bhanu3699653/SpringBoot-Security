<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <title>Create an account</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <%-- <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   
</head>

<body>

<%-- <div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="roles">
            <div class="form-group ${status.error ? 'has-error' : ''}">
               <form:checkboxes items = "${roleList}" value="${roleList.key}" path = "roles" />
                <form:errors path="roles"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="role">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              
               <form:radiobutton path="role" value="1" />USER
                <form:radiobutton path="role" value="2" />ADMIN
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div> --%>
<!-- /container -->
<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
            <div class="form-group">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                           <span class="text-danger">  <form:errors path="username" ></form:errors></span>
            </div>
        
            <div class="form-group">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <span class="text-danger"> <form:errors path="password"></form:errors></span>
            </div>
       
            <div class="form-group">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                           <span class="text-danger">  <form:errors path="passwordConfirm"></form:errors></span>
            </div>
       
        
       
            <div class="form-group">
              
               <form:radiobutton path="role" value="1" />USER
                <form:radiobutton path="role" value="2" />ADMIN
            </div>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
</body>
</html>