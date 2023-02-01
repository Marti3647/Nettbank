"use strict";
$(function(){
    // henter de aktuelle registrerte betalinger for alle konti for den påloggende personen personen.
    const url = "./hentBetalinger";
    $.get(url,function(retur) {
        if(retur ==="Feil innlogging") {
            $(location).attr('href', 'loggInn.html');
        }
        const tabell=formaterBetalingsData(retur);
        //console.log(retur);
        $("#listBetalinger").html(tabell);
        $("button").click(function() {
            const txid = $(this).attr('data-txid');
            // oppdater viewet ved å kalle utforBetaling og returner det oppdaterte resultatet
            const url = "/utforBetaling?txID=" + txid;
            $.get(url, function (retur) {
                if (retur ==null) {
                    $(location).attr('href', 'logginn.html');
                }
                const tabell = formaterBetalingsData(retur);
                $("#listBetalinger").html(tabell);
                window.location.reload();
            });
        });
    });

    function formaterBetalingsData(betalinger){
        let tabell ="<table class='table table-bordered'>" +
            "<thead><tr><th>Fra kontonr</th><th>Til kontonr</th>" +
            "<th>Dato</th><th>Beløp</th><th>Melding</th></tr>" +
            "</thead><tbody>";
        $.each(betalinger, function( key, betaling) {
            tabell+="<tr>" +
                "<td>"+betaling.kontonummer+"</td>" +
                "<td>"+betaling.fraTilKontonummer+"</td>" +
                "<td>"+betaling.dato+"</td>" +
                "<td>"+betaling.belop.toFixed(2)+"</td>" +
                "<td>"+betaling.melding+"</td>" +
                "<td><button class='btn btn-success' data-txid='"+betaling.txID+"'>Utfør betaling</button></td></tr>";
        });
        tabell +="</tbody></table>";
        return tabell;
    };
});
