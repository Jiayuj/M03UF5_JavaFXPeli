import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.*;


import javax.xml.bind.JAXB;
import java.io.IOException;
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
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Aplicación de Busqueda");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
        Scene scene = primaryStage.getScene();
    }

    public static void main(String[] args) {
        launch(args);
        long contador = 0;

        try {
            URL url = new URL(filmURL);
            films= JAXB.unmarshal(url, Films.class).filmList;

            contador = films.stream().count();
            System.out.println(contador);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
