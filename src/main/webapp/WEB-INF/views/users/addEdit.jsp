<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<div>
    <%@ include file="../fragments/header.jspf" %>





        <h1>Dodaj, Edycja</h1>


            <p>Id zmienianego usera: ${user.id}</p>
            <form:form method="post" modelAttribute="user">

                <%--<div><label class="formAddQuestion"> <form:hidden path="login" /></label></div>--%>
                <%--<form:errors path="login" cssClass="error" element="div"/>--%>

                <%--<div><label class="formAddQuestion"> <form:hidden path="password"/></label></div>--%>
                <%--<form:errors path="password" cssClass="error" element="div"/>--%>

                <div><label class="formAddQuestion">Imię: <form:input path="firstName"/></label></div>
                <form:errors path="firstName" cssClass="error" element="div"/>

                <div><label class="formAddQuestion">Nazwisko: <form:input path="lastName"/></label></div>
                <form:errors path="lastName" cssClass="error" element="div"/>

                <div><label class="formAddQuestion">Miasto: <form:input path="city"/></label></div>
                <form:errors path="city" cssClass="error" element="div"/>

                <div><label class="formAddQuestion">Ulica: <form:input path="street"/></label></div>
                <form:errors path="street" cssClass="error" element="div"/>

                <div><label class="formAddQuestion">Numer domu: <form:input path="homeNumber"/></label></div>
                <form:errors path="homeNumber" cssClass="error" element="div"/>

                <div><label class="formAddQuestion">Email: <form:input path="email"/></label></div>
                <form:errors path="email" cssClass="error" element="div"/>

                <div><label class="formAddQuestion">Czy zdany egzamin: <form:checkbox path="passedEgzam"/></label></div>
                <form:errors path="passedEgzam" cssClass="error" element="div"/>

                <div><label class="formAddQuestion">Czy jest superUserem <form:checkbox path="superUser"/></label></div>
                <form:errors path="superUser" cssClass="error" element="div"/>

                <form:hidden path="lastTestTime"></form:hidden>
                <form:errors path="lastTestTime" cssClass="error" element="div"/>


                <input type="submit" value="Zapisz">


            </form:form>





    <div style="clear:both;"></div>
    <%@ include file="../fragments/footer.jspf" %>

</div>
</body>
</html>
