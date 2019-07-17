<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/choicePage.css"/>
</head>
<body id="voc">
<form method="post">
    <div id="first"><c:choose><c:when test="${type.type.type == 'Rus-Lat'}"><h1 style=" float: left;margin-left: -166px;width: 800px;">Вы выбрали Латинско-Русский словарь</h1></c:when><c:when test="${type.type.type == 'Bin-Dec'}"><h1 style=" float: left;margin-left: -166px;width: 800px;">Вы выбрали цифро-буквенный словарь</h1></c:when></c:choose></div>
    <input id="inp"  type="hidden" name="inputNameRus" value=""/>
    <input type="hidden" name="_flowExecutionKey"/>
    <br>
    <button id="returnMainPage"  type="submit" class="button" name="_eventId_next">Главная страница</button>
    <c:choose><c:when test="${type.type.type == 'Rus-Lat'}"><button type="submit" class="button" name="_eventId_nextBin">другой словарь</button></c:when><c:when test="${type.type.type == 'Bin-Dec'}"><button type="submit" class="button" name="_eventId_nextRus">другой словарь</button></c:when></c:choose>
    <button id="openVoc"  type="submit" class="button" name="_eventId_voc">Словарь</button>
</form>
</body>
</html>
