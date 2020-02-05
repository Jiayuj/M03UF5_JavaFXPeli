import javafx.application.Application;
import javafx.stage.Stage;
import model.*;


import javax.xml.bind.JAXB;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

public class Main extends Application {

    static final String CineURL ="http://gencat.cat/llengua/cinema/cinemes.xml";
    static List<Cine> cines;
    static final String filmURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    static List<Film> films;
    static final String cicloURL ="http://gencat.cat/llengua/cinema/cicles.xml";
    static List<Ciclo> ciclos;
    static final String sessionURL ="http://www.gencat.cat/llengua/cinema/film_sessions.xml";
    static List<Session> sessions;


    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        try {
            URL url = new URL(filmURL);
            films= JAXB.unmarshal(url, Films.class).filmList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
