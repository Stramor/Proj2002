package com.stramor.tryto;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {

  public static void main(String[] args) throws IOException {
    String content = ReadingF.readFile("01.txt");

    XYSeries series = new XYSeries("sin(a)");
    int i = 0;
    for (String s : Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().trimResults().split(content)) {
      double numb = Double.parseDouble(s);
      System.out.println(numb);
      series.add(i, numb);
      //series.add(i, Math.cos(i));
      i++;
    }

    XYDataset xyDataset = new XYSeriesCollection(series);
    JFreeChart chart = ChartFactory.createXYLineChart("", "i", "mV", xyDataset, PlotOrientation.VERTICAL, false, true, true);
    JFrame frame = new JFrame("MinimalStaticChart");
    frame.getContentPane().add(new ChartPanel(chart));
    frame.setSize(800, 500);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    WindowUtils.centerOnScreenAndSetVisible(frame);
  }
}
