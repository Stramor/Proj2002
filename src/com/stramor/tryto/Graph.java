package com.stramor.tryto;

import java.io.IOException;
import javax.swing.*;
import java.awt.*;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 */
public class Graph {

  public static void main(String[] args) throws IOException {
    String content = ReadingF.readFile("01.txt");
    JButton button = new JButton("Click");

    XYSeries series = new XYSeries("sin(a)");
    XYSeries series1 = new XYSeries("sin(a)+500");
    Double[] sigMassive = new Double[10000];
    int i = 0;
    for (String s : Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().trimResults().split(content)) {
      double numb = Double.parseDouble(s);
      sigMassive[i]=numb;
      //System.out.println(numb);
      series.add(i, numb);
      series1.add(i, sigMassive[i]+500);
      i++;
    }
    i=0;
    System.out.println(ProcessECG.findMax(sigMassive));
    XYSeriesCollection xydataset = new XYSeriesCollection();
    xydataset.addSeries(series);
    xydataset.addSeries(series1);
    JFreeChart chart = ChartFactory.createXYLineChart("", "i", "mV", xydataset, PlotOrientation.VERTICAL, true, true, true);
    JFrame frame = new JFrame("MinimalStaticChart");
    frame.getContentPane().add(BorderLayout.SOUTH,new ChartPanel(chart));
    button.setSize(100,100);
    frame.getContentPane().add(BorderLayout.NORTH,button);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(WindowUtils.getDimensionFromPercent(50, 70));
    WindowUtils.centerOnScreenAndSetVisible(frame);
  }
}
