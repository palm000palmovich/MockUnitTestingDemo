package org.example;

public class Student {
    private int id;
    private String name;
    private int age;
    private boolean isMale;

    public Student(String name, boolean isMale){
        this.name = name;
        this.isMale = isMale;
    }

    //Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}