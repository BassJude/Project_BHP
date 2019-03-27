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
<p>Slajd numer ${numberImage} / 37</p>
<p>
    <a href="${pageContext.request.contextPath}/course/${numberImage-1}">Poprzedni</a><span>---------</span><a href="${pageContext.request.contextPath}/course/${numberImage+1}">Następny</a>
</p>
        <img width="700" height="500" src="${pageContext.request.contextPath}/img/Slajd${numberImage}.PNG">
        


    </div>

    <div style="clear:both;"></div>
    <%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
