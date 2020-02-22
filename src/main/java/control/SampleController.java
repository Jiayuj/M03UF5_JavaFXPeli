package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import model.*;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SampleController implements Initializable {
    @FXML
    TabPane tabPane;

    private List<Film> films;
    private List<Cine> cines;
    private List<Ciclo> ciclos;

    private ObservableList<String> nombrePelicula, nombreCines,nombreCiclos;
    private ObservableList<PieChart.Data> dataChartsYears, dataChartsCines;

    @FXML
    private ListView<String> peliculasLista, cinesLista,ciclosLista;
    @FXML
    private ImageView imagenPeli,imagenCiclo;
    @FXML
    private Text titol,original,direccio;
    @FXML
    private Text direccion,localidad,comarca,provincia;
    @FXML
    private Text nombreCiclo, infoCiclo,webCiclo;
    @FXML
    private Button projeccionsButtonPeli, projeccionsButtonCine,projeccionsButtonCiclo;
    @FXML
    private PieChart estadisticasAño, estadisticasCine;

    static int id;
    static String tapclick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombrePelicula  = FXCollections.observableArrayList();
        nombreCines  = FXCollections.observableArrayList();
        nombreCiclos  = FXCollections.observableArrayList();

        try {
            String cineURL = "http://gencat.cat/llengua/cinema/cinemes.xml";
            String filmURL = "http://gencat.cat/llengua/cinema/provacin.xml";
            String cicloURL ="http://gencat.cat/llengua/cinema/cicles.xml";
            URL urlFilms = new URL(filmURL), urlCines = new URL(cineURL), urlCiclos = new URL(cicloURL);
            films = JAXB.unmarshal(urlFilms, Films.class).filmList;
            cines = JAXB.unmarshal(urlCines, Cines.class).cineList;
            ciclos = JAXB.unmarshal(urlCiclos, Ciclos.class).cicloList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (Film f : films) {
            nombrePelicula.add(f.getTitol());
        }
        for (Cine c: cines) {
            nombreCines.add(c.getCinenom());
        }
        for (Ciclo c: ciclos) {
            nombreCiclos.add(c.getCiclenom());
        }

        peliculasLista.setItems(nombrePelicula);
        cinesLista.setItems(nombreCines);
        ciclosLista.setItems(nombreCiclos);

        dataChartsYears = FXCollections.observableArrayList();
        loadDataPieChart();
        estadisticasAño.setLegendSide(Side.LEFT);

        dataChartsCines = FXCollections.observableArrayList();
        loadDataPieChartCine();
        estadisticasCine.setData(dataChartsCines);
        estadisticasCine.setLegendSide(Side.LEFT);

        projeccionsButtonPeli.setVisible(false);
        projeccionsButtonCine.setVisible(false);
        projeccionsButtonCiclo.setVisible(false);

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
            id= f.getIdfilm();
            System.out.println("1");
        }
        projeccionsButtonPeli.setVisible(true);
    }

    @FXML
    public void projeccionsClick(MouseEvent arg0) {
        try {
            tapclick = tabPane.getSelectionModel().getSelectedItem().getText();
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
        for (Cine c : cines.stream().filter(l -> l.getCinenom().equals(s)).collect(Collectors.toList())) {
            direccion.setText("Dirección:    " + c.getCineadreca());
            localidad.setText("Localidad:    " + c.getLocalitat());
            comarca.setText("Comarca:     " + c.getComarca());
            provincia.setText("Provincia:     " + c.getProvincia());
            id= c.getCineid();
        }
        projeccionsButtonCine.setVisible(true);
    }
    @FXML
    public void listCicloClick(MouseEvent mouseEvent) {
        String s = ciclosLista.getSelectionModel().getSelectedItem();
        for (Ciclo c : ciclos.stream().filter(l -> l.getCiclenom().equals(s)).collect(Collectors.toList())) {
            Image image = new Image("http://gencat.cat/llengua/cinema/"+c.getImgcicle());
            imagenCiclo.setImage(image);
            nombreCiclo.setText("Nombre: " + c.getCiclenom());
            infoCiclo.setText("Información: " + c.getCicleinfo());
            id= c.getCicleid();
            if (c.getWeb() == null) {
                webCiclo.setText("");
                projeccionsButtonCiclo.setVisible(true);
            } else {
                webCiclo.setText("Web: " + c.getWeb());
                projeccionsButtonCiclo.setVisible(false);
            }
        }

    }

    @FXML
    Pane panePelicula = new Pane(), paneCine = new Pane();

    public void loadDataPieChart() {
        List<Integer> años = films.stream()
                .map(film -> film.getAny())
                .filter(i -> i > 0 && i < 3000).distinct()
                .sorted(Comparator.comparingInt(integer -> integer))
                .collect(Collectors.toList());

        for (Integer i: años) {
            long numResultat = films.stream()
                    .filter(film1 -> film1.getAny() == i)
                    .count();
            dataChartsYears.add(new PieChart.Data(i.toString(), numResultat));
        }

        estadisticasAño.setData(dataChartsYears);

        final Label label = new Label();
        panePelicula.getChildren().add(label);
        label.setFont(Font.font("SanSerif", FontWeight.BLACK, 20));

        estadisticasAño.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e->{
                int intValue = (int) data.getPieValue();
                panePelicula.setVisible(true);
                if(intValue==1){
                    label.setText(intValue + " pelicula");
                }else {
                    label.setText(intValue + " peliculas");
                }
            });
        });
    }

    public void loadDataPieChartCine() {
        List<String> localidades = cines.stream()
                .map(cine -> cine.getLocalitat())
                .distinct()
                .sorted(Comparator.comparing(String -> String))
                .collect(Collectors.toList());

        for (String i: localidades) {
            long numResultat= cines.stream()
                    .filter(cine1 -> cine1.getLocalitat() == i)
                    .count();
            dataChartsCines.add(new PieChart.Data(i, numResultat));
        }

        final Label label = new Label();
        paneCine.getChildren().add(label);
        label.setFont(Font.font("SanSerif", FontWeight.BLACK, 20));

        estadisticasCine.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e->{
                int intValue = (int) data.getPieValue();
                paneCine.setVisible(true);
                if(intValue==1){
                    label.setText(intValue + " cine");
                }else {
                    label.setText(intValue + " cines");
                }
            });
        });
    }
}
