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

        <h1>Informacje o nas:</h1>
        <h2>Jan Kowalski</h2>
        <h3>ul. Dobra 38</h3>
        <h3>04-081 Warszawa</h3>
        <h3>telefon: 123456789</h3>
        <h3>e-mail: mail@mail.pl</h3>


    </div>

    <div style="clear:both;"></div>
    <%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
