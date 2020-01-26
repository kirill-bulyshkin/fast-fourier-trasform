package com.graph;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This program demonstrates how to draw XY line chart with XYDataset
 * using JFreechart library.
 *
 * @author www.codejava.net
 */
public class XYDrawer extends JFrame {
    private XYDataset xyDataset;

    public XYDrawer(XYDataset xyDataset) {
        super("Kirill Bulyshkin");
        this.xyDataset = xyDataset;
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel() {
        String chartTitle = "Fast Fourier Transform";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, xyDataset, PlotOrientation.VERTICAL, true, true, false);

        customizeChart(chart);

        // saves the chart as an image files
        File imageFile = new File("XYLineChart.png");
        int width = 1200;
        int height = 1080;

        try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return new ChartPanel(chart);
    }


    private void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // sets paint color for each series
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);

        // sets thickness for series (using strokes)
        renderer.setSeriesStroke(0, new BasicStroke(7.0f));
        renderer.setSeriesStroke(1, new BasicStroke(6.0f));

        // sets renderer for lines
        plot.setRenderer(renderer);

        // sets plot background
        plot.setBackgroundPaint(Color.DARK_GRAY);

        // sets paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        plot.getRangeAxis().setVisible(false);
        plot.getDomainAxis().setVisible(false);


    }
}