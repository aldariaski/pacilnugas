package com.pacilnugas.pesan.controller;

import com.pacilnugas.Template.controller.PesanController;
import com.pacilnugas.Template.service.PesanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PesanController.class)
public class PesanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PesanService pesanService;

    @Test
    public void whenPesanUrlShouldCallPesanService() throws Exception{
        mockMvc.perform(get("/pesan"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getDaftarPesan"))
                .andExpect(model().attributeExists("DaftarPesan"))
                .andExpect(view().name("template/pesan"));
        verify(pesanService, times(1)).getDaftarPesan();
    }

    @Test
    public void whenNewPesanShouldCallNewPesan() throws Exception {
        mockMvc.perform(post("/pesan/new-pesan")
                .param("kategori", "Ujian")
                .param("nama", "Maung")
                .param("motivasi", "Semangat semua!")
                .param("quotes", "Yes"))
                .andExpect(handler().methodName("createPesan"));

        verify(pesanService, times(1)).createPesan("Ujian", "Maung", "Semangat semua!", "Yes");
    }

    @Test
    public void whenCreatePesanUrlShouldCallPesanService() throws Exception{
        mockMvc.perform(get("/pesan/create-pesan"))
                .andExpect(status().isOk())
                .andExpect(view().name("template/formPesan"));
        //verify(pesanService, times(1)).getDaftarPesan();
    }


}
