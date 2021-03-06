<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 24.02.19
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../fragments/head.jspf" %>
<body>
<%@ include file="../fragments/header.jspf" %>
<%@ include file="../fragments/sidebarAdmin.jspf" %>

<h1>Dodaj lub edytuj pytanie</h1>
<p>Id zmienianego pytania: ${question.id}</p>

<div>

    <form:form method="post" modelAttribute="question">

        <div><label class="formAddQuestion">Wybierz prawidłową odpowiedź:
            <form:select path="good_answer"  >
                <%--<form:option value="0" label="--Wybierz odpowiedź--"/>--%>
                <form:options items="${abcd}"/>
            </form:select></label>
            <form:errors path="good_answer" cssClass="error" /> <input type="submit" value="Zapisz"></div>

        <div><label class="formAddQuestion"> Wpisz pytanie: <form:textarea rows="5" cols="80" path="question"/></label>
        <form:errors path="question" cssClass="error" /></div>

        <div><label class="formAddQuestion">Wpisz odpowiedź A: <form:textarea rows="5" cols="80" path="answer1"/></label>
        <form:errors path="answer1" cssClass="error" /></div>

        <div><label class="formAddQuestion">Wpisz odpowiedź B: <form:textarea rows="5" cols="80" path="answer2"/></label>
        <form:errors path="answer2" cssClass="error" /></div>

        <div><label class="formAddQuestion">Wpisz odpowiedź C: <form:textarea rows="5" cols="80" path="answer3"/></label>
        <form:errors path="answer3" cssClass="error" /></div>

        <div><label class="formAddQuestion">Wpisz odpowiedź D: <form:textarea rows="5" cols="80" path="answer4"/></label>
        <form:errors path="answer4" cssClass="error" /></div>






    </form:form>

</div>
<%@ include file="../fragments/footer.jspf" %>

</body>
</html>
