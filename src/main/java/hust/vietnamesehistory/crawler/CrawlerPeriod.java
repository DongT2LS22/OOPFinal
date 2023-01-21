package hust.vietnamesehistory.crawler;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hust.vietnamesehistory.crawler.model.Period;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerPeriod {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    public static final String URI = "https://nguoikesu.com";
    public static final String TIMELINE_HREF = "/dong-lich-su";

    public static void main(String[] args) throws IOException {
        // Tạo document từ url dòng lịch sử
        Document document = Jsoup.connect(URI + TIMELINE_HREF).timeout(0).get();
        Element mainContext = document.getElementById("Mod88");
        mainContext = mainContext.getElementsByClass("module-ct").first();

        // CrawlerPeriod các thời kỳ lịch sử
        List<Period> periods = new ArrayList<>();

        Elements periodHrefs = mainContext.getElementsByTag("li");
        for (Element periodHref:periodHrefs) {
            String href = periodHref.getElementsByTag("a").get(0).attr("href");
            String name = periodHref.getElementsByTag("a").get(0).text();

            System.out.println("=> " + name + "\n" + href);

            List<String> people = new ArrayList<>();
            List<String> places = new ArrayList<>();

            Document periodDoc = Jsoup.connect(CrawlerPeriod.URI + href).timeout(0).get();
            Elements periodElements = periodDoc.getElementsByAttributeValue("class", "readmore");
            if (!periodElements.isEmpty()) {
                for (Element periodE : periodElements) {
                    try {
                        Element subPeriod = periodE.getElementsByAttributeValue("class", "btn btn-secondary").get(0);
                        Document subDoc = Jsoup.connect(CrawlerPeriod.URI + subPeriod.attr("href")).timeout(0).get();
                        Element content = subDoc.getElementsByClass("com-content-article__body").get(0);
                        Elements listHref = content.getElementsByTag("a");


                        for (Element hrefNode : listHref) {

                            // Nếu là link thông tin nhân vật
                            if (hrefNode.attr("href").contains("/nhan-vat/")
                                    && !people.contains(hrefNode.attr("href"))) {
                                people.add(hrefNode.attr("href"));

                                System.out.println("\t" + hrefNode.attr("href"));

                            }
                            // Nếu là link thông tin địa danh
                            if (hrefNode.attr("href").contains("/dia-danh/")
                                    && !places.contains(hrefNode.attr("href"))) {
                                places.add(hrefNode.attr("href"));

                                System.out.println("\t" + hrefNode.attr("href"));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Không tìm thấy thông tin nhân vật, địa danh nào. " + e);
                    }
                }
                Period period = new Period(href, name, people, places);
                periods.add(period);
            }
        }
        writer.writeValue(new File("src/main/resources/json/periods.json"), periods);
    }
}
