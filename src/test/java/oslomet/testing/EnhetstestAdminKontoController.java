package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.BankController;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

    @InjectMocks
    //denne skal testes
    private AdminKontoController adminKontoController;

    @Mock
    //denne skal Mock'es, vi later som at vi kaller den eller den skal simuleres
    private AdminKontoController repository;

    //denne skal Mock'es - simulere kallet
    @Mock
    private Sikkerhet sjekk;

    //Første test, tester hentAlleKonti() - sjekker om
    @Test
    public void hentAlleKonti_LoggetInn() {
        //arrange - må sette opp kunden/kunder før vi fortsetter

        //siden hent kontoinformasjon returnerer en list med info, tar vi
        //å lage en kunde liste eller flere
        Konto konti1 = new Konto("93840283962","39482101038", 500,
                "Brukskonto", "NOK", null);
        Konto konti2 = new Konto("67840283940","56482101011", 1000,
                "Lønnskonto", "NOK", null);
        List<Konto> kontoinfo = new ArrayList<>(); //oppretter en ny arraylist
        kontoinfo.add(konti1);
        kontoinfo.add(konti2);

        //sjekker om sikkerhet først
        when(sjekk.loggetInn()).thenReturn("93840283962");

        //sjekker for repository - skal kun returnere kontoinfo eller listen
        when(repository.hentAlleKonti()).thenReturn(kontoinfo);

        //act - kjører løsningen
        //returnere en liste av kontoinfo, fra Bank controlleren

        List<Konto> resultat = BankController.hentKonti();

        //assert
        assertEquals(kontoinfo, resultat);
    }

    @Test
    public void hentKonti_IkkeloggetInn() {
        //arrange - må sette opp kunden/kunder før vi fortsetter
        when(sjekk.loggetInn()).thenReturn(null);

        //act - kjører løsningen

        List<Konto> resultat = BankController.hentKonti();

        //assert
        assertNull(resultat);
    }

    @Test
    public void registrerKonto_loggetInn(){

    }


}
