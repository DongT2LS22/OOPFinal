package hust.vietnamesehistory.crawler.model;

public class Place extends Model {
    private String national;
    private String location;
    private String coordinates;
    private String area;

    public Place(String name, String href, String national, String location, String coordinates, String area) {
        super(href, name);
        this.national = national;
        this.location = location;
        this.coordinates = coordinates;
        this.area = area;
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
}