/**
 * This class was created at 16-Jan-23 10:48:17
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler.model;

public abstract class Model {
    public Model() {
    }

    public Model(String href, String name) {
        this.href = href;
        this.name = name;
    }

    private String href;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
