package hust.vietnamesehistory.controller.people;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.King;
import hust.vietnamesehistory.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PeopleDetailController {
    @FXML
    private Label birthInfo;

    @FXML
    private Label deathInfo;

    @FXML
    private Label hrefInfo;

    @FXML
    private Label nameInfo;

    @FXML
    private Label predecessorExist;

    @FXML
    private Label predecessorInfo;

    @FXML
    private Label successorExist;

    @FXML
    private Label successorInfo;

    public void setPerson(Person person){
        birthInfo.setText(person.getBirth());
        deathInfo.setText(person.getDeath());
        hrefInfo.setText(person.getHref());
        nameInfo.setText(person.getName());
        predecessorExist.setVisible(false);
        predecessorInfo.setVisible(false);
        successorInfo.setVisible(false);
        successorExist.setVisible(false);
    }
    public void setKing(King king){
        birthInfo.setText(king.getBirth());
        deathInfo.setText(king.getDeath());
        hrefInfo.setText(king.getHref());
        nameInfo.setText(king.getName());
        predecessorExist.setText("Tiền nhiệm :");
        successorExist.setText("Kế nhiệm");
        predecessorInfo.setText(king.getPredecessor());
        successorInfo.setText(king.getSuccessor());
    }
    @FXML
    public void backScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("people.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
