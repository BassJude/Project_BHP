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
        <form style="text-align: center" action="${pageContext.request.contextPath}/course/number" method="post">
            <input style="width: 40px; text-align: right; font-size: 15px;" min="1" max="${images}" type="number"
                   name="number" value="${numberImage}"> / ${images}
            <input type="submit" value="Wczytaj slajd">
        </form>

        <p style="text-align: center">
            <a href="${pageContext.request.contextPath}/course/${numberImage-1}">Poprzedni</a><span>---------</span><a
                href="${pageContext.request.contextPath}/course/${numberImage+1}">Następny</a>
        </p>
        <img width="700" height="500" src="${pageContext.request.contextPath}/slides/Slajd${numberImage}.PNG">
    </div>

    <div style="clear:both;"></div>
    <%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
