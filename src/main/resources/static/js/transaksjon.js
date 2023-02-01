"use strict";
$(function(){
    // henter de aktuelle kontonumre for den påloggede personen.
    const url = "/hentKonti";
    $.get(url,function(data) {
        if(data ==="Feil innlogging") {
            $(location).attr('href', 'loggInn.html');
        }
        // bygger så en dropdown-meny og legger den ut slik at den kan velges av bruker.
        let dropdown ="<Select id='kontoNr' class='form-control'>";
        $.each(data, function( key, konto) {
            dropdown +="<option>"+konto.kontonummer+"</option>";
        });
        dropdown +="</Select>";
        $("#selekt").html(dropdown);
    });

    $("#sok").click(function(){
        const fraDato = $("#fraDato").val();
        const tilDato = $("#tilDato").val();
        const kontoNr = $("#kontoNr").val();
        const url = "/hentTransaksjoner?fraDato="+fraDato+"&tilDato="+tilDato+"&kontoNr="+kontoNr;
        $.get(url,function(retur) {
            if(retur ==="Feil innlogging") {
                $(location).attr('href', 'loginn.html');
            }
            const tabell=formaterTransaksjonsData(retur);
            $("#listTransaksjoner").html(tabell);
        });
    });
});

function formaterTransaksjonsData(retur) {
    let tabell= "<table class='table table-bordered'> " +
        "<thead><tr><th>Dato</th><th>Til kontonr</th><th>Beløp</th><th>Melding</th></tr></thead><tbody>";
    $.each(retur.transaksjoner, function( key, transaksjon ) {
        tabell+="<tr><td>"+transaksjon.dato+"</td>" +
            "<td>"+transaksjon.fraTilKontonummer+"</td>"+
            "<td>"+transaksjon.belop+"</td>" +
            "<td>"+transaksjon.melding+"</td></tr>";
    });
    tabell +="</tbody></table>";
    return tabell;
}