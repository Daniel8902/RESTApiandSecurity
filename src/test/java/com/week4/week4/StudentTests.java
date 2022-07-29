package com.week4.week4;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.week4.week4.FIlter.AuthorizationStudentFilter;
import com.week4.week4.Model.Student;
import com.week4.week4.Model.Subject;
import com.week4.week4.RrestController.StudentController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentController studentController;
//    @Autowired
//    private AuthorizationStudentFilter authorizationStudentFilter;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getEveryStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/allstudent").header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].firstName", Is.is("Jan")))
                .andDo(print());
    }
    @Test void getStudent2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/student/2")

                        .header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Is.is("mat")));

    }
    @Test
    void getStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/student/2").header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.surname", Is.is("Lon")))
                .andDo(print());
    }
    @Test
    void addStudent() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.post("/api/student/add").header("role", "TEACHER_ROLE")
                        .content(asJsonString(new Student(8L, "Dzion", "deded",60, new ArrayList<>(List.of(Subject.ALGEBRA)))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test void getStudentAllOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/allstudent").header("role", "TEACHER_ROLE"))
                .andExpect(status().isOk())
                .andDo(print());
    }





    @Test
    void updateStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/student/{id}", 0).header("role", "TEACHER_ROLE")
                .content(asJsonString(new Student(0L, "ttet", "cicic", 77, new ArrayList<>(List.of(Subject.ALGEBRA)))))
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test void deleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/de/6")
                        .header("role", "TEACHER_ROLE")
                        )
                .andDo(print())
                .andExpect(status().isOk());


            mockMvc.perform(MockMvcRequestBuilders.get("/api/student/allstudent").header("role", "TEACHER_ROLE"))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
}
