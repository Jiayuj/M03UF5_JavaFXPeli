package uf2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ciclos {
    @XmlElement(name = "CICLE")
    public
    List<Ciclo> cicloList;

    @Override
    public String toString() {
        return "Ciclos{" +
                "cicloList=" + cicloList +
                '}';
    }
}
