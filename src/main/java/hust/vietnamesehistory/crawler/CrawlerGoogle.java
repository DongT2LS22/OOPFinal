package hust.vietnamesehistory.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class CrawlerGoogle {
    public static final String GOOGLE_URI = "https://www.google.com/search?q=";
    public static final String URI = "https://nguoikesu.com";

    public static String searchGoogle(String keyword) {
        Document doc;
        try {
            doc = Jsoup.connect(GOOGLE_URI + keyword + " nguoikesu").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36").get();
        } catch (IOException e) {
            return "";
        }
        String link = doc.select(".yuRUbf a").first().attr("href");
        if(link.contains(URI + "/dia-danh")||link.contains(URI + "/nhan-vat")){
            link = link.replace(URI,"");
        }else{
            link = "";
        }
        return link;
    }
}
