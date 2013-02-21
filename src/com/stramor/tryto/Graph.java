package com.stramor.tryto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {

  public static void main(String[] args) {
    String content = readFile("01.txt");


    String[] dataStr = content.split("\\s+");
    System.out.println(content);

    double numb;

    XYSeries series = new XYSeries("sin(a)");
    for (int i = 0; i < dataStr.length; i++) {
      if (dataStr[i] != null && !dataStr[i].isEmpty()) {
        numb = Double.parseDouble(dataStr[i]);
        System.out.println(numb);
        series.add(i, numb);
        //series.add(i, Math.cos(i));
      }
    }
    System.out.println(dataStr.length);

    XYDataset xyDataset = new XYSeriesCollection(series);
    JFreeChart chart = ChartFactory.createXYLineChart("", "i", "mV", xyDataset, PlotOrientation.VERTICAL, false, true, true);
    JFrame frame = new JFrame("MinimalStaticChart");
    frame.getContentPane().add(new ChartPanel(chart));
    frame.setSize(800, 500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private static String readFile(String fileName) {
    File file = new File(fileName);
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      String text;
      while ((text = reader.readLine()) != null) {
        stringBuilder.append(text).append(System.getProperty("line.separator"));
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (reader != null) {
          reader.close();
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return stringBuilder.toString();
  }
}
