<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add/update meal</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" value="${meal == null ? '' : meal.id}">
    Дата и время
    <input type="datetime-local" name="dateTime" value="${meal == null ? '' : meal.dateTime}">
    <br>
    Описание
    <input type="text" name="description" value="${meal == null ? '' : meal.description}">
    <br>
    Число калорий
    <input type="number" name="calories" value="${meal == null ? '' : meal.calories}">
    <br>
    <input type="submit" value="OK">
</form>
</body>
</html>
