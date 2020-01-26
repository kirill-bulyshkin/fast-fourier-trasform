package com;

import com.graph.XYDrawer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

import static com.FFT.FFT;
import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Runner {
    public static Double[] ista = new Double[256];
    public static Double[] ista1 = new Double[256];

    public static void main(String[] args) {
        int stvig = 0;

        for (int i = 0; i < ista.length; i++) {
            ista[i] = 20 * cos(2 * PI / 256 * i + 20)+ 40 * cos(2 * PI / 256 * i * 20 + stvig);
        }


        stvig += 1;
        if (stvig >= 360)
            stvig = 0;

        ista1 = FFT(ista, 256);

        SwingUtilities.invokeLater(() -> {
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series1 = new XYSeries("Входной сигнал");
            XYSeries series = new XYSeries("Быстрое преобразование Фурье");
            dataset.addSeries(series1);
            dataset.addSeries(series);
            for (int i = 0; i < 254; ++i) {
                series1.add((i * 3), (float) ((ista[i] + 120) * 3) + 800);
                series1.add((float) (i * 3) + (float) 1, (float) (ista[i + 1] + (double) 120) * (float) 3 + 800);
            }

            for (int i = 0; i < 100; ++i) {
                series.add((float) i * (float) 8 + 20, (float) ((double) 0 + ista1[i] * (double) 5 + (double) 1200) - 800);
                series.add((float) 8 + 20, 1200.0F - 800);
            }
            XYDrawer xyDrawer = new XYDrawer(dataset);
            xyDrawer.setVisible(true);
        });
    }

}
