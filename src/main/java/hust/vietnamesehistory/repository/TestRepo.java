package hust.vietnamesehistory.repository;

import hust.vietnamesehistory.crawler.model.Period;
import hust.vietnamesehistory.crawler.model.Person;

import java.io.IOException;
import java.util.List;

public class TestRepo {
    public static PersonRepository personRepository = new PersonRepository();
    public static PeriodRepository periodRepository = new PeriodRepository();
    public static void main(String[] args) throws IOException {
//        App.setPersonList();
//        List<Person> people = App.getPersonList();
//        Person p = people.get(0);
//        PersonRepository repo = new PersonRepository();
//        JSONObject obj = new JSONObject();
//        obj.put("birth",p.getBirth());
//        System.out.println(repo.getBirth(obj));
//        List<Person> people = personRepository.readJson("src/main/resources/json/people.json");
//        System.out.println(people.get(0).getName());
        List<Period> periods = periodRepository.readJson("src/main/resources/json/periods.json");
        System.out.println(periods.get(2).getKings().get(0).getName());
    }
}
