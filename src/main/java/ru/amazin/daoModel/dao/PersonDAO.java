package ru.amazin.daoModel.dao;

import org.springframework.stereotype.Component;
import ru.amazin.daoModel.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Vanek"));
        people.add(new Person(++PEOPLE_COUNT, "Klyan"));
        people.add(new Person(++PEOPLE_COUNT, "Batman"));
        people.add(new Person(++PEOPLE_COUNT, "Urok"));
        people.add(new Person(++PEOPLE_COUNT, "Serega"));
        people.add(new Person(++PEOPLE_COUNT, "Polis"));
        people.add(new Person(++PEOPLE_COUNT, "Mark"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(people -> people.getId() == id).findAny().orElse(null);
    }
}
