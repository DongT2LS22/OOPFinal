package hust.vietnamesehistory.controller;

import hust.vietnamesehistory.model.*;
import hust.vietnamesehistory.repository.FestivalRepository;
import hust.vietnamesehistory.repository.PeriodRepository;
import hust.vietnamesehistory.repository.PersonRepository;
import hust.vietnamesehistory.repository.PlaceRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private static List<Person> personList = new ArrayList<Person>();
    private static List<Place> placeList = new ArrayList<Place>();
    private static List<Festival> festivalList = new ArrayList<Festival>();
    private static List<Period> periodList = new ArrayList<Period>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static List<Person> getPersonList() {
        return personList;
    }
    public static void setPersonList(){
        if(personList.isEmpty()){
            PersonRepository personRepo = new PersonRepository();
            try {
                personList = personRepo.readJson("src/main/resources/json/people.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Place> getPlaceList() {
        return placeList;
    }

    public static void setPlaceList() {
        if(placeList.isEmpty()){
            PlaceRepository placeRepo = new PlaceRepository();
            try {
                placeList = placeRepo.readJson("src/main/resources/json/places.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Festival> getFestivalList() {
        return festivalList;
    }

    public static void setFestivalList() {
        if(festivalList.isEmpty()){
            FestivalRepository fesRepo = new FestivalRepository();
            try {
                festivalList = fesRepo.readJson("src/main/resources/json/festival.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Period> getPeriodList() {
        return periodList;
    }

    public static void setPeriodList(List<Period> periodList) {
        if(periodList.isEmpty()){
            PeriodRepository periodRepo = new PeriodRepository();
            try {
                periodList = periodRepo.readJson("src/main/resources/json/periods.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        setPersonList();
        setPlaceList();
        setFestivalList();
        launch();
    }
}