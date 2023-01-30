package hust.vietnamesehistory.repository;

import org.json.JSONObject;

public class PlaceRepository {
    public PlaceRepository(){
    }

    public String getHref(JSONObject obj){
        String href = "";
        if(!obj.isNull("href") && obj.has("href")){
            href = obj.getString("href");
        }
        return href;
    }
    public String getName(JSONObject obj){
        String name = "";
        if(!obj.isNull("name") && obj.has("name")){
            name = obj.getString("name");
        }
        return name;
    }

    public String getNational(JSONObject obj){
        String national = "";
        if(!obj.isNull("national") && obj.has("national")){
            national = obj.getString("national");
        }
        return national;
    }

    public String getLocation(JSONObject obj){
        String location = "";
        if(!obj.isNull("location") && obj.has("location")){
            location = obj.getString("location");
        }
        return location;
    }

    public String getCoordinates(JSONObject obj){
        String coordinates = "";
        if(!obj.isNull("coordinates") && obj.has("coordinates")){
            coordinates = obj.getString("coordinates");
        }
        return coordinates;
    }

    public String getArea(JSONObject obj){
        String area = "";
        if(!obj.isNull("area") && obj.has("area")){
            area = obj.getString("area");
        }
        return area;
    }
}
