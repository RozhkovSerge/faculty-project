
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
            <a href="${pageContext.request.contextPath}/courses?user_id=${user.id}">${user.firstName} ${user.lastName} </a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
