package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class Festival{
    String name;
    String date;
    List<Place> listofPlace;
    String note;
    List<Person> listofPerson;

    String root;

    public Festival(String name,String date, List<Place> listofPlace, String note, List<Person> listofPerson,String root) {
        this.name = name;
        this.date = date;
        this.listofPlace = listofPlace;
        this.note = note;
        this.listofPerson = listofPerson;
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

    public List<Place> getListofPlace() {
        return listofPlace;
    }

    public void setListofPlace(List<Place> listofPlace) {
        this.listofPlace = listofPlace;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Person> getListofPerson() {
        return listofPerson;
    }

    public void setListofPerson(List<Person> listofPerson) {
        this.listofPerson = listofPerson;
    }
}
