package com;

import com.graph.XYDrawer;
import com.transform.fourier.Complex;
import com.transform.fourier.FastFourierTransform;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {
        double[] input = {1, 1, 1, 1, 0, 0, 0, 0};

        Complex[] cinput = new Complex[input.length];
        for (int i = 0; i < input.length; i++)
            cinput[i] = new Complex(input[i], 0.0);
        FastFourierTransform.fft(cinput);
        System.out.println("Results: ");
        for (Complex complex1 : cinput) {
            System.out.println(complex1);
        }

        SwingUtilities.invokeLater(() -> {
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series1 = new XYSeries("Chart");
            dataset.addSeries(series1);
            for (Complex complex : cinput) {
                series1.add(complex.im, complex.re);
            }
            XYDrawer xyDrawer = new XYDrawer(dataset);
            xyDrawer.setVisible(true);
        });
    }


}
