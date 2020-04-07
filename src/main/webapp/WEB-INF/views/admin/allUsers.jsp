<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div>
    <div id="container">
        <%@ include file="../fragments/header.jspf" %>
        <%@ include file="../fragments/sidebarAdmin.jspf" %>
        <div id="content">
            <c:choose>
                <c:when test="${userDelete==true}">

                    <p class="error">Usunąłeś uzytkownika!</p>
                    <p class="error">Id: ${user.id}, email: ${user.email}, imię: ${user.firstName},
                        nazwisko: ${user.lastName}</p>
                </c:when>

                <c:when test="${AdminInvalid==true}">
                    <p class="error">Nie możesz usunąć użytkownika, który aktualnie jest jedynym administratorem</p>
                    <p class="error">Zawsze jeden użytkownik musi być administratorem</p>
                    <p class="error">Nie możesz usunąć samego siebie, będąc zalogowanym</p>
                </c:when>

                <c:otherwise>

                </c:otherwise>
            </c:choose>


            <h1>Lista użytkowników</h1>

            <h3>Wyszukiwarka użytkowników</h3>
            <form action="${pageContext.request.contextPath}/admin/search" method="post">
                Wpisz nazwisko: <input type="text" name="search" placeholder="nazwisko">

                <input type="submit" value="szukaj">
            </form>

            <form action="${pageContext.request.contextPath}/admin/notPassedEgzam" method="post">
                zaliczony egzamin: <input type="checkbox" name="passed" ${status}>

                <input type="submit" value="szukaj">
            </form>

            <c:forEach items="${users}" var="user">
                <div class="border"></div>
                <span>Id: ${user.id} | </span>
                <span>Login: ${user.login} | </span>
                <span>Imię: ${user.firstName} | </span>
                <span>Nazwisko: ${user.lastName} | </span>
                <span>Miasto: ${user.city}</span>
                <div class="borderUsers"></div>
                <span>E-mail: ${user.email} | </span>
                <span>Zaliczony test:
                    <c:choose>
                        <c:when test="${user.passedEgzam==true}">
                            <span style="color:green;font-weight: bold;">Zaliczony</span>
                        </c:when>

                        <c:otherwise>
                            <span style="color:red;font-weight: bold;">Niezaliczony</span>
                        </c:otherwise>
                    </c:choose>
                </span>
                <span><c:if test="${user.superUser==true}"> | Admin</c:if></span>
                <div class="borderUsers"></div>
                <span><a style="color: #309125"
                         href="${pageContext.request.contextPath}/admin/detailsUser/${user.id}">Szczegóły
                    użytkownika</a></span>
                <span> | </span>
                <span><a style="color: #309125"
                         href="${pageContext.request.contextPath}/admin/editUser/${user.id}">Edytuj
                    użytkownika</a></span>
                <span> | </span>
                <span><a style="color: #309125"
                         href="${pageContext.request.contextPath}/admin/deleteUser/${user.id}"
                         onclick="return confirm('Czy na pewno skasować pytanie?')">Usuń
                    użytkownika</a></span>

            </c:forEach>
        </div>
        <%@ include file="../fragments/footer.jspf" %>

    </div>
</div>
</body>
</html>
