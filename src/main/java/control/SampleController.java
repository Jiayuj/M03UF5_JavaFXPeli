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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    Button projeccionsButton, closs;
    @FXML
    Pane projeccionPane;
    @FXML
    TableView projeccionTable;


    int idFilms;

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
            idFilms = f.getIdfilm();
        }
        projeccionsButton.setVisible(true);
    }

    @FXML
    public void projeccionsClick(MouseEvent arg0) {
        projeccionPane.setVisible(true);
        setprojeccionTable();

    }
    @FXML
    public void clossclick(MouseEvent arg0) {
        projeccionPane.setVisible(false);
        projeccionDataTable.clear();
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


    private void setprojeccionTable() {
        TableColumn columna1 = new TableColumn("Titol");
        TableColumn columna2 = new TableColumn("Date");
        TableColumn columna3 = new TableColumn("Cinema");
        TableColumn columna4 = new TableColumn("localitat");
        TableColumn columna5 = new TableColumn("comarca");
        TableColumn columna6 = new TableColumn("versio");

        try {
            URL urlSession = new URL(sessionURL);
            sessions = JAXB.unmarshal(urlSession,Sessions.class).sessionList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (Session session : sessions.stream().filter(session -> session.getIdfilm() == idFilms).collect(Collectors.toList())){
            try {
                Date  date1 = new SimpleDateFormat("dd/MM/yyyy").parse(session.getSes_data());
                if (date1.after(Calendar.getInstance().getTime()))
                    projeccionDataTable.add(new ProjeccionData(session.getTitol(),session.getSes_data(),session.getCinenom(),session.getLocalitat(),session.getComarca(),session.getVer()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        columna1.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("titol"));
        columna2.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("data"));
        columna3.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("cinema"));
        columna4.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("localitat"));
        columna5.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("comarca"));
        columna6.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("versio"));

        projeccionTable.getColumns().addAll(columna1,columna2,columna3,columna4,columna5,columna6);
        projeccionTable.setItems(projeccionDataTable);
    }
}
