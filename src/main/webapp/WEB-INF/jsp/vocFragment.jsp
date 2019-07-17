<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="disabled" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: влад
  Date: 20.05.2019
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="voc">
    <table border="0"  width="100%" cellpadding="5" class="one">
        <tr>
            <th><input id="searchkey" name="searchkey"  type="text" value=""><button type="submit" name="_eventId_SearchKeys" title="Поиск по ключу" style="background: url(/images/search.png);
    background-size: 38px 35px;" ></button></th>
            <th><input id="searchwrod" name="searchword" type="text" value=""><button type="submit" name="_eventId_SearchWords" style="background: url(/images/search.png);
    background-size: 38px 35px;" title="Поиск по слову"></button></th>
            <th><button id="ButAdd" type="button" onclick="addKey('table')" title="Добавить ключ" style="background: url(/images/add.png);background-size: 38px 35px;float:right;margin-left:10px"></button><span style="color: red">
<c:if test="${not empty out}"><c:if test="${out.addKeyValid == true}">${out.outMess}</c:if>
</c:if></span></th>
        </tr>
    </table>
    <table border="0"  width="100%" cellpadding="5" class="two">
        <thead>
        <tr >
            <th style="padding-left:15px"><b>Ключи</b></th>
            <th style="padding-left:15px"><b>Значения</b></th>
            <th style="padding-left:147px"><b>Действия</b></th>
        </tr>
        </thead>
        <c:if test="${not empty out.keys}">
            <c:forEach items="${out.keys}" var="items">
                <tr id="${items.id}">
                    <td><input type="text" name="${items.id}"  value="${items.key}" id="${items.id}inpK"/><span style="color: red">
                        <c:if test="${items.id == out.keysDtos.id and out.wordsDtos==null}">${out.outMess}</c:if>
                    </span></td>
                    <c:choose>
                        <c:when test="${items.words.size()==0}"><td></td></c:when>
                        <c:when
                            test="${items.words.size()==1}">
                        <td>
                            <input type="text"  value="${items.words.get(0).word}" readonly="readonly" id="${items.id}inpOO"/><span style="color: red">
                        <c:if test="${items.words.get(0).id == out.wordsDtos.id}">${out.outMess}</c:if>
                    </span>
                            <button type="button" onclick="hiddRow('${items.id}O')" style="background: url(/images/any.png);
    background-size: 38px 35px;" title="выбор слов"></button>
                        </td>
                    </c:when><c:when test="${items.words.size()>1}">
                        <td>
                            <input type="text" value="${items.words.get(0).word}..." readonly="readonly"/><span  style="color: red">
                        <c:if test="${items.id == out.keysDtos.id and out.wordsDtos!=null}">${out.outMess}</c:if>
                    </span>
                            <button type="button" onclick="hiddRow('${items.id}M')" style="background: url(/images/any.png);
    background-size: 38px 35px;" title="выбор слов"></button>
                        </td>

                    </c:when></c:choose>
                    <td>
                        <button type="button" name="_eventId_editKey" onclick="editKeyOrSingleWord('${items.id}inpK','keys','${items.key}')" style="background: url(/images/edit.png);
    background-size: 38px 35px;" title="изменить ключ"></button>
                        <button type="button" name="_eventId_deletedKey" onclick="deleteKeyOrSingleWord('${items.id}inpK','keys')" style="background: url(/images/del.png);
    background-size: 38px 35px;" title="удалить"></button>
                        <button type="button" onclick="addWord('${items.id}t2',${items.id})" style="background: url(/images/add.png);
    background-size: 38px 35px;" title="добавить слово" id="butAddOneWord${items.id}" name="WordsInK"></button>
                        <button type="button" onclick="addAllWord('${items.id}t2',${items.id})" style="background: url(/images/addAllBW.png);
    background-size: 38px 35px" disabled="disabled"  title="добавить все слова" id="butAllWord${items.id}" name="_editManyWordsInKey"></button>
                    </td>
                </tr>
                <c:if test="${items.words.size()==1}">
                    <tr id="${items.id}O" style="display: none;">
                        <td style="border-top:0"></td>
                        <td style="border-top:0"><input id="${items.words.get(0).id}inpO" type="text" name="${items.words.get(0).id}"  value="${items.words.get(0).word}"/>
                            <span style="color: red">
                        <c:if test="${items.words.get(0).id == out.wordsDtos.id}">${out.outMess}</c:if>
                    </span>
                        </td>
                        <td style="border-top:0">
                            <button type="button" name="_eventId_editWord" onclick="editKeyOrSingleWord('${items.words.get(0).id}inpO','words','${items.words.get(0).word}')" style="background: url(/images/edit.png);
    background-size: 38px 35px;" title="изменить"></button>
                            <button type="button"  name="_eventId_deletedWord" onclick="deleteKeyOrSingleWord('${items.words.get(0).id}inpO','words')" style="background: url(/images/del.png);
    background-size: 38px 35px;" title="удалить"></button>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${items.words.size()>1}">
                    <tbody id="${items.id}M" style="display: none;">
                    <c:forEach items="${items.words}" var="words">
                        <tr>
                            <td style="border-top:0"></td>
                            <td style="border-top:0"><input id="${words.id}inpM" type="text" name="${words.id}"  value="${words.word}"/>
                                <span style="color: red">
                        <c:if test="${words.id == out.wordsDtos.id}">${out.outMess}</c:if>
                    </span>
                            </td>
                            <td style="border-top:0">
                                <button type="button" name="_eventId_editWord" onclick="editWords('${words.id}inpM','${items.id}','${words.word}')" style="background: url(/images/edit.png);
    background-size: 38px 35px;" title="изменить"></button>
                                <button type="button" name="_eventId_deletedWord" onclick="deleteWords('${words.id}inpM','${items.id}')" style="background: url(/images/del.png);
    background-size: 38px 35px;" title="удалить"></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:if>
                <tbody id="${items.id}t2">
                </tbody>
            </c:forEach>
        </c:if>
        <tbody id="table">
        </tbody>
    </table>
</div>
