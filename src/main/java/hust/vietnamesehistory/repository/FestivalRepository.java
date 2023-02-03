package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hust.vietnamesehistory.model.Festival;
import hust.vietnamesehistory.model.King;
import hust.vietnamesehistory.model.Person;
import hust.vietnamesehistory.model.Place;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FestivalRepository implements Repository<Festival>{
    @Override
    public List<Festival> readJson(String filePath) throws IOException {
        List<Festival> festivals = new ArrayList<>();
        ObjectNode festivalsObj = reader.forType(new TypeReference<ObjectNode>() {}).readValue(new File(filePath));
        ArrayNode arrayNode = festivalsObj.withArray("festivals");
        for (JsonNode node : arrayNode) {
            String name = (node.get("name").isNull())?"":node.get("name").asText();
            String date = (node.get("date").isNull())?"":node.get("date").asText();
            String note = (node.get("note").isNull())?"":node.get("note").asText();
            String root = (node.get("root").isNull())?"":node.get("root").asText();

            List<Person> people = new ArrayList<>();
            ArrayNode personNodes = node.withArray("people");
            for (JsonNode personNode : personNodes) {
                String personHref = (personNode.get("href").isNull())?"":personNode.get("href").asText();
                String personName = (personNode.get("name").isNull())?"":personNode.get("name").asText();
                String birth = (personNode.get("birth").isNull())?"":personNode.get("birth").asText();
                String death = (personNode.get("death").isNull())?"":personNode.get("death").asText();
                if (personNode.has("reignTime")) {
                    String reignTime = (personNode.get("reignTime").isNull())?"":personNode.get("reignTime").asText();
                    String predecessor = (personNode.get("predecessor").isNull())?"":personNode.get("predecessor").asText();
                    String successor = (personNode.get("successor").isNull())?"":personNode.get("successor").asText();
                    String aliases = (personNode.get("aliases").isNull())?"":personNode.get("aliases").asText();
                    String realName = (personNode.get("realName").isNull())?"":personNode.get("realName").asText();
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

            Festival festival = new Festival(name, date, places, note, people, root);
            festivals.add(festival);
        }
        return festivals;
    }

    @Override
    public void writeJson(List<Festival> listObject, String filePath) throws IOException {
        ArrayNode festivalNodes = mapper.createArrayNode();
        for (Festival f : listObject) {
            ObjectNode festival = mapper.createObjectNode();
            festival.put("name", f.getName());
            festival.put("date", f.getDate());
            festival.put("note", f.getNote());
            festival.put("root", f.getRoot());

            ArrayNode people = mapper.createArrayNode();
            for (Person p : f.getPeople()) {
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
            festival.set("people", people);

            ArrayNode places = mapper.createArrayNode();
            for (Place p : f.getPlaces()) {
                ObjectNode place = mapper.createObjectNode();
                place.put("href", p.getHref());
                place.put("name", p.getName());
                place.put("national", p.getNational());
                place.put("location", p.getLocation());
                place.put("coordinates", p.getCoordinates());
                place.put("area", p.getArea());
                places.add(place);
            }
            festival.set("places", places);

            festivalNodes.add(festival);
        }
        ObjectNode festivalsObj = mapper.createObjectNode();
        festivalsObj.set("festivals", festivalNodes);
        writer.writeValue(new File(filePath), festivalsObj);
    }
}
