package oslomet.testing.Sikkerhet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import oslomet.testing.DAL.BankRepository;

import javax.servlet.http.HttpSession;

@RestController
public class Sikkerhet {
    @Autowired
    BankRepository rep;

    @Autowired
    private HttpSession session;

    @GetMapping("/loggInn")
    public String sjekkLoggInn(String personnummer, String passord) {
        String regexPersonnummer = "[0-9]{11}";
        String regexPassord = ".{6,30}";

        boolean personnummerOK = personnummer.matches(regexPersonnummer);
        boolean passordOK = passord.matches(regexPassord);

        if (!personnummerOK) {
            return "Feil i personnummer";
        }
        if (!passordOK) {
            return "Feil i passord";
        }

        String resultat = rep.sjekkLoggInn(personnummer, passord);
        if (resultat.equals("OK")) {
            session.setAttribute("Innlogget", personnummer);
            return "OK";
        }
        return "Feil i personnummer eller passord";
    }

    @GetMapping("/loggUt")
    public void loggUt() {
        session.setAttribute("Innlogget", null);
    }

    @GetMapping("/loggInnAdmin")
    public String loggInnAdmin(String bruker, String passord){
        if (bruker.equals("Admin") && (passord.equals(("Admin")))) {
            session.setAttribute("Innlogget", "Admin");
            return "Logget inn";
        }
        else {
            session.setAttribute("Innlogget", null);
            return "Ikke logget inn";
        }
    }

    public String loggetInn() {
        if (session.getAttribute("Innlogget") != null){
            return (String) session.getAttribute("Innlogget");
        }
        return null;
    }
}
