package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Film;
import model.Films;

import javax.imageio.ImageIO;
import javax.swing.text.Element;


import javax.xml.bind.JAXB;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SampleController implements Initializable {

    static final String filmURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    static List<Film> films;

    private int i;
    ObservableList<String> names = FXCollections.observableArrayList();

    @FXML
    ListView<String> lsvLlista01;
    @FXML
    ImageView imagenPeli;
    @FXML
    Text titol,original,direccio;
    @FXML
    Pane pane;

    TableView tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            URL url1 = new URL(filmURL);
            films= JAXB.unmarshal(url1, Films.class).filmList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        i=0;
        for (Film f : films) {
            names.add(f.getTitol());
        }

        lsvLlista01.setItems(names);

        pane.setVisible(false);
        tableView = new TableView<>();

    }

    @FXML
    public void listPeliClick(MouseEvent arg0) {

        String s = lsvLlista01.getSelectionModel().getSelectedItem();

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

        tableView.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");


        tableView.setItems(names);


        tableView.getColumns().addAll(firstNameCol);



        pane.getChildren().add(tableView);




    }


}
