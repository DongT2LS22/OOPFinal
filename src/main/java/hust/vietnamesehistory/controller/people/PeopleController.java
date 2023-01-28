package hust.vietnamesehistory.controller.people;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.crawler.model.King;
import hust.vietnamesehistory.crawler.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;


public class PeopleController implements Initializable {
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> birthColumn;

    @FXML
    private TableColumn<Person, String> deathColumn;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person,String> hrefColumn;
    @FXML
    private TextField searchText;
    private ObservableList<Person> personList;
    private List<Person> personArrayList = new ArrayList<Person>();


    @FXML
    public void detailScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("peopleDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        PeopleDetailController controller = fxmlLoader.getController();
        Person selected = table.getSelectionModel().getSelectedItem();
        if(selected instanceof King)
        {   King king = (King)selected;
            controller.setKing(king);
        }
        else{controller.setPerson(selected);}
        stage.show();
    }

    @FXML
    public void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        personList = FXCollections.observableArrayList(
                App.getPersonList()
        );
        hrefColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("href"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("birth"));
        deathColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("death"));
        table.setItems(personList);
    }
    @FXML
    public void searchPeople(ActionEvent event) {
        List<Person> listPersonSearch = new ArrayList<Person>();
        String search = searchText.getText();
        for (Person p: personList
             ) {
            if(p.getName().startsWith(search)){
                listPersonSearch.add(p);
            }
        }
        ObservableList<Person> searchPeople = FXCollections.observableArrayList(listPersonSearch);
        table.setItems(searchPeople);
    }
}