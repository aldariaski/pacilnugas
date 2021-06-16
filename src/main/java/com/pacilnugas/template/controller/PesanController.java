package com.pacilnugas.template.controller;

import com.pacilnugas.template.service.PesanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(path = "/pesan")
public class PesanController {
    @Autowired
    private PesanService pesanService;

    @GetMapping
    public String getDaftarPesan(Model model) {
        model.addAttribute("DaftarPesan", pesanService.getDaftarPesan());
        return "template/pesan";
    }

    /**
     * Calls pesanService to create a pesan.
     */
    @PostMapping("/new-pesan")
    public String createPesan(HttpServletRequest request) {
        String kategori = request.getParameter("kategori");
        String nama = request.getParameter("nama");
        String motivasi = request.getParameter("motivasi");
        String quotes = request.getParameter("quotes");
        pesanService.createPesan(kategori, nama, motivasi, quotes);
        return "redirect:/pesan";
    }

    @GetMapping("/create-pesan")
    public String createPesan(Model model) {
        return "template/formPesan";
    }
}
