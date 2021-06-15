package com.pacilnugas.account.controller;

import com.pacilnugas.account.security.URLCoder;
import com.pacilnugas.account.service.AccountService;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.service.MatkulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private MatkulService matkulService;

    /**
     * Accessing personal page.
     */
    @GetMapping(path = "/personal")
    public String personal(@RequestParam("username") String username, Model model) {
        username = accountService.decode(username);
        model.addAttribute("listMatkulPersonal", accountService.getAccountByUsername(username)
                .getPersonalizedMatkul());
        model.addAttribute("listMatkulTotal", matkulService.getAllMatkulObject());
        model.addAttribute("display", "Hai, " + username + "!");
        username = accountService.encode(username);
        model.addAttribute("username", username);
        return "account/personal/personalPage";
    }

    /**
     * Filtering chosen courses on personal page.
     */
    @GetMapping("/personalFilter")
    public String personalFilter(@RequestParam("listMatkul") List<String> listMatkul,
                                 @RequestParam("username") String username) {
        List<Matkul> checkedMatkul = new ArrayList<>();
        for (String stringMatkul : listMatkul) {
            try {
                String result = URLDecoder.decode(stringMatkul, StandardCharsets.UTF_8.name());
                Matkul matkul = matkulService.getMatkulByNama(result);
                checkedMatkul.add(matkul);
            } catch (UnsupportedEncodingException e) {
                return "redirect:/";
            }
        }
        try {
            username = java.net.URLDecoder.decode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return "redirect:/";
        }
        accountService.saveMatkul(username, checkedMatkul);
        try {
            username = java.net.URLEncoder.encode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return "redirect:/";
        }
        return "redirect:/personal?username=" + username;
    }
}
