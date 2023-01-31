package hust.vietnamesehistory.repository;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    List<T> readJson(String filePath) throws IOException;
    void writeJson(List<T> listObject, String filePath) throws IOException;
}
