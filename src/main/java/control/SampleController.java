package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
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
import java.util.stream.Collectors;

public class SampleController implements Initializable {

    static final String filmURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    static List<Film> films;
    static final String cineURL ="http://gencat.cat/llengua/cinema/cinemes.xml";
    static List<Cine> cines;

    ObservableList<String> nombrePelicula = FXCollections.observableArrayList(), nombreCines = FXCollections.observableArrayList();

    @FXML
    ListView<String> peliculasLista, cinesLista;

    @FXML
    ImageView imagenPeli;
    @FXML
    Text titol,original,direccio;
    @FXML
    Pane pane;

    @FXML
    Text direccion,localidad,comarca,provincia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            URL urlFilms = new URL(filmURL), urlCines = new URL(cineURL);
            films = JAXB.unmarshal(urlFilms, Films.class).filmList;
            cines = JAXB.unmarshal(urlCines, Cines.class).cineList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (Film f : films) {
            nombrePelicula.add(f.getTitol());
        }
        for (Cine c: cines) {
            nombreCines.add(c.getCinenom());
        }

        pane.setVisible(false);
        peliculasLista.setItems(nombrePelicula);
        cinesLista.setItems(nombreCines);


    }

    @FXML
    public void listPeliClick(MouseEvent arg0) {

        String s = peliculasLista.getSelectionModel().getSelectedItem();

        for (Film f : films.stream().filter(l -> l.getTitol().equals(s)).collect(Collectors.toList())) {
            Image image = new Image("http://gencat.cat/llengua/cinema/"+f.getCartell());
            imagenPeli.setImage(image);
            titol.setText("Titol: " + f.getTitol());
            original.setText("Títol original: " + f.getOriginal());
            direccio.setText("Director: " + f.getDireccion());
        }

        pane.setVisible(true);
        pane.getChildren().clear();
        pane.getChildren().add(imagenPeli);
        pane.getChildren().add(titol);


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
