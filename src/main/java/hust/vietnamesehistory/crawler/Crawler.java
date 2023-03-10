package hust.vietnamesehistory.crawler;

import hust.vietnamesehistory.model.*;
import hust.vietnamesehistory.repository.PeriodRepository;
import hust.vietnamesehistory.repository.PersonRepository;
import hust.vietnamesehistory.repository.PlaceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Crawler {
    public static final int MAX_TRIES = 3;
    public static final PersonRepository personRepository = new PersonRepository();
    public static final PlaceRepository placeRepository = new PlaceRepository();
    public static final PeriodRepository periodRepository = new PeriodRepository();
    public static final String URI = "https://nguoikesu.com";
    public static final String TIMELINE_HREF = "/dong-lich-su";
    public static final String CHARACTERS_HREF = "/nhan-vat?start=";
    public static final String PLACES_HREF = "/dia-danh?start=";
    static List<Person> crawlPeople() {
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
                            Person person = new Person(href, name, infoKV.get("Sinh"), infoKV.get("Mất"));
                            people.add(person);

                            System.out.println("Danh nhân: " + person.getName());
                            System.out.println("\tSinh: " + person.getBirth());
                            System.out.println("\tMất: " + person.getDeath());

                        }
                        break;
                    } catch (Exception e) {
                        if (++count == MAX_TRIES) {
                            System.out.println("Không thể lấy thông tin nhân vật " + name + ". " + e);
                            break;
                        }
                    }
                }
            }
        }
        return people;
    }
    static List<Place> crawlPlaces() {
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
                        Document placeDoc = Jsoup.connect(URI + href).timeout(0).get();
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
                        if (++count == MAX_TRIES) {
                            System.out.println("Không thể lấy thông tin địa danh " + name + ". " + e);
                            break;
                        }
                    }
                }
            }
        }
        return places;
    }
    static List<Period> crawlPeriods(HashMap<String, Person> personHashMap) {
        List<Period> periods = new ArrayList<>();
        // Tạo document từ url dòng lịch sử
        try {
            Document document = Jsoup.connect(URI + TIMELINE_HREF).timeout(0).get();
            Element mainContext = document.getElementById("Mod88");
            mainContext = mainContext.getElementsByClass("module-ct").first();

            // CrawlerPeriod các thời kỳ lịch sử
            Elements periodHrefs = mainContext.getElementsByTag("li");
            for (Element periodHref:periodHrefs) {
                String href = periodHref.getElementsByTag("a").get(0).attr("href");
                String name = periodHref.getElementsByTag("a").get(0).text();

                System.out.println("=> " + name + "\n" + href);

                List<King> kings = new ArrayList<>();

                int count = 0;
                while (true) {
                    try {
                        Document periodDoc = Jsoup.connect(URI + href).timeout(0).get();
                        Elements periodElements = periodDoc.getElementsByAttributeValue("class", "readmore");
                        if (!periodElements.isEmpty()) {
                            for (Element periodE : periodElements) {
                                try {
                                    Element subPeriod = periodE.getElementsByAttributeValue("class", "btn btn-secondary").get(0);
                                    Document subDoc = Jsoup.connect(URI + subPeriod.attr("href")).timeout(0).get();
                                    Element kingElement = subDoc.getElementById("Mod100");
                                    String kingHref = kingElement.getElementsByAttributeValueStarting("href", "/nhan-vat/")
                                            .first().attr("href");
                                    if (personHashMap.containsKey(kingHref)) {
                                        if (personHashMap.get(kingHref) instanceof King king) {
                                            kings.add(king);
                                            System.out.println("+++++" + kingHref);
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Không tìm thấy thông tin nhân vật, địa danh nào. " + e);
                                }
                            }
                            Period period = new Period(href, name, kings);
                            periods.add(period);
                        }
                        break;
                    } catch (Exception e) {
                        if (++count == MAX_TRIES) {
                            System.out.println("Không thể lấy thông tin thời kì lịch sử " + name + ". " + e);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: Không thể lấy thông tin dòng lịch sử. " + e);
        }
        return periods;
    }
    public static void main(String[] args) {
        List<Person> people = crawlPeople();
        List<Place> places = crawlPlaces();
        HashMap<String, Person> personHashMap = new HashMap<>();
        for (Person person : people) {
            personHashMap.put(person.getHref(), person);
        }
        List<Period> periods = crawlPeriods(personHashMap);

        int count = 0;
        while (true) {
            try {
                personRepository.writeJson(people, "src/main/resources/json/people.json");
                placeRepository.writeJson(places, "src/main/resources/json/places.json");
                periodRepository.writeJson(periods, "src/main/resources/json/periods.json");

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
