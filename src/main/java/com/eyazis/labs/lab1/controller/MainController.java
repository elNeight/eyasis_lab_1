package com.eyazis.labs.lab1.controller;

import com.eyazis.labs.lab1.entity.dto.TableElement;
import com.eyazis.labs.lab1.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.Set;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService service;
    private boolean isIndexed;

    @GetMapping
    public String main() throws FileNotFoundException {
        if (!isIndexed) {
            service.index();
            isIndexed = true;
        }
        return "main";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam String text, Model model) {
        Set<TableElement> search = service.search(text);

        model.addAttribute("table", search);

        System.out.println(text);
        System.out.println(search);

        return "main";

    }


}
