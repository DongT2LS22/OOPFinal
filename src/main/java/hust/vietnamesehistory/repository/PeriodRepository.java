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
import hust.vietnamesehistory.crawler.model.Period;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PeriodRepository implements Repository<Period>{
    @Override
    public List<Period> readJson(String filePath) throws IOException {
        List<Period> periods = new ArrayList<>();
        ObjectNode PeriodsObj = reader.forType(new TypeReference<ObjectNode>(){}).readValue(new File(filePath));
        ArrayNode arrayNode = PeriodsObj.withArray("periods");
        for (JsonNode node : arrayNode) {
            String href = node.get("href").asText();
            String name = node.get("name").asText();
            ArrayNode kingNodes = node.withArray("kings");
            List<King> kings = new ArrayList<>();
            for (JsonNode kingNode : kingNodes) {
                String kingHref = kingNode.get("href").asText();
                String kingName = kingNode.get("name").asText();
                String birth = kingNode.get("birth").asText();
                String death = kingNode.get("death").asText();
                String reignTime = kingNode.get("reignTime").asText();
                String predecessor = kingNode.get("predecessor").asText();
                String successor = kingNode.get("successor").asText();
                String aliases = kingNode.get("aliases").asText();
                String realName = kingNode.get("realName").asText();
                King king = new King(kingName, kingHref, birth, death, reignTime
                        , predecessor, successor, aliases, realName);
                kings.add(king);
            }
            Period p = new Period(href, name, kings);
            periods.add(p);
        }
        return periods;
    }

    @Override
    public void writeJson(List<Period> listObject, String filePath) throws IOException {
        ArrayNode periodNodes = mapper.createArrayNode();
        for (Period p : listObject) {
            ObjectNode period = mapper.createObjectNode();
            period.put("href", p.getHref());
            period.put("name", p.getName());
            ArrayNode kings = mapper.createArrayNode();
            for (King k : p.getKings()) {
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
                kings.add(king);
            }
            period.set("kings", kings);
            periodNodes.add(period);
        }
        ObjectNode periodsObj = mapper.createObjectNode();
        periodsObj.set("periods", periodNodes);
        writer.writeValue(new File(filePath), periodsObj);
    }
}
