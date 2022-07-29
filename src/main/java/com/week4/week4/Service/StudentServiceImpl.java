package com.week4.week4.Service;

import com.week4.week4.Model.Student;
import com.week4.week4.Model.Subject;
import com.week4.week4.Model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl {

    ArrayList<Student> students;

    private Student student1 = new Student(1l, "Jan", "Benol", 22, new ArrayList<Subject>(List.of(Subject.ALGEBRA)));
    private Student student2 = new Student(2l, "Dan", "kram", 33, new ArrayList<Subject>(List.of(Subject.ALGEBRA)));
    private Student student3 = new Student(3l, "mat", "Lon", 23,  new ArrayList<Subject>(List.of(Subject.BIOLOGY)));
    private Student student4 = new Student(4l, "Anteo", "Bartol", 46,  new ArrayList<Subject>(List.of(Subject.LAW)));
    private Student student5 = new Student(5l, "Kamil", "Kant", 33,  new ArrayList<Subject>(List.of(Subject.ALGEBRA)));
    private Student student6 = new Student(6l, "DOOn", "Jusis", 22,  new ArrayList<Subject>(List.of(Subject.BIOLOGY)));
    private Student student7 = new Student(7l, "Franz", "Klaid", 33,  new ArrayList<Subject>(List.of(Subject.BIOLOGY)));
    private Student student8 = new Student(8l, "Mathiu", "Boni", 23,  new ArrayList<Subject>(List.of(Subject.LAW)));
    private Student student9 = new Student(9l, "Olimpi", "Jantol", 46,  new ArrayList<Subject>(List.of(Subject.LAW)));
    private Student student10 = new Student(10l, "Dariusz", "Koli", 33, new ArrayList<Subject>(List.of(Subject.ALGEBRA)));

    public List<Student> findAll() {
        return students;
    }


    public StudentServiceImpl() {
        students = new ArrayList<>(10);
        students.addAll(List.of(student1, student2, student3, student4, student5,student6,student7,student8,student9,student10));
    }

    public void addStudent(Student student) {
        students.add(student);

    }

    public void editStudent(Long id, Student student) {
        Student studentToUpdate = students.get(id.intValue());
        studentToUpdate.setSurname(student.getSurname());
        studentToUpdate.setAge(student.getAge());
    }

    public void deleteStudent(int theId) {
        students.remove(theId);
    }

    public Student getStudentById(Long id) {
        return students.get(id.intValue());
    }

}



