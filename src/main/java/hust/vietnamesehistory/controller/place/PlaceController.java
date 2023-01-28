package hust.vietnamesehistory.controller.place;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.crawler.model.Person;
import hust.vietnamesehistory.crawler.model.Place;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceController implements Initializable {
    @FXML
    private TableColumn<Place, String> areaColumn;

    @FXML
    private TableColumn<Place, String> coordinatesColumn;

    @FXML
    private TableColumn<Place, String> locationColumn;

    @FXML
    private TableColumn<Place, String> nameColumn;

    @FXML
    private TableColumn<Place, String> nationalColumn;

    @FXML
    private TextField searchText;

    @FXML
    private TableView<Place> table;

    @FXML
    void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void detailScene(ActionEvent event) {

    }

    @FXML
    void searchPlace(ActionEvent event){

    }
    ObservableList<Place> placeList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeList = FXCollections.observableArrayList(
                App.getPlaceList()
        );
        coordinatesColumn.setCellValueFactory(new PropertyValueFactory<Place,String>("coordinates"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Place,String>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Place,String>("location"));
        nationalColumn.setCellValueFactory(new PropertyValueFactory<Place,String>("national"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<Place,String>("area"));
        table.setItems(placeList);
    }
}
