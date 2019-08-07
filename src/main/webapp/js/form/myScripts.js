var countAddedWordRows = 0;
var countAddedKeyRows = 0;
var wordsInKey = 0;
var countAddedWords = 0;

function editKeyOrSingleWord(id, op, newword) {
    newword = newword + "";
    if (newword == $("#"+id).val() || newword == "") {
        alert("введите что-нибудь или измененное значение");
    }
    else {
        countAddedWords = 0;
        if ((op + '') == 'keys') {

            $("#keys").attr("value",$('#' + id).val());
            $("#ID").val( $("#"+id).attr("name"));
            $("#forma1").ajaxSubmit({

                url: $("#flow").val()+"&_eventId_editKey&ajaxSource=true",
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
            $("#words").val( $("#"+id).val());
            $("#ID").val( $("#"+id).attr("name"));
            $("#forma1").ajaxSubmit({

                url: $("#flow").val()+"&_eventId_editWord&ajaxSource=true",
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
    if (id=='hidden')
        $('#hidden').children('button').toggle();
    else
        $('#' + id).toggle();
}

function deleteKeyOrSingleWord(id, op) {
    countAddedWords = 0;
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

            url: $("#flow").val()+"&_eventId_deletedWord&ajaxSource=true",
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
    countAddedWords = 0;
    var words = "";
    jQuery('#'+id+' input').each(function() {
        words = words + this.value + "#_#";
    });
    if (words != "") {
        words = words.substring(0, words.length - 3);
        $('#words').val(words);
        $('#ID').val(idKey);
        $("#forma1").ajaxSubmit({
            url: $("#flow").val()+"&_eventId_editManyWordsInKey&ajaxSource=true",
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
    if (countAddedWords == 0) {
        $("button[name~='WordsInK']").toggle();
        $('#butAddOneWord' + idKey).toggle();
    }
    countAddedWords++;
    if (countAddedWords == 2) {
        $('#butAllWord' + idKey).attr("style", "background: url(/images/addAll.png);background-size:38px 35px");
        $('#butAllWord' + idKey).attr("disabled", false);
    }
    var a = "countAddedWordRows" + countAddedWordRows;
    countAddedWordRows++;
    var tbody = $('#' + id);
    var row = $('<tr></tr>');
    var td1 = $('<td></td>');
    var td2 = $('<td></td>');
    var td3 = $('<td></td>');
    var inp = $('<input>');
    var butSave = $('<button></button>');
    var butDel = $('<button></button>');
    td1.attr("style","border-top:0");
    td2.attr("style","border-top:0");
    td3.attr("style","border-top:0");
    inp.attr("id",a + "input");
    inp.attr("name",idKey + '');
    row.attr("name",a + "R");
    butSave.attr("title","Сохранить");
    butSave.attr("type","button");
    butSave.attr("name","_eventId_addWordsss");
    butSave.attr("style","background: url(/images/upload.png);background-size: 38px 35px;");
    butDel.attr("title","Удалить");
    butDel.attr("style","background: url(/images/del.png);background-size: 38px 35px;");
    $(butDel).on('click',function () {
        $("#" + row.attr("id")).remove();
        countAddedWords--;
        var x = $("#" + id+" input");
        if (countAddedWords == 1) {
            $('#butAllWord' + idKey).attr("style", "background: url(/images/addAllBW.png);background-size:38px 35px");
            $('#butAllWord' + idKey).attr("disabled", true);
        }
        if (!x[0]) {
            countAddedWords = 0;
            $("button[name~='WordsInK']").toggle();
            $('#butAddOneWord' + idKey).toggle();
            $('#butAllWord' + idKey).attr("style", "background: url(/images/addAllBW.png);background-size:38px 35px");
            $('#butAllWord' + idKey).attr("disabled", true);
        }
    });
    $(butSave).on('click',function () {
        countAddedWords = 0;
        var id = a + "input";
        var id2 = a + "R";
        var text = $("#"+id).val();
        var text2 = $("#"+id).attr("name");
        $("#words").val(text);
        $("#ID").val(text2);
        $("#forma1").ajaxSubmit({

            url: $("#flow").val()+"&_eventId_addWordsss&ajaxSource=true",
            success: function (html) {
                $("#voc").html($(html).filter("#voc")),
                    $("#answer").html($(html).filter("#answer"));
            },
            error: function (error) {
                console.log(error)
            }
        });
    });

    td2.append(inp);
    td3.append(butDel);
    td3.append(butSave);
    row.append(td1);
    row.append(td2);
    row.append(td3);
    row.attr("style","background: deepskyblue;");
    tbody.append(row);
}

function addKey(id) {
    var a = "countAddedKeyRows" + countAddedKeyRows;
    countAddedKeyRows++;
    var table = $('#'+id);
    var row = $('<tr></tr>');
    var td1 = $('<td></td>');
    var td2 = $('<td></td>');
    var td3 = $('<td></td>');
    var inp = $('<input>');
    var inp2 = $('<input>');
    var butSave = $('<button></button>');
    var butDel = $('<button></button>');
    var addWord = $('<button></button>');
    inp.attr("id",a + "inputWord");
    inp2.attr("id",a + "inputKey");
    row.attr("id",a + "R");
    addWord.attr("title","Добавить слово");
    addWord.attr("type","button");
    addWord.attr("style","background: url(/images/addKey.png);background-size: 38px 35px;  margin-left:25px;");
    butSave.attr("title","Сохранить");
    butSave.attr("type","button");
    butSave.attr("style","background: url(/images/upload.png);background-size: 38px 35px; float:right; margin-left:10px;");
    butDel.attr("title","Удалить");
    butDel.attr("style","background: url(/images/del.png);background-size: 38px 35px;");
    butDel.on('click',function () {
        var id = a + "R";
        $("[name='rowWord[]']").remove();
        $("#" + id).remove();
        $('#ButAdd').attr('disabled', false);
        $('#ButAdd').attr('style', "background: url(/images/addKey.png);background-size: 38px 35px;float:right;margin-left:10px");
    });
    addWord.on('click',function () {
        var butDel = $('</button>');
        var id = wordsInKey + "manyWord";
        butDel.on('click',function () {
            $("#" + id).remove();
        });
        butDel.attr("style","background: url(/images/del.png);background-size: 38px 35px;");
        var tdW = $('<td></td>');
        var tdWInp = $('<td></td>');
        var tdWBut = $('<td></td>');
        tdW.attr("style","border-top:0");
        tdWInp.attr("style","border-top:0");
        tdWBut.attr("style","border-top:0");
        var inpW = $('<input>');
        inpW.attr("name", "allInp");
        tdWBut.append(butDel);
        var rowW = $('<tr></tr>');
        rowW.attr("name", "rowWord");
        rowW.attr("id", id);
        wordsInKey = wordsInKey + 1;
        tdWInp.append(inpW);
        rowW.append(tdW);
        rowW.append(tdWInp);
        rowW.append(tdWBut);
        rowW.attr("style","background: LIGHTSEAGREEN");
        table.append(rowW);


    });

    butSave.on('click',function () {
        countAddedWords = 0;
        var words = a + "inputWord";
        var id2 = a + "R";
        var keys = a + "inputKey";
        var allW = "";
        var word2 = $("#"+words).val();
        var key2 = $("#"+keys).val();
        jQuery("input[name='allInp']").each(function() {
            allW = allW + this.value + "#_#";
        });
        if (allW != "") {
            allW = allW.substring(0, allW.length - 3);
            butSave.attr("name","_addManyWordsAndKey");
            $("#words").val(word2 + "#_#" + allW);
            $("#keys").val(key2);
            $("#forma1").ajaxSubmit({
                url: $("#flow").val()+"&_eventId_addManyWordsAndKey&ajaxSource=true",
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
                    butSave.attr("name","_eventId_addWordss");
                    $("#words").attr("value",word2);
                    $("#keys").attr("value",key2);
                    $("#forma1").ajaxSubmit({
                        url: $("#flow").val()+"&_eventId_addWordss&ajaxSource=true",
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
    });

    td1.append(inp2);
    td2.append(inp);
    td3.append(butDel);
    td3.append(butSave);
    td3.append(addWord);
    row.append(td1);
    row.append(td2);
    row.append(td3);
    row.attr("style","background: lightblue;");
    table.append(row);
    $('#ButAdd').prop('disabled', true);
    $('#ButAdd').attr('style', "background: url(/images/addKeyBW.png);background-size: 38px 35px;float:right;margin-left:10px");
}


function editWords(id, id2, newword) {
    newword = newword + "";
    if (newword == $("#"+id).val() || newword == "") {
        alert("введите что-нибудь или измененное значение");
    }
    else {
        countAddedWords = 0;
        $("#words").val($("#"+id).val());
        $("#ID").val($("#"+id).attr("id"));
        var ids = "";
        ids = ids + id2;
        $("#kID").val(ids);
        $("#forma1").ajaxSubmit({
            url: $("#flow").val()+"&_eventId_editWord&ajaxSource=true",
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
    $("#ID").val($("#"+id).attr("name"));
    var ids = "";
    ids = ids + id2;
    $("#kID").val(ids);
    countAddedWords = 0;
    $("#forma1").ajaxSubmit({

        url: $("#flow").val()+"&_eventId_deletedWord&ajaxSource=true",
        success: function (html) {
            $("#voc").html($(html).filter("#voc")),
                $("#answer").html($(html).filter("#answer"));

        },
        error: function (error) {
            console.log(error)
        }
    });

}