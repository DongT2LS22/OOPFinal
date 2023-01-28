package hust.vietnamesehistory.controller;

import hust.vietnamesehistory.crawler.model.*;
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
import java.util.LinkedList;
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
            String file = "src/main/resources/json/people.json";
            String jsonString = null;
            try {
                jsonString = new String(Files.readAllBytes(Paths.get(file)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject obj = new JSONObject(jsonString);
            JSONArray arr = obj.getJSONArray("People"); // notice that `"posts": [...]`
            for (int i = 0; i < arr.length(); i++)
            {
                String href = arr.getJSONObject(i).getString("href");
                String name = arr.getJSONObject(i).getString("name");
                String birth;
                String death;
                if(arr.getJSONObject(i).isNull("birth")){
                    birth = "";
                }else{
                    birth = arr.getJSONObject(i).getString("birth");
                }
                if(arr.getJSONObject(i).isNull("death")){
                    death = "";
                }else{
                    death = arr.getJSONObject(i).getString("death");
                }
                if(!arr.getJSONObject(i).has("reignTime")){
                    Person p = new Person(href,name,birth,death);
                    personList.add(p);
                }else{
                    String reignTime;
                    if(arr.getJSONObject(i).isNull("reignTime")){
                        reignTime = "";
                    }else{
                        reignTime = arr.getJSONObject(i).getString("reignTime");
                    }
                    String predecessor;
                    if(arr.getJSONObject(i).isNull("predecessor")){
                        predecessor = "";
                    }else{
                        predecessor = arr.getJSONObject(i).getString("predecessor");
                    }
                    String successor;
                    if(arr.getJSONObject(i).isNull("successor")){
                        successor = "";
                    }else{
                        successor = arr.getJSONObject(i).getString("successor");
                    }
                    String aliases;
                    if(arr.getJSONObject(i).isNull("aliases")){
                        aliases = "";
                    }else{
                        aliases = arr.getJSONObject(i).getString("aliases");
                    }
                    String realName;
                    if(arr.getJSONObject(i).isNull("realName")){
                        realName = "";
                    }else{
                        realName = arr.getJSONObject(i).getString("realName");
                    }
                    Person p = new King(href,name,birth,death,reignTime,predecessor,successor,aliases,realName);
                    personList.add(p);
                }
            }
        }
    }

    public static List<Place> getPlaceList() {
        return placeList;
    }

    public static void setPlaceList() {
        if(placeList.isEmpty()){
            String file = "src/main/resources/json/places.json";
            String jsonString = null;
            try {
                jsonString = new String(Files.readAllBytes(Paths.get(file)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject obj = new JSONObject(jsonString);
            JSONArray arr = obj.getJSONArray("Place"); // notice that `"posts": [...]`
            for (int i = 0; i < arr.length(); i++)
            {
                String href = arr.getJSONObject(i).getString("href");
                String name = arr.getJSONObject(i).getString("name");
                String location;
                if(arr.getJSONObject(i).isNull("location")){
                    location = "";
                }else{
                    location = arr.getJSONObject(i).getString("location");
                }
                String area ;
                if(arr.getJSONObject(i).isNull("area")){
                    area = "";
                }else{
                    area = arr.getJSONObject(i).getString("area");
                }
                String national ;
                if(arr.getJSONObject(i).isNull("national")){
                    national = "";
                }else{
                    national = arr.getJSONObject(i).getString("national");
                }
                String coordinates;
                if(arr.getJSONObject(i).isNull("coordinates")){
                    coordinates = "";
                }else{
                    coordinates = arr.getJSONObject(i).getString("coordinates");
                }
                Place p = new Place(href,name,national,location,coordinates,area);
                placeList.add(p);
            }
        }
    }

    public static List<Festival> getFestivalList() {
        return festivalList;
    }

    public static void setFestivalList() {
        if(festivalList.isEmpty()){
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
                        listPlace.add(pl);
                        break;
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
        App.periodList = periodList;
    }

    public static void main(String[] args) {
        setPersonList();
        setPlaceList();
        setFestivalList();
        launch();
    }
}