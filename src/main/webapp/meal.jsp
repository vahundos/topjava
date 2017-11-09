<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<a href="${pageContext.request.contextPath}/meals/addUpdate">Add new meal</a>
<table>
    <tr>
        <th>Id</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr style="background-color: ${meal.exceed ? 'red' : 'green'}">
            <td>${meal.id}</td>
            <td>${meal.dateTime.format(formatter)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="${pageContext.request.contextPath}/meals/remove?id=${meal.id}">X</a></td>
            <td><a href="${pageContext.request.contextPath}/meals/addUpdate?id=${meal.id}">EDIT</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
