package com.pacilnugas.activities.controller;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.service.AssignmentService;
import com.pacilnugas.activities.service.MatkulService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

@WebMvcTest(controllers = ActivitiesController.class)
public class ActivitiesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentService assignmentService;

    @MockBean
    private MatkulService matkulService;

    @Test
    public void whenAssignmentUrlShouldCallAssignmentService() throws Exception {
        mockMvc.perform(get("/task/all"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getAllAssignment"))
                .andExpect(model().attributeExists("SemuaAssignment"))
                .andExpect(view().name("activities/assignment/allAssignment"));
        verify(assignmentService, times(1)).getAllAssignment();
    }

    @Test
    public void whenAssignmentUrlShouldCallAssignmentServiceV2() throws Exception {
        mockMvc.perform(get("/task/"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getPersonalizeAssignment"))
                .andExpect(model().attributeExists("SemuaAssignment"))
                .andExpect(view().name("activities/assignment/allAssignment"));
        verify(assignmentService, times(1)).getAllAssignment();
    }

    @Test
    public void whenNewAssignmentShouldCallNewAssignment() throws Exception {
        LocalDate deadline = LocalDate.of(2020, 1, 8);
        LocalTime time = LocalTime.of(12, 5);
        mockMvc.perform(post("/task/proses-input-tugas")
                .param("title", "TP 1")
                .param("matkul", "DDP")
                .param("description", "Belajar TP")
                .param("tahunajaran", "2020")
                .param("deadline", "2020-01-08")
                .param("deadline-time", "12:05"))
                .andExpect(handler().methodName("assignmentFormPro"));

        verify(assignmentService, times(1))
                .createAssignment("TP 1", "Belajar TP", "DDP",
                        "2020", deadline, time);
    }

    @Test
    public void whenCreateAssignmentUrlShouldCallAssignmentView() throws Exception {
        mockMvc.perform(get("/task/input-tugas"))
                .andExpect(status().isOk())
                .andExpect(view().name("activities/assignment/inputPage"));
    }

    @Test
    void whenGetSpecificAssignmentUrlIsAccessed() throws Exception {
        Matkul matkul = new Matkul();
        matkul.setMajor("Ilmu Komputer");
        matkul.setAngkatan(2019);
        Assignment assignment = new Assignment(
                "Group Project - Adpro", matkul, LocalDateTime.now());
        lenient().when(assignmentService.getAssignmentById(0)).thenReturn(assignment);
        mockMvc.perform(get("/task/view/0"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("AssignmentIni"))
                .andExpect(view().name("activities/rincian/rincian"));
    }

    @Test
    void whenGetAssignmentToEditUrl() throws Exception {
        Matkul matkul = new Matkul();
        matkul.setMajor("Ilmu Komputer");
        matkul.setAngkatan(2019);
        Assignment assignment = new Assignment(
                "Group Project - Adpro", matkul, LocalDateTime.now());

        lenient().when(assignmentService.getAssignmentById(0)).thenReturn(assignment);
        mockMvc.perform(get("/task/view/0/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("AssignmentIni"))
                .andExpect(view().name("activities/edit/editAssignment"));
    }

    @Test
    void whenAssignmentEditFormUrl() throws Exception {
        Matkul matkul = new Matkul();
        matkul.setMajor("Ilmu Komputer");
        matkul.setAngkatan(2019);
        Assignment assignment = new Assignment(
                "Group Project - Adpro", matkul, LocalDateTime.now());

        lenient().when(assignmentService.getAssignmentById(0)).thenReturn(assignment);
        mockMvc.perform(post("/task/view/0/update")
                .param("title", assignment.getTitle())
                .param("matkul", matkul.getTitle())
                .param("description", "Belajar TP")
                .param("deadline", "2020-01-08")
                .param("deadline-time", "12:05"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/task/view/0"));
    }
}
