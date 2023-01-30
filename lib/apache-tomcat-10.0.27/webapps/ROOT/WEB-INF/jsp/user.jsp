
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Users</h1>
<ul>
    <c:forEach var="user" items="${requestScope.users}">
        <li>
            ${user.firstName} ${fn:toLowerCase(user.lastName)}
        </li>
    </c:forEach>
</ul>
</body>
</html>
