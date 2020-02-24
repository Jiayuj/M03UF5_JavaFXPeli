package uf2.model;


import javax.xml.bind.annotation.XmlElement;

public class Ciclo {

    int cicleid;
    String ciclenom, cicleinfo, imgcicle,web;

    public int getCicleid() {
        return cicleid;
    }
    @XmlElement(name = "CICLEID")
    public void setCicleid(int cicleid) {
        this.cicleid = cicleid;
    }

    public String getCiclenom() {
        return ciclenom;
    }
    @XmlElement(name = "CICLENOM")
    public void setCiclenom(String ciclenom) {
        this.ciclenom = ciclenom;
    }

    public String getCicleinfo() {
        return cicleinfo;
    }
    @XmlElement(name = "CICLEINFO")
    public void setCicleinfo(String cicleinfo) {
        this.cicleinfo = cicleinfo;
    }

    public String getImgcicle() {
        return imgcicle;
    }
    @XmlElement(name = "IMGCICLE")
    public void setImgcicle(String imgcicle) {
        this.imgcicle = imgcicle;
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
        return "Ciclo{" +
                "CICLEID=" + cicleid +
                ", CICLENOM='" + ciclenom + '\'' +
                ", CICLEINFO='" + cicleinfo + '\'' +
                ", IMGCICLE='" + imgcicle + '\'' +
                '}';
    }
}
