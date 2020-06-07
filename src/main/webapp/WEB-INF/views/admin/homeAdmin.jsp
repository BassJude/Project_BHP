<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div id="container">
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/sidebarAdmin.jspf" %>

    <div id="content">
        <c:choose>
            <c:when test="${registration==true}">
                <span>${message}</span>
            </c:when>

            <c:when test="${changes==true}">
                <span>${message}</span>
            </c:when>

            <c:otherwise>
                <h1>Panel Administratora</h1>
                <p>Ilość użytkowników: <c:if test="${numberOfUsers!=null}">${numberOfUsers}</c:if></p>
                <p>Ilość pytań: <c:if test="${numberOfQuestions!=null}">${numberOfQuestions}</c:if></p>
                <p>Procent użytkowników z zaliczonym testem: <c:if test="${percentageOfPassedExams!=null}">${percentageOfPassedExams}</c:if> %</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
