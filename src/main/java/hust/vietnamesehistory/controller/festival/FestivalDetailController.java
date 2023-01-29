package hust.vietnamesehistory.controller.festival;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.crawler.model.Festival;
import hust.vietnamesehistory.crawler.model.Person;
import hust.vietnamesehistory.crawler.model.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FestivalDetailController {
    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label noteLabel;

    @FXML
    private Label personLabel;

    @FXML
    private Label placeLabel;

    @FXML
    private Label rootLabel;

    @FXML
    void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("festival.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    void setFestival(Festival festival){
        nameLabel.setText(festival.getName());
        rootLabel.setText(festival.getRoot());
        noteLabel.setText(festival.getNote());
        dateLabel.setText(festival.getDate());
        String people = "";
        for (Person p:festival.getListofPerson()) {
            people = people + p.getName() + " ";
        }
        personLabel.setText(people);
        String places = "";
        for (Place p:festival.getListofPlace()) {
            places = places + p.getName() + " ";
        }
        placeLabel.setText(places);
    }
}
