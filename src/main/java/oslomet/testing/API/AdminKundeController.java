package oslomet.testing.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.List;

@RestController
@RequestMapping("/adminKunde")
public class AdminKundeController {
    @Autowired
    AdminRepository repository;

    @Autowired
    private Sikkerhet sjekk;

    @GetMapping("/hentAlle")
    public List<Kunde> hentAlle() {
        String personnummer = sjekk.loggetInn();
        if (personnummer!=null) {
            return repository.hentAlleKunder();
        }
        return null;
    }

    @PostMapping("/lagre")
    public String lagreKunde(@RequestBody Kunde innKunde) {
        String personnummer = sjekk.loggetInn();
        if (personnummer!=null) {
            return repository.registrerKunde(innKunde);
        }
        return "Ikke logget inn";
    }

    @PostMapping("/endre")
    public String endre(@RequestBody Kunde innKunde) {
        String personnummer = sjekk.loggetInn();
        if (personnummer!=null) {
            return repository.endreKundeInfo(innKunde);
        }
        return "Ikke logget inn";
    }

    @GetMapping("/slett")
    public String slett(String personnummer) {
        String p = sjekk.loggetInn();
        if (p !=null) {
            return repository.slettKunde(personnummer);
        }
        return "Ikke logget inn";
    }
}



