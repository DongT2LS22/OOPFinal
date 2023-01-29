package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class Period extends Model {
    private List<String> people;

    public Period(String href, String name, List<String> people) {
        super(href, name);
        this.people = people;
    }

    public List<String> getPeople() {
        return people;
    }

    public  void setPeople(List<String> people) {this.people = people; }


}
