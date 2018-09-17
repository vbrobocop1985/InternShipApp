<%@ page import="com.test.parts.dao.PartsDAO" %>
<%@ page import="com.test.parts.dao.PartsDAOImpl" %>
<%@ page import="com.test.parts.service.PartsServiceImpl" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.test.parts.model.Parts" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.parts.service.PartsService" %>
<%@ page import="com.test.parts.controller.PartsController" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PartsList App</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<div class="header">
    <div class="header-panel add-part">
        <form action="createPart">
            <input type='submit' value='Новая деталь'/>
        </form>
    </div>
    <div class="header-panel filter-part">
        <form action="filterPart">
            <input type="text" name="filterName" placeholder="Type a partname here...">
            <input type='submit' value='Найти по имени'/>
        </form>
    </div>
    <div class="header-panel sort-part">
        <form action="sortNeedParts">
            <input name="sortNeedPart" type="radio" value="2"> Все детали
            <input name="sortNeedPart" type="radio" value="1"> Необходимые детали
            <input name="sortNeedPart" type="radio" value="0"> Опциональные детали
            <input type='submit' value='Сортировать'/>
        </form>
    </div>
</div>
<div class="content">

    <div class="table-of-parts">
        <table>
            <thead>
            <tr>
                <th>ИД</th>
                <th>Наименование</th>
                <th>Главная деталь</th>
                <th>Кол-во</th>
                <th>Тип детали</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${partsList}" var="part">
                <tr>
                    <td><c:out value="${part.id}" /></td>
                    <td><c:out value="${part.partname}" /></td>
                    <td><c:out value="${part.partbase}" /></td>
                    <td><c:out value="${part.partqty}" /></td>
                     <c:choose>
                        <c:when test="${part.parttype == 1}">
                            <td><c:out value="1-Материнская плата" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 2}">
                            <td><c:out value="2-Процессор" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 3}">
                            <td><c:out value="3-Память" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 4}">
                            <td><c:out value="4-Жесткий диск" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 5}">
                            <td><c:out value="5-Корпус" /></td>
                        </c:when>
                        <c:when test="${part.parttype == 6}">
                            <td><c:out value="6-Опционная деталь" /></td>
                        </c:when>
                    </c:choose>
                    <th><a href="editPart?id=<c:out value='${part.id}'/>">Редакт</a></th>
                    <th><a href="deletePart?id=<c:out value='${part.id}'/>">Удалить</a></th>
                </tr>
            </c:forEach>
            </tbody>
          </table>
    </div>
    <div class="pagination">
        <c:url value="/" var="prev" >
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">«</a>
        </c:if>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">»</a>
        </c:if>
    </div>
</div>
<%
    int cComp=0;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        PreparedStatement ps=con.prepareStatement("select parttype,sum(partqty) as qty from parts where partbase=1 and parttype <> 6 group by parttype");
        ResultSet rs=ps.executeQuery();
        int cParts = 1;

        rs.next();
        cComp = rs.getInt(2);
        while(rs.next()){
            cParts++;
            if (cComp>rs.getInt(2)){
                cComp = rs.getInt(2);
            }
        }
        if (cParts!=5)
        {
            cComp = 0;
        }
        con.close();
    }catch(Exception e){e.printStackTrace();}

%>
<div class="content">
  <div class="table-of-parts">
    <table>
        <tbody>
        <td><c:out value="Можно собрать" /></td>
        <td><c:out value = "<%=cComp%>" /></td>
        <td><c:out value="компьютеров" /></td>
        </tbody>
    </table>
  </div>
</div>


</body>
</html>
