package hust.vietnamesehistory.model;

import java.util.ArrayList;
import java.util.List;

public class Event{
    String date;
    String name;
    List<Person> people = new ArrayList<>();
    List<Place> places = new ArrayList<>();

    public Event(String date, String name, List<Person> people, List<Place> places) {
        this.date = date;
        this.name = name;
        this.people = people;
        this.places = places;
    }

    public Event() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
