package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class University {
    private final StudentValueGenerator studentValueGenerator;

    private Map<Integer, Student> allStudents = new HashMap<>();
    private int countId = 1;

    public University(StudentValueGenerator studentValueGenerator){
        this.studentValueGenerator = studentValueGenerator;
    }


    //Добавление студентов
    public void addStudent(Student student){
        if (allStudents == null){allStudents = new HashMap<>();}

        student.setId(countId);
        studentValueGenerator.generateAge();
        student.setAge(studentValueGenerator.generateAge());
        allStudents.put(countId, student);
        countId++;
    }


    //Вывод всех студентов
    public List<Student> getAllStudents(){
        return new ArrayList<>(allStudents.values());
    }

    public void addStudentInRange(Student student, int minYear, int maxYear){
        if (allStudents == null){allStudents = new HashMap<>();}
        student.setId(countId);
        studentValueGenerator.generateAge();
        studentValueGenerator.generateAge();
        student.setAge(studentValueGenerator.generateAgeInRange(minYear, maxYear));
        allStudents.put(countId, student);
        countId++;

    }

}