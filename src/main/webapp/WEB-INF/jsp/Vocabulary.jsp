<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: влад
  Date: 28.04.2019
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <title>Словарь</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/form/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/form/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/form/myScripts.js"></script>
</head>
<body>
<form id="forma1" method="post">
    <input id='flow' type="hidden" value='${flowExecutionUrl}'>
    <input id="keys" name="keys" type="hidden" value="">
    <input id="words" name="words" type="hidden" value="">
    <input id="ID" name="ID" type="hidden" value="">
    <input id="kID" name="kID" type="hidden" value="">
    <button type="submit" name="_eventId_reload" style="margin-left:0;color:red">Перезагрузить</button>
    <button type="submit" name="_eventId_backPage" style="margin-left: 10px;float:right;color:red">Назад</button>
    <tiles:insertAttribute name="voc"/>
</form>
</body>
</html>