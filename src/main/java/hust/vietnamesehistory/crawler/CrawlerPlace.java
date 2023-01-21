/**
 * This class was created at 20-Jan-23 23:15:03
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hust.vietnamesehistory.crawler.model.Place;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CrawlerPlace {
    public static final int MAX_TRIES = 3;
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    public static final String URI = "https://nguoikesu.com";
    public static final String PLACES_HREF = "/dia-danh?start=";

    public static void main(String[] args) {
        List<Place> places = new ArrayList<>();
        int idx = 0;
        int cnt = 0;
        while (true) {
            Document document = null;
            try {
                document = Jsoup.connect(URI + PLACES_HREF + idx).timeout(0).get();
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
            Elements placeElements = mainContent.getElementsByClass("com-content-category-blog__item");
            for (Element placeE : placeElements) {
                Element hrefE = placeE.getElementsByAttributeValueContaining("href", "/dia-danh/").first();
                String href = hrefE.attr("href");
                String name = hrefE.text();

                int count = 0;
                while (true) {
                    try {
                        Document placeDoc = Jsoup.connect(CrawlerPeriod.URI + href).timeout(0).get();
                        HashMap<String, String> infoKV = new HashMap<>();
                        try {
                            Element infoElement = placeDoc.getElementsByClass("infobox").get(1);
                            Elements trElements = infoElement.getElementsByTag("tr");
                            for (Element tr : trElements) {
                                infoKV.put(tr.getElementsByTag("th").text().trim(),
                                        tr.getElementsByTag("td").text().trim());
                            }
                        } catch (Exception e) {
                            System.out.println("Không có thông tin địa danh "+ name + ". " + e);
                        }
                        String location = null;
                        if (infoKV.containsKey("Vị trí")) location = infoKV.get("Vị trí");
                        if (infoKV.containsKey("Địa điểm")) location = infoKV.get("Địa điểm");
                        if (infoKV.containsKey("Khu vực")) location = infoKV.get("Khu vực");
                        if (infoKV.containsKey("Địa chỉ")) location = infoKV.get("Địa chỉ");
                        Place place = new Place(name, href, infoKV.get("Quốc gia"), location,
                                infoKV.get("Tọa độ"), infoKV.get("Diện tích"));
                        places.add(place);

                        System.out.println("Địa danh: " + place.getName());
                        System.out.println("\tQuốc gia: " + place.getNational());
                        System.out.println("\tVị trí: " + place.getLocation());
                        System.out.println("\tTọa độ: " + place.getCoordinates());
                        System.out.println("\tDiện tích: " + place.getArea());

                        break;
                    } catch (Exception e) {
                        if (++count == MAX_TRIES) System.out.println("Không thể lấy thông tin địa danh " + name + ". " + e);
                    }
                }
            }
        }
        int count = 0;
        while (true) {
            try {
                writer.writeValue(new File("src/main/resources/json/places.json"), places);
                break;
            } catch (Exception e) {
                if (++count == MAX_TRIES) System.out.println("Không thể viết vào file json. " + e);
            }
        }
    }
}
