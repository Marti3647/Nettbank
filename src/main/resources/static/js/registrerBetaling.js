"use strict";
$(function(){
    // henter de aktuelle kontonumre for den påloggede personen.
    const url = "/hentKonti";
    $.get(url,function(retur) {
        if(retur ==="Feil innlogging") {
            $(location).attr('href', 'loggInn.html');
        }
        // bygger så en dropdown-meny og legger den ut slik at den kan velges av bruker.
        var dropdown ="<Select id='kontoNr' class='form-control'>";
        $.each(retur, function( key, konto) {
            dropdown +="<option>"+konto.kontonummer+"</option>";
        });
        dropdown +="</Select>";
        $("#selekt").html(dropdown);
    });

    $("#regBetaling").click(function(){
        const url = "/registrerBetaling";
        var betaling = {
            kontoNr     : $("#kontoNr").val(),
            tilKonto    : $("#tilKonto").val(),
            dato        : $("#dato").val(),
            belop       : $("#belop").val(),
            melding     : $("#melding").val()
        }
        $.post(url,betaling,function(retur) {
            if(retur ==="Feil innlogging") {
                $(location).attr('href', 'loggInn.html');
            }
            $(location).attr('href', 'utforBetalinger.html');
        });

    });
    // input validering
    $("#tilKonto").change(function(){
        $('#feilTilKontonummer').html("");
        const regex = /[0-9]{11}/;
        const personNr = $("#tilKonto").val();
        if(!regex.test(personNr)) {
            $('#feilTilKontonummer').html("Kontonummeret må være 11 siffer");
        }
    });
    $("#dato").change(function(){
        $('#feilDato').html("");
        const regex = /[0-9]{4}\-[0-9]{2}\-[0-9]{2}/;
        const personNr = $("#dato").val();
        if(!regex.test(personNr)) {
            $('#feilDato').html("Dato må være på formatet yyyy-mm-dd");
        }
    });
});