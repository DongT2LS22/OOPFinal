package hust.vietnamesehistory.crawler.model;

import java.util.ArrayList;
import java.util.List;

public class Person extends Model {
    private String birth;
    private String death;
    private List<Person> parents = new ArrayList<Person>();
    private List<Person> mates = new ArrayList<Person>();

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }

    public Person() {
    }

    public Person(String href, String name, String birth, String death) {
        super(href, name);
        this.birth = birth;
        this.death = death;
    }

    public Person(String href, String name, String birth, String death, List<Person> parents, List<Person> mates) {
        super(href, name);
        this.birth = birth;
        this.death = death;
        this.parents = parents;
        this.mates = mates;
    }

    public List<Person> getMates() {
        return mates;
    }

    public void setMates(List<Person> mates) {
        this.mates = mates;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

}
