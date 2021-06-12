package com.pacilnugas.personalized.controller;

import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.service.MatkulService;
import com.pacilnugas.authentication.service.UserService;
import com.pacilnugas.personalized.core.Task;
import com.pacilnugas.personalized.service.ObserverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalizedController {

    @Autowired
    private ObserverServiceImpl observerService;

    @Autowired
    private UserService userService;

    @Autowired
    private MatkulService matkulService;

    @GetMapping(path = "/personal")
    public String personal(@RequestParam("username") String username, Model model) {
        List<Matkul> listMatkulPersonal = userService.getUserByUsername(username).getPersonalizedMatkul();
        List<Matkul> listMatkulTotal = matkulService.getAllMatkulObject();
        model.addAttribute("listMatkulPersonal", listMatkulPersonal);
        model.addAttribute("listMatkulTotal", listMatkulTotal);
        return "personalized/personalPage";
    }

    @GetMapping("/personalFilter")
    public String personalFilter(@RequestParam("listMatkul") List<String> listMatkul){
        List<Matkul> checkedMatkul = new ArrayList<>();
        for (String stringMatkul: listMatkul) {
            try {
                String result = java.net.URLDecoder.decode(stringMatkul, StandardCharsets.UTF_8.name());
                Matkul matkul = matkulService.getMatkulByNama(result);
                checkedMatkul.add(matkul);
            } catch (UnsupportedEncodingException e) {
            }
        }
        userService.saveMatkul("sasfort", checkedMatkul);
        return "redirect:/personal?username=sasfort";
    }
}
