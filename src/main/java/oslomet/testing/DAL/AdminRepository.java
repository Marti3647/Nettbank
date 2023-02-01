package oslomet.testing.DAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate db;

    public List<Kunde> hentAlleKunder(){
         try{
             String sql = "Select * from Kunde Join Poststed On Kunde.Postnr = Poststed.Postnr ORDER BY Personnummer" ;
             List<Kunde> alleKunder = db.query(sql,new BeanPropertyRowMapper(Kunde.class));
             return alleKunder;
         }
         catch(Exception e){
             return null;
        }
    }

    public String endreKundeInfo(Kunde kunde) {
        // Sjekk om nytt postnr ligger i Poststeds-tabellen, dersom ikke legg det inn
        int etPostSted;
        String sql;
        try {
            sql = "SELECT count(*) FROM Poststed WHERE postnr = ?";
            etPostSted = db.queryForObject(sql, Integer.class, kunde.getPostnr());
        }
        catch (Exception e){
            return "Feil";
        }
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

    public String registrerKunde(Kunde kunde) {
        // Sjekk om nytt postnr ligger i Poststeds-tabellen, dersom ikke legg det inn
        String sql;
        int etPostSted;
        try {
            sql = "SELECT count(*) FROM Poststed WHERE postnr = ?";
            etPostSted = db.queryForObject(sql, Integer.class, kunde.getPostnr());
        }
        catch(Exception e){
            return "Feil";
        }
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
        try{
            sql = "Insert into Kunde (Personnummer,Fornavn,Etternavn,Adresse,Postnr,Telefonnr,Passord) " +
                    "Values (?,?,?,?,?,?,?)";
                    db.update(sql,kunde.getPersonnummer(),kunde.getFornavn(),kunde.getEtternavn(),
                    kunde.getAdresse(),kunde.getPostnr(),kunde.getTelefonnr(),kunde.getPassord());
        }
        catch(Exception e){
            return "Feil";
        }
        return "OK";
    }

    public String slettKunde(String personnummer)  {
        try{
            String sql = "Delete From Kunde Where Personnummer = ?";
            db.update(sql,personnummer);
        }
        catch(Exception e){
            return "Feil";
        }
        return "OK";
    }

    public String registrerKonto(Konto konto) {
        try{
            String sql = "Select count(*) from Kunde Where Personnummer = ?";
            int funnetPersonnummer  = db.queryForObject(sql,Integer.class,konto.getPersonnummer());
            if (funnetPersonnummer == 0){
                return "Feil";
            }
            sql = "Insert into Konto (Personnummer, Kontonummer, Saldo, Type, Valuta) Values (?,?,?,?,?)";
            db.update(sql,konto.getPersonnummer(),konto.getKontonummer(),konto.getSaldo(),konto.getType(),
                    konto.getValuta());
        }
        catch(Exception e){
            return "Feil";
        }
        return "OK";
    }

    public String endreKonto(Konto konto)  {
        try{
            String sql = "Select count(*) from Kunde Where Personnummer = ?";
            int funnetPersonnummer  = db.queryForObject(sql,Integer.class,konto.getPersonnummer());
            if (funnetPersonnummer == 0){
                return "Feil i personnummer";
            }

            sql = "Select count(*) from Konto Where Kontonummer = ?";
            int funnetKontonummer  = db.queryForObject(sql,Integer.class,konto.getKontonummer());
            if (funnetKontonummer == 0){
                return "Feil i kontonummer";
            }

            sql =  "Update Konto Set Personnummer = ?, Kontonummer = ?, Type = ?, " +
                    "Saldo = ?, Valuta = ? Where Kontonummer = ?";
            db.update(sql,konto.getPersonnummer(),konto.getKontonummer(),konto.getType(),
                    konto.getSaldo(),konto.getValuta(),konto.getKontonummer());
        }
        catch(Exception e){
            return "Feil";
        }
        return "OK";
    }

    public List<Konto> hentAlleKonti() {
        String sql = "Select * from Konto ORDER BY Kontonummer";
        List<Konto> alleKonti = db.query(sql,new BeanPropertyRowMapper(Konto.class));
        return alleKonti;
    }

    public String slettKonto(String kontonummer)
    {
        try{
            String sql = "Delete from Konto Where Kontonummer = ?";
            db.update(sql,kontonummer);
        }
        catch(Exception e){
            return "Feil kononummer";
        }
        return "OK";
    }
}
