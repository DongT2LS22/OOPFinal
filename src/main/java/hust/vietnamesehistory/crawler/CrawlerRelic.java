package hust.vietnamesehistory.crawler;

import hust.vietnamesehistory.crawler.model.Relic;
import hust.vietnamesehistory.repository.RelicRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerRelic {
    public static final int MAX_TRIES = 3;
    public static final String URI = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam";
    public static final RelicRepository relicRepository = new RelicRepository();
    static List<Relic> crawlRelic() throws IOException {
        List<Relic> relics = new ArrayList<>();
        int cnt = 0;
        Document document = Jsoup.connect(URI).timeout(0).get();
        Element mainContent = document.getElementById("mw-content-text");
        Elements tableElements = mainContent.getElementsByTag("tbody");
        for (Element tableElement : tableElements) {
            Elements relicElements = tableElement.getElementsByTag("tr");
            for (Element relicElement : relicElements) {
                Elements relicProps = relicElement.getElementsByTag("td");
                if (relicProps.size() == 5) {
                    String name = relicProps.get(0).text();
                    String location = relicProps.get(1).text();
                    String type = relicProps.get(2).text();
                    String recognizedYear = relicProps.get(3).text();
                    Relic relic = new Relic(name, null, "Việt Nam", location, null, null, type, recognizedYear);
                    relics.add(relic);

                    System.out.println("Di tích: " + relic.getName()
                            + "\n\tLocation: " + relic.getLocation()
                            + "\n\tType: " + relic.getType()
                            + "\n\tRecognized Year: " + relic.getRecognizedYear());
                }
            }
        }
        while (true) {
            try {

                break;
            } catch (Exception e) {
                if (++cnt == MAX_TRIES) {
                    System.out.println("Không thể lấy thông tin di tích từ Wiki. " + e);
                    break;
                }
            }
        }
        return relics;
    }

    public static void main(String[] args) throws IOException {
        List<Relic> relics = crawlRelic();
        int count = 0;
        while (true) {
            try {
                relicRepository.writeJson(relics, "src/main/resources/json/relics.json");

                break;
            } catch (Exception e) {
                if (++count == MAX_TRIES) {
                    System.out.println("Không thể viết vào file json. " + e);
                    break;
                }
            }
        }
    }
}
