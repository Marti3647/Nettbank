package oslomet.testing.Models;

public class Transaksjon
{
    private int txID;
    private String fraTilKontonummer;
    private double belop;
    private String dato;
    private String melding;
    private String avventer;
    private String kontonummer;

    public Transaksjon(int txID, String fraTilKontonummer, double belop, String dato, String melding, String avventer, String kontonummer) {
        this.txID = txID;
        this.fraTilKontonummer = fraTilKontonummer;
        this.belop = belop;
        this.dato = dato;
        this.melding = melding;
        this.avventer = avventer;
        this.kontonummer = kontonummer;
    }

    public Transaksjon() {
    }

    public int getTxID() {
        return txID;
    }

    public void setTxID(int txID) {
        this.txID = txID;
    }

    public String getFraTilKontonummer() {
        return fraTilKontonummer;
    }

    public void setFraTilKontonummer(String fraTilKontonummer) {
        this.fraTilKontonummer = fraTilKontonummer;
    }

    public double getBelop() {
        return belop;
    }

    public void setBelop(double belop) {
        this.belop = belop;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getMelding() {
        return melding;
    }

    public void setMelding(String melding) {
        this.melding = melding;
    }

    public String getAvventer() {
        return avventer;
    }

    public void setAvventer(String avventer) {
        this.avventer = avventer;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }
}


    
  

