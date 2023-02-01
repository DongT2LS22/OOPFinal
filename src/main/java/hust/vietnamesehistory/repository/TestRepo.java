package hust.vietnamesehistory.repository;

import hust.vietnamesehistory.model.Period;
import hust.vietnamesehistory.model.Person;
import hust.vietnamesehistory.model.Place;

import java.io.IOException;
import java.util.List;

public class TestRepo {
    public static PersonRepository personRepository = new PersonRepository();
    public static PlaceRepository placeRepository = new PlaceRepository();
    public static PeriodRepository periodRepository = new PeriodRepository();
    public static void main(String[] args) throws IOException {
        List<Person> people = personRepository.readJson("src/main/resources/json/people.json");
        System.out.println(people.size());
        List<Place> places = placeRepository.readJson("src/main/resources/json/places.json");
        System.out.println(places.size());
        List<Period> periods = periodRepository.readJson("src/main/resources/json/periods.json");
        System.out.println(periods.size());
    }
}
