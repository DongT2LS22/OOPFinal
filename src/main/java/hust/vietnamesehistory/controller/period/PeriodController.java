package hust.vietnamesehistory.controller.period;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.King;
import hust.vietnamesehistory.model.Period;
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

public class PeriodController implements Initializable {

    @FXML
    private TableColumn<Period, String> nameColumn;
    @FXML
    private TableView<Period> table;
    private ObservableList<Period> periodList;
    @FXML
    public void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void detailScene(ActionEvent event) throws IOException {
        Period selected = table.getSelectionModel().getSelectedItem();
        List<King> kings = selected.getKings();
        PeriodDetailController.setKing(kings);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("peroidDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PeriodDetailController controller = fxmlLoader.getController();
        stage.setTitle("Chi tiết thời kỳ");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        periodList = FXCollections.observableArrayList(
                App.getPeriodList()
        );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(periodList);
    }
}
