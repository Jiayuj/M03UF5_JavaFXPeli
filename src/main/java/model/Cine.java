package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Cines {
    @XmlElement(name = "FILM")
    List<Cine> cineList;
    @Override
    public String toString() {
        return "Cines{" +
                "cineList=" + cineList +
                '}';
    }
}
public class Cine {

    int CINEID,

}

