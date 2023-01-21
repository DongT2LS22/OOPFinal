/**
 * This class was created at 18-Jan-23 14:10:29
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class Celebrity extends Person {
    public Celebrity(String name, String href, List<String> periodsHref, String birth, String death) {
        super(name, href, periodsHref, birth, death);
    }
}
