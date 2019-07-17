<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/choicePage.css"/>
<body id="main">
<form method="post">
        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
        <br>
        <b>Выберите словарь</b>
        <br>
        <button type="submit" class="button" name="_eventId_nextRus"><img class="main" src="${pageContext.request.contextPath}/images/rus.png"/></button>
        <br>
        <button style="margin-left:16px" type="submit" class="button" name="_eventId_nextBin"><img  class="main" src="${pageContext.request.contextPath}/images/Bin.jpg"/></button>
</form>
</body>