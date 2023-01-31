package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hust.vietnamesehistory.crawler.model.Place;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaceRepository implements Repository<Place> {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectReader reader = mapper.reader();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    @Override
    public List<Place> readJson(String filePath) throws IOException {
        List<Place> places = new ArrayList<>();
        ObjectNode placesObj = reader.forType(new TypeReference<ObjectNode>(){}).readValue(new File(filePath));
        ArrayNode arrayNode = placesObj.withArray("places");
        for (JsonNode node : arrayNode) {
            String href = node.get("href").asText();
            String name = node.get("name").asText();
            String national = node.get("national").asText();
            String location = node.get("location").asText();
            String coordinates = node.get("coordinates").asText();
            String area = node.get("area").asText();
            Place place = new Place(name,href, national, location, coordinates, area);
            places.add(place);
        }
        return places;
    }

    @Override
    public void writeJson(List<Place> listObject, String filePath) throws IOException {
        ArrayNode placeNodes = mapper.createArrayNode();
        for (Place p : listObject) {
            ObjectNode place = mapper.createObjectNode();
            place.put("href", p.getHref());
            place.put("name", p.getName());
            place.put("national", p.getNational());
            place.put("location", p.getLocation());
            place.put("coordinates", p.getCoordinates());
            place.put("area", p.getArea());
            placeNodes.add(place);
        }
        ObjectNode placesObj = mapper.createObjectNode();
        placesObj.set("places", placeNodes);
        writer.writeValue(new File(filePath), placesObj);
    }
//    public PlaceRepository(){
//    }
//
//    public String getHref(JSONObject obj){
//        String href = "";
//        if(!obj.isNull("href") && obj.has("href")){
//            href = obj.getString("href");
//        }
//        return href;
//    }
//    public String getName(JSONObject obj){
//        String name = "";
//        if(!obj.isNull("name") && obj.has("name")){
//            name = obj.getString("name");
//        }
//        return name;
//    }
//
//    public String getNational(JSONObject obj){
//        String national = "";
//        if(!obj.isNull("national") && obj.has("national")){
//            national = obj.getString("national");
//        }
//        return national;
//    }
//
//    public String getLocation(JSONObject obj){
//        String location = "";
//        if(!obj.isNull("location") && obj.has("location")){
//            location = obj.getString("location");
//        }
//        return location;
//    }
//
//    public String getCoordinates(JSONObject obj){
//        String coordinates = "";
//        if(!obj.isNull("coordinates") && obj.has("coordinates")){
//            coordinates = obj.getString("coordinates");
//        }
//        return coordinates;
//    }
//
//    public String getArea(JSONObject obj){
//        String area = "";
//        if(!obj.isNull("area") && obj.has("area")){
//            area = obj.getString("area");
//        }
//        return area;
//    }
}
