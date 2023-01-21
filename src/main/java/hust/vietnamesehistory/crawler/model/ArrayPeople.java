package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class ArrayPeople {
    List<Person> people;

    public ArrayPeople(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
