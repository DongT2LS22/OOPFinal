package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hust.vietnamesehistory.model.King;
import hust.vietnamesehistory.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository<Person> {
    @Override
    public List<Person> readJson(String filePath) throws IOException {
        List<Person> people = new ArrayList<>();
        ObjectNode peopleObj = reader.forType(new TypeReference<ObjectNode>(){}).readValue(new File(filePath));
        ArrayNode arrayNode = peopleObj.withArray("people");
        for (JsonNode node : arrayNode) {
            String href = node.get("href").asText();
            String name = node.get("name").asText();
            String birth = node.get("birth").asText();
            String death = node.get("death").asText();
            if (node.has("reignTime")) {
                String reignTime = node.get("reignTime").asText();
                String predecessor = node.get("predecessor").asText();
                String successor = node.get("successor").asText();
                String aliases = node.get("aliases").asText();
                String realName = node.get("realName").asText();
                King king = new King(name, href, birth, death, reignTime
                        , predecessor, successor, aliases, realName);
                people.add(king);
            } else {
                Person person = new Person(href, name, birth, death);
                people.add(person);
            }
        }
        return people;
    }

    @Override
    public void writeJson(List<Person> listObject, String filePath) throws IOException {
        ArrayNode personNodes = mapper.createArrayNode();
        for (Person p : listObject) {
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
                personNodes.add(king);
            } else {
                ObjectNode person = mapper.createObjectNode();
                person.put("href", p.getHref());
                person.put("name", p.getName());
                person.put("birth", p.getBirth());
                person.put("death", p.getDeath());
                personNodes.add(person);
            }
        }
        ObjectNode peopleObj = mapper.createObjectNode();
        peopleObj.set("people", personNodes);
        writer.writeValue(new File(filePath), peopleObj);
    }
}
