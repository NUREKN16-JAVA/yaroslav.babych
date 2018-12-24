<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User management/Add</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/add" method="post">
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