package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Film;
import model.Films;

import javax.imageio.ImageIO;
import javax.swing.text.Element;

import javax.xml.bind.JAXB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    static final String filmURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    static List<Film> films;

    private int i;
    ObservableList<String> names = FXCollections.observableArrayList();

    @FXML
    ListView<String> lsvLlista01;
    @FXML
    Pane paneV;

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



    }

    @FXML public void listPeliClick(MouseEvent arg0) {
        String s = lsvLlista01.getSelectionModel().getSelectedItem();
        for (Film f : films) {
            if (f.getTitol().equals(s)) s=f.getCartell();
        }
        paneV.getChildren().clear();
        ImageView imagenPeli = new ImageView("http://gencat.cat/llengua/cinema/"+s);
        imagenPeli.setFitHeight(180);
        imagenPeli.setFitWidth(110);
        paneV.getChildren().add(imagenPeli);


    }


}
