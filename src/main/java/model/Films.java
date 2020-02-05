package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Films{
    @XmlElement(name = "FILM")
    public
    List<Film> filmList;

    @Override
    public String toString() {
        return "Films{" +
                "filmList=" + filmList +
                '}';
    }
}
