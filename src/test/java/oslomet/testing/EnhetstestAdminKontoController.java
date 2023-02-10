package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

    @InjectMocks
    //denne skal testes
    private AdminKontoController adminKontoController;

    @Mock
    //denne skal Mock'es, vi later som at vi kaller den eller den skal simuleres
    private AdminRepository repository;

    //denne skal Mock'es - simulere kallet
    @Mock
    private Sikkerhet sjekk;

    //Første test, tester hentAlleKonti() - sjekker om
    @Test
    public void hentAlleKonti_LoggetInn() {
        //arrange - må sette opp kunden/kunder før vi fortsetter

        List<Konto> kontoinfo = new ArrayList<>(); //oppretter en ny arraylist
        //siden hent kontoinformasjon returnerer en list med info, tar vi
        //å lage en kunde liste eller flere
        Konto konti1 = new Konto("93840283962","39482101038", 500,
                "Brukskonto", "NOK", null);
        Konto konti2 = new Konto("67840283940","56482101011", 1000,
                "Lønnskonto", "NOK", null);
        kontoinfo.add(konti1);
        kontoinfo.add(konti2);

        //sjekker om sikkerhet først
        Mockito.when(sjekk.loggetInn()).thenReturn("84840323962");

        //sjekker for repository - skal kun returnere kontoinfo eller listen
        Mockito.when(repository.hentAlleKonti()).thenReturn(kontoinfo);

        //act - kjører løsningen
        //returnere en liste av kontoinfo, fra Bank controlleren

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        //assert
       assertEquals(kontoinfo, resultat);
    }

    @Test
    public void hentKonti_IkkeloggetInn() {
        //arrange - må sette opp kunden/kunder før vi fortsetter
        Mockito.when(sjekk.loggetInn()).thenReturn(null);

        //act - kjører løsningen

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        //assert
       assertNull(resultat);
    }

    @Test
    public void registrerKonto_loggetInn(){
        //arrange
        Konto konto1 = new Konto("39482048203", "49305936782", 200, "Brukskont",
                "NOK", null);

        Mockito.when(sjekk.loggetInn()).thenReturn("384793872947");
        Mockito.when(repository.registrerKonto(konto1)).thenReturn("OK");

        //act - kjører løsningen
        String resultat = adminKontoController.registrerKonto(konto1);

        //assert
        assertEquals("OK", resultat);

    }
    @Test
    public void registrerKonto_ikkeLoggetInn(){
        //arrange
        Konto konto1 = new Konto("39482048203", "49305936782", 200, "Brukskont",
                "NOK", null);

        Mockito.when(repository.registrerKonto(konto1)).thenReturn("Ikke innlogget");

        //act
        String resultat = adminKontoController.registrerKonto(konto1);

        //assert
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void endreKonto_loggetInn(){
        //arrange
        Konto konto1 = new Konto("39482048203", "49305936782", 200, "Brukskont",
                "NOK", null);

        Mockito.when(sjekk.loggetInn()).thenReturn("384793872947");
        Mockito.when(repository.endreKonto(konto1)).thenReturn("OK");

        //act - kjører løsningen
        String resultat = adminKontoController.endreKonto(konto1);

        //assert
        assertEquals("OK", resultat);

    }

    @Test
    public void endreKonto_ikkeLoggetInn(){
        //arrange
        Konto konto1 = new Konto("39482048203", "49305936782", 200, "Brukskont",
                "NOK", null);

        Mockito.when(repository.endreKonto(konto1)).thenReturn("Ikke innlogget");

        //act
        String resultat = adminKontoController.endreKonto(konto1);

        //assert
        assertEquals("Ikke innlogget", resultat);

    }

    @Test
    public void slettKonto_loggetInn(){
        //arrange
        String kontonummer = "21345672";

        Mockito.when(sjekk.loggetInn()).thenReturn(kontonummer);
        Mockito.when(repository.slettKonto(kontonummer)).thenReturn("OK");

        //act
        String resultat = adminKontoController.slettKonto(kontonummer);

        //assert
        assertEquals("OK", resultat);

    }

    @Test
    public void slettKonto_ikkeLoggetInn(){
        //arrange
        String kontonummer = "21345672";

        Mockito.when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKontoController.slettKonto(kontonummer);

        //assert
        assertEquals("Ikke innlogget", resultat);

    }


}
