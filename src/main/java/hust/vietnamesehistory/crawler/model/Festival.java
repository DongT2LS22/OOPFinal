package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class Festival{
    String name;
    String date;
    List<String> places;
    String note;
    List<String> people;
    String root;

    public Festival(String name,String date, List<String> places, String note, List<String> people,String root) {
        this.name = name;
        this.date = date;
        this.places = places;
        this.note = note;
        this.people = people;
        this.root = root;
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

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }
}
