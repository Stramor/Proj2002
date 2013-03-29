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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import sun.awt.VariableGridLayout;

/**
 *
 */
public class GraphNewTry {

  public static void main(String[] args) throws IOException {
    String content = ReadingF.readFile("01.txt");

    // Initialization
    JButton button = new JButton("Click");
    button.setVisible(true);
    button.setLocation(0,0);
    button.setSize(200,100);

    double heightWindow = 0;
    //button.addActionListener(new ActionListener());
    //

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
    FlowLayout experimentLayout = new FlowLayout();

    JPanel panelOne = new JPanel();
    panelOne.setVisible(true);
    panelOne.add(button);
    //panelOne.setSize(WindowUtils.getDimensionFromPercent(4,2));

    JPanel panelTwo = new JPanel();
    panelTwo.add(new ChartPanel(chart));
    panelTwo.setVisible(true);

    frame.getContentPane().add(panelOne);
    frame.getContentPane().add(panelTwo);
    frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(WindowUtils.getDimensionFromPercent(50, 70));
    WindowUtils.centerOnScreenAndSetVisible(frame);
    frame.pack();


    System.out.println(frame.getSize());
  }
}
