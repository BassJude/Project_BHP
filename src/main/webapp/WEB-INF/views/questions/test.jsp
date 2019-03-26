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
        <p style="border-bottom: 1px solid #4d83e7;"></p>


        <form action="${pageContext.request.contextPath}/questions/test/${number+1}" method="post">

            <div style="margin-bottom: 30px">${question.question}</div>
            <c:if test="${number+1==12}">
                <img width="130" height="129" src="/img/piktogram.png">

            </c:if>

            <div style="margin-bottom: 30px"><label>A<input type="radio" name="answer" value="A">${question.answer1}</label></div>
            <div style="margin-bottom: 30px"><label>B<input type="radio" name="answer" value="B">${question.answer2}</label></div>
            <div style="margin-bottom: 30px"><label>C<input type="radio" name="answer" value="C">${question.answer3}</label></div>
            <div style="margin-bottom: 30px"><label>D<input type="radio" name="answer" value="D">${question.answer4}</label></div>
            <div style="margin-bottom: 30px"><input type="submit" value="Zatwiedź"></div>

        </form>


    </div>

    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
