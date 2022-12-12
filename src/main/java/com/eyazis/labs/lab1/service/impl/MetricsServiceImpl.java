package com.eyazis.labs.lab1.service.impl;

import com.eyazis.labs.lab1.service.MetricsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MetricsServiceImpl implements MetricsService {
    @Override
    public List<Double> getCalculations(Double a, Double b, Double c, Double d) {

        double recall = a / (a + c);
        double precision = a / (a + b);
        double accuracy = (a + d) / (a + b + c + d);
        double error = (b + c) / (a + b + c + d);
        double fMeasure = 2 / ((1/precision)/(1/recall));

        return List.of(recall, precision, accuracy, error, fMeasure);
    }

    @Override
    public List<Map.Entry<Double, Double>> getGraphicData(Integer allDocs, String relDocs) {

        List<Map.Entry<Double, Double>> data = new ArrayList<>();

        String[] separatedRelevantNumbersLocal = relDocs.split(" ");
        List<Integer> relevantNumbersList = new ArrayList<>();
        for (String s : separatedRelevantNumbersLocal) {
            relevantNumbersList.add(Integer.parseInt(s));
        }

        int aL = 0;
        int bL = 0;
        int cL = 0;
        for (int i = 1; i <= allDocs; i++) {
            int buffI = i;
            cL++;
            if (relevantNumbersList.stream().anyMatch(integer -> integer == buffI)){
                aL++;
                double pre = (double) aL/(aL + bL);
                double rec = (double) aL/relevantNumbersList.size();
                data.add(Map.entry(rec, pre));
                System.out.println(rec + "  " + pre);

            }else {
                bL++;
                double pre = (double) aL/(aL + bL);
                double rec = (double) aL/relevantNumbersList.size();
                data.add(Map.entry(rec, pre));
                System.out.println(rec + "  " + pre);
            }
        }

        return data;
    }


}
