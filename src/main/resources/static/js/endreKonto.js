"use strict";
$(function(){
    // henter alleKonti
    const url = "/adminKonto/hentAlle";
    $.get(url,function(konti) {
        if(konti ==="Ikke innlogget") {
            $(location).attr('href', 'loggInnAdmin.html');
        }
        const tabell = formaterKontoData(konti);
        $("#endreKonto").html(tabell);
    });
});
function formaterKontoData(konti){
    let tabell="<table class='table table-condensed'>" +
        "<thead><tr><th>Kontonummer</th><th>Saldo</th><th>Type</th><th>Valuta</th>" +
        "<th>Personnummer</th><th>Endre</th><th>Slett</th></thead><tbody>";
    let linje=1;
    $.each(konti, function( key, konto) {
        let saldoNum = parseFloat(konto.saldo);
        saldoNum = saldoNum.toFixed(2);
        tabell+="<tr><td><input type='text' readonly id='kontonummer"+linje+"' size='13' value='"+konto.kontonummer+"'/></td>" +
            "<td><input type='text' id='saldo"+linje+"' value='"+saldoNum+"' style='text-align:right'/></td>" +
            "<td><input type='text' id='type"+linje+"' value='"+konto.type+"'/></td>" +
            "<td><input type='text' id='valuta"+linje+"' value='"+konto.valuta+"'/></td>"+
            "<td><input type='text' readonly id='personnummer"+linje+"' size=12 value='"+konto.personnummer+"'/></td>" +
            "<td><a class='btn btn-success' onclick='endreKonto("+linje+")'>Endre</button></td>" +
            "<td><a class='btn btn-danger' onclick='slettKonto("+linje+")'>Slett</button></td></tr>";
        linje++;
    });
    tabell +="</tbody></table>";
    return tabell;
}
function endreKonto(linje){
    const konto = {
        kontonummer     : $("#kontonummer"+linje).val(),
        saldo           : $("#saldo"+linje).val(),
        type            : $("#type"+linje).val(),
        valuta          : $("#valuta"+linje).val(),
        personnummer    : $("#personnummer"+linje).val()
    };
    const url = "/adminKonto/endre";
    $.post(url,konto,function(retur) {
        if(retur ==="Feil innlogging") {
            $(location).attr('href', 'loggInnAdmin.html');
        }
        else {
            $(location).attr('href', 'adminEndreKonto.html');
        }
    });
}
function slettKonto(linje){
    const kontonummer = $("#kontonummer"+linje).val();
    const url = "/adminKonto/slett?kontonummer="+kontonummer;
    const slettOK = confirm("Slett kunde med kontonummer "+kontonummer);
    if (slettOK) {
        $.get(url,function(retur)  {
            if(retur ==="Feil innlogging")  {
                $(location).attr('href', 'loggInnAdmin.html');
            }
            else  {
                $(location).attr('href', 'adminEndreKonto.html');
            }
        });
    }
}