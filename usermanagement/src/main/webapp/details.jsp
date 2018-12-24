<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" class="ua.nure.kn16.babych.usermanagement.User" scope="session"/>

<html>
<head>
    <title>User management/Details</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/browse" method="post">
    <p>Id: ${user.id}</p>
    <p>First name: ${user.firstName}</p>
    <p>Last name: ${user.lastName}</p>
    <p>Date of birth: <fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy.MM.dd"/></p>
    <input type="submit" name="okButton" value="Ok">
</form>
</body>
</html>