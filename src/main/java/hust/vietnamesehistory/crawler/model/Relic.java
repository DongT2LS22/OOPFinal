/**
 * This class was created at 30-Jan-23 17:47:07
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler.model;

public class Relic extends Place{
    private String type;
    private String recognizedYear;

    public Relic(String name, String href, String national, String location, String coordinates, String area) {
        super(name, href, national, location, coordinates, area);
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
