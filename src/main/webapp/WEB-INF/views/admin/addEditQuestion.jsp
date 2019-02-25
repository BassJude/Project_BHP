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
<%@ include file="../fragments/sidebar.jspf" %>

<h1>Dodaj pytanie</h1>

<div>

    <form:form method="post" modelAttribute="question">

        <div><label class="formAddQuestion"> Wpisz pytanie: <form:textarea rows="5" cols="20" path="question"/></label></div>
        <form:errors path="question" cssClass="error" element="div"/>

        <div><label class="formAddQuestion">Wpisz odpowiedź A: <form:input path="answer1"/></label></div>
        <form:errors path="answer1" cssClass="error" element="div"/>

        <div><label class="formAddQuestion">Wpisz odpowiedź B: <form:input path="answer2"/></label></div>
        <form:errors path="answer2" cssClass="error" element="div"/>

        <div><label class="formAddQuestion">Wpisz odpowiedź A: <form:input path="answer3"/></label></div>
        <form:errors path="answer3" cssClass="error" element="div"/>

        <div><label class="formAddQuestion">Wpisz odpowiedź A: <form:input path="answer4"/></label></div>
        <form:errors path="answer4" cssClass="error" element="div"/>

        <div><label class="formAddQuestion">Wybierz prawidłową odpowiedź:
            <form:select path="good_answer"  >
            <form:option value="0" label="--Wybierz odpowiedź--"/>
            <form:options items="${abcd}"/>
            </form:select></label></div>
        <form:errors path="good_answer" cssClass="error" element="div"/>

        <input type="submit" value="Zapisz">


    </form:form>

</div>
<%@ include file="../fragments/footer.jspf" %>

</body>
</html>
