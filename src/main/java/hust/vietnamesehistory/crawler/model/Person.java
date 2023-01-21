package hust.vietnamesehistory.crawler.model;

import java.util.List;

public abstract class Person extends Model {
    private List<String> periodsHref;
    private String birth;
    private String death;

    public Person(String href, String name, List<String> periodsHref, String birth, String death) {
        super(href, name);
        this.periodsHref = periodsHref;
        this.birth = birth;
        this.death = death;
    }

    public List<String> getPeriodsHref() {
        return periodsHref;
    }

    public void setPeriodsHref(List<String> periodHref) {
        this.periodsHref = periodHref;
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

    public void addPeriod(String periodHref) {
        this.periodsHref.add(periodHref);
    }
}
