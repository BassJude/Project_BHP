<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 24.02.19
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>Pytanie numer ${number+1}, ilość wszystkich pytań: ${size}.</p>
<form action="/questions/test/${number+1}" method="post">


<%--<c:forEach items="question" var="q">--%>
    <div>${question.question}</div>
    <div><input type="radio" name="answer" value="A">${question.answer1}</div>
    <div><input type="radio" name="answer" value="B">${question.answer2}</div>
    <div><input type="radio" name="answer" value="C">${question.answer3}</div>
    <div><input type="radio" name="answer" value="D">${question.answer4}</div>
    <div><input type="submit" value="Zatwiedź"></div>



<%--</c:forEach>--%>

</form>


</body>
</html>
