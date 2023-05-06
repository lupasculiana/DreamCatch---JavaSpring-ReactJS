package com.example.demo.charts;

import com.example.demo.dream.Dream;
import org.jfree.chart.JFreeChart;

import java.io.IOException;
import java.util.List;

public interface Chart {
    JFreeChart generateChart(List<Dream> chartDataList) throws IOException;
}
