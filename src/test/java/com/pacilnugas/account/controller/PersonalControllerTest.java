package com.pacilnugas.account.controller;

import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.core.Student;
import com.pacilnugas.account.service.AccountService;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.service.MatkulService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = PersonalController.class)
public class PersonalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private MatkulService matkulService;

    @MockBean
    private Student student;

    @Test
    public void whenPersonalUrlShouldCallPersonalView() throws Exception {
        List<Matkul> listMatkulPersonal = new ArrayList<>();
        List<Matkul> listMatkulTotal = new ArrayList<>();
        when(accountService.decode("sasfort")).thenReturn("sasfort");
        when(accountService.getAccountByUsername("sasfort")).thenReturn(student);
        when(student.getPersonalizedMatkul()).thenReturn(listMatkulPersonal);
        when(matkulService.getAllMatkulObject()).thenReturn(listMatkulTotal);

        mockMvc.perform(get("/personal?username=sasfort"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("personal"))
                .andExpect(model().attributeExists("listMatkulPersonal"))
                .andExpect(model().attributeExists("listMatkulTotal"))
                .andExpect(model().attributeExists("display"))
                .andExpect(model().attributeExists("username"))
                .andExpect(view().name("account/personal/personalPage"));
    }

    @Test
    public void whenPersonalFilterShouldSavePersonalizedMatkul() throws Exception {
        when(accountService.encode("sasfort")).thenReturn("sasfort");
        mockMvc.perform(get("/personalFilter?listMatkul=Advanced%20Programming&username=sasfort"))
                .andExpect(redirectedUrl("/personal?username=sasfort"));
    }
}