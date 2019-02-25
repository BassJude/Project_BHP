<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="fragments/head.jspf" %>
<body>

<div id="container">
    <%@ include file="fragments/header.jspf" %>
    <%@ include file="fragments/sidebar.jspf" %>

    <div id="content">
<span>
        <c:if test="${registration==true}">
            ${message}
        </c:if></span>
        <c:if test="${changes==true}">
            ${message}
        </c:if>


    </div>

    <div style="clear:both;"></div>
<%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
