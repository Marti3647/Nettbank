"use strict";
$(function(){
    $("#regKonto").click(function(){
        const url = "/adminKonto/registrer";
        const konto = {
            kontonummer    : $("#kontoNr").val(),
            type                    : $("#type").val(),
            valuta                 : $("#valuta").val(),
            personnummer   : $("#personnummer").val()
        }
        $.post(url,konto,function(retur) {
            $("#feilPersonnr").html("");
            if(retur === "Feil") {
                $("#feilPersonnr").html("Personnummer finnes ikke!");
            }
            else if(retur ==="Ikke innlogget") {
                $(location).attr('href', 'loggInnAdmin.html');
            }
            else {
                $(location).attr('href', 'adminKunde.html');
            }
        });

    });
    // input validering
    $("#kontoNr").change(function(){
        $('#feilKontonummer').html("");
        const regex = /[0-9]{11}/;
        const kontoNr = $("#kontoNr").val();
        if(!regex.test(kontoNr)) {
            $('#feilKontonummer').html("Kontonummeret må være 11 siffer");
        }
    });
    $("#personnummer").change(function(){
        $('#feilPersonnummer').html("");
        const regex = /[0-9]{11}/;
        const personNr = $("#personnummer").val();
        if(!regex.test(personNr)) {
            $('#feilPersonnummer').html("Personnummeret må være 11 siffer");
        }
    });
});