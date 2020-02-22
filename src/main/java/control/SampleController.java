package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import model.*;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SampleController implements Initializable {

    private final String filmURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    private List<Film> films;
    private final String cineURL ="http://gencat.cat/llengua/cinema/cinemes.xml";
    private List<Cine> cines;

    private ObservableList<String> nombrePelicula, nombreCines;

    @FXML
    private ListView<String> peliculasLista, cinesLista;
    @FXML
    private ImageView imagenPeli;
    @FXML
    private Text titol,original,direccio;
    @FXML
    private Text direccion,localidad,comarca,provincia;
    @FXML
    private Button projeccionsButton;

    static int idFilms;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombrePelicula  = FXCollections.observableArrayList();
        nombreCines  = FXCollections.observableArrayList();

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

        peliculasLista.setItems(nombrePelicula);
        cinesLista.setItems(nombreCines);

        projeccionsButton.setVisible(false);
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
            idFilms= f.getIdfilm();
        }
        projeccionsButton.setVisible(true);
    }

    @FXML
    public void projeccionsClick(MouseEvent arg0) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projeccions.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
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
