package com.week4.week4.Model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private ArrayList<Subject> subjectList;

    public Student(Long id, String name, String surname, int age, ArrayList<Subject> subjectList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.subjectList = subjectList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return name;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String lastName) {
        this.surname = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(ArrayList<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", subjectList=" + subjectList +
                '}';
    }
}
