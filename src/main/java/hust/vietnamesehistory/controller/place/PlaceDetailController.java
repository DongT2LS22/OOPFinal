package hust.vietnamesehistory.controller.place;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceDetailController {
    @FXML
    private Label areaLabel;

    @FXML
    private Label coordinatesLabel;

    @FXML
    private Label hrefLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nationalLabel;

    @FXML
    void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("place.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Địa điểm");
        stage.setScene(scene);
        stage.show();
    }

    public void setPlace(Place place){
        nameLabel.setText(place.getName());
        coordinatesLabel.setText(place.getCoordinates());
        hrefLabel.setText(place.getHref());
        areaLabel.setText(place.getArea());
        locationLabel.setText(place.getLocation());
        nationalLabel.setText(place.getNational());
    }
}
