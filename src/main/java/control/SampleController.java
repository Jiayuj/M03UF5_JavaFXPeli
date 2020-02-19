package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Cine;
import model.Cines;
import model.Film;
import model.Films;

import javax.xml.bind.JAXB;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    static final String filmURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    static List<Film> films;
    static final String cineURL ="http://gencat.cat/llengua/cinema/cinemes.xml";
    static List<Cine> cines;


    private int i;
    ObservableList<String> nombrePeliculas = FXCollections.observableArrayList(), nombreCines = FXCollections.observableArrayList();

    @FXML
    ListView<String> peliculasLista, cinesLista;

    @FXML
    Pane panePeliculas;

    @FXML
    Text direccion = new Text(), localidad = new Text(), comarca = new Text(), provincia = new Text();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            URL urlFilms = new URL(filmURL), urlCines = new URL(cineURL);
            films = JAXB.unmarshal(urlFilms, Films.class).filmList;
            cines = JAXB.unmarshal(urlCines, Cines.class).cineList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        i=0;
        for (Film f : films) {
            nombrePeliculas.add(f.getTitol());
        }
        for (Cine c: cines) {
            nombreCines.add(c.getCinenom());
        }

        peliculasLista.setItems(nombrePeliculas);
        cinesLista.setItems(nombreCines);


    }

    @FXML public void listPeliClick(MouseEvent arg0) {
        String s = peliculasLista.getSelectionModel().getSelectedItem();
        for (Film f : films) {
            if (f.getTitol().equals(s)) s=f.getCartell();
        }
        panePeliculas.getChildren().clear();
        ImageView imagenPeli = new ImageView("http://gencat.cat/llengua/cinema/"+s);
        imagenPeli.setFitHeight(512);
        imagenPeli.setFitWidth(384);
        panePeliculas.getChildren().add(imagenPeli);
    }

    public void listCineClick(MouseEvent mouseEvent) {
        String s = cinesLista.getSelectionModel().getSelectedItem();
        for (Cine c : cines) {
            if (c.getCinenom().equals(s)) {
                direccion.setText("Dirección:    " + c.getCineadreca());
                localidad.setText("Localidad:    " + c.getLocalitat());
                comarca.setText("Comarca:     " + c.getComarca());
                provincia.setText("Provincia:     " + c.getProvincia());
            }
        }
    }
}
