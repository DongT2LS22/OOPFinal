package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hust.vietnamesehistory.crawler.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements Repository<Event> {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectReader reader = mapper.reader();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    @Override
    public List<Event> readJson(String filePath) throws IOException {
        List<Event> events = new ArrayList<>();
        ObjectNode eventsObj = reader.forType(new TypeReference<ObjectNode>() {}).readValue(new File(filePath));
        ArrayNode arrayNode = eventsObj.withArray("events");
        for (JsonNode node : arrayNode) {
            String date = node.get("date").asText();
            String name = node.get("name").asText();
            List<Person> people = new ArrayList<>();
            ArrayNode personNodes = node.withArray("people");
            for (JsonNode personNode : personNodes) {
                String personHref = personNode.get("href").asText();
                String personName = personNode.get("name").asText();
                String birth = personNode.get("birth").asText();
                String death = personNode.get("death").asText();
                if (personNode.has("reignTime")) {
                    String reignTime = personNode.get("reignTime").asText();
                    String predecessor = personNode.get("predecessor").asText();
                    String successor = personNode.get("successor").asText();
                    String aliases = personNode.get("aliases").asText();
                    String realName = personNode.get("realName").asText();
                    King king = new King(personName, personHref, birth, death, reignTime
                            , predecessor, successor, aliases, realName);
                    people.add(king);
                } else {
                    Person person = new Person(personHref, personName, birth, death);
                    people.add(person);
                }
            }
            List<Place> places = new ArrayList<>();
            ArrayNode placeNodes = node.withArray("places");
            for (JsonNode placeNode : placeNodes) {
                String placeHref = placeNode.get("href").asText();
                String placeName = placeNode.get("name").asText();
                String national = placeNode.get("national").asText();
                String location = placeNode.get("location").asText();
                String coordinates = placeNode.get("coordinates").asText();
                String area = placeNode.get("area").asText();
                Place place = new Place(placeName,placeHref, national, location, coordinates, area);
                places.add(place);
            }
            Event event = new Event(date, name, people, places);
            events.add(event);
        }
        return events;
    }

    @Override
    public void writeJson(List<Event> listObject, String filePath) throws IOException {
        ArrayNode eventNodes = mapper.createArrayNode();
        for (Event e : listObject) {
            ObjectNode event = mapper.createObjectNode();
            event.put("date", e.getDate());
            event.put("name", e.getName());

            ArrayNode people = mapper.createArrayNode();
            for (Person p : e.getPeople()) {
                if (p instanceof King k) {
                    ObjectNode king = mapper.createObjectNode();
                    king.put("href", k.getHref());
                    king.put("name", k.getName());
                    king.put("birth", k.getBirth());
                    king.put("death", k.getDeath());
                    king.put("reignTime", k.getReignTime());
                    king.put("predecessor", k.getPredecessor());
                    king.put("successor", k.getSuccessor());
                    king.put("aliases", k.getAliases());
                    king.put("realName", k.getRealName());
                    people.add(king);
                } else {
                    ObjectNode person = mapper.createObjectNode();
                    person.put("href", p.getHref());
                    person.put("name", p.getName());
                    person.put("birth", p.getBirth());
                    person.put("death", p.getDeath());
                    people.add(person);
                }
            }
            event.set("people", people);

            ArrayNode places = mapper.createArrayNode();
            for (Place p : e.getPlaces()) {
                ObjectNode place = mapper.createObjectNode();
                place.put("href", p.getHref());
                place.put("name", p.getName());
                place.put("national", p.getNational());
                place.put("location", p.getLocation());
                place.put("coordinates", p.getCoordinates());
                place.put("area", p.getArea());
                places.add(place);
            }
            event.set("places", places);

            eventNodes.add(event);
        }
        ObjectNode eventsObj = mapper.createObjectNode();
        eventsObj.set("events", eventNodes);
        writer.writeValue(new File(filePath), eventsObj);
    }
}
