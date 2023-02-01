package hust.vietnamesehistory.controller;

import hust.vietnamesehistory.model.*;
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
//            FestivalRepository fesRepo = new PlaceRepository();
//            try {
//                festivalList = fesRepo.readJson("src/main/resources/json/festival.json");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            String file = "src/main/resources/json/festival.json";
            String jsonString = null;
            try {
                jsonString = new String(Files.readAllBytes(Paths.get(file)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject obj = new JSONObject(jsonString);
            JSONArray arr = obj.getJSONArray("festival"); // notice that `"posts": [...]`
            for (int i = 0; i < arr.length(); i++)
            {
                String note ; //note
                if(arr.getJSONObject(i).isNull("note")){
                    note = "";
                }else{
                    note = arr.getJSONObject(i).getString("note");
                }
                String date = arr.getJSONObject(i).getString("date"); //date
                String name = arr.getJSONObject(i).getString("name"); //name

                String root = arr.getJSONObject(i).getString("root"); //root
                List<String> linkPeople = new ArrayList<String>();
                List<Person> listPerson = new ArrayList<Person>();
                JSONArray arrPeople = arr.getJSONObject(i).getJSONArray("person");
                int lengthPeople = arrPeople.length();
                for (int j=0;j<lengthPeople;j++){
                    linkPeople.add(arrPeople.getString(j));
                }
                for (String p:linkPeople) {
                    for (Person per:personList) {
                        if(per.getHref().equals(p)){
                            listPerson.add(per);
                            break;
                        }
                    }
                }
                List<String> linkPlace = new ArrayList<String>();
                List<Place> listPlace = new ArrayList<Place>();
                JSONArray arrPlace = arr.getJSONObject(i).getJSONArray("place");
                int lengthPlace = arrPlace.length();
                for(int j=0;j<lengthPlace;j++){
                    linkPlace.add(arrPlace.getString(j));
                }
                for (String p:linkPlace) {
                    for (Place pl:placeList) {
                        if(pl.getHref().equals(p)){
                            listPlace.add(pl);
                            break;
                        }
                    }
                }
                Festival fes = new Festival(name,date,listPlace,note,listPerson,root);
                festivalList.add(fes);
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