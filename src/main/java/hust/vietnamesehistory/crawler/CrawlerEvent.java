package hust.vietnamesehistory.crawler;

import hust.vietnamesehistory.model.Event;
import hust.vietnamesehistory.model.Person;
import hust.vietnamesehistory.model.Place;
import hust.vietnamesehistory.repository.EventRepository;
import hust.vietnamesehistory.repository.PersonRepository;
import hust.vietnamesehistory.repository.PlaceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerEvent {
    public static final PlaceRepository placeRepo = new PlaceRepository();
    public static final PersonRepository personRepo = new PersonRepository();
    public static final String GOOGLE_URI = "https://www.google.com/search?q=";
    public static final String URI = "https://nguoikesu.com";
    static List<Event> crawlEvent(){
        List<Place> placeList;
        List<Person> personList;
        try {
             placeList =  placeRepo.readJson("src/main/resources/json/places.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
             personList = personRepo.readJson("src/main/resources/json/people.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Event> events = new ArrayList<>();
        String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".mw-parser-output>*");
            int length = elements.size();
            String year = null;
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
                            Event event = new Event();
                            String date = year + " " + dd.select("b").text();
                            event.setDate(date);
                            String name = dd.text().replace(dd.select("b").text()+" ","");
                            event.setName(name);
                            if (!dd.select("a").isEmpty()){
                                Elements dda = dd.select("a");
                                List<Place> places = new ArrayList<>();
                                List<Person> people = new ArrayList<>();
                                for (Element ddat:dda) // ddat : lay text cua dd>a
                                {
                                    String search = searchGoogle(ddat.text());
                                    if(search.contains("dia-danh")){
                                        for (Place placeSearch: placeList) {
                                            if(placeSearch.getHref().equals(search)){
                                                places.add(placeSearch);
                                                break;
                                            }
                                        }
                                    }
                                    if(search.contains("nhan-vat")){
                                        for (Person personSearch: personList) {
                                            if(personSearch.getHref().equals(search)){
                                                people.add(personSearch);
                                                break;
                                            }
                                        }
                                    }
                                }
                                event.setPeople(people);
                                event.setPlaces(places);
                            }
                            events.add(event);
                        }
                    }
                    if(dldd){
                        Event event = new Event();
                        if(!e.select("b").isEmpty()){
                            String date = e.select("b").text();
                            event.setDate(date);
                            String name = e.text().replace(date+" ","");
                            if(name.equals(date)){
                                year = date;
                            }else {
                                event.setName(name);
                                if (!e.select("a").isEmpty()){
                                    Elements ea = e.select("a");
                                    List<Place> places = new ArrayList<>();
                                    List<Person> people = new ArrayList<>();
                                    for (Element eat:ea) // ddat : lay text cua dd>a
                                    {
                                        String search = searchGoogle(eat.text());
                                        if(search.contains("dia-danh")){
                                            for (Place placeSearch: placeList) {
                                                if(placeSearch.getHref().equals(search)){
                                                    places.add(placeSearch);
                                                    System.out.println(placeSearch.getHref());
                                                    break;
                                                }
                                            }
                                        }
                                        if(search.contains("nhan-vat")){
                                            for (Person personSearch: personList) {
                                                if(personSearch.getHref().equals(search)){
                                                    people.add(personSearch);
                                                    System.out.println(personSearch.getHref());
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    event.setPeople(people);
                                    event.setPlaces(places);
                                }
                                events.add(event);
                            }

                        }
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    static String searchGoogle(String keyword) {
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
    public static void main(String[] args) {
        EventRepository repo = new EventRepository();
        try {
            repo.writeJson(crawlEvent(),"src/main/resources/json/events.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
