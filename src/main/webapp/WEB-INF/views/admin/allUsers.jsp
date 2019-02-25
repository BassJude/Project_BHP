<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div>
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/sidebar.jspf" %>


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
    <a href="/users/add">Dodaj Użytkownika</a>




    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
