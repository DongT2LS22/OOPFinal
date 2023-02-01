package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hust.vietnamesehistory.crawler.model.King;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KingRepository implements Repository<King>{
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectReader reader = mapper.reader();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    @Override
    public List<King> readJson(String filePath) throws IOException {
        List<King> kings = new ArrayList<>();
        ObjectNode kingsObj = reader.forType(new TypeReference<ObjectNode>(){}).readValue(new File(filePath));
        ArrayNode arrayNode = kingsObj.withArray("people");
        for (JsonNode node : arrayNode) {
            if (node.has("reignTime")) {
                String href = node.get("href").asText();
                String name = node.get("name").asText();
                String birth = node.get("birth").asText();
                String death = node.get("death").asText();
                String reignTime = node.get("reignTime").asText();
                String predecessor = node.get("predecessor").asText();
                String successor = node.get("successor").asText();
                String aliases = node.get("aliases").asText();
                String realName = node.get("realName").asText();
                King king = new King(name, href, birth, death, reignTime
                        , predecessor, successor, aliases, realName);
                kings.add(king);
            }
        }
        return kings;
    }

    @Override
    public void writeJson(List<King> listObject, String filePath) throws IOException {
        ArrayNode kingNodes = mapper.createArrayNode();
        for (King k : listObject) {
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
            kingNodes.add(king);
        }
        ObjectNode kingsObj = mapper.createObjectNode();
        kingsObj.set("people", kingNodes);
        writer.writeValue(new File(filePath), kingsObj);
    }
}
