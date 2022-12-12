package com.eyazis.labs.lab1.controller;

import com.eyazis.labs.lab1.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService service;

    @GetMapping
    public String metrics() {
        return "metrics";
    }

    @GetMapping("/calculations")
    public String calculations(
            @RequestParam Double a,
            @RequestParam Double b,
            @RequestParam Double c,
            @RequestParam Double d,
             Model model
    ) {
        List<Double> calculations = service.getCalculations(a, b, c, d);
        model.addAttribute("calculations", calculations);
        System.out.println(calculations);
        return "metrics";
    }

    @GetMapping("/graphics")
    public String graphics(
            @RequestParam Integer allDocs,
            @RequestParam String relDocs,
            Model model
    ) {
        var graphicData = service.getGraphicData(allDocs, relDocs);
        model.addAttribute("graphics", graphicData);
        return "metrics";
    }

}
