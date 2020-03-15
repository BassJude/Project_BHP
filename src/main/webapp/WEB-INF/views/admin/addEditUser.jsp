<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div>
    <div id="container">
        <%@ include file="../fragments/header.jspf" %>
        <%@ include file="../fragments/sidebar.jspf" %>
        <div id="content">

            <h1>Dodaj lub edytuj użytkownika</h1>

            <p>Id zmienianego usera: ${user.id}</p>

            <form:form method="post" modelAttribute="user">

                <div><label class="formAddQuestion">Imię: <form:input path="firstName"/></label>
                    <form:errors path="firstName" cssClass="error"/></div>

                <div><label class="formAddQuestion">Nazwisko: <form:input path="lastName"/></label>
                    <form:errors path="lastName" cssClass="error"/></div>

                <div><label class="formAddQuestion">Miasto: <form:input path="city"/></label>
                    <form:errors path="city" cssClass="error"/></div>

                <div><label class="formAddQuestion">Ulica: <form:input path="street"/></label>
                    <form:errors path="street" cssClass="error"/></div>

                <div><label class="formAddQuestion">Numer domu: <form:input path="homeNumber"/></label>
                    <form:errors path="homeNumber" cssClass="error"/></div>

                <div><label class="formAddQuestion">Email: <form:input path="email"/></label>
                    <form:errors path="email" cssClass="error"/></div>

                <div><label class="formAddQuestion">Czy zdany egzamin: <form:checkbox path="passedEgzam"/></label>
                    <form:errors path="passedEgzam" cssClass="error"/></div>

                <div><label class="formAddQuestion">Czy jest superUserem <form:checkbox path="superUser"/></label>
                    <form:errors path="superUser" cssClass="error"/></div>


                <input type="submit" value="Zapisz">


            </form:form>


            <div style="clear:both;"></div>
        </div>
        <%@ include file="../fragments/footer.jspf" %>

    </div>
</div>
</body>
</html>
