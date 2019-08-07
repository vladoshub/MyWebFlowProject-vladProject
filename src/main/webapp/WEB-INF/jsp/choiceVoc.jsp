<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/choicePage.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/form/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/form/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/form/myScripts.js"></script>
</head>
<body id="voc">
<form method="post">
    <div id="first"><c:choose><c:when test="${type.type.type == 'Rus-Lat'}"><h1 style=" float: left;margin-left: -166px;width: 800px;">Вы выбрали Латинско-Русский словарь</h1></c:when><c:when test="${type.type.type == 'Bin-Dec'}"><h1 style=" float: left;margin-left: -166px;width: 800px;">Вы выбрали цифро-буквенный словарь</h1></c:when></c:choose></div>
    <input id="inp"  type="hidden" name="inputNameRus" value=""/>
    <input type="hidden" name="_flowExecutionKey"/>
    <br>
    <button id="returnMainPage"  type="submit" class="button" name="_eventId_next">Главная страница</button>
    <button type="button" class="button" name="_eventId_nextBin"  onclick="hiddRow('hidden')">другой словарь</button>
    <div id="hidden">
    <c:forEach items="${type.typeList}" var="items">
    <c:choose><c:when test="${items.type == 'Bin-Dec' and items.id!=type.type.id}"><button id="${items.id}"  name="_eventId_nextBin" style="display: none;">Цифровой словарь</button><br></c:when><c:when test="${items.type == 'Rus-Lat' and items.id!=type.type.id}"><button id="${items.id}" type="submit" class="button" name="_eventId_nextRus" style="display: none;">Буквенный словарь</button></c:when></c:choose>
    </c:forEach>
    </div>
    <button id="openVoc"  type="submit" class="button" name="_eventId_voc">Словарь</button>
</form>
</body>
</html>
