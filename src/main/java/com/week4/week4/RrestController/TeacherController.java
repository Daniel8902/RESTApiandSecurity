package com.week4.week4.RrestController;

import com.week4.week4.Model.Student;
import com.week4.week4.Model.Teacher;
import com.week4.week4.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("one/{id}")
    public ResponseEntity<Teacher> getTeacher (@PathVariable Long id) {
        return ResponseEntity.ok().header("Successful",
                        "true")
                .body(teacherService.findById(id));
    }

    @GetMapping("/teachers")
    public ArrayList<Teacher> findAll() {


        return teacherService.getAllTeachers();
    }
    @GetMapping("{id}/class")
    public ResponseEntity<List<Student>> getTeacherClass(@PathVariable Long id) {


       return ResponseEntity.ok().header("Successful", "true")
                .body(teacherService.getTeacherClass(id));
    }
    @DeleteMapping("/deleteteacher/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher) {


        teacherService.addTeacher(teacher);

        return ResponseEntity.ok().header("Successful", "true").body("sucses");
    }


    @DeleteMapping(path = "{teacherId}/class/{studentId}")
    ResponseEntity<String> deleteStudentFromClassByTeacherId(@PathVariable("teacherId") Long teacherId, @PathVariable("studentId") Long studentId) {
        if (teacherId < 0 || studentId < 0) {
            return ResponseEntity.badRequest()
                    .header("Successful", "false")
                    .body("Wrong student or teacher");
        }

        teacherService.deleteStudentFromClassByTeacherId(teacherId, studentId);
        return ResponseEntity.ok()
                .header("Successful", "true")
                .body("deleted sucess");
    }

}

