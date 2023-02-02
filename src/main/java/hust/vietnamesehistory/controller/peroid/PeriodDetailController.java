package hust.vietnamesehistory.controller.peroid;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PeriodDetailController implements Initializable  {
    private Period period;
    @FXML
    private TableView<King> table;
    @FXML
    private TableColumn<King, String> birthColumn;

    @FXML
    private TableColumn<King, String> deathColumn;

    @FXML
    private TableColumn<King, String> nameColumn;

    @FXML
    private TableColumn<King,String> reignTimeColumn;

    @FXML
    private TableColumn<King,String> predecessorColumn;

    @FXML
    private TableColumn<King,String> successorColumn;

    @FXML
    private TableColumn<King,String> aliasesColumn;

    @FXML
    private TableColumn<King,String> realNameColumn;

       private ObservableList<King> periodList;
    private List<King> kingList = new ArrayList<>();
    @FXML
    public void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("peroid.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    void setPeriod(Period period){
        kingList = period.getKings();
        for (King p: kingList) {
            nameColumn.setCellValueFactory(new PropertyValueFactory<King,String>("name"));
            birthColumn.setCellValueFactory(new PropertyValueFactory<King,String>("bỉrth"));
            deathColumn.setCellValueFactory(new PropertyValueFactory<King,String>("death"));
            reignTimeColumn.setCellValueFactory(new PropertyValueFactory<King,String>("reignTime"));
            predecessorColumn.setCellValueFactory(new PropertyValueFactory<King,String>("predecessor"));
            successorColumn.setCellValueFactory(new PropertyValueFactory<King,String>("successor"));
            aliasesColumn.setCellValueFactory(new PropertyValueFactory<King,String>("aliases"));
            realNameColumn.setCellValueFactory(new PropertyValueFactory<King,String>("realName"));
        }


        table.setItems(FXCollections.observableList(kingList));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

//        periodList = FXCollections.observableArrayList(
//                App.getPeriodList()
//        );
//        kingList = period.getKings();
//        for (King p: kingList) {
//            nameColumn.setCellValueFactory(new PropertyValueFactory<King,String>("name"));
//            birthColumn.setCellValueFactory(new PropertyValueFactory<King,String>("bỉrth"));
//            deathColumn.setCellValueFactory(new PropertyValueFactory<King,String>("death"));
//            reignTimeColumn.setCellValueFactory(new PropertyValueFactory<King,String>("reignTime"));
//            predecessorColumn.setCellValueFactory(new PropertyValueFactory<King,String>("predecessor"));
//            successorColumn.setCellValueFactory(new PropertyValueFactory<King,String>("successor"));
//            aliasesColumn.setCellValueFactory(new PropertyValueFactory<King,String>("aliases"));
//            realNameColumn.setCellValueFactory(new PropertyValueFactory<King,String>("realName"));
//        }
//
//
//        table.setItems(FXCollections.observableList(kingList));

    }


}
