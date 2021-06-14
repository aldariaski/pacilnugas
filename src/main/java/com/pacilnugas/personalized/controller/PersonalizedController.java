package com.pacilnugas.personalized.controller;

import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.service.MatkulService;
import com.pacilnugas.authentication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalizedController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private MatkulService matkulService;

    @GetMapping(path = "/personal")
    public String personal(@RequestParam("username") String username, Model model) {
        try {
            username = java.net.URLDecoder.decode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        List<Matkul> listMatkulPersonal = accountService.getAccountByUsername(username).getPersonalizedMatkul();
        List<Matkul> listMatkulTotal = matkulService.getAllMatkulObject();
        try {
            username = URLEncoder.encode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        model.addAttribute("username", username);
        model.addAttribute("listMatkulPersonal", listMatkulPersonal);
        model.addAttribute("listMatkulTotal", listMatkulTotal);
        return "personalized/personalPage";
    }

    @GetMapping("/personalFilter")
    public String personalFilter(@RequestParam("listMatkul") List<String> listMatkul,
                                 @RequestParam("username") String username){
        List<Matkul> checkedMatkul = new ArrayList<>();
        for (String stringMatkul: listMatkul) {
            try {
                String result = URLDecoder.decode(stringMatkul, StandardCharsets.UTF_8.name());
                Matkul matkul = matkulService.getMatkulByNama(result);
                checkedMatkul.add(matkul);
            } catch (UnsupportedEncodingException e) {
            }
        }
        try {
            username = java.net.URLDecoder.decode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        accountService.saveMatkul(username, checkedMatkul);
        try {
            username = java.net.URLEncoder.encode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        return "redirect:/personal?username=" + username;
    }
}
