package model;

public class ProjeccionData {

    public String titol;
    public String data;
    public String cinema;
    public String localitat;
    public String comarca;
    public String versio;

    public ProjeccionData(String titol, String data, String cinema, String localitat, String comarca, String versio) {
        this.titol = titol;
        this.data = data;
        this.cinema = cinema;
        this.localitat = localitat;
        this.comarca = comarca;
        this.versio = versio;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getLocalitat() {
        return localitat;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getVersio() {
        return versio;
    }

    public void setVersio(String versio) {
        this.versio = versio;
    }
}
