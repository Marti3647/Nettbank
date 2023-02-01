package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

    @InjectMocks
    //denne skal testes
    private AdminKontoController adminKontoController;

    @Mock
    //denne skal Mock'es, vi later som at vi kaller den
    private AdminKontoController repository;

    //denne skal Mock'es
    @Mock
    private Sikkerhet sjekk;

    //Første test, tester hentAlleKonti() - sjekker om
    @Test
    public void hentKonti_LoggetInn() {
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

        //act - kjører løsningen



        //assert
    }

    @Test
    public void hentKonti_IkkeloggetInn() {
        //arrange - må sette opp kunden/kunder før vi fortsetter


        //act - kjører løsningen



        //assert
    }



}
