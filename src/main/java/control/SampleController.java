package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import model.*;

import javax.xml.bind.JAXB;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SampleController implements Initializable {

    final String filmURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    List<Film> films;
    final String cineURL ="http://gencat.cat/llengua/cinema/cinemes.xml";
    List<Cine> cines;
    final String sessionURL ="http://www.gencat.cat/llengua/cinema/film_sessions.xml";
    List<Session> sessions;

    ObservableList<String> nombrePelicula, nombreCines;
    ObservableList<ProjeccionData> projeccionDataTable;

    @FXML
    ListView<String> peliculasLista, cinesLista;
    @FXML
    ImageView imagenPeli;
    @FXML
    Text titol,original,direccio;
    @FXML
    Text direccion,localidad,comarca,provincia;
    @FXML
    Button projeccions;
    @FXML
    Pane projeccionPane;
    @FXML
    TableView projeccionTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombrePelicula  = FXCollections.observableArrayList();
        nombreCines  = FXCollections.observableArrayList();
        projeccionDataTable  = FXCollections.observableArrayList();

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

        projeccionPane.setVisible(false);


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

    }

    @FXML
    public void projeccionsClick(MouseEvent arg0) {
        projeccionPane.setVisible(true);
        setprojeccionTable(titol.getText());

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


    private void setprojeccionTable(String s) {
        TableColumn columna1 = new TableColumn("Titol");
        TableColumn columna2 = new TableColumn("Date");
        TableColumn columna3 = new TableColumn("Cinema");



        try {
            URL urlSession = new URL(sessionURL);
            sessions = JAXB.unmarshal(urlSession,Sessions.class).sessionList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        columna1.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("titol"));


        projeccionDataTable.add(new ProjeccionData(titol.getText(),"1","1","1","1","1"));


        projeccionTable.getColumns().addAll(columna1);
        projeccionTable.setItems(projeccionDataTable);



    }
}
