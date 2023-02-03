package hust.vietnamesehistory.controller.event;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.Event;
import hust.vietnamesehistory.model.Person;
import hust.vietnamesehistory.model.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EventDetailController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label personLabel;

    @FXML
    private Label placeLabel;

    @FXML
    void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("event.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sự kiện lịch sử");
        stage.setScene(scene);
        stage.show();
    }
    void setEvent(Event event){
        nameLabel.setText(event.getName());
        dateLabel.setText(event.getDate());
        String people = "";
        for (Person p:event.getPeople()) {
            people = people + p.getName() + " , ";
        }
        personLabel.setText(people);
        String places = "";
        for (Place p:event.getPlaces()) {
            places = places + p.getName() + " , ";
        }
        placeLabel.setText(places);
    }
}
