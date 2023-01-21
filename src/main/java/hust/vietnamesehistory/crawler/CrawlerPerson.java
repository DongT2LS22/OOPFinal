/**
 * This class was created at 20-Jan-23 19:42:51
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hust.vietnamesehistory.crawler.model.Celebrity;
import hust.vietnamesehistory.crawler.model.King;
import hust.vietnamesehistory.crawler.model.Person;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CrawlerPerson {
    public static final int MAX_TRIES = 3;
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    public static final String URI = "https://nguoikesu.com";
    public static final String CHARACTERS_HREF = "/nhan-vat?start=";

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        int idx = 0;
        int cnt = 0;
        while (true) {
            Document document = null;
            try {
                document = Jsoup.connect(URI + CHARACTERS_HREF + idx).timeout(0).get();
                System.out.println("Page " + (idx/5+1));
                cnt = 0;
            } catch (IOException e) {
                if (++cnt < MAX_TRIES) {
                    continue;
                }
            }
            idx +=5;
            if (document == null) continue;
            if (document.html().contains("Không có bài nào trong mục này. Nếu có các mục con được hiển thị, chúng sẽ có bài viết trong đó.")) {
                System.out.println("Đã crawler hết.");
                break;
            }

            Element mainContent = document.getElementById("content");
            Elements personElements = mainContent.getElementsByClass("com-content-category-blog__item");
            for (Element personE : personElements) {
                Element hrefE = personE.getElementsByAttributeValueContaining("href", "/nhan-vat/").first();
                String href = hrefE.attr("href");
                String name = hrefE.text();

                int count = 0;
                while(true) {
                    try {
                        Document personDoc = Jsoup.connect(URI + href).timeout(0).get();
                        HashMap<String, String> infoKV = new HashMap<>();
                        try {
                            Element infoElement = personDoc.getElementsByClass("infobox").get(1);
                            Elements trElements = infoElement.getElementsByTag("tr");
                            for (Element tr : trElements) {
                                infoKV.put(tr.getElementsByTag("th").text().trim(),
                                        tr.getElementsByTag("td").text().trim());
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR: Không có thông tin nhân vật "+ name + ". " + e);
                        }
                        if(        infoKV.containsKey("Niên hiệu")
                                || infoKV.containsKey("Tiền nhiệm")
                                || infoKV.containsKey("Kế nhiệm")
                                || infoKV.containsKey("Trị vì")
                                || infoKV.containsKey("Tên thật")) {
                            King king = new King(name, href, infoKV.get("Sinh"), infoKV.get("Mất"),
                                    infoKV.get("Niên hiệu"), infoKV.get("Tiền nhiệm"), infoKV.get("Kế nhiệm"),
                                    infoKV.get("Trị vì"), infoKV.get("Tên thật"));
                            people.add(king);

                            System.out.println("Vua: " + king.getName());
                            System.out.println("\tSinh: " + king.getBirth());
                            System.out.println("\tMất: " + king.getDeath());
                            System.out.println("\tNiên hiệu: " + king.getAliases());
                            System.out.println("\tTiền nhiệm: " + king.getPredecessor());
                            System.out.println("\tKế nhiệm: " + king.getSuccessor());
                            System.out.println("\tTrị vì: " + king.getReignTime());
                            System.out.println("\tTên thật: " + king.getRealName());

                        } else {
                            Celebrity celebrity = new Celebrity(name, href, infoKV.get("Sinh"), infoKV.get("Mất"));
                            people.add(celebrity);

                            System.out.println("Danh nhân: " + celebrity.getName());
                            System.out.println("\tSinh: " + celebrity.getBirth());
                            System.out.println("\tMất: " + celebrity.getDeath());

                        }
                        break;
                    } catch (Exception e) {
                        if (++count == MAX_TRIES) System.out.println("Không thể lấy thông tin nhân vật " + name + ". " + e);
                    }
                }
            }
        }
        int count = 0;
        while(true) {
            try {
                writer.writeValue(new File("src/main/resources/json/people.json"), people);
                break;
            } catch (Exception e) {
                if (++count == MAX_TRIES) System.out.println("Không thể viết vào file json. " + e);
            }
        }
    }
}
