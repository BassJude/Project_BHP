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
        <h1>Logowanie</h1>
        <form action="${pageContext.request.contextPath}/login" method="post">

            <div><label class="formAddQuestion">
                <div>Podaj login:</div>
                <div><input type="text" name="login" placeholder="login" value="${login}">
                    <span class="error"><c:if test="${loginInvalid==true}">${messageLogin}</c:if></span></div>
            </label></div>
            <div><label class="formAddQuestion">
                <div>Podaj hasło:</div>
                <div><input type="password" name="pass" placeholder="hasło" value="${pass}">
                    <span class="error"><c:if test="${passInvalid==true}">${messagePass}</c:if></span></div>
            </label></div>
            <div><input type="submit" value="Zaloguj się"></div>

        </form>
    </div>

    <div style="clear:both;"></div>
    <%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
