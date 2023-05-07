package com.example.demo.charts;

import com.example.demo.dream.Dream;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;

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
       CategoryDataset energyValue = chart.getCategoryPlot().getDataset(0);
        CategoryPlot energyPlot = new CategoryPlot();
        energyPlot.setDataset(0, energyValue);
        BarRenderer energyRenderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int col) {
                double value = energyValue.getValue(row, col).doubleValue();
                if (value < 3) {
                    return Color.GREEN; // set color to green if value is 4 or greater
                } else {
                    return Color.RED; // set color to red otherwise
                }
            }
        };
        energyPlot.setRenderer(0, energyRenderer);
        energyPlot.setRangeAxis(0, new NumberAxis("Energy"));

        CategoryDataset stressValue = chart.getCategoryPlot().getDataset(1);
        CategoryPlot stressPlot = new CategoryPlot();
        energyPlot.setDataset(0, stressValue);
        BarRenderer stressRenderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int col) {
                double value = stressValue.getValue(row, col).doubleValue();
                if (value < 2) {
                    return Color.GREEN; // set color to green if value is 4 or greater
                } else {
                    return Color.RED; // set color to red otherwise
                }
            }
        };
        stressPlot.setRenderer(0, stressRenderer);
        stressPlot.setRangeAxis(0, new NumberAxis("Stress"));

        CategoryDataset durationValue = chart.getCategoryPlot().getDataset(2);
        CategoryPlot durationPlot = new CategoryPlot();
        energyPlot.setDataset(0, durationValue);
        BarRenderer durationRenderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int col) {
                double value = durationValue.getValue(row, col).doubleValue();
                if (value >= 3) {
                    return Color.GREEN; // set color to green if value is 4 or greater
                } else {
                    return Color.RED; // set color to red otherwise
                }
            }
        };
        durationPlot.setRenderer(0, durationRenderer);
        durationPlot.setRangeAxis(0, new NumberAxis("Duration"));

    }
}