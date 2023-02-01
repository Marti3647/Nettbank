function LoggInn(){
    // dersom innloggingen var vellykket bør vi gå til adminKunde
    // dersom innloggingen ikke var vellykket står vi på samme sted
    const bruker = $("#bruker").val();
    const passord = $("#passord").val();
    const url = "/loggInnAdmin?bruker="+bruker+"&passord="+passord;

    $.get(url,function(retur) {
        if(retur ==="Logget inn") {
            $(location).attr('href', 'adminKunde.html');
        }
        else {
            $("#feilMelding").html("Feil personnummer / passord!");
        }
    });
};

$(function(){
    "use strict";
    // input validering
    $("#bruker").change(function(){
        $('#feilBruker').html("");
        const regex = /[a-zøæåA-ZØÆÅ]{2,20}/;
        const regexData = $("#bruker").val();
        if(!regex.test(regexData)) {
            $('#feilBruker').html("Bruker må være 2 til 20 tegn");
        }
    });

    $("#passord").change(function(){
        $('#feilPassord').html("");
        const regex = /.{4,30}/;
        const regexData = $("#passord").val();
        if(!regex.test(regexData)) {
            $('#feilPassord').html("Minimum 4 tegn");
        }
    });
    // sjekk at all validering er OK når submit-knapp trykkes
    // det skal ikke utføres en submit, men et kall til API'et via LoggInn() funksjonen
    $("form").submit(function(event) {
        event.preventDefault(); // skal ikke utføre en submit
        let regex = /[a-zøæåA-ZØÆÅ]{2,20}/;
        let regexData = $("#bruker").val();
        if(!regex.test(regexData)){
            return; // feil, ikke gjør noe
        }
        regex = /.{4,30}/;
        regexData = $("#passord").val();
        if(!regex.test(regexData)) {
            return; // feil, ikke gjør noe
        }
        LoggInn(); // submit RegEx OK!
    });
});