<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 22.02.19
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../fragments/head.jspf" %>
<body>
<%@ include file="../fragments/header.jspf" %>
<%@ include file="../fragments/sidebarAdmin.jspf" %>

<h1>Wszystkie pytania</h1>

<table border="1">
    <thead>
    <tr>
        <td>Id pytania</td>
        <td>Pytanie</td>
        <td>Odpowiedz A</td>
        <td>Odpowiedz B</td>
        <td>Odpowiedz C</td>
        <td>Odpowiedz D</td>
        <td>Prawidłowa odpowiedź</td>
        <td>Edytuj</td>
        <td>Usuń</td>
    </tr>

    </thead>
    <tbody>
    <c:forEach items="${questions}" var="question">
        <tr>
            <td>${question.id}</td>
            <td>${question.question}</td>
            <td>${question.answer1}</td>
            <td>${question.answer2}</td>
            <td>${question.answer3}</td>
            <td>${question.answer4}</td>
            <td>${question.good_answer}</td>

            <td><a href="/admin/editQuestion/${question.id}">Edytuj pytanie</a></td>
            <td><a href="/admin/deleteQuestion/${question.id}" onclick="return confirm('Czy na pewno skasować pytanie?')">Usuń
                pytanie</a></td>


        </tr>

    </c:forEach>

    </tbody>


</table>
<br><br>
<a href="/admin/addQuestion">Dodaj pytanie</a>
<%@ include file="../fragments/footer.jspf" %>

</body>
</html>
