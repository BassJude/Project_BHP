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

        <form action="/users/login" method="post">

            <div>Podaj login: <input type="text" name="login" placeholder="login" value="${login}">
            <span class="error"><c:if test="${loginInvalid==true}">${messageLogin}</c:if></span></div>
            <div>Podaj hasło: <input type="password" name="pass" placeholder="hasło" value="${pass}">
                <span class="error" ><c:if test="${passInvalid==true}">${messagePass}</c:if></span>
            <div><input type="submit" value="Zaloguj się"></div></div>


        </form>


    </div>

    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
