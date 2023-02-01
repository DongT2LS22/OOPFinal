/**
 * This class was created at 21-Jan-23 17:32:12
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.crawler;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.crawler.model.Festival;
import hust.vietnamesehistory.crawler.model.Person;
import hust.vietnamesehistory.crawler.model.Place;
import hust.vietnamesehistory.repository.FestivalRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerFestival {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    static List<Festival> crawlFestival(){
        App.setPersonList();
        App.setPlaceList();
        List<Person>personList = App.getPersonList();
        List<Place> placeList = App.getPlaceList();
        List<Festival> festivals = new ArrayList<Festival>();
        String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
        Document doc = null;
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
                }
                if(i==1){
                    String places = festival.get(i).text();
                    List<Place> arrPlace = new ArrayList<Place>();
                    if(places.contains(",")){
                        String[] place = places.split(",");
                        for (String p:place) {
                            String search = null;
                            try {
                                search = searchGoogle(p);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if(search!=""){
                                for (Place pl:placeList) {
                                    if(pl.getHref().equals(search)){
                                        arrPlace.add(pl);
                                        break;
                                    }
                                }
                            }
                        }

                    }else {
                        try {
                            if(searchGoogle(places)!=""){
                                String search = null;
                                for (Place pl:placeList) {
                                    if(pl.getHref().equals(search)){
                                        arrPlace.add(pl);
                                        break;
                                    }
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
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
                    List<Person> arrPeople = new ArrayList<Person>();
                    if(people.contains(",")){
                        String[] person = people.split(",");
                        for (String p:person){
                            String search = null;
                            try {
                                search = searchGoogle(p);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if(search!=""){
                                for (Person per:personList) {
                                    if(per.getHref().equals(search)){
                                        arrPeople.add(per);
                                        break;
                                    }
                                }
                            }
                        }
                    }else{
                        try {
                            if(searchGoogle(people)!=""){
                                String search = null;
                                for (Person per:personList) {
                                    if(per.getHref().equals(search)){
                                        arrPeople.add(per);
                                        break;
                                    }
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
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
////        String url = "https://www.google.com/search?q=dong+da+nguoikesu";
////        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36").get();
////        String html = doc.html();
////
////        String link = doc.select(".yuRUbf a").first().attr("href");
////        if(link.contains("https://nguoikesu.com")){
////            link = link.replace("https://nguoikesu.com","");
////        }
////        System.out.println(link);
////        JSONObject jsonObject = new JSONObject();
////        JSONArray jsonArray = new JSONArray();
////        jsonObject.put("festival",jsonArray);
////        try {
////            FileWriter file = new FileWriter("src/main/resources/json/festival.json");
////            file.write(jsonObject.toString());
////            file.flush();
////            file.close();
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//        JSONObject obj = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//        String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
//        Document doc = Jsoup.connect(url).get();
//        Elements elements = doc.getElementsByTag("tbody").get(1).getElementsByTag("tr");
//        elements.remove(0);
//        for (Element element: elements) {
//            JSONObject fes = new JSONObject();
//            Elements festival = element.getElementsByTag("td");
//            int length = festival.size();
//            for(int i=0;i<length;i++){
//                if(i==0){
//                    fes.put("date",festival.get(i).text());
//                    System.out.println(festival.get(i).text());
//                }
//                if(i==1){
//                    String places = festival.get(i).text();
//                    JSONArray arrPlace = new JSONArray();
//                    if(places.contains(",")){
//                        String[] place = places.split(",");
//                        for (String p:place
//                             ) {
//                            JSONObject jsonPlace = new JSONObject();
//                            String search = searchGoogle(p);
//                            if(search!=""){
//                                arrPlace.put(search);
//                            }
//                        }
//
//                    }else {
//                        if(searchGoogle(places)!=""){
//                            arrPlace.put(places);
//                        }
//                    }
//                    fes.put("place",arrPlace);
//                }
//                if(i==2){
//                    String name = festival.get(i).text();
//                    fes.put("name",name);
//                }
//                if(i==3){
//                    fes.put("root",festival.get(i).text());
//                }
//                if(i==4){
//                    String people = festival.get(i).text();
//                    JSONArray arrPeople = new JSONArray();
//                    if(people.contains(",")){
//                        String[] person = people.split(",");
//                        for (String p:person){
//                            String search = searchGoogle(p);
//                            if(search!=""){
//                                arrPeople.put(search);
//                            }
//                        }
//                    }else{
//                        String search = searchGoogle(people);
//                        if(search!=""){
//                            arrPeople.put(search);
//                        }
//                    }
//                    fes.put("person",arrPeople);
//                }
//                if(i==5){
//                    fes.put("note",festival.get(i).text());
//                }
//
//            }
//            jsonArray.put(fes);
//        }
//        obj.put("festival",jsonArray);
//        try {
//            FileWriter file = new FileWriter("src/main/resources/json/festival.json");
//            file.write(obj.toString());
//            file.flush();
//            file.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        List<Festival> festivals = crawlFestival();
        System.out.println(festivals.get(0).getName());
    }
    public static String searchGoogle(String keyword) throws IOException{
        String url = "https://www.google.com/search?q=";
        Document doc = Jsoup.connect(url+keyword+" nguoikesu").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36").get();
        String html = doc.html();
        String link = doc.select(".yuRUbf a").first().attr("href");
        if(link.contains("https://nguoikesu.com/dia-danh")||link.contains("https://nguoikesu.com/nhan-vat")){
            link = link.replace("https://nguoikesu.com","");
        }else{
            link = "";
        }
        return link;
    }

}
