package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ProjeccionData;
import model.Session;
import model.Sessions;

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

public class ProjeccionsController extends SampleController {

    private List<Session> sessions;

    private ObservableList<ProjeccionData> projeccionDataTable;

    @FXML
    TableView projeccionTable;
    @FXML
    Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        projeccionDataTable  = FXCollections.observableArrayList();

        CreaTable();
        setprojeccionTable();
    }

    private void CreaTable() {

        TableColumn columna1 = new TableColumn("Titol");
        TableColumn columna2 = new TableColumn("Date");
        TableColumn columna3 = new TableColumn("Cinema");
        TableColumn columna4 = new TableColumn("localitat");
        TableColumn columna5 = new TableColumn("comarca");
        TableColumn columna6 = new TableColumn("versio");

        columna1.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("titol"));
        columna2.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("data"));
        columna3.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("cinema"));
        columna4.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("localitat"));
        columna5.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("comarca"));
        columna6.setCellValueFactory(new PropertyValueFactory<ProjeccionData,String>("versio"));

        projeccionTable.getColumns().addAll(columna1,columna2,columna3,columna4,columna5,columna6);
    }

    private void setprojeccionTable() {
        try {
            String sessionURL = "http://www.gencat.cat/llengua/cinema/film_sessions.xml";
            URL urlSession = new URL(sessionURL);
            sessions = JAXB.unmarshal(urlSession, Sessions.class).sessionList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (Session session : sessions.stream().filter(session -> session.getIdfilm() == idFilms).collect(Collectors.toList())){
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(session.getSes_data());
                if (date1.after(Calendar.getInstance().getTime()))
                    projeccionDataTable.add(new ProjeccionData(session.getTitol(),session.getSes_data(),session.getCinenom(),session.getLocalitat(),session.getComarca(),session.getVer()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        projeccionTable.setItems(projeccionDataTable);
    }

    @FXML
    public void back(MouseEvent mouseEvent) {

        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
