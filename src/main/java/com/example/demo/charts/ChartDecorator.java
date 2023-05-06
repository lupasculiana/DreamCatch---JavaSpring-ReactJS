package com.example.demo.charts;

import com.example.demo.dream.Dream;
import org.jfree.chart.JFreeChart;

import java.io.IOException;
import java.util.List;

public abstract class ChartDecorator implements Chart {
    protected Chart decoratedChart;

    public ChartDecorator(Chart decoratedChart){
        this.decoratedChart = decoratedChart;
    }

    public JFreeChart generateChart(List<Dream> dreams) throws IOException {
        JFreeChart chart = decoratedChart.generateChart(dreams);
        return chart;
    }
}