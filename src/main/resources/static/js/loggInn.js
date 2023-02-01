$(function(){
    "use strict";

    $("#loggInn").click(function(){
        const personNr = $("#personNr").val();
        const passord = $("#passord").val();
        const url = "/loggInn?personnummer="+personNr+"&passord="+passord;

        $.get(url,function(retur) {
            if(retur ==="OK") {
                // dersom innloggingen var vellykket gå til saldo.php
                $(location).attr('href', 'saldo.html');
            }
            else {
                // dersom innloggingen ikke var vellykket står vi på samme sted
                $("#feilMelding").html("Feil personnummer / passord!");
            }
        });
    });
    // input validering
    $("#personNr").change(function(){
        $('#feilPersonNr').html("");
        const regex = /[0-9]{11}/;
        const personNr = $("#personNr").val();
        if(!regex.test(personNr)) {
            $('#feilPersonNr').html("Personnummer nå være 11 siffer");
        }
    });

    $("#passord").change(function(){
        $('#feilPassord').html("");
        const regex = /.{6,30}/;
        const passord = $("#passord").val();
        if(!regex.test(passord)) {
            $('#feilPassord').html("Minimum 6 tegn");
        }
    });
});
