package com.pacilnugas.account.controller;

import com.pacilnugas.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

//    @Test
//    public void whenPersonalUrlShouldCallPersonalView() throws Exception {
//        accountService.createAccount("sasfort", "12345678", "Student");
//        mockMvc.perform(get("/personal?username=sasfort"))
//                .andExpect(status().isOk())
//                .andExpect(handler().methodName("personal"))
//                .andExpect(model().attributeExists("listMatkulPersonal"))
//                .andExpect(model().attributeExists("listMatkulTotal"))
//                .andExpect(model().attributeExists("display"))
//                .andExpect(model().attributeExists("username"))
//                .andExpect(view().name("account/personal/personalPage"));
//    }
}