<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="fragments/head.jspf" %>
<body>

<div id="container">
    <%@ include file="fragments/header.jspf" %>
    <%@ include file="fragments/sidebar.jspf" %>

    <div id="content">
<p>Slajd numer ${numberImage}</p>
<p>
    <a href="/course/${numberImage-1}">Poprzedni</a><span>---------</span><a href="/course/${numberImage+1}">Następny</a>
</p>
        <img src="img/${numberImage}.png">
        


    </div>

    <div style="clear:both;"></div>
    <%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
