package com.pacilnugas.activities.controller;

import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.service.MatkulService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = MatkulController.class)
class MatkulControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatkulService matkulService;

    @Test
    void whenMatkulFormUrlIsAccessed() throws Exception {
        mockMvc.perform(get("/matkul/input-matkul"))
                .andExpect(handler().methodName("matkulForm"))
                .andExpect(view().name("activities/matkul/inputPageMatkul"));
    }

    @Test
    void whenGetPersonalizedMatkulUrlIsAccessed() throws Exception {
        mockMvc.perform(get("/matkul/"))
                .andExpect(handler().methodName("getPersonalizedMatkul"))
                .andExpect(model().attributeExists("SemuaMatkul"))
                .andExpect(view().name("activities/matkul/allAssignment"));
    }

    @Test
    void whenGetAllMatkulUrlIsAccessed() throws Exception {
        mockMvc.perform(get("/matkul/all"))
                .andExpect(handler().methodName("getAllMatkul"))
                .andExpect(model().attributeExists("SemuaMatkul"))
                .andExpect(view().name("activities/matkul/allAssignment"));
    }

    @Test
    void whenMatkulFormProUrlIsAccessed() throws Exception {
        Matkul matkul = new Matkul("Advanced Programming");
        LocalDate date = LocalDate.now();
        mockMvc.perform(post("/matkul/proses-input-matkul")
                .param("title", matkul.getTitle())
                .param("semester", "Genap")
                .param("description", "")
                .param("tahun", date.toString())
                .param("major", "Ilmu Komputer")
                .param("angkatan", "2019"))
                .andExpect(handler().methodName("matkulFormPro"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/matkul/all"));
    }
}
