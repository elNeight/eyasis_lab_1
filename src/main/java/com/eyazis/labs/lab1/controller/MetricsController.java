package com.eyazis.labs.lab1.controller;

import com.eyazis.labs.lab1.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

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

        Thread thread = new Thread(() -> {
            XYLineChart chart = new XYLineChart(graphicData);
            chart.setVisible(true);
            chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
        thread.setDaemon(true);
        thread.start();

        return "metrics";
    }

    private static class XYLineChart extends JFrame {

        private final List<Map.Entry<Double, Double>> coordinates;

        public XYLineChart(List<Map.Entry<Double, Double>> coordinates) {
            super("XY Line Chart Example with JFreechart");

            this.coordinates = coordinates;

            JPanel chartPanel = createChartPanel();
            add(chartPanel, BorderLayout.CENTER);

            setSize(640, 480);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        }

        private JPanel createChartPanel() {
            String chartTitle = "Chart";
            String xAxisLabel = "Recall";
            String yAxisLabel = "Precision";

            XYDataset dataset = createDataset();

            JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                    xAxisLabel, yAxisLabel, dataset);

            return new ChartPanel(chart);
        }

        private XYDataset createDataset() {
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series = new XYSeries("Line");

            coordinates.forEach(coordinate -> {
                series.add(coordinate.getKey(), coordinate.getValue());
            });

            dataset.addSeries(series);

            return dataset;
        }
    }


}
