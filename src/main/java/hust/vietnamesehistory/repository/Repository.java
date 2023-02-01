package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    ObjectMapper mapper = new ObjectMapper();
    ObjectReader reader = mapper.reader();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    List<T> readJson(String filePath) throws IOException;
    void writeJson(List<T> listObject, String filePath) throws IOException;
}
