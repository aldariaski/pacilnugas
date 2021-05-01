package com.pacilnugas.Template.controller;

import com.pacilnugas.Template.core.Pesan;
import com.pacilnugas.Template.service.PesanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/pesan")
public class PesanController {
    @Autowired
    private PesanService pesanService;

    @GetMapping
    public String getDaftarPesan(Model model) {
        model.addAttribute("DaftarPesan", pesanService.getDaftarPesan());
        return "template/pesan"; }

    @PostMapping("/new-pesan")
    public String createPesan(HttpServletRequest request) {
        String kategori = request.getParameter("kategori");
        String nama = request.getParameter("nama");
        String motivasi = request.getParameter("motivasi");
        String quotes = request.getParameter("quotes");
        pesanService.createPesan(kategori, nama, motivasi, quotes);
        return "redirect:/pesan"; }

    @GetMapping("/create-pesan")
    public String createPesan(Model model) {
        return "template/formPesan"; }

//    //REST
//    @GetMapping(value = "")
//    public List<Pesan> getPesan() {
//        return pesanService.getListPesan(); }

    @PostMapping(value = "")
    public Pesan createPesan(@RequestBody Map<String,String> json) {
        String kategori = json.get("kategori");
        String nama = json.get("nama");
        String motivasi = json.get("motivasi");
        String quotes = json.get("quotes");
        return pesanService.createPesan(kategori, nama, motivasi, quotes); }

}
