<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PartsList App</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>
</head>
<body>
<div class="form-content">
    <form:form id="PartRegisterForm" modelAttribute="parts" method="post" action="savePart">
        <div class="form-group">
            <div class="form-row">
                <form:label path="partname">Наименование</form:label>
            </div>
            <form:hidden path="id" value="${partsObject.id}"/>
            <form:input path="partname" value="${partsObject.partname}"/>
        </div>
        <div class="form-group">
            <div class="form-row">
                <form:label path="partbase">Необходимость</form:label>
            </div>
            <form:input path="partbase" value="${partsObject.partbase}"/>
        </div>
        <div class="form-group">
            <div class="form-row">
                <form:label path="partqty">Кол-во</form:label>
            </div>
            <form:input path="partqty" value="${partsObject.partqty}"/>
        </div>
        <div class="form-group">
            <div class="form-row">
                <form:label path="parttype">Тип детали</form:label>
            </div>
            <form:input path="parttype" value="${partsObject.parttype}"/>
        </div>
        <div>
            <label>1 - Материнская плата</label>
            <label>2 - Процессор</label>
            <label>3 - Память</label>
            <label>4 - Жесткий диск</label>
            <label>5 - Корпус</label>
            <label>6 - Опционная деталь</label>
        </div>
        <div class="form-group">
            <div class="form-submit-button">
                <input type="submit" id="savePart" value="Сохранить" onclick="return submitPartForm();"/>
            </div>
        </div>
    </form:form>
</div>
<div id="review_form_overlay"></div>

</body>
</html>
