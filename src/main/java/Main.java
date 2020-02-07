import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;


import javax.xml.bind.JAXB;
import java.awt.*;
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

    private TableView table = new TableView();


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));

        stage.setTitle("App");

        table.setEditable(true);

        TableColumn cineIdCol = new TableColumn("Cine_ID");
        TableColumn cineCol = new TableColumn("Cine");
        TableColumn cineAdrecaCol = new TableColumn("Cine_Adreça");
        TableColumn localitatCol = new TableColumn("Cine_Adreça");
        TableColumn comarcaCol = new TableColumn("Cine_Adreça");
        TableColumn provinciaCol = new TableColumn("Cine_Adreça");


        table.getColumns().addAll(cineIdCol, cineCol, cineAdrecaCol, localitatCol, comarcaCol, provinciaCol);

        stage.setScene(new Scene(root, 500, 510));
        stage.show();
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
