package hust.vietnamesehistory.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    public void festivalScene(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("festival.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Lễ hội");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void peopleScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("people.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Thông tin nhân vật");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void periodScene(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("peroid.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Thời đại");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void placeScene(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("place.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Địa điểm");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void eventScene(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("event.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sự kiện lịch sử");
        stage.setScene(scene);
        stage.show();
    }

    @FXML void exit(ActionEvent event){
        Platform.exit();
    }
}
