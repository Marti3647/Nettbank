package oslomet.testing.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;

import java.util.List;

@Repository
public class BankRepository {

    @Autowired
    private JdbcTemplate db;

    public Konto hentTransaksjoner(String kontonr, String fraDato, String tilDato) {
        if(fraDato.equals("")) {
            fraDato="2000-01-01";
        }
        if(tilDato.equals("")) {
            tilDato="2100-01-01";
        }
        try {
            String sql = "SELECT * FROM Transaksjon WHERE Kontonummer = ? AND Dato >= ? " +
                               "AND Dato <= ? AND Avventer = 0";

            List<Transaksjon> transaksjoner = db.query(sql, new BeanPropertyRowMapper(Transaksjon.class), kontonr, fraDato, tilDato);

            sql = "Select * from Konto Where Kontonummer = ?";
            List<Konto> konti = db.query(sql, new BeanPropertyRowMapper(Konto.class), kontonr);
            Konto kontoen = konti.get(0);

            kontoen.setTransaksjoner(transaksjoner);
            return kontoen;
        }
        catch(Exception e){
            return null;
        }
    }

    public String sjekkLoggInn(String personnummer,String passord) {
        try {
            String sql = "Select count(*) from Kunde Where personnummer = ? AND passord = ?";
            int funnetBruker = db.queryForObject(sql, Integer.class, personnummer, passord);
            if (funnetBruker > 0) {
                return "OK";
            }
        }
        catch(Exception e){
            return "Feil";
        }
        return "Feil";
    }
    public List<Konto> hentKonti(String personnummer) {
        try{
            String sql = "Select Distinct Kontonummer from Konto Where Personnummer = ?";
            List<Konto> konti = db.query(sql,new BeanPropertyRowMapper(Konto.class),personnummer);
            return konti;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public List<Konto> hentSaldi(String personnummer) {
        try {
            String sql = "Select Distinct * from Konto Where Personnummer = ?";
            List<Konto> konti = db.query(sql, new BeanPropertyRowMapper(Konto.class), personnummer);
            return konti;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public String registrerBetaling(Transaksjon betaling) {
        try{
            String sql = "Insert into Transaksjon (FraTilKontonummer,Belop,Dato,Melding,Kontonummer,Avventer) " +
                        "Values (?,?,?,?,?,'1')";
            db.update(sql,betaling.getFraTilKontonummer(),betaling.getBelop(),betaling.getDato(),
                        betaling.getMelding(),betaling.getKontonummer());
            return "OK";
        }
        catch(Exception e){
            return "Feil";
        }
    }
    
    public List<Transaksjon> hentBetalinger(String personnummer) {
        // hent alle betalinger for kontonummer som avventer betaling (lik 1)
        try{
            String sql = "Select * from Transaksjon Join Konto On Transaksjon.Kontonummer = Konto.Kontonummer " +
                        "Where Personnummer= ? AND Avventer='1' Order By Transaksjon.Kontonummer";
            List<Transaksjon> transaksjoner = db.query(sql,new BeanPropertyRowMapper(Transaksjon.class),personnummer);
            return transaksjoner;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public String utforBetaling(int txID)  {
        try {
            // hent Belop og Kontonummer fra Transaksjonenen
            String sql = "Select Belop, Kontonummer from Transaksjon where TxID = ?";
            List<Transaksjon> transaksjoner = db.query(sql, new BeanPropertyRowMapper(Transaksjon.class), txID);
            Transaksjon transaksjonen = transaksjoner.get(0);

            // hent Saldo fra Kontoen funnet i transaksjonen
            sql = "Select Saldo from Konto where kontonummer =?";
            List<Konto> konti = db.query(sql, new BeanPropertyRowMapper(Konto.class), transaksjonen.getKontonummer());
            Konto kontoen = konti.get(0);

            double nySaldo = kontoen.getSaldo() - transaksjonen.getBelop();

            // sett "Avventer" på TXiD til 0
            sql = "Update Transaksjon Set Avventer = '0' Where TxID = ?";
            db.update(sql,txID);

            // oppdater Saldo på Konto
            sql = "Update Konto Set Saldo = ? Where kontonummer = ?";
            db.update(sql,nySaldo,transaksjonen.getKontonummer());

            return "OK";
        }
        catch(Exception e){
            return "Feil";
        }
    }

    public Kunde hentKundeInfo(String personnummer){
        try {
            String sql = "Select * from Kunde Join Poststed On Kunde.Postnr = Poststed.Postnr Where Personnummer= ?";
            List<Kunde> kunder = db.query(sql, new BeanPropertyRowMapper(Kunde.class), personnummer);
            return kunder.get(0);
        }
        catch(Exception e){
            return null;
        }
    }

    public String endreKundeInfo(Kunde kunde) {
        // Sjekk om nytt postnr ligger i Poststeds-tabellen, dersom ikke legg det inn
        String sql = "SELECT count(*) FROM Poststed WHERE postnr = ?";
        int etPostSted  = db.queryForObject(sql,Integer.class,kunde.getPostnr());

        if(etPostSted == 0)
        {
            // ligger ikke i poststedstabellen
            try {
                sql = "Insert Into Poststed (Postnr, Poststed) Values (?,?)";
                db.update(sql, kunde.getPostnr(), kunde.getPoststed());
            }
            catch(Exception e){
                return "Feil";
            }
        }
        // oppdater Kunde-tabellen
        try {
            sql = "Update Kunde Set Fornavn = ?, Etternavn = ?," +
                    " Adresse = ?, Postnr = ?, Telefonnr = ?, Passord =? Where Personnummer = ?";
            db.update(sql, kunde.getFornavn(), kunde.getEtternavn(), kunde.getAdresse(), kunde.getPostnr(),
                    kunde.getTelefonnr(), kunde.getPassord(), kunde.getPersonnummer());
        }
        catch(Exception e){
            return "Feil";
        }
        return "OK";
    }
}