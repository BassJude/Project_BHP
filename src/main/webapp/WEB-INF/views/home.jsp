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

<span>

            <c:choose>
                <c:when test="${registration==true}">
                    ${message}
                </c:when>

                <c:when test="${changes==true}">
                    ${message}
                </c:when>
                <c:otherwise>
                    <h1>Witamy na kursie online</h1>
                    <p>Zapraszamy do zapoznania się z instrukcjami dotyczącymi zaliczenia kursu.</p>
                </c:otherwise>
            </c:choose>



            </span>


    </div>

    <div style="clear:both;"></div>
    <%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
