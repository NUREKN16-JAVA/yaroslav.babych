<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" class="ua.nure.kn16.babych.usermanagement.User" scope="session"/>

<html>
<head>
    <title>User management/Edit</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input type="hidden" name="id" value="${user.id}">
    First name <input type="text" name="firstName" value="${user.firstName}"><br>
    Last name <input type="text" name="lastName" value="${user.lastName}"><br>
    Date of birth <input type="text" name="date" value="<fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy.MM.dd"/>"><br>
    <input type="submit" name="okButton" value="Ok">
    <input type="submit" name='cancelButton' value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}")
    </script>
</c:if>
</body>
</html>