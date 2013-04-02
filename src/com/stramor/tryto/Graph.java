package com.stramor.tryto;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.FloatDimension;
import sun.awt.VariableGridLayout;

/**
 *
 */
public class Graph {

  public static void main(String[] args) throws IOException {
    String content = ReadingF.readFile("01.txt");

    // Initialization
    JButton button = new JButton("Click");
    button.setVisible(true);
    button.setPreferredSize(new Dimension(150,50));

    JButton startSearch = new JButton("Start Search");
    startSearch.setVisible(true);
    startSearch.setPreferredSize(new Dimension(150,50));

    XYSeries series = new XYSeries("sin(a)");
    XYSeries series1 = new XYSeries("sin(a)+500");
    Double[] sigMassive = new Double[10000];
    int i = 0;
    for (String s : Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().trimResults().split(content)) {
      double numb = Double.parseDouble(s);
      sigMassive[i]=numb;
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

    JPanel panelOne = new JPanel();
    panelOne.setVisible(true);
    panelOne.setBackground(new Color(60, 80, 90).brighter());
    panelOne.add(button);
    panelOne.add(startSearch);
    panelOne.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    panelOne.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel panelTwo = new JPanel();
    ChartPanel chPanel = new ChartPanel(chart);
    chPanel.setPreferredSize(WindowUtils.getDimensionFromPercent(45, 50));
    panelTwo.add(chPanel);
    panelTwo.setBackground(new Color(255,255,255));
    panelTwo.setVisible(true);

    frame.getContentPane().add(panelOne);
    frame.getContentPane().add(panelTwo);
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(WindowUtils.getDimensionFromPercent(50, 70));
    frame.setResizable(false);
    WindowUtils.centerOnScreenAndSetVisible(frame);

    System.out.println(frame.getSize());
  }
}
