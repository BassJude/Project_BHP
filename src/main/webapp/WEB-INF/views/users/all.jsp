<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 24.02.19
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Lista użytkowników</h1>


<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Login</td>
        <td>Hasło</td>
        <td>Imię</td>
        <td>Nazwisko</td>
        <td>Miasto</td>
        <td>Ulica</td>
        <td>Numer domu</td>
        <td>email</td>
        <td>Data ostatniego testu</td>
        <td>Zaliczony test</td>
        <td>Admin</td>
        <td>Edytuj</td>
        <td>Usuń</td>
    </tr>

    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.city}</td>
            <td>${user.street}</td>
            <td>${user.homeNumber}</td>
            <td>${user.email}</td>
            <td>${user.lastTestTime}</td>
            <td>${user.passedEgzam}</td>
            <td>${user.superUser}</td>

            <td><a href="/users/edit/${user.id}">Edytuj pytanie</a></td>
            <td><a href="/users/delete/${user.id}" onclick="return confirm('Czy na pewno skasować pytanie?')">Usuń
                pytanie</a></td>


        </tr>

    </c:forEach>

    </tbody>


</table>
<br><br>
<a href="/users/add">Edytuj Użytkownika</a>

</body>
</html>
