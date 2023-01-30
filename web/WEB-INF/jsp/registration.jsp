<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="firstName">First name:
        <input type="text" name="firstName" id="firstName">
    </label><br><br>
    <label for="lastName">Last name:
        <input type="text" name="lastName" id="lastName">
    </label><br><br>
    <label for="email">Email:
        <input type="text" name="email" id="email">
    </label><br><br>
    <label for="password">Password:
        <input type="password" name="password" id="password">
    </label><br><br>
    <fieldset>
        <legend>Address:</legend>
        <label for="city">City:
            <input type="text" name="city" id="city">
        </label><br><br>
        <label for="street">Street:
            <input type="text" name="street" id="street">
        </label><br><br>
        <label for="house">House:
            <input type="text" name="house" id="house">
        </label><br><br>
        <label for="apartment">Apartment:
            <input type="text" name="apartment" id="apartment">
        </label>
    </fieldset>
    <br><br>
    <span>Role: </span><select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value=${role}>${role}</option>
        </c:forEach>
    </select><br><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
