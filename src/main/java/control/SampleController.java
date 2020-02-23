package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SampleController implements Initializable {
    @FXML
    TabPane tabPane;

    private List<Film> films;
    private List<Cine> cines;
    private List<Ciclo> ciclos;

    private ObservableList<String> nombrePelicula, nombreCines, nombreCiclos;
    private ObservableList<PieChart.Data> dataChartsYears, dataChartsCines;

    @FXML
    private ListView<String> peliculasLista, cinesLista, ciclosLista;
    @FXML
    private ImageView imagenPeli, imagenCiclo;
    @FXML
    private Text titol, original, direccio;
    @FXML
    private Text direccion, localidad, comarca, provincia;
    @FXML
    private Text nombreCiclo, infoCiclo, webCiclo;
    @FXML
    private Button projeccionsButtonPeli, projeccionsButtonCine, projeccionsButtonCiclo;
    @FXML
    private PieChart estadisticasAño, estadisticasCine;
    @FXML
    Pane panePelicula, paneCine;
    @FXML
    TextField textFieldPelicula;

    static int id, idTap, idPeliListSelect, idCineListSelect, idCicloListSelect;
    URL url;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombrePelicula = FXCollections.observableArrayList();
        nombreCines = FXCollections.observableArrayList();
        nombreCiclos = FXCollections.observableArrayList();

        dataChartsYears = FXCollections.observableArrayList();
        estadisticasAño.setLegendSide(Side.LEFT);

        dataChartsCines = FXCollections.observableArrayList();
        estadisticasCine.setLegendSide(Side.LEFT);

        showTabPaneDetail();

        tabPane.getSelectionModel().select(idTap);
    }

    public void tabpaneClick(MouseEvent mouseEvent) {
        idTap = tabPane.getSelectionModel().getSelectedIndex();
        showTabPaneDetail();
    }

    private void showTabPaneDetail() {
        try {


            switch (idTap) {
                case 0:
                    showPeliList();
                    showPeliSelectDetail();
                    break;
                case 1:
                    showCinelist();
                    showCineSelectDetail();
                    break;
                case 2:
                    showCiclosList();
                    showCicloSelectDetail();
                    break;
                case 3:
                    //Esto es necesario para poder cargar los datos en el gráfico sin que pete en ningún momento.
                    showPeliList();
                    loadDataPieChart();
                    break;
                case 4:
                    //Esto es necesario para poder cargar los datos en el gráfico sin que pete en ningún momento.
                    showCinelist();
                    loadDataPieChartCine();
                    break;
                default:
                    break;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //Carga los datos, información, dentro del XML para poder mostrarlos posteriormente.
    private void showCiclosList() throws MalformedURLException {
        url = new URL("http://gencat.cat/llengua/cinema/cicles.xml");
        nombreCiclos.clear();
        ciclos = JAXB.unmarshal(url, Ciclos.class).cicloList;
        for (Ciclo c : ciclos) {
            nombreCiclos.add(c.getCiclenom());
        }
        ciclosLista.setItems(nombreCiclos);
        ciclosLista.getSelectionModel().select(idCicloListSelect);
    }

    private void showPeliList() throws MalformedURLException {
        url = new URL("http://gencat.cat/llengua/cinema/provacin.xml");
        nombrePelicula.clear();
        films = JAXB.unmarshal(url, Films.class).filmList;
        for (Film f : films) {
            nombrePelicula.add(f.getTitol());
        }
        peliculasLista.setItems(nombrePelicula);
        peliculasLista.getSelectionModel().select(idPeliListSelect);
        panePelicula.getChildren().clear();
    }

    private void showCinelist() throws MalformedURLException {
        url = new URL("http://gencat.cat/llengua/cinema/cinemes.xml");
        nombreCines.clear();
        cines = JAXB.unmarshal(url, Cines.class).cineList;
        for (Cine c : cines) {
            nombreCines.add(c.getCinenom());
        }
        cinesLista.setItems(nombreCines);
        cinesLista.getSelectionModel().select(idCineListSelect);
        paneCine.getChildren().clear();
    }

    //Muestra los detalles de las peliculas a clicar sobre estas.
    @FXML
    public void listPeliClick(MouseEvent arg0) {
        idPeliListSelect = peliculasLista.getSelectionModel().getSelectedIndex();
        showPeliSelectDetail();
    }

    private void showPeliSelectDetail() {
        for (Film f : films.stream().filter(l -> l.getTitol().equals(peliculasLista.getSelectionModel().getSelectedItem())).collect(Collectors.toList())) {
            Image image = new Image("http://gencat.cat/llengua/cinema/" + f.getCartell());
            imagenPeli.setImage(image);
            titol.setText("Titol: " + f.getTitol());
            original.setText("Títol original: " + f.getOriginal());
            direccio.setText("Director: " + f.getDireccion());
            id = f.getIdfilm();
        }
    }

    //Botón para entrar a las proyecciones de cada cine, pelicula o ciclo.
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

    //Muestra los detalles de los cines al clicar sobre estos.
    @FXML
    public void listCineClick(MouseEvent mouseEvent) {
        idCineListSelect = cinesLista.getSelectionModel().getSelectedIndex();
        showCineSelectDetail();
    }

    private void showCineSelectDetail() {
        for (Cine c : cines.stream().filter(l -> l.getCinenom().equals(cinesLista.getSelectionModel().getSelectedItem())).collect(Collectors.toList())) {
            direccion.setText("Dirección:    " + c.getCineadreca());
            localidad.setText("Localidad:    " + c.getLocalitat());
            comarca.setText("Comarca:     " + c.getComarca());
            provincia.setText("Provincia:     " + c.getProvincia());
            id = c.getCineid();
        }
    }

    //Muestra los detalles de los ciclos al clicar sobre estos.
    @FXML
    public void listCicloClick(MouseEvent mouseEvent) {
        idCicloListSelect = ciclosLista.getSelectionModel().getSelectedIndex();
        showCicloSelectDetail();

    }

    private void showCicloSelectDetail() {
        for (Ciclo c : ciclos.stream().filter(l -> l.getCiclenom().equals(ciclosLista.getSelectionModel().getSelectedItem())).collect(Collectors.toList())) {
            Image image = new Image("http://gencat.cat/llengua/cinema/" + c.getImgcicle());
            imagenCiclo.setImage(image);
            nombreCiclo.setText("Nombre: " + c.getCiclenom());
            infoCiclo.setText("Información: " + c.getCicleinfo());
            id = c.getCicleid();
            if (c.getWeb() == null) {
                webCiclo.setText("");
                projeccionsButtonCiclo.setVisible(true);
            } else {
                projeccionsButtonCiclo.setVisible(false);
                webCiclo.setText("Web: " + c.getWeb());
            }
        }
    }

    //Carga el gráfico (quesito) de cuantas peliculas hay por año y al pasar el ratón sobre una porción muestra la cantidad.
    public void loadDataPieChart() {
        dataChartsYears.clear();
        List<Integer> any = films.stream()
                .map(Film::getAny)
                .filter(i -> i > 0 && i < 3000).distinct()
                .sorted(Comparator.comparingInt(integer -> integer))
                .collect(Collectors.toList());

        for (Integer i : any) {
            long numResultat = films.stream()
                    .filter(film1 -> film1.getAny() == i)
                    .count();
            dataChartsYears.add(new PieChart.Data(i.toString(), numResultat));
        }

        estadisticasAño.setData(dataChartsYears);

        final Label label = new Label();
        panePelicula.getChildren().add(label);
        label.setFont(Font.font("SanSerif", FontWeight.BLACK, 20));

        estadisticasAño.getData().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e -> {
                int intValue = (int) data.getPieValue();
                panePelicula.setVisible(true);
                if (intValue == 1) {
                    label.setText(intValue + " pelicula");
                } else {
                    label.setText(intValue + " peliculas");
                }
            });
        });
    }

    //Carga el gráfico (quesito) de cuantos cines hay por localidad y al pasar el ratón sobre una porción muestra la cantidad.
    public void loadDataPieChartCine() {
        dataChartsCines.clear();
        List<String> localidades = cines.stream()
                .map(Cine::getLocalitat)
                .distinct()
                .sorted(Comparator.comparing(String -> String))
                .collect(Collectors.toList());

        for (String i : localidades) {
            long numResultat = cines.stream()
                    .filter(cine1 -> cine1.getLocalitat().equals(i))
                    .count();
            dataChartsCines.add(new PieChart.Data(i, numResultat));
        }

        estadisticasCine.setData(dataChartsCines);

        final Label label = new Label();
        paneCine.getChildren().add(label);
        label.setFont(Font.font("SanSerif", FontWeight.BLACK, 20));

        estadisticasCine.getData().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e -> {
                int intValue = (int) data.getPieValue();
                paneCine.setVisible(true);
                if (intValue == 1) {
                    label.setText(intValue + " cine");
                } else {
                    label.setText(intValue + " cines");
                }
            });
        });
    }

    public void buscador(MouseEvent mouseEvent) {
        peliculasLista.getItems().clear();

        for (Film f : films.stream()
                        .filter(film -> film.getTitol().toLowerCase().equals(textFieldPelicula.getText().toLowerCase()))
                        .collect(Collectors.toList())) {
            peliculasLista.getItems().add(f.getTitol());
        }
    }

    public void bus(ActionEvent actionEvent) {

        // se para toda a minuscula despues saca titol real.

        String s = textFieldPelicula.getText().toLowerCase();
        Predicate<String> filter = Pattern
                .compile("(.*)"+s+"(.*)")
                .asPredicate();

        nombrePelicula.clear();
        for (String buscapeli : films
                .stream()
                .map(f -> f.getTitol().toLowerCase())
                .filter(filter)
                .collect(Collectors.toList())) {
            for (Film f : films.stream()
                    .filter(film -> film.getTitol().toLowerCase().equals(buscapeli))
                    .collect(Collectors.toList())) {
                nombrePelicula.add(f.getTitol());
            }
        }
    }
}
