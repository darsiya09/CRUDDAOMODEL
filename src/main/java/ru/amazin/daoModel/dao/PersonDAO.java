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
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(people -> people.getId() == id).findAny().orElse(null);
    }

    public void addPerson(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person){
        Person replacedPerson = show(id);
        replacedPerson.setName(person.getName());
    }

    public void delete(int id){
        people.removeIf(person -> person.getId()==id);
    }
}
