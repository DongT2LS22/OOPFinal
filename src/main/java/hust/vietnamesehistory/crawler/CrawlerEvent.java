package hust.vietnamesehistory.crawler;

import hust.vietnamesehistory.model.Event;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerEvent {
    static List<Event> crawlEvent(){
        List<Event> events = new ArrayList<Event>();
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".mw-parser-output>*");
            int length = elements.size();
            for (int i=0;i<length;i++) {
                Element e = elements.get(i);
                Boolean pb = e.select("p>b").isEmpty();
                Boolean dldd = e.select("dl>dd").isEmpty();
                if ((pb||dldd)
                        &&(e.select("h2").isEmpty()&&e.select("h3").isEmpty()&&e.select("h1").isEmpty())
                        &&(e.select("table").isEmpty())){
                    if(pb){
                        Elements dds = e.select("dl>dd");
                        for (Element dd:dds) {
                            System.out.println("    "+ dd.text());
                        }
                    }
                    if(dldd){
                        System.out.println(e.text());
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    public static void main(String[] args) {
        crawlEvent();
    }
}
