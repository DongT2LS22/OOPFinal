package hust.vietnamesehistory.repository;

import hust.vietnamesehistory.crawler.model.King;
import hust.vietnamesehistory.crawler.model.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.util.List;

public class PersonRepository {

    public PersonRepository() {
    }

    public String getName(JSONObject obj){
        String name = "";
        if(!obj.isNull("name") && obj.has("name")){
            name = obj.getString("name");
        }
        return name;
    }
    public String getBirth(JSONObject obj){
        String birth = "";
        if(!obj.isNull("birth") && obj.has("birth")){
            birth = obj.getString("birth");
        }
        return birth;
    }

    public String getDeath(JSONObject obj){
        String death = "";
        if(!obj.isNull("death") && obj.has("death")){
            death = obj.getString("death");
        }
        return death;
    }

    public String getHref(JSONObject obj){
        String href = "";
        if(!obj.isNull("href") && obj.has("href")){
            href = obj.getString("href");
        }
        return href;
    }

//    public Person getMate(){
//
//    }

    public JSONObject putPerson(Person p){
        JSONObject obj = new JSONObject();
        obj.put("href",p.getHref());
        obj.put("name",p.getName());
        obj.put("birth",p.getBirth());
        if(p.getMates().size()!=0){
            JSONArray arr = new JSONArray();
            for (Person ma:p.getMates()) {
                arr.put(ma.getHref()+","+ma.getName());
            }
            obj.put("mates",arr);
        }
        obj.put("death",p.getDeath());
        if(p.getParents().size()!=0){
            JSONArray arr = new JSONArray();
            for (Person pa:p.getParents()) {
                arr.put(pa.getHref()+","+pa.getName());
            }
            obj.put("parents",arr);
        }
        if(p instanceof King){
            obj.put("reignTime",((King) p).getReignTime());
            obj.put("predecessor",((King) p).getPredecessor());
            obj.put("successor",((King) p).getSuccessor());
            obj.put("aliases",((King) p).getAliases());
            obj.put("realName",((King) p).getRealName());
        }
        return obj;
    }

    public void writePerson(List<Person> people, String link){
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Person p:people) {
            JSONObject person = putPerson(p);
            arr.put(person);
        }
        obj.put("person",arr);
        try {
            FileWriter file = new FileWriter(link);
            file.write(obj.toString());
            file.flush();
            file.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
