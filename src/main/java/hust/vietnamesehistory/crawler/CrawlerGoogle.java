package hust.vietnamesehistory.crawler;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.crawler.model.Person;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerGoogle {
    public static void mapGoogle(String keyword) {

        try {
            Crawler.searchGoogle(keyword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writePerson(List<Person> people){

    }
    public static void main(String[] args) throws IOException {
//        writePerson(App.getPersonList());
        App.setPersonList();
        List<Person> people = App.getPersonList();
        for (int i=0;i<100;i++) {
            String name = people.get(i).getName();
            String url = "https://www.google.com/search?q=";
            Document doc = Jsoup.connect(url+name).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36").get();
            String html = doc.html();
            Elements info = doc.select(".rVusze");
            for (Element e:info) {
                String text = e.text();
                if(text.contains("Ngày/nơi sinh: ")){
                    text = text.replaceAll("Ngày/nơi sinh: ","");
                    System.out.println(text);
                }
            }
        }

    }
}
