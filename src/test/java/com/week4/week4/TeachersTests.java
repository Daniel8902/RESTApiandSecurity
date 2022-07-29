package com.week4.week4;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.week4.week4.Model.Subject;
import com.week4.week4.Model.Teacher;
import com.week4.week4.RrestController.TeacherController;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TeachersTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TeacherController teacherController;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteStudentFromClassByTeacherId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}/class", 1).header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void deleteTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/deleteteacher/2", 1).header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/teachers").header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void getTeacherAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/teachers")
                        .header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllTeachers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/teachers").header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].name", Is.is("Kasia")))
                .andDo(print());
    }

    @Test
    void getTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/one/2").header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.surname", Is.is("Tola")))
                .andDo(print());
    }

    @Test
    void addTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/teacher/add").header("role", "TEACHER_ROLE")
                        .content(asJsonString(new Teacher(0L, "Janusz", "Kronk", Subject.ALGEBRA)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }



    @Test
    void getTeacherClass() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}/class", 1).header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andDo(print())
                .andExpect(jsonPath("$.[0].firstName", Is.is("mat")));
    }



}

