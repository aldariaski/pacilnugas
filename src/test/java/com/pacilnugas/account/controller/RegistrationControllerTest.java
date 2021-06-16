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

@WebMvcTest(controllers = RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void whenExistingAccountUrlShouldCallAccountService() throws Exception {
        mockMvc.perform(get("/existingAccount"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("existingAccount"))
                .andExpect(model().attributeExists("accountDisplayList"))
                .andExpect(view().name("account/registration/existingAccountPage"));
        verify(accountService, times(1)).getAllDisplayMessage();
    }

    @Test
    public void whenRegistrationUrlShouldCallRegistrationView() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("registration"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/registration/registrationPage"));
    }

    @Test
    public void whenRegistrationUsedUrlShouldCallRegistrationUsedView() throws Exception {
        mockMvc.perform(get("/registrationUsed"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("registrationUsed"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/registration/registrationPage"));
    }

    @Test
    public void whenRegistrationErrorShouldCallRegistrationErrorView() throws Exception {
        mockMvc.perform(get("/registrationError"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("registrationError"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/registration/registrationPage"));
    }

    @Test
    public void whenRegistrationProcessShouldCreateNewAccount() throws Exception {
        mockMvc.perform(post("/registrationProcess")
                .param("username", "sasfort")
                .param("password", "12345678")
                .param("confirmPassword", "12345678")
                .param("type", "Student"))
                .andExpect(handler().methodName("registrationProcess"));
        verify(accountService, times(1)).registration("sasfort", "12345678", "12345678",
                "Student");
    }
}