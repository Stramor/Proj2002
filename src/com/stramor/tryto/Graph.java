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
  public static void main(String[] args) {
    File file = new File("011.txt");
    StringBuilder contents = new StringBuilder();
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      String text = null;
      while ((text = reader.readLine())!=null) {
        contents.append(text).append(System.getProperty("line.separator"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try {
        if (reader != null) {reader.close();}
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String[] dataStr = contents.toString().split("\\s+");
    System.out.println(contents.toString());

    double numb = 0;

    XYSeries series = new XYSeries("sin(a)");
    for (int i = 0; i < dataStr.length; i++) {
      numb = Double.parseDouble(dataStr[i]);
      System.out.println(numb);
      series.add(i, numb);
      //series.add(i, Math.cos(i));
    }
    System.out.println(dataStr.length);

    XYDataset xyDataset = new XYSeriesCollection(series);
    JFreeChart chart = ChartFactory.createXYLineChart("", "i","mV", xyDataset, PlotOrientation.VERTICAL, false, true, true);
    JFrame frame = new JFrame("MinimalStaticChart");
    frame.getContentPane().add(new ChartPanel(chart));
    frame.setSize(800,500);
    frame.setVisible(true);
  }

}
