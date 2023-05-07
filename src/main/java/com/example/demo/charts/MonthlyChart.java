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

public class MonthlyChart implements Chart {

    private String month;

    public MonthlyChart(String month){
        this.month = month;
    }

    @Override
    public JFreeChart generateChart(List<Dream> dreams) throws IOException {

        DefaultCategoryDataset energyDataset = new DefaultCategoryDataset();
        for (Dream current : dreams) {
            if(current.getMonth().equals(month)){
                energyDataset.addValue(current.getEnergyLevel(), "Energy Level", current.getMonth());
            }
        }
        CategoryPlot energyPlot = new CategoryPlot();
        energyPlot.setDataset(0, energyDataset);
        energyPlot.setRangeAxis(0, new NumberAxis("Energy"));


        DefaultCategoryDataset stressDataset = new DefaultCategoryDataset();
        for (Dream current : dreams) {
            if(current.getMonth().equals(month)){
                stressDataset.addValue(current.getStress(), "Stress Level", current.getMonth());
            }
        }
        CategoryPlot stressPlot = new CategoryPlot();
        stressPlot.setDataset(0, stressDataset);
        stressPlot.setRangeAxis(0, new NumberAxis("Stress"));


        DefaultCategoryDataset durationDataset = new DefaultCategoryDataset();
        for (Dream current : dreams) {
            if(current.getMonth().equals(month)){
                durationDataset.addValue(current.getDuration(), "Duration Level", current.getMonth());
            }
        }
        CategoryPlot durationPlot = new CategoryPlot();
        durationPlot.setDataset(0, durationDataset);
        durationPlot.setRangeAxis(0, new NumberAxis("Duration"));

        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot();
        combinedPlot.add(energyPlot);
        combinedPlot.add(stressPlot);
        combinedPlot.add(durationPlot);
        combinedPlot.setGap(20.0);

        JFreeChart chart = new JFreeChart("Monthly Chart", JFreeChart.DEFAULT_TITLE_FONT, combinedPlot, true);
        BufferedImage image = chart.createBufferedImage(800, 600);
        File output = new File("chart.png");
        ImageIO.write(image, "png", output);

        return chart;
    }
}