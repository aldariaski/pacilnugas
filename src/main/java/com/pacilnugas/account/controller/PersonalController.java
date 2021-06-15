package com.pacilnugas.account.controller;

import com.pacilnugas.account.service.AccountService;
import com.pacilnugas.activities.service.MatkulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("listMatkulPersonal", accountService.getAccountByUsername(accountService
                .decode(username)).getPersonalizedMatkul());
        model.addAttribute("listMatkulTotal", matkulService.getAllMatkulObject());
        model.addAttribute("display", "Hai, " + username + "!");
        model.addAttribute("username", username);
        return "account/personal/personalPage";
    }

    /**
     * Filtering chosen courses on personal page.
     */
    @GetMapping("/personalFilter")
    public String personalFilter(@RequestParam("listMatkul") List<String> listMatkul,
                                 @RequestParam("username") String username) {
        accountService.decodeCheckedMatkul(username, listMatkul);
        username = accountService.encode(username);
        return "redirect:/personal?username=" + username;
    }

}
