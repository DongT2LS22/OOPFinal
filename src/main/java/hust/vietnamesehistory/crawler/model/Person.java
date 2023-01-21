package hust.vietnamesehistory.crawler.model;

public class Person extends Model {
    private String birth;
    private String death;

    public Person(String href, String name, String birth, String death) {
        super(href, name);
        this.birth = birth;
        this.death = death;
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
