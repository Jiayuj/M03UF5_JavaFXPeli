package model;

import javax.xml.bind.annotation.XmlElement;

public class Session {
    int idfilm, ses_id, cineid, cicleid,preu;
    String titol, ses_data, cinenom, localitat, comarca,ver, ordresessio;

    public int getIdfilm() {
        return idfilm;
    }
    @XmlElement(name = "IDFILM")
    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    public int getSes_id() {
        return ses_id;
    }
    @XmlElement(name = "ses_id")
    public void setSes_id(int ses_id) {
        this.ses_id = ses_id;
    }

    public int getCineid() {
        return cineid;
    }
    @XmlElement(name = "CINEID")
    public void setCineid(int cineid) {
        this.cineid = cineid;
    }

    public int getCicleid() {
        return cicleid;
    }
    @XmlElement(name = "CICLEID")
    public void setCicleid(int cicleid) {
        this.cicleid = cicleid;
    }

    public int getPreu() {
        return preu;
    }
    @XmlElement(name = "preu")
    public void setPreu(int preu) {
        this.preu = preu;
    }

    public String getTitol() {
        return titol;
    }
    @XmlElement(name = "TITOL")
    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getSes_data() {
        return ses_data;
    }
    @XmlElement(name = "ses_data")
    public void setSes_data(String ses_data) {
        this.ses_data = ses_data;
    }

    public String getCinenom() {
        return cinenom;
    }
    @XmlElement(name = "CINENOM")
    public void setCinenom(String cinenom) {
        this.cinenom = cinenom;
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

    public String getVer() {
        return ver;
    }
    @XmlElement(name = "ver")
    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getOrdresessio() {
        return ordresessio;
    }
    @XmlElement(name = "ORDRESESSIO")
    public void setOrdresessio(String ordresessio) {
        this.ordresessio = ordresessio;
    }

    @Override
    public String toString() {
        return "Session{" +
                "IDFILM=" + idfilm +
                ", ses_id=" + ses_id +
                ", CINEID=" + cineid +
                ", CICLEID=" + cicleid +
                ", preu=" + preu +
                ", TITOL='" + titol + '\'' +
                ", ses_data='" + ses_data + '\'' +
                ", CINENOM='" + cinenom + '\'' +
                ", LOCALITAT='" + localitat + '\'' +
                ", COMARCA='" + comarca + '\'' +
                ", ver='" + ver + '\'' +
                ", ORDRESESSIO='" + ordresessio + '\'' +
                '}';
    }
}
