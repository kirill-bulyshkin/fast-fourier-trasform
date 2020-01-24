package com.graph;

import javax.swing.*;
import java.awt.*;

import static com.FFT.FFT;
import static java.lang.Math.PI;
import static java.lang.Math.cos;

class Success extends JFrame {
    public static Double[] ista = new Double[256];
    public static Double[] ista1 = new Double[256];

    public Success(){
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(1000,1000);

    }

    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        int stvig = 0;
        for (int i = 0; i < ista.length; i++) {
            ista[i] = 20 * cos(2* PI/256 * i + 20) + 40 * cos(2* PI/256 * i *20 + stvig);
        }

        stvig+= 1;
        if(stvig >= 360)
            stvig = 0;

        ista1 = FFT(ista, 256);
        for(int i  = 0; i < 254; ++i) {
            g.drawLine(i * 3, (ista[i].intValue() + 120 * 3), (i * 3) + 1, (ista[i + 1].intValue() + 120) * 3);
        }

        for(int i = 0; i < 100; ++i) {
            g.drawLine(i * 8+20, (0 - ista1[i].intValue() * 5 + 1200), i * 8+20, 1200);
        }

    }

    public static void main(String []args){
        Success s=new Success();
        s.setVisible(true);
    }
}