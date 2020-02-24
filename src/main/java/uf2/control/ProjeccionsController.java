package uf2.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uf2.model.ProjeccionData;
import uf2.model.Session;
import uf2.model.Sessions;

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
    List<Session> sessionsfiter;

    private ObservableList<ProjeccionData> projeccionDataTable;

    @FXML
    TableView projeccionTable;
    @FXML
    Button backButton;

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
    // cojer datos de xml,  indicar tipo es peli , cine o ciclo,  final mosntra en datos en tabla
    private void setprojeccionTable() {
        try {
            String sessionURL = "http://www.gencat.cat/llengua/cinema/film_sessions.xml";
            URL urlSession = new URL(sessionURL);
            sessions = JAXB.unmarshal(urlSession, Sessions.class).sessionList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        switch (idTap){
            case 0:
                sessionsfiter = sessions.stream().filter(session -> session.getIdfilm() == id).collect(Collectors.toList());
                break;
            case 1:
                sessionsfiter = sessions.stream().filter(session -> session.getCineid() == id).collect(Collectors.toList());
                break;
            case 2:
                sessionsfiter = sessions.stream().filter(session -> session.getCicleid() == id).collect(Collectors.toList());
                break;
            default:
                break;
        }

        for (Session session : sessionsfiter){
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

    //volver a inicior
    @FXML
    public void back(MouseEvent mouseEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/sample.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
