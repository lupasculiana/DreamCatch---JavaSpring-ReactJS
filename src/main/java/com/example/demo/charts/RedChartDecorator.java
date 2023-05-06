package com.example.demo.charts;

import com.example.demo.dream.Dream;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class RedChartDecorator extends ChartDecorator {

    public RedChartDecorator(Chart decoratedChart) {
        super(decoratedChart);
    }

    @Override
    public JFreeChart generateChart(List<Dream> dreams) throws IOException {
        JFreeChart chart = decoratedChart.generateChart(dreams);
        setRedColor(chart);
        return chart;
    }

    private void setRedColor(JFreeChart chart){
        double value = chart.getXYPlot().getDataset().getXValue(2,2);
        XYPlot plot = chart.getXYPlot();
        XYItemRenderer render = plot.getRenderer();
        if (value < 2.0) {
            render.setSeriesPaint(0, new Color(233, 27, 239));
        } else if (value >= 2.0) {
            render.setSeriesPaint(0, new Color(29, 167, 171));
        }
        System.out.println("Line Color: Red");

    }
}