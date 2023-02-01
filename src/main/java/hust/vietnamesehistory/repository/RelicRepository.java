/**
 * This class was created at 01-Feb-23 09:53:55
 * This class is owned by FaceNet Company
 */
package hust.vietnamesehistory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hust.vietnamesehistory.crawler.model.Relic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelicRepository implements Repository<Relic>{
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ObjectReader reader = mapper.reader();
    public static final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    @Override
    public List<Relic> readJson(String filePath) throws IOException {
        List<Relic> relics = new ArrayList<>();
        ObjectNode relicsObj = reader.forType(new TypeReference<ObjectNode>(){}).readValue(new File(filePath));
        ArrayNode arrayNode = relicsObj.withArray("places");
        for (JsonNode node : arrayNode) {
            if (node.has("type")) {
                String href = node.get("href").asText();
                String name = node.get("name").asText();
                String national = node.get("national").asText();
                String location = node.get("location").asText();
                String coordinates = node.get("coordinates").asText();
                String area = node.get("area").asText();
                String type = node.get("type").asText();
                String recognizedYear = node.get("recognizedYear").asText();
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
