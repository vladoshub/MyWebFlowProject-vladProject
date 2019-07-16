var rows = 0;
var rows1 = 0;
var wordInKey = 0;
var check = 0;

function editKeyOrSingleWord(id, op, newword) {
    newword = newword + "";
    if (newword == document.getElementById(id).value || newword == "") {
        alert("введите что-нибудь или измененное значение");
    }
    else {
        check = 0;
        if ((op + '') == 'keys') {
            document.getElementById("keys").value = document.getElementById(id).value;
            document.getElementById("ID").value = document.getElementById(id).name;
            $("#forma1").ajaxSubmit({

                url: "${flowExecutionUrl}&_eventId_editKey&ajaxSource=true",
                success: function (html) {
                    $("#voc").html($(html).filter("#voc")),
                        $("#answer").html($(html).filter("#answer"));
                },
                error: function (error) {
                    console.log(error)
                }
            });
        }
        else {
            document.getElementById("words").value = document.getElementById(id).value;
            document.getElementById("ID").value = document.getElementById(id).name;
            $("#forma1").ajaxSubmit({

                url: "${flowExecutionUrl}&_eventId_editWord&ajaxSource=true",
                success: function (html) {
                    $("#voc").html($(html).filter("#voc")),
                        $("#answer").html($(html).filter("#answer"));
                },
                error: function (error) {
                    console.log(error)
                }
            });
        }
    }
}


function hiddRow(id) {
    $('#' + id).toggle();
}


function deleteKeyOrSingleWord(id, op) {
    check = 0;
    if ((op + '') == 'keys') {
        $("#ID").val($("#"+id).attr('name'));
        $("#forma1").ajaxSubmit({

            url: $("#flow").val()+"&_eventId_deletedKey&ajaxSource=true",
            success: function (html) {
                $("#voc").html($(html).filter("#voc")),
                    $("#answer").html($(html).filter("#answer"));

            },
            error: function (error) {
                console.log(error)
            }
        });
    }
    else {

        $("#ID").val($("#"+id).attr('name'));
        $("#forma1").ajaxSubmit({

            url: "${flowExecutionUrl}&_eventId_deletedWord&ajaxSource=true",
            success: function (html) {
                $("#voc").html($(html).filter("#voc")),
                    $("#answer").html($(html).filter("#answer"));
            },
            error: function (error) {
                console.log(error)
            }
        });
    }

}

function addAllWord(id, idKey) {
    check = 0;
    var words = "";
    var x = document.getElementById(id).getElementsByTagName('input');
    for (var i = 0; i < x.length; i++) {
        if (x[i].value != "") {
            words = words + x[i].value + "#_#";
        }
    }
    if (words != "") {
        words = words.substring(0, words.length - 3);
        document.getElementById("words").value = words;
        document.getElementById("ID").value = idKey;
        $("#forma1").ajaxSubmit({
            url: "${flowExecutionUrl}&_eventId_editManyWordsInKey&ajaxSource=true",
            success: function (html) {
                $("#voc").html($(html).filter("#voc")),
                    $("#answer").html($(html).filter("#answer"));
            },
            error: function (error) {
                console.log(error)
            }
        });
    }
    else {
        alert("введите что-нибудь")
    }
}

function addWord(id, idKey) {
    if (check == 0) {
        $("button[name~='WordsInK']").toggle();
        $('#butAddOneWord' + idKey).toggle();
    }
    check++;
    if (check == 2) {
        $('#butAllWord' + idKey).attr("style", "background: url(/images/addAll.png);background-size:38px 35px");
        $('#butAllWord' + idKey).attr("disabled", false);
    }
    var a = "rows" + rows;
    rows++;
    var tbody = $('#' + id);
    var row = document.createElement("TR");
    var td1 = document.createElement("TD");
    var td2 = document.createElement("TD");
    var td3 = document.createElement("TD");
    var inp = document.createElement("input");
    var butSave = document.createElement("button");
    var butDel = document.createElement("button");
    td1.style = "border-top:0";
    td2.style = "border-top:0";
    td3.style = "border-top:0";
    inp.id = a + "input";
    inp.name = idKey + '';
    row.id = a + "R";
    butSave.title = "Сохранить";
    butSave.type = "button";
    butSave.name = "_eventId_addWordsss";
    butSave.style = "background: url(/images/upload.png);background-size: 38px 35px;";
    butDel.title = "Удалить";
    butDel.style = "background: url(/images/deleteKeyOrSingleWord.png);background-size: 38px 35px;";
    $(butDel).on('click',function () {
        $("#" + row.id).remove();
        check--;
        var x = $("#" + id).getElementsByTagName('input');
        if (check == 1) {
            $('#butAllWord' + idKey).attr("style", "background: url(/images/addAllBW.png);background-size:38px 35px");
            $('#butAllWord' + idKey).attr("disabled", true);
        }
        if (!x[0]) {
            check = 0;
            $("button[name~='WordsInK']").toggle();
            $('#butAddOneWord' + idKey).toggle();
            $('#butAllWord' + idKey).attr("style", "background: url(/images/addAllBW.png);background-size:38px 35px");
            $('#butAllWord' + idKey).attr("disabled", true);
        }
    });
    $(butSave).on('click',function () {
        check = 0;
        var id = a + "input";
        var id2 = a + "R";
        var text = document.getElementById(id).value;
        var text2 = document.getElementById(id).name;
        document.getElementById("words").value = text;
        document.getElementById("ID").value = text2;
        $("#forma1").ajaxSubmit({

            url: "${flowExecutionUrl}&_eventId_addWordsss&ajaxSource=true",
            success: function (html) {
                $("#voc").html($(html).filter("#voc")),
                    $("#answer").html($(html).filter("#answer"));
            },
            error: function (error) {
                console.log(error)
            }
        });
    });

    td2.appendChild(inp);
    td3.appendChild(butDel);
    td3.appendChild(butSave);
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.style = "background: deepskyblue;";
    tbody.appendChild(row);
}

function addKey(id) {
    var a = "rows1" + rows1;
    rows1++;
    var table = document.getElementById(id);
    var row = document.createElement("TR");
    var td1 = document.createElement("TD");
    var td2 = document.createElement("TD");
    var td3 = document.createElement("TD");
    var inp = document.createElement("input");
    var inp2 = document.createElement("input");
    var butSave = document.createElement("button");
    var butDel = document.createElement("button");
    var addWord = document.createElement("button");
    inp.id = a + "inputWord";
    inp2.id = a + "inputKey";
    row.id = a + "R";
    addWord.title = "Добавить слово";
    addWord.type = "button";
    addWord.style = "background: url(/images/addKey.png);background-size: 38px 35px;  margin-left:25px;";
    butSave.title = "Сохранить";
    butSave.type = "button";
    butSave.style = "background: url(/images/upload.png);background-size: 38px 35px; float:right; margin-left:10px;";
    butDel.title = "Удалить";
    butDel.style = "background: url(/images/deleteKeyOrSingleWord.png);background-size: 38px 35px;";
    butDel.onclick = function () {
        var id = a + "R";
        var x = document.getElementsByName("rowWord");
        while (x[0]) x[0].remove();//при обычном for длина массива будет динамически менятся при удалении элемента
        document.getElementById(id).remove();
        $('#ButAdd').attr('disabled', false);
        $('#ButAdd').attr('style', "background: url(/images/addKey.png);background-size: 38px 35px;float:right;margin-left:10px");
    }
    addWord.onclick = function () {
        var butDel = document.createElement("button");
        var id = wordInKey + "manyWord";
        butDel.onclick = function () {
            document.getElementById(id).remove();
        }
        butDel.style = "background: url(/images/deleteKeyOrSingleWord.png);background-size: 38px 35px;";
        var tdW = document.createElement("TD");
        var tdWInp = document.createElement("TD");
        var tdWBut = document.createElement("TD");
        tdW.style = "border-top:0";
        tdWInp.style = "border-top:0";
        tdWBut.style = "border-top:0";
        var inpW = document.createElement("input");
        inpW.setAttribute("name", "allInp");
        tdWBut.appendChild(butDel);
        var rowW = document.createElement("TR");
        rowW.setAttribute("name", "rowWord");
        rowW.setAttribute("id", id);
        wordInKey = wordInKey + 1;
        tdWInp.appendChild(inpW);
        rowW.appendChild(tdW);
        rowW.appendChild(tdWInp);
        rowW.appendChild(tdWBut);
        rowW.style = "background: LIGHTSEAGREEN";
        table.appendChild(rowW);


    }

    butSave.onclick = function () {
        check = 0;
        var words = a + "inputWord";
        var id2 = a + "R";
        var keys = a + "inputKey";
        var allW = "";
        var word2 = document.getElementById(words).value;
        var key2 = document.getElementById(keys).value;
        var x = document.getElementsByName("allInp");
        for (var i = 0; i < x.length; i++) {
            if (x[i].value != "") {
                allW = allW + x[i].value + "#_#";
            }
        }
        if (allW != "") {
            allW = allW.substring(0, allW.length - 3);
            butSave.name = "_addManyWordsAndKey";
            document.getElementById("words").value = word2 + "#_#" + allW;
            document.getElementById("keys").value = key2;
            $("#forma1").ajaxSubmit({
                url: "${flowExecutionUrl}&_eventId_addManyWordsAndKey&ajaxSource=true",
                success: function (html) {
                    $("#voc").html($(html).filter("#voc")),
                        $("#answer").html($(html).filter("#answer"));
                },
                error: function (error) {
                    console.log(error)
                }
            });
        }
        else {
            if (word2 == "") {
                alert("введите слово")

            }
            else {
                if (key2 == "") {
                    alert("введите ключ ")
                }
                else {
                    butSave.name = "_eventId_addWordss";
                    document.getElementById("words").value = word2;
                    document.getElementById("keys").value = key2;
                    $("#forma1").ajaxSubmit({
                        url: "${flowExecutionUrl}&_eventId_addWordss&ajaxSource=true",
                        success: function (html) {
                            $("#voc").html($(html).filter("#voc")),
                                $("#answer").html($(html).filter("#answer"));
                        },
                        error: function (error) {
                            console.log(error)
                        }
                    });
                }

            }
        }
    }

    td1.appendChild(inp2);
    td2.appendChild(inp);
    td3.appendChild(butDel);
    td3.appendChild(butSave);
    td3.appendChild(addWord);
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.style = "background: lightblue;";
    table.appendChild(row);
    table.appendChild(row);
    $('#ButAdd').attr('disabled', true);
    $('#ButAdd').attr('style', "background: url(/images/addKeyBW.png);background-size: 38px 35px;float:right;margin-left:10px");
}


function editWords(id, id2, newword) {
    newword = newword + "";
    if (newword == document.getElementById(id).value || newword == "") {
        alert("введите что-нибудь или измененное значение");
    }
    else {
        check = 0;
        document.getElementById("words").value = document.getElementById(id).value;
        document.getElementById("ID").value = document.getElementById(id).name;
        var ids = "";
        ids = ids + id2;
        document.getElementById("kID").value = ids;
        $("#forma1").ajaxSubmit({

            url: "${flowExecutionUrl}&_eventId_editWord&ajaxSource=true",
            success: function (html) {
                $("#voc").html($(html).filter("#voc")),
                    $("#answer").html($(html).filter("#answer"));
            },
            error: function (error) {
                console.log(error)
            }
        });
    }
}

function deleteWords(id, id2) {
    document.getElementById("ID").value = document.getElementById(id).name;
    var ids = "";
    ids = ids + id2;
    document.getElementById("kID").value = ids;
    check = 0;
    $("#forma1").ajaxSubmit({

        url: "${flowExecutionUrl}&_eventId_deletedWord&ajaxSource=true",
        success: function (html) {
            $("#voc").html($(html).filter("#voc")),
                $("#answer").html($(html).filter("#answer"));

        },
        error: function (error) {
            console.log(error)
        }
    });

}