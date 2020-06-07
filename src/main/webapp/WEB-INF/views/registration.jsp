<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="fragments/head.jspf" %>
<body>

<div id="container">
    <%@ include file="fragments/header.jspf" %>
    <%@ include file="fragments/sidebar.jspf" %>

    <div id="content">
        <h1>Rejestracja konta</h1>
        <span class="error">
        <c:if test="${invalid==true}">
            ${message}
        </c:if></span>
        <form:form method="post" modelAttribute="user">

            <div><label class="formAddQuestion">
                <div>Podaj login</div>
                <div><form:input path="login"/>
                    <form:errors path="login" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Podaj hasło:</div>
                <div><form:password path="password"/>
                    <form:errors path="password" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Powtórz hasło</div>
                <div><form:password path="password2"/>
                    <form:errors path="password2" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Imię:</div>
                <div><form:input path="firstName"/>
                    <form:errors path="firstName" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Nazwisko:</div>
                <div><form:input path="lastName"/>
                    <form:errors path="lastName" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Miasto:</div>
                <div><form:input path="city"/>
                    <form:errors path="city" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Ulica:</div>
                <div><form:input path="street"/>
                    <form:errors path="street" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Numer domu:</div>
                <div><form:input path="homeNumber"/>
                    <form:errors path="homeNumber" cssClass="error"/></div>
            </label></div>

            <div><label class="formAddQuestion">
                <div>Email:</div>
                <div><form:input path="email"/>
                    <form:errors path="email" cssClass="error"/></div>
            </label></div>

            <input type="submit" value="Zarejestruj się">
        </form:form>
    </div>

    <div style="clear:both;"></div>
    <%@ include file="fragments/footer.jspf" %>

</div>
</body>
</html>
