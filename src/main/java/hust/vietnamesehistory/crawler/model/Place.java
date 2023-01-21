package hust.vietnamesehistory.crawler.model;

import java.util.ArrayList;
import java.util.List;

public class Place extends Model {
    private List<String> periodsHref = new ArrayList<>();
    private String national;
    private String location;
    private String coordinates;
    private String area;

    public Place(String name, String href, List<String> periodsHref, String national, String location, String coordinates, String area) {
        super(name, href);
        this.periodsHref = periodsHref;
        this.national = national;
        this.location = location;
        this.coordinates = coordinates;
        this.area = area;
    }

    public List<String> getPeriodsHref() {
        return periodsHref;
    }

    public void setPeriodsHref(List<String> periodsHref) {
        this.periodsHref = periodsHref;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void addPeriod(String periodHref) {
        this.periodsHref.add(periodHref);
    }
}