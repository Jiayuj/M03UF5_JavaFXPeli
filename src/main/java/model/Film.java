package model;

import javax.xml.bind.annotation.XmlElement;


public class Film {

    int idfilm, prioritat, any;
    String titol, cartell, original, direccion, sinopsi, versio, idioma, qualificacio,situacio, trailer, estrena,interprets,web;

    public int getIdfilm() {
        return idfilm;
    }
    @XmlElement(name = "IDFILM")
    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    public int getPrioritat() {
        return prioritat;
    }
    @XmlElement(name = "PRIORITAT")
    public void setPrioritat(int prioritat) {
        this.prioritat = prioritat;
    }

    public int getAny() {
        return any;
    }
    @XmlElement(name = "ANY")
    public void setAny(int any) {
        this.any = any;
    }

    public String getTitol() {
        return titol;
    }
    @XmlElement(name = "TITOL")
    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getCartell() {
        return cartell;
    }
    @XmlElement(name = "CARTELL")
    public void setCartell(String cartell) {
        this.cartell = cartell;
    }

    public String getOriginal() {
        return original;
    }
    @XmlElement(name = "ORIGINAL")
    public void setOriginal(String original) {
        this.original = original;
    }

    public String getDireccion() {
        return direccion;
    }
    @XmlElement(name = "DIRECCIO")
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSinopsi() {
        return sinopsi;
    }
    @XmlElement(name = "SINOPSI")
    public void setSinopsi(String sinopsi) {
        this.sinopsi = sinopsi;
    }

    public String getVersio() {
        return versio;
    }
    @XmlElement(name = "VERSIO")
    public void setVersio(String versio) {
        this.versio = versio;
    }

    public String getIdioma() {
        return idioma;
    }
    @XmlElement(name = "IDIOMA_x0020_ORIGINAL")
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getQualificacio() {
        return qualificacio;
    }
    @XmlElement(name = "QUALIFICACIO")
    public void setQualificacio(String qualificacio) {
        this.qualificacio = qualificacio;
    }

    public String getTrailer() {
        return trailer;
    }
    @XmlElement(name = "TRAILER")
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getEstrena() {
        return estrena;
    }
    @XmlElement(name = "ESTRENA ")
    public void setEstrena(String estrena) {
        this.estrena = estrena;
    }

    public String getSituacio() {
        return situacio;
    }
    @XmlElement(name = "SITUACIO")
    public void setSituacio(String situacio) {
        this.situacio = situacio;
    }

    public String getInterprets() {
        return interprets;
    }
    @XmlElement(name = "INTERPRETS")
    public void setInterprets(String interprets) {
        this.interprets = interprets;
    }

    public String getWeb() {
        return web;
    }
    @XmlElement(name = "WEB")
    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Film{" +
                "IDFILM=" + idfilm +
                ", PRIORITAT=" + prioritat +
                ", TITOL=" + titol +
                ", SITUACIO='" + situacio + '\'' +
                ", ANY='" + any + '\'' +
                ", CARTELL='" + cartell + '\'' +
                ", ORIGINAL='" + original + '\'' +
                ", DIRECCIO='" + direccion + '\'' +
                ", INTERPRETS='" + interprets + '\'' +
                ", SINOPSI='" + sinopsi + '\'' +
                ", VERSIO='" + versio + '\'' +
                ", IDIOMA_x0020_ORIGINAL='" + idioma + '\'' +
                ", QUALIFICACIO='" + qualificacio + '\'' +
                ", TRAILER='" + trailer + '\'' +
                ", WEB='" + web + '\'' +
                ", ESTRENA='" + estrena + '\'' +
                '}';
    }

}
