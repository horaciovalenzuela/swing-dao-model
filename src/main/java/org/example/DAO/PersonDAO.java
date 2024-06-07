package org.example.DAO;

import org.example.model.Person;

import java.util.List;

public interface PersonDAO {
    public Person savePerson(Person person);
    public List<Person> getAllPersons();
}