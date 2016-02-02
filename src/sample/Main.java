package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class Main extends Application {
    private Parent root;
    private Drawer drawer;
    private ObservableList<Prism> inputPrisms = FXCollections.observableArrayList();
    private List<Prism> intersectionPrisms = new LinkedList<>();
    @FXML
    private ComboBox<Prism> firstPolCB;
    @FXML
    private ComboBox<Prism> secondPolCB;
    @FXML
    private Pane drawPane;
    @FXML
    private CheckBox intersectionCheckBox;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainWindow.fxml"));
        loader.setController(this);
        root = loader.load();
        primaryStage.setTitle("Polygons intersections");
        primaryStage.setScene(new Scene(root, 1000, 800));

        drawer = new Drawer(drawPane);
        drawer.getIntersectionProperty().bind(intersectionCheckBox.selectedProperty());
        firstPolCB.setItems(inputPrisms);
        secondPolCB.setItems(inputPrisms);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void inputButtonHandler(){
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if(chosenFile != null){
            inputPrisms.setAll(FileProcessor.getPrismsFromFile(chosenFile));
        }
    }

    @FXML
    private void intersectionButtonHandler(){
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if(chosenFile != null){
            intersectionPrisms.clear();
            FileProcessor processor = new FileProcessor();
            intersectionPrisms.addAll(processor.getIntersectionPrismsFromFile(chosenFile));
            drawer.setIntersectionList(intersectionPrisms);
        }
    }

    @FXML
    private void firstPolHandler(){
        drawer.setFirstPrism(firstPolCB.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void secondPolHandler(){
        drawer.setSecondPrism(secondPolCB.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void intersectionCheckBoxHandler(){

    }
}
