<%--
  Created by IntelliJ IDEA.
  User: Serge
  Date: 04.01.2023
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>

<c:if test="${not empty requestScope.courses}">
    <h1>Courses</h1>
    <ul>
        <c:forEach var="course" items="${requestScope.courses}">
            <li>
                    ${course.name}
            </li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
