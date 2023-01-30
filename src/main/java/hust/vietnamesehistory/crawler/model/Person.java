package hust.vietnamesehistory.crawler.model;

import java.security.PrivateKey;
import java.util.List;

public class Person extends Model {
    private String birth;
    private String death;

    private Person dad,mom;
    private Person mate;

    private List<Person> child;

    public Person(String href, String name, String birth, String death) {
        super(href, name);
        this.birth = birth;
        this.death = death;
    }

    public Person(String birth, String death, Person dad, Person mom,Person mate, List<Person> child) {
        this.birth = birth;
        this.death = death;
        this.dad = dad;
        this.mom = mom;
        this.child = child;
        this.mate = mate;
    }

    public Person(String href, String name, String birth, String death, Person dad, Person mom,Person mate, List<Person> child) {
        super(href, name);
        this.birth = birth;
        this.death = death;
        this.dad = dad;
        this.mom = mom;
        this.child = child;
        this.mate = mate;
    }

    public Person getMate() {
        return mate;
    }

    public void setMate(Person mate) {
        this.mate = mate;
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

    public Person getDad() {
        return dad;
    }

    public void setDad(Person dad) {
        this.dad = dad;
    }

    public Person getMom() {
        return mom;
    }

    public void setMom(Person mom) {
        this.mom = mom;
    }

    public List<Person> getChild() {
        return child;
    }

    public void setChild(List<Person> child) {
        this.child = child;
    }
}
