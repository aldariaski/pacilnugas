package com.pacilnugas.Template.controller;

import com.pacilnugas.Template.service.PesanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PesanController {
    @Autowired
    private PesanService pesanService;

    @GetMapping("/daftar-pesan")
    public String getPesan(Model model) {
        model.addAttribute("DaftarPesan", pesanService.getPesan());
        return "template/pesan";
    }

    @PostMapping("/new-pesan")
    public String createPesan(HttpServletRequest request) {
        String kategori = request.getParameter("kategori");
        String nama = request.getParameter("nama");
        String motivasi = request.getParameter("motivasi");
        String quotes = request.getParameter("quotes");
        pesanService.createPesan(kategori, nama, motivasi, quotes);
        return "redirect:/daftar-pesan";
    }

    @GetMapping("/create-pesan")
    public String createPesan(Model model) {
        return "template/formPesan";
    }

}
