<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div id="container">
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/sidebar.jspf" %>

    <div id="content">


        <p>Pytanie numer ${number+1}, ilość wszystkich pytań: ${size}.</p>
        <form action="/questions/test/${number+1}" method="post">

            <div>${question.question}</div>
            <div><input type="radio" name="answer" value="A">${question.answer1}</div>
            <div><input type="radio" name="answer" value="B">${question.answer2}</div>
            <div><input type="radio" name="answer" value="C">${question.answer3}</div>
            <div><input type="radio" name="answer" value="D">${question.answer4}</div>
            <div><input type="submit" value="Zatwiedź"></div>

        </form>


    </div>

    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
