<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div>
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/sidebarAdmin.jspf" %>


    <h1>Lista użytkowników</h1>

    <h3>Wyszukiwarka użytkowników</h3>
    <form action="${pageContext.request.contextPath}/admin/search" method="get">
        Wpisz nazwisko: <input type="text" name="search" placeholder="nazwisko">

        <input type="submit" value="szukaj" >
    </form>

    <form action="${pageContext.request.contextPath}/admin/notPassedEgzam" method="get">
        zaliczony egzamin: <input type="checkbox" name="passed" >

        <input type="submit" value="szukaj" >
    </form>
    <%--<a href="/admin/notPassedEgzam" >Niezaliczone</a>--%>




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
            <td>Data zaliczenia egzaminu</td>
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
                <td>

                    <c:choose>
                        <c:when test="${user.passedEgzam==true}">
                            <span style="color:green;font-weight: bold;" >Zaliczony</span>
                        </c:when>

                        <c:otherwise>
                            <span style="color:red;font-weight: bold;" >Niezaliczony</span>
                        </c:otherwise>
                    </c:choose>

                        <%--${user.passedEgzam}--%>

                </td>
                <td>${user.superUser}</td>

                <td><a style="color: #309125" href="${pageContext.request.contextPath}/admin/editUser/${user.id}">Edytuj użytkownika</a></td>
                <td><a style="color: #309125" href="${pageContext.request.contextPath}/admin/deleteUser/${user.id}" onclick="return confirm('Czy na pewno skasować pytanie?')">Usuń
                    użytkownika</a></td>


            </tr>

        </c:forEach>

        </tbody>


    </table>





    <%--<div style="clear:both;"></div>--%>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
