
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div id="container">
    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/sidebar.jspf" %>

    <div id="content">

        <h1>Wyniki testu:</h1>
        <p>Ilość poprawnych odpowiedzi : ${points}</p>
        <p>Ilość wszystkich pytań : ${size}</p>
        <p>Egzamin  ${evaluation}</p>
        <div>-------------------------------------</div>
        <p>Jeżeli zaliczyłeś egzamin, możesz już się wylogować, w przeciwnym wypadku spróbuj ponownie -> <a href="/questions/test">Zacznij test</a></p>


    </div>

    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
