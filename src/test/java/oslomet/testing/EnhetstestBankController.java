package oslomet.testing;  //Martine jobber med denne

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("01010110523", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("01010110523", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentTransaksjoner_LoggetInn() {
        // arrange  // Hvordan skal txID defineres i arrayet? og hva vil avventer si?
        List<Transaksjon> transaksjoner = new ArrayList<>();
        Transaksjon transaksjon1 = new Transaksjon(0, "20102012345", 100.50, "2015-03-15", "Fjordkraft", "1", "105010123456");
        Transaksjon transaksjon2 = new Transaksjon(1, "105010123456", 3000, "2023-02-07", "OverføringMellomKonto", "1", "12345678901");
        transaksjoner.add(transaksjon1);
        transaksjoner.add(transaksjon2);

        Konto testKonto = new Konto("01010110523", "5428458416", 20000.0, "Brukskonto", "NOK", transaksjoner);

        // act
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.hentTransaksjoner("12345678901", "2023-02-01", "2023-02-07")).thenReturn(testKonto);
        Konto resultat = bankController.hentTransaksjoner("12345678901", "2023-02-01", "2023-02-07");

        // assert
        assertEquals(testKonto, resultat);
    }
//Forventet utfall er autolaget txID, "105010123456", 100.50, "2015-03-15", "Fjordkraft", " ", "20102012345"

    @Test
    public void hentTransaksjoner_ikkeLoggetInn() {
        when(sjekk.loggetInn()).thenReturn(null);
        Konto resultat = bankController.hentTransaksjoner("5428458416", "2022-02.18", "2022-02-28");
        assertNull(resultat);
    }

    @Test
    public void hentSaldi_loggetInn() {
        //arrage
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("01010110523", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("01010110523", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        Konto konto3 = new Konto("105010123457", "12345678901",
                1000, "Lønnskonto", "NOK", null);

        konti.add(konto1);
        konti.add(konto2);
        konti.add(konto3);

        //act
        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentSaldi("01010110523")).thenReturn(konti);
        List<Konto> resultat = bankController.hentSaldi();

        //assert
        assertEquals(konti, resultat);

        }

    @Test
    public void hentSaldi_ikkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);
        List<Konto> resultat = bankController.hentSaldi();
        assertNull(resultat);
    }

    @Test
    public void registrerBetaling_loggetInn(){
        Transaksjon transaksjoner = new Transaksjon(0, "41275254977", 3000, "2023-02-01", "overføringMellomKonto", "1", "12345678901");
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.registrerBetaling(transaksjoner)).thenReturn("testOk");
        String resultat = repository.registrerBetaling(transaksjoner);
        assertEquals("testOk", resultat);
    }

    @Test
    public void registrerBetaling_ikkeLoggetInn(){
        Transaksjon transaksjoner = new Transaksjon(0, "41275254977", 3000, "2023-02-01", "overføringMellomKonto", "1", "12345678901");
        when(sjekk.loggetInn()).thenReturn(null);
        String resultat = repository.registrerBetaling(transaksjoner);
        assertNull(resultat);
    }

//Du er her Martine
    @Test
    public void hentBetaling_LoggetInn(){
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("01010110523", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("01010110523", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void endreKunde() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        Kunde inKunde = new Kunde("01010110524",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();
        assertEquals(enKunde, resultat);

        when(repository.endreKundeInfo(inKunde)).thenReturn("OK");
        String result = bankController.endre(inKunde);
        assertEquals(result, "OK");
    }


}



