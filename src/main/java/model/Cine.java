package model;


import javax.xml.bind.annotation.XmlElement;

public class Cine {

    int cineid;
    String cinenom, cineadreca, localitat, comarca, provincia;

    public int getCineid() {
        return cineid;
    }
    @XmlElement(name = "CINEID")
    public void setCineid(int cineid) {
        this.cineid = cineid;
    }

    public String getCinenom() {
        return cinenom;
    }
    @XmlElement(name = "CINENOM")
    public void setCinenom(String cinenom) {
        this.cinenom = cinenom;
    }

    public String getCineadreca() {
        return cineadreca;
    }
    @XmlElement(name = "CINEADRECA")
    public void setCineadreca(String cineadreca) {
        this.cineadreca = cineadreca;
    }

    public String getLocalitat() {
        return localitat;
    }
    @XmlElement(name = "LOCALITAT")
    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    public String getComarca() {
        return comarca;
    }
    @XmlElement(name = "COMARCA")
    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getProvincia() {
        return provincia;
    }
    @XmlElement(name = "PROVINCIA")
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "cineid=" + cineid +
                ", cinenom='" + cinenom + '\'' +
                ", cineadreca='" + cineadreca + '\'' +
                ", localitat='" + localitat + '\'' +
                ", comarca='" + comarca + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}

