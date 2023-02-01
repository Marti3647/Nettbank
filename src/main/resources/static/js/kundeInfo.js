"use strict";
$(function(){

    var url = "/hentKundeInfo";
    $.get(url,function(kunde) {
        if(kunde ==="Feil innlogging") {
            $(location).attr('href', 'loggInn.html');
        }
        // formater kunde informasjon
        var heading = "Kundeinformasjon for personnummer "+ kunde.personnummer;
        $("#heading").html(heading);
        $("#fornavn").val(kunde.fornavn);
        $("#etternavn").val(kunde.etternavn);
        $("#adresse").val(kunde.adresse);
        $("#postnr").val(kunde.postnr);
        $("#poststed").val(kunde.poststed);
        $("#telefonnr").val(kunde.telefonnr);
        $("#passord").val(kunde.passord);
    });

    $("#endre").click(function(){
        var kunde = {
            fornavn   : $("#fornavn").val(),
            etternavn : $("#etternavn").val(),
            adresse   : $("#adresse").val(),
            postnr    : $("#postnr").val(),
            poststed  : $("#poststed").val(),
            telefonnr : $("#telefonnr").val(),
            passord   : $("#passord").val()
        };
        var url = "/endreKundeInfo";
        $.post(url,kunde,function(retur) {
            if(retur ==="Feil innlogging") {
                $(location).attr('href', 'loginn.html');
            }
            $(location).attr('href', 'kundeInfo.html');
        });
    });
});