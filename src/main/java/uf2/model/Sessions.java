package uf2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Sessions{
    @XmlElement(name = "SESSIONS")
    public List<Session> sessionList;

    @Override
    public String toString() {
        return "Sessions{" +
                "sessionList=" + sessionList +
                '}';
    }
}
