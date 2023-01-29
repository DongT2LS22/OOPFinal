package hust.vietnamesehistory.controller.festival;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.controller.people.PeopleDetailController;
import hust.vietnamesehistory.crawler.model.Festival;
import hust.vietnamesehistory.crawler.model.King;
import hust.vietnamesehistory.crawler.model.Person;
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

public class FestivalController implements Initializable {
    @FXML
    private TableColumn<Festival, String> dateColumn;

    @FXML
    private TableColumn<Festival, String> nameColumn;

    @FXML
    private TableColumn<Festival, String> noteColumn;

    @FXML
    private TableColumn<Festival, String> rootColumn;

    @FXML
    private TextField searchText;

    @FXML
    private TableView<Festival> table;

    private ObservableList<Festival> festivalList;

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
    void detailScene(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("festivalDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        FestivalDetailController controller = fxmlLoader.getController();
        Festival selected = table.getSelectionModel().getSelectedItem();
        controller.setFestival(selected);
        stage.show();
    }

    @FXML
    void searchFestival(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        festivalList = FXCollections.observableArrayList(
                App.getFestivalList()
        );
        dateColumn.setCellValueFactory(new PropertyValueFactory<Festival,String>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Festival,String>("name"));
        rootColumn.setCellValueFactory(new PropertyValueFactory<Festival,String>("root"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Festival,String>("note"));
        table.setItems(festivalList);
    }
}
