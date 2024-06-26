package org.example.model;

public class Person {
    private String name;
    private String surname;
    private int age;

    private int id;

    public Person(String aName, String aSurname, int anAge){
        this.name = aName;
        this.surname = aSurname;
        this.age = anAge;
    }

    public Person(String aName, String aSurname, int anAge, int id){
        this.name = aName;
        this.surname = aSurname;
        this.age = anAge;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}