package com.eyazis.labs.lab1.service;

import java.util.List;
import java.util.Map;

public interface MetricsService {

    List<Double> getCalculations(Double a, Double b, Double c, Double d);

    List<Map.Entry<Double, Double>> getGraphicData(Integer allDocs, String relDocs);

}
