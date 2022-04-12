package ru.amazin.daoModel.dao;

import org.springframework.stereotype.Component;
import ru.amazin.daoModel.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static String USER = "postgres";
    private static String PASS = "test";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM Person");

            while (set.next()) {
                Person person = new Person();
                person.setId(set.getInt("id"));
                person.setName(set.getString("name"));
                person.setAge(set.getInt("age"));
                person.setEmail(set.getString("email"));
                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person where id=?");
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            person = new Person();
            person.setId(id);
            person.setName(set.getString("name"));
            person.setAge(set.getInt("age"));
            person.setEmail(set.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void addPerson(Person person) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO Person values(?, ?, ?, ?)");
            prepareStatement.setInt(1, 1);
            prepareStatement.setString(2, person.getName());
            prepareStatement.setInt(3, person.getAge());
            prepareStatement.setString(4, person.getEmail());
            prepareStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
