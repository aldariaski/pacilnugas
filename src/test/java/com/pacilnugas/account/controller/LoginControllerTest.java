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

@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void whenLoginPersonalUrlShouldCallLoginPersonalView() throws Exception {
        mockMvc.perform(get("/loginPersonal"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("loginPersonal"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/login/loginPersonalPage"));
    }

    @Test
    public void whenLoginPersonaFaillUrlShouldCallLoginPersonalFailView() throws Exception {
        mockMvc.perform(get("/loginPersonalFail"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("loginPersonalFail"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/login/loginPersonalPage"));
    }

    @Test
    public void whenLoginPersonalErrorUrlShouldCallLoginPersonalErrorView() throws Exception {
        mockMvc.perform(get("/loginPersonalError"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("loginPersonalError"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/login/loginPersonalPage"));
    }

    @Test
    public void whenLoginPersonalProcessShouldAuthenticate() throws Exception {
        mockMvc.perform(post("/loginPersonalProcess")
                .param("username", "sasfort")
                .param("password", "12345678"))
                .andExpect(handler().methodName("loginPersonalProcess"));
        verify(accountService, times(1)).authenticatePersonal("sasfort", "12345678");
    }

    @Test
    public void whenLoginCourseUrlShouldCallLoginCourseView() throws Exception {
        mockMvc.perform(get("/loginCourse"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("loginCourse"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/login/loginCoursePage"));
    }

    @Test
    public void whenLoginCourseFailUrlShouldCallLoginCourseFailView() throws Exception {
        mockMvc.perform(get("/loginCourseFail"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("loginCourseFail"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/login/loginCoursePage"));
    }

    @Test
    public void whenLoginCourseErrorUrlShouldCallLoginCourseErrorView() throws Exception {
        mockMvc.perform(get("/loginCourseError"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("loginCourseError"))
                .andExpect(model().attributeExists("display"))
                .andExpect(view().name("account/login/loginCoursePage"));
    }

    @Test
    public void whenLoginCourseProcessShouldAuthenticate() throws Exception {
        mockMvc.perform(post("/loginCourseProcess")
                .param("username", "sasfort")
                .param("password", "12345678"))
                .andExpect(handler().methodName("loginCourseProcess"));
        verify(accountService, times(1)).authenticateCourse("sasfort", "12345678");
    }
}