<%--
  Created by IntelliJ IDEA.
  User: Serge
  Date: 17.12.2022
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div>
    <span>Content. По-русски</span>
    <p>Size: ${requestScope.users.size()}</p>
    <p>Id: ${requestScope.users[1].id}</p>
    <p>Name: ${sessionScope.usersMap[1]}</p>
    <p>JsessionId: ${cookie["JSESSIONID"].value}</p>
    <p>Cookie: ${header["Cookie"]}</p>
    <p>ParamId: ${param.id}</p>
    <p>ParamTest: ${param.test}</p>

</div>

<%@include file="footer.jsp"%>
</body>
</html>
