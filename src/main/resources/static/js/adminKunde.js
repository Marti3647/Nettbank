"use strict";
$(function(){
    // henter alle kunder
    const url = "/adminKunde/hentAlle";
    $.get(url,function(retur) {
        if(retur ==="Feil innlogging") {
            $(location).attr('href', 'loggInnAdmin.html');
        }
        // formater kunde-informasjon
        const tabell=formaterKundeData(retur);
        $("#listKunde").html(tabell);
    });
});

function formaterKundeData(kunder){
    let tabell="<table class='table table-condensed'>" +
        "<thead><tr><th>Personnr</th><th>Fornavn</th><th>Etternavn</th><th>Adresse</th>" +
        "<th>Postnr</th><th>Poststed</th><th>Telefonnr</th><th>Passord</th><th>Endre</th><th>Slett</th>" +
        "</thead><tbody>";
    let linje=1;
    $.each(kunder, function( key, kunde) {
        tabell+="<tr>" +
            "<td><input type='text' readonly id='personnummer"+linje+"' size='12' value='"+kunde.personnummer+"'/></td>" +
            "<td><input type='text' id='fornavn"+linje+"' value='"+kunde.fornavn+"'/></td>" +
            "<td><input type='text' id='etternavn"+linje+"' value='"+kunde.etternavn+"'/></td>" +
            "<td><input type='text' id='adresse"+linje+"' value='"+kunde.adresse+"'/></td>" +
            "<td><input type='text' id='postnr"+linje+"' size='4' value='"+kunde.postnr+"'/></td>" +
            "<td><input type='text' id='poststed"+linje+"' value='"+kunde.poststed+"'/></td>" +
            "<td><input type='text' id='telefonnr"+linje+"' size='8' value='"+kunde.telefonnr+"'/></td>" +
            "<td><input type='text' id='passord"+linje+"' value='"+kunde.passord+"'/></td>" +
            "<td><a class='btn btn-success' onclick='endreKunde("+linje+")'>Endre</button></td>"+
            "<td><a class='btn btn-danger' onclick='slettKunde("+linje+")'>Slett</button></td>" +
            "</tr>";
        linje++;
    });
    tabell +="</tbody></table>";
    return tabell;
}

function endreKunde(linje) {
    const kunde = {
        personnummer : $("#personnummer"+linje).val(),
        fornavn      : $("#fornavn"+linje).val(),
        etternavn    : $("#etternavn"+linje).val(),
        adresse      : $("#adresse"+linje).val(),
        postnr       : $("#postnr"+linje).val(),
        poststed     : $("#poststed"+linje).val(),
        telefonnr    : $("#telefonnr"+linje).val(),
        passord      : $("#passord"+linje).val()
    };
    const url = "/adminKunde/endre";
    $.post(url,kunde,function(retur) {
        if(retur ==="Ikke logget inn") {
            $(location).attr('href', 'loggInnAdmin.html');
        }
        $(location).attr('href', 'adminKunde.html');
    });
}

function slettKunde(linje) {
    const persNr = $("#personnummer"+linje).val();
    const slettOK = confirm("Slett kunde med personnummer "+persNr);
    if (slettOK) {
        const url = "/adminKunde/slett?personnummer="+persNr;
        $.get(url,function() {
            window.location.reload();
        });
    }
}