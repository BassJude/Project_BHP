<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div id="container">
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/sidebar.jspf" %>

    <div id="content">
<h1>Status egzaminu</h1>
        <p>Czy kurs został zaliczony ->
            <c:choose>
                <c:when test="${status==true}">
                    Kurs zaliczony.
                </c:when>


                <c:otherwise>
                Kurs niezaliczony.
                </c:otherwise>
            </c:choose>
        </p>
<p>Data zaliczenia kursu -> <c:out value="${time}" default="brak" /> </p>


    </div>

    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
