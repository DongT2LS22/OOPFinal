package hust.vietnamesehistory.controller.event;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.Event;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventController implements Initializable {
    @FXML
    private TableColumn<Event, String> dateColumn;

    @FXML
    private TableColumn<Event, String> nameColumn;
    @FXML
    private TableView<Event> table;
    @FXML
    private TextField searchText;

    private ObservableList<Event> eventList;

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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("eventDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        EventDetailController controller = fxmlLoader.getController();
        Event selected = table.getSelectionModel().getSelectedItem();
        controller.setEvent(selected);
        stage.show();
    }

    @FXML
    void searchEvent() {
        List<Event> listEventSearch = new ArrayList<>();
        String search = searchText.getText();
        for (Event p : eventList
        ) {
            if(p.getName().contains(search)){
                listEventSearch.add(p);
            }
        }
        ObservableList<Event> searchEvent = FXCollections.observableArrayList(listEventSearch);
        table.setItems(searchEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventList = FXCollections.observableArrayList(
                App.getEventList()
        );
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(eventList);
    }
}
