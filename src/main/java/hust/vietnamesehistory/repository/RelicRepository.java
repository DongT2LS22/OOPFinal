package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hust.vietnamesehistory.model.Relic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelicRepository implements Repository<Relic>{
    @Override
    public List<Relic> readJson(String filePath) throws IOException {
        List<Relic> relics = new ArrayList<>();
        ObjectNode relicsObj = reader.forType(new TypeReference<ObjectNode>(){}).readValue(new File(filePath));
        ArrayNode arrayNode = relicsObj.withArray("places");
        for (JsonNode node : arrayNode) {
            if (node.has("type")) {
                String href = (node.get("href").isNull())?"":node.get("href").asText();
                String name = (node.get("name").isNull())?"":node.get("name").asText();
                String national = (node.get("national").isNull())?"":node.get("national").asText();
                String location = (node.get("location").isNull())?"":node.get("location").asText();
                String coordinates = (node.get("coordinates").isNull())?"":node.get("coordinates").asText();
                String area = (node.get("area").isNull())?"":node.get("area").asText();
                String type = (node.get("type").isNull())?"":node.get("type").asText();
                String recognizedYear = (node.get("recognizedYear").isNull())?"":node.get("recognizedYear").asText();
                Relic relic = new Relic(name,href, national, location, coordinates, area, type, recognizedYear);
                relics.add(relic);
            }
        }
        return relics;
    }

    @Override
    public void writeJson(List<Relic> listObject, String filePath) throws IOException {
        ArrayNode relicNodes = mapper.createArrayNode();
        for (Relic r : listObject) {
            ObjectNode relic = mapper.createObjectNode();
            relic.put("href", r.getHref());
            relic.put("name", r.getName());
            relic.put("national", r.getNational());
            relic.put("location", r.getLocation());
            relic.put("coordinates", r.getCoordinates());
            relic.put("area", r.getArea());
            relic.put("type", r.getType());
            relic.put("recognizedYear", r.getRecognizedYear());
            relicNodes.add(relic);
        }
        ObjectNode relicsObj = mapper.createObjectNode();
        relicsObj.set("places", relicNodes);
        writer.writeValue(new File(filePath), relicsObj);
    }
}
