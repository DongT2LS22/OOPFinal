package hust.vietnamesehistory.crawler;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.model.Festival;
import hust.vietnamesehistory.model.Person;
import hust.vietnamesehistory.model.Place;
import hust.vietnamesehistory.repository.FestivalRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerFestival extends CrawlerGoogle {
    static List<Festival> crawlFestival(){
        App.setPersonList();
        App.setPlaceList();
        List<Person>personList = App.getPersonList();
        List<Place> placeList = App.getPlaceList();
        List<Festival> festivals = new ArrayList<>();
        String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements elements = doc.getElementsByTag("tbody").get(1).getElementsByTag("tr");
        elements.remove(0);
        for (Element element: elements) {
            Festival fes = new Festival();
            Elements festival = element.getElementsByTag("td");
            int length = festival.size();
            for(int i=0;i<length;i++){
                if(i==0){
                    fes.setDate(festival.get(i).text());
                    System.out.println(festival.get(i).text());
                }
                if(i==1){
                    String places = festival.get(i).text();
                    String search;
                    List<Place> arrPlace = new ArrayList<>();
                    if(places.contains(",")){
                        String[] place = places.split(",");
                        for (String p:place) {
                            search = searchGoogle(p);
                            if(!search.equals("")){
                                for (Place pl:placeList) {
                                    if(pl.getHref().equals(search)){
                                        arrPlace.add(pl);
                                        break;
                                    }
                                }
                            }
                        }

                    }else {
                        search = searchGoogle(places);
                        if(!search.equals("")){
                            for (Place pl:placeList) {
                                if(pl.getHref().equals(search)){
                                    arrPlace.add(pl);
                                    break;
                                }
                            }
                        }
                    }
                    fes.setPlaces(arrPlace);
                }
                if(i==2){
                    String name = festival.get(i).text();
                    fes.setName(name);
                }
                if(i==3){
                    fes.setRoot(festival.get(i).text());
                }
                if(i==4){
                    String people = festival.get(i).text();
                    List<Person> arrPeople = new ArrayList<>();
                    String search;
                    if(people.contains(",")){
                        String[] person = people.split(",");
                        for (String p:person){
                            search = searchGoogle(p);
                            if(!search.equals("")){
                                System.out.println(search);
                                for (Person per:personList) {
                                    if(per.getHref().equals(search)){
                                        arrPeople.add(per);
                                        break;
                                    }
                                }
                            }
                        }
                    }else{
                        search = searchGoogle(people);
                        if(!search.equals("")){
                            for (Person per:personList) {
                                if(per.getHref().equals(search)){
                                    arrPeople.add(per);
                                    break;
                                }
                            }
                        }
                    }
                    fes.setPeople(arrPeople);
                }
                if(i==5){
                    fes.setNote(festival.get(i).text());
                }

            }
            festivals.add(fes);
        }
        return festivals;
    }
    public static void main(String[] args) throws IOException {
        List<Festival> festivals = crawlFestival();
        FestivalRepository repo = new FestivalRepository();
        repo.writeJson(festivals,"src/main/resources/json/festival.json");
    }
}
