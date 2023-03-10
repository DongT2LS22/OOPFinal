package hust.vietnamesehistory.model;

import java.util.ArrayList;
import java.util.List;

public class Festival{
    private String name;
    private String date;
    private List<Place> places = new ArrayList<>();
    private String note;
    private List<Person> people = new ArrayList<>();
    private String root;

    public Festival(String name,String date, List<Place> places, String note, List<Person> people,String root) {
        this.name = name;
        this.date = date;
        this.places = places;
        this.note = note;
        this.people = people;
        this.root = root;
    }

    public Festival() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
