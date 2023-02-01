package hust.vietnamesehistory.crawler.model;

public class Relic extends Place{
    private String type;
    private String recognizedYear;

    public Relic(String name, String href, String national, String location, String coordinates, String area, String type, String recognizedYear) {
        super(name, href, national, location, coordinates, area);
        this.type = type;
        this.recognizedYear = recognizedYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecognizedYear() {
        return recognizedYear;
    }

    public void setRecognizedYear(String recognizedYear) {
        this.recognizedYear = recognizedYear;
    }

}
