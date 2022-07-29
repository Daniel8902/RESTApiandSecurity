package com.week4.week4.Service;

import com.week4.week4.Model.Student;
import com.week4.week4.Model.Subject;
import com.week4.week4.Model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherService {
    private ArrayList<Teacher> teachers;
    private StudentServiceImpl studentService;

  private Teacher teacher1 = new Teacher(1l, "Kasia", "Wola", Subject.LAW);
    private Teacher teacher2 = new Teacher(2l, "Malgorzata", "Tola", Subject.BIOLOGY);
    private Teacher teacher3 = new Teacher(3l, "Maja", "Kola", Subject.ALGEBRA );

    public Teacher findById(Long id) {
        for (Teacher teacher : teachers)
            if (Objects.equals(teacher.getId(), id))
                return teacher;
        return null;
    }
    @Autowired
    public TeacherService(ArrayList<Teacher> teachers, StudentServiceImpl studentService) {
        this.studentService = studentService;
        this.teachers = teachers;
        teachers.addAll(List.of(teacher1, teacher2, teacher3));
    }

    public ArrayList<Teacher> getAllTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {


        teachers.add(teacher);

    }
    public void deleteTeacher(Long id) {
        Teacher teacher = findById(id);
        teachers.remove(teacher);
    }
    public List<Student> getTeacherClass(Long id) {
        if (teachers.isEmpty()) {
            return null;
        }

        List<Student> students = studentService.findAll();
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getSubjectList().contains(teachers.get(id.intValue()).getSubjectName())) {
                result.add(student);
            }
        }

        return result;
    }
    public void deleteStudentFromClassByTeacherId(Long teacherId, Long studentId) {
        List<Student> students = getTeacherClass(teacherId);
        students.get(students.indexOf(studentService.getStudentById(studentId))).getSubjectList().
                remove(teachers.get(teacherId.intValue()).getSubjectName());
    }

}
