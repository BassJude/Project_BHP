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
        <h1>Wszystkie pytania</h1>

        <h3>Wyszukiwarka pytań</h3>
        <form action="${pageContext.request.contextPath}/admin/searchQuestion" method="get">
            Wpisz szukaną frazę: <input type="text" name="search" placeholder="szukaj">
            <input type="submit" value="szukaj">
        </form>
        <div class="border"></div>

        <c:forEach items="${questions}" var="question">

            <div>Id pytania: ${question.id}</div>
            <div>${question.question}</div>
            <div>a) ${question.answer1}</div>
            <div>b) ${question.answer2}</div>
            <div>c)${question.answer3}</div>
            <div>d) ${question.answer4}</div>
            <div>Prawidłowa odpowiedź to: ${question.good_answer}</div>

            <div>
                <a style="color: #309125" href="${pageContext.request.contextPath}/admin/editQuestion/${question.id}">Edytuj
                    pytanie</a>
                <span>    |    </span>
                <a style="color: #309125" href="${pageContext.request.contextPath}/admin/deleteQuestion/${question.id}"
                   onclick="return confirm('Czy na pewno skasować pytanie?')">Usuń
                    pytanie</a>
            </div>
            <div class="border"></div>

        </c:forEach>
        <br><br>
        <div>
            <a style="color: #309125" href="${pageContext.request.contextPath}/admin/addQuestion">Dodaj pytanie</a>
        </div>
    </div>

    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
