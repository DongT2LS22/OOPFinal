package hust.vietnamesehistory.repository;

import hust.vietnamesehistory.controller.App;
import hust.vietnamesehistory.crawler.model.Person;
import org.json.JSONObject;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        App.setPersonList();
        List<Person> people = App.getPersonList();
        Person p = people.get(0);
        PersonRepository repo = new PersonRepository();
        JSONObject obj = new JSONObject();
        obj.put("birth",p.getBirth());
        System.out.println(repo.getBirth(obj));
    }
}
