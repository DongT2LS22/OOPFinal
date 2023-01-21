/**
 * This class was created at 21-Jan-23 17:32:12
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hust.vietnamesehistory.crawler.model.King;

import java.io.File;
import java.io.IOException;

public class Test {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    public static void main(String[] args) throws IOException {
        King king = new King("name", "href", null, "a", "b", "c", "d", "e", "f", "g");
        writer.writeValue(new File("D:/Code/test.json"), king);
    }
}
