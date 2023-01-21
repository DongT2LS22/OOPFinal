package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class Period extends Model {
    private List<String> people;
    private List<String> places;
    private List<String> events;
    private List<String> festivals;

    public Period(String href, String name, List<String> people, List<String> places,
                  List<String> events, List<String> festivals) {
        super(href, name);
        this.people = people;
        this.places = places;
        this.events = events;
        this.festivals = festivals;
    }

    public List<String> getPeople() {
        return people;
    }

    public  void setPeople(List<String> people) {this.people = people; }

    public List<String> getPlaces() {
        return places;
    }

    public  void setPlaces(List<String> places) {this.places = places; }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public List<String> getFestivals() {
        return festivals;
    }

    public void setFestivals(List<String> festivals) {
        this.festivals = festivals;
    }

}
