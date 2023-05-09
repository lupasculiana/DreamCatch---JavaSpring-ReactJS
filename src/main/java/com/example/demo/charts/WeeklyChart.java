package com.example.demo.charts;

import com.example.demo.dream.Dream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WeeklyChart implements Chart {

    private String week;

    public WeeklyChart(String week){
        this.week = week;
    }

    @Override
    public JFreeChart generateChart(List<Dream> dreams) throws IOException {

        DefaultCategoryDataset energyDataset = new DefaultCategoryDataset();
        for (Dream current : dreams) {
            if(current.getWeek().equals(week)){
                energyDataset.addValue(current.getEnergyLevel(), "Energy Level", current.getWeek());
            }
        }
        CategoryPlot energyPlot = new CategoryPlot();
        energyPlot.setDataset(0, energyDataset);
        BarRenderer energyRenderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                double value = energyDataset.getValue(row, column).doubleValue();
                if (value < 3) {
                    return Color.GREEN;
                } else {
                    return Color.RED;
                }
            }
        };

        energyPlot.setRenderer(0, energyRenderer);
        energyPlot.setRangeAxis(0, new NumberAxis("Energy"));



        DefaultCategoryDataset stressDataset = new DefaultCategoryDataset();
        for (Dream current : dreams) {
            if(current.getWeek().equals(week)){
                stressDataset.addValue(current.getStress(), "Stress Level", current.getWeek());
            }
        }
        CategoryPlot stressPlot = new CategoryPlot();
        stressPlot.setDataset(0, stressDataset);
        BarRenderer stressRenderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int col) {
                double value = stressDataset.getValue(row, col).doubleValue();
                if (value < 2) {
                    return Color.RED;
                } else {
                    return Color.GREEN;
                }
            }
        };
        stressPlot.setRenderer(0, stressRenderer);
        stressPlot.setRangeAxis(0, new NumberAxis("Stress"));



        DefaultCategoryDataset durationDataset = new DefaultCategoryDataset();
        for (Dream current : dreams) {
            if(current.getWeek().equals(week)){
                durationDataset.addValue(current.getDuration(), "Duration Level", current.getWeek());
            }
        }
        CategoryPlot durationPlot = new CategoryPlot();
        durationPlot.setDataset(0, durationDataset);
        BarRenderer durationRenderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int col) {
                double value = durationDataset.getValue(row, col).doubleValue();
                if (value < 4) {
                    return Color.RED;
                } else {
                    return Color.GREEN;
                }
            }
        };
        durationPlot.setRenderer(0, durationRenderer);
        durationPlot.setRangeAxis(0, new NumberAxis("Duration"));

        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot();
        combinedPlot.add(energyPlot);
        combinedPlot.add(stressPlot);
        combinedPlot.add(durationPlot);
        combinedPlot.setGap(20.0);

        JFreeChart chart = new JFreeChart("Weekly Chart", JFreeChart.DEFAULT_TITLE_FONT, combinedPlot, true);
        BufferedImage image = chart.createBufferedImage(800, 600);
        File output = new File("chart.png");
        ImageIO.write(image, "png", output);

        return chart;

    }
}