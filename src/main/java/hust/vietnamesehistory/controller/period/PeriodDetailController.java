package hust.vietnamesehistory.controller.period;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.King;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PeriodDetailController implements Initializable {

    @FXML
    private TableColumn<King, String> nameColumn;

    @FXML
    private TableColumn<King, String> hrefColumn;

    @FXML
    private TableColumn<King, String> birthColumn;

    @FXML
    private TableColumn<King, String> deathColumn;

    @FXML
    private TableColumn<King, String> predecessorColumn;

    @FXML
    private TableColumn<King, String> successorColumn;

    @FXML
    private TableColumn<King, String> aliasesColumn;

    @FXML
    private TableColumn<King, String> realNameColumn;

    @FXML
    private TableView<King> table;
    private static ObservableList<King> kingObservableList;


   public static void setKing(List<King> kings){
       kingObservableList = FXCollections.observableArrayList(kings);
   }
    @FXML
    public void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("peroid.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Thời đại");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        hrefColumn.setCellValueFactory(new PropertyValueFactory<>("href"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));
        deathColumn.setCellValueFactory(new PropertyValueFactory<>("death"));
        predecessorColumn.setCellValueFactory(new PropertyValueFactory<>("predecessor"));
        successorColumn.setCellValueFactory(new PropertyValueFactory<>("successor"));
        aliasesColumn.setCellValueFactory(new PropertyValueFactory<>("aliases"));
        realNameColumn.setCellValueFactory(new PropertyValueFactory<>("realName"));
        table.setItems(kingObservableList);
    }
}
