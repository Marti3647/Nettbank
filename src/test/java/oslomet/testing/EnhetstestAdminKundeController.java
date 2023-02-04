package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {
    @InjectMocks
    private AdminKundeController kundeController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sikkerhet;

    @Test
    public void test_hentAlle(){
        // arrange
        Kunde kunde1 = new Kunde("12345678", "Bhiravina", "Mahesh", "Ryenstubben 1",
                "1187", "OSLO", "45673864", "passord123");
        Kunde kunde2 = new Kunde("87654321", "Archanaa", "Mahesh", "Ryenstubben 1",
                "1187", "OSLO", "43892098", "passord123");
        List<Kunde> kundeListe = new ArrayList<>();

        kundeListe.add(kunde1);
        kundeListe.add(kunde2);

        Mockito.when(sikkerhet.loggetInn()).thenReturn("12345678");
        Mockito.when(repository.hentAlleKunder()).thenReturn(kundeListe);

        // act
        List<Kunde> resultat = kundeController.hentAlle();

        //assert
        assertEquals(kundeListe,resultat);
    }

    @Test
    public void test_hentAlleFeil(){
        //arrange
        Mockito.when(sikkerhet.loggetInn()).thenReturn(null);

        //act
        List<Kunde> resultat = kundeController.hentAlle();

        //assert
        assertNull(resultat);
    }

    @Test
    public void test_lagreKunde(){
        //arrange
        Kunde kunde1 = new Kunde("12345678", "Bhiravina", "Mahesh", "Ryenstubben 1",
                "1187", "OSLO", "45673864", "passord123");
        Mockito.when(sikkerhet.loggetInn()).thenReturn("12345678");
        Mockito.when(repository.registrerKunde(kunde1)).thenReturn("OK");

        //act
        String resultat = kundeController.lagreKunde(kunde1);

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void test_lagreKundeFeil(){
        //arrange
        Kunde kunde1 = new Kunde("12345678", "Bhiravina", "Mahesh", "Ryenstubben 1",
                "1187", "OSLO", "45673864", "passord123");

        Mockito.when(sikkerhet.loggetInn()).thenReturn(null);

        //act
        String resultat = kundeController.lagreKunde(kunde1);

        //asert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void test_endre(){
        //arrange
        Kunde kunde1 = new Kunde("12345678", "Bhiravina", "Mahesh", "Ryenstubben 1",
                "1187", "OSLO", "45673864", "passord123");

        Mockito.when(sikkerhet.loggetInn()).thenReturn("12345678");
        Mockito.when(repository.endreKundeInfo(kunde1)).thenReturn("OK");

        //act
        String resultat = kundeController.endre(kunde1);

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void test_endreFeil(){
        //arrange
        Kunde kunde1 = new Kunde("12345678", "Bhiravina", "Mahesh", "Ryenstubben 1",
                "1187", "OSLO", "45673864", "passord123");

        Mockito.when(sikkerhet.loggetInn()).thenReturn(null);

        //act
        String resultat = kundeController.endre(kunde1);

        //assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void test_slett(){
        //arrange
        String personnummer = "12345678";

        Mockito.when(sikkerhet.loggetInn()).thenReturn(personnummer);
        Mockito.when(repository.slettKunde(personnummer)).thenReturn("OK");

        //act
        String resultat = kundeController.slett(personnummer);

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void test_slettFeil(){
        //arrange
        String personnummer = "12345678";

        Mockito.when(sikkerhet.loggetInn()).thenReturn(null);

        //act
        String resultat = kundeController.slett(personnummer);

        //assert
        assertEquals("Ikke logget inn", resultat);
    }
}
