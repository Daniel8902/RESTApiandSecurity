package com.week4.week4.RrestController;

import com.week4.week4.Model.Student;
import com.week4.week4.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping("/allstudent")
    public ResponseEntity<List<Student>> getAllStudents() {


        return ResponseEntity.status(HttpStatus.OK)
                .header("successful", "true")
                .body(studentServiceImpl.findAll());
    }

    @PostMapping("/add")
    public Student addStudentt(@RequestBody Student student) {


        studentServiceImpl.addStudent(student);

        return student;
    }

    @PutMapping("{id}")
    public void editStudent(@PathVariable Long id, @RequestBody Student student) {
        studentServiceImpl.editStudent(id, student);
    }

    @DeleteMapping("/de/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentServiceImpl.deleteStudent(id);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok()
                .header("successful", "true")
                .body(studentServiceImpl.getStudentById(id));
    }
}