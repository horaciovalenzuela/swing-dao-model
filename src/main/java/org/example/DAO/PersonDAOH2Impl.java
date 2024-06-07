package org.example.DAO;

import org.example.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOH2Impl implements PersonDAO{

    private static final String URL = "jdbc:h2:./data/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "persons";

    public PersonDAOH2Impl() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255), age INT)";
            statement.executeUpdate(createTableQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person savePerson(Person person) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(name, surname, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //person.setId(????)
        return person;
    }


    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT * FROM " + TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                Person person = new Person(name, surname, age);
                persons.add(person);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Persons saved!");
        return persons;
    }

}
