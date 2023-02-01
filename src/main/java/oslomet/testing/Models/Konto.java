package oslomet.testing.Models;

import java.util.List;

public class Konto
{
    private String personnummer;
    private String kontonummer;
    private double saldo;
    private String type;
    private String valuta;
    private List<Transaksjon> transaksjoner;

    public Konto(String personnummer, String kontonummer, double saldo, String type, String valuta, List<Transaksjon> transaksjoner) {
        this.personnummer = personnummer;
        this.kontonummer = kontonummer;
        this.saldo = saldo;
        this.type = type;
        this.valuta = valuta;
        this.transaksjoner = transaksjoner;
    }

    public Konto() {
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public List<Transaksjon> getTransaksjoner() {
        return transaksjoner;
    }

    public void setTransaksjoner(List<Transaksjon> transaksjoner) {
        this.transaksjoner = transaksjoner;
    }
}
