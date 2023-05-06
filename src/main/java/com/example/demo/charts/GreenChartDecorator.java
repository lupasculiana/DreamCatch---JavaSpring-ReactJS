package com.example.demo.charts;

import com.example.demo.dream.Dream;
import org.jfree.chart.JFreeChart;

import java.io.IOException;
import java.util.List;

public class GreenChartDecorator extends ChartDecorator {

    public GreenChartDecorator(Chart decoratedChart) {
        super(decoratedChart);
    }

    @Override
    public JFreeChart generateChart(List<Dream> dreams) throws IOException {
        JFreeChart chart = decoratedChart.generateChart(dreams);
        setGreenColor(decoratedChart);
        return chart;
    }

    private void setGreenColor(Chart decoratedChart){
        System.out.println("Line Color: Green");
    }


}