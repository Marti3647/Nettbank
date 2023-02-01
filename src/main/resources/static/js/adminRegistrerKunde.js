"use strict";
$(function(){
    $("#regKunde").click(function(){
        const url = "/adminKunde/lagre";
        const kunde = {
            personnummer    : $("#personnummer").val(),
            fornavn         : $("#fornavn").val(),
            etternavn       : $("#etternavn").val(),
            adresse         : $("#adresse").val(),
            postnr          : $("#postnr").val(),
            poststed        : $("#poststed").val(),
            telefonnr       : $("#telefonnr").val(),
            passord         : $("#passord").val()
        }
        $.post(url,kunde,function(retur) {
            if(retur ==="Feil innlogging") {
                $(location).attr('href', 'logInnAdmin.html');
            }
            $(location).attr('href', 'adminKunde.html');
        });

    });
    // input validering
    $("#personnummer").change(function(){
        $('#feilPersonnummer').html("");
        const regex = /[0-9]{11}/;
        const data = $("#personnummer").val();
        if(!regex.test(data)) {
            $('#feilPersonnummer').html("Personnummeret må være 11 siffer");
        }
    });
    $("#fornavn").change(function(){
        $('#feilFornavn').html("");
        const regex = /[a-zæøåA-ZÆØÅ.\- ]{2,30}/;
        const data = $("#fornavn").val();
        if(!regex.test(data)) {
            $('#feilFornavn').html("Fornavnet må være mellom 2 og 30 tegn");
        }
    });
    $("#etternavn").change(function(){
        $('#feilEtternavn').html("");
        const regex = /[a-zæøåA-ZÆØÅ.\- ]{2,30}/;
        const data = $("#etternavn").val();
        if(!regex.test(data)) {
            $('#feilEtternavn').html("Etternavn må være mellom 2 og 30 tegn");
        }
    });
    $("#adresse").change(function(){
        $('#feilAdresse').html("");
        const regex = /[a-zæøåA-ZÆØÅ0-9.\- ]{2,50}/;
        const personNr = $("#adresse").val();
        if(!regex.test(personNr)) {
            $('#feilAdresse').html("Adresse må være mellom 2 og 30 tegn");
        }
    });
    $("#postnr").change(function(){
        $('#feilPostnr').html("");
        const regex = /[0-9]{4}/;
        const personNr = $("#postnr").val();
        if(!regex.test(personNr)) {
            $('#feilPostnr').html("Postnr må være 4 siffer");
        }
    });
    $("#poststed").change(function(){
        $('#feilPoststed').html("");
        const regex = /[a-zæøåA-ZÆØÅ0-9.\- ]{2,30}/;
        const personNr = $("#poststed").val();
        if(!regex.test(personNr)) {
            $('#feilPoststed').html("Poststed må være mellom 2 og 30 siffer");
        }
    });
    $("#telefonnr").change(function(){
        $('#feilTelefonnr').html("");
        const regex = /[0-9]{8}/;
        const personNr = $("#telefonnr").val();
        if(!regex.test(personNr)) {
            $('#feilTelefonnr').html("Telefonnr må være 8 siffer");
        }
    });

});