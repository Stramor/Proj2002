package com.stramor.tryto;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.io.*;
import javax.swing.*;

public class Graph {

  /*void readTxtMe() {
    BufferedReader inbuf;
    int i=0;
    try {
      inbuf = new BufferedReader(new InputStreamReader(new FileInputStream("C://Other/chf05.txt")));
      while (i<100) {
        String s = inbuf.readLine();
        System.out.println(s);
      }

    } catch (IOException e) {}
  }*/

  public static void main(String[] args) {


    XYSeries series = new XYSeries("sin(a)");
    float otv = 0.0f;
    for (float i = 0; i < 4*Math.PI; i += 0.1) {
      series.add(i, Math.sin(i)*Math.cos(i*2));
    }

    XYDataset xyDataset = new XYSeriesCollection(series);
    JFreeChart chart = ChartFactory.createXYLineChart("y=sin(x)", "x","y", xyDataset, PlotOrientation.VERTICAL, false, true, true);
    JFrame frame = new JFrame("MinimalStaticChart");
    frame.getContentPane().add(new ChartPanel(chart));
    frame.setSize(500,500);
    frame.setVisible(true);

    File file = new File("C://Other/chf05.txt");
    StringBuilder contents = new StringBuilder();
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      String text = null
    }
  }

}
