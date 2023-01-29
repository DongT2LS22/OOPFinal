package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class Period extends Model {
    private List<Person> kings;

    public Period(String href, String name, List<Person> kings) {
        super(href, name);
        this.kings = kings;
    }

    public List<Person> getKings() {
        return kings;
    }

    public  void setKings(List<Person> kings) {this.kings = kings; }


}
