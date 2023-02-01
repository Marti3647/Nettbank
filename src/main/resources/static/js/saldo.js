// kall til hentSaldo.php
"use strict";
$(function(){
    // henter de aktuelle kontonumre for den påloggede personen.
    const url = "/hentSaldi";
    $.get(url,function(retur) {
        if(retur ==="Feil innlogging") {
            $(location).attr('href', 'loggInn.html');
        }
        // formater saldo informasjon
        $("#personNr").html("Saldo for personnummer "+retur[0].personnummer);
        const tabell=formaterSaldoData(retur);

        $("#listSaldi").html(tabell);
    });
});

function formaterSaldoData(saldi){
    let tabell="";
    tabell +="<table class='table table-bordered'>";
    tabell +="<thead><tr><th>Kontonr</th><th>Type</th><th>Saldo</th><th>Valuta</th>";
    tabell +="</thead><tbody>";
    $.each(saldi, function( key, saldo) {
        tabell+="<tr>";
        tabell+="<td>"+saldo.kontonummer+"</td>";
        tabell+="<td>"+saldo.type+"</td>";
        tabell+="<td>"+saldo.saldo.toFixed(2)+"</td>";
        tabell+="<td>"+saldo.valuta+"</td>";
        tabell+="</tr>";
    });
    tabell +="</tbody></table>";
    return tabell;
}