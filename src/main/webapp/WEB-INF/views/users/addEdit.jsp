<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div>

    <%@ include file="../fragments/header.jspf" %>
    <%@ include file="../fragments/sidebar.jspf" %>

    <h1>Edycja profilu</h1>

    <form:form method="post" modelAttribute="user">

        <div><label class="formAddQuestion">Imię: <form:input path="firstName"/></label>
        <form:errors path="firstName" cssClass="error" /></div>

        <div><label class="formAddQuestion">Nazwisko: <form:input path="lastName"/></label>
        <form:errors path="lastName" cssClass="error" /></div>

        <div><label class="formAddQuestion">Miasto: <form:input path="city"/></label>
        <form:errors path="city" cssClass="error" /></div>

        <div><label class="formAddQuestion">Ulica: <form:input path="street"/></label>
        <form:errors path="street" cssClass="error" /></div>

        <div><label class="formAddQuestion">Numer domu: <form:input path="homeNumber"/></label>
        <form:errors path="homeNumber" cssClass="error" /></div>

        <div><label class="formAddQuestion">Email: <form:input path="email"/></label>
        <form:errors path="email" cssClass="error" /></div>

        <input type="submit" value="Zapisz">


    </form:form>


    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
