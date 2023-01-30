package hust.vietnamesehistory.repository;

import org.json.JSONObject;

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
}
