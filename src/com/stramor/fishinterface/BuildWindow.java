package com.stramor.fishinterface;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.stramor.tryto.WindowUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class BuildWindow {

  public static void createButton(Container pane, String text, int width, int height) {
    JButton button = new JButton(text);
    //button.setVisible(true);
    button.setPreferredSize(new Dimension(width, height));
    pane.add(button);
  }

  public static JPanel addPanelOneToPane() {
    JPanel panelOne = new JPanel();
    panelOne.setVisible(true);
    panelOne.setBackground(new Color(70, 110, 130).brighter());
    createButton(panelOne, "First",100,100);
    createButton(panelOne, "Second",150,150);
    panelOne.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    panelOne.setAlignmentX(Component.CENTER_ALIGNMENT);

    return panelOne;
  }
  public static JPanel addPanelTwoToPane(JFreeChart chart) {
    JPanel panelTwo = new JPanel();
    ChartPanel chPanel = new ChartPanel(chart);
    chPanel.setPreferredSize(WindowUtils.getDimensionFromPercent(45, 45));
    panelTwo.add(chPanel);
    panelTwo.setBackground(new Color(255,255,255));
    panelTwo.setVisible(true);

    return panelTwo;
  }

  public static XYSeries bindSeries(Double[] massiv, String text, int maxInc){
    XYSeries series = new XYSeries(text);
    int i = 0;
    for (; i < maxInc; i++) {
      series.add(i, massiv[i]);
    }
    return series;
  }

  public static void Drawing(XYSeries seriesLoc){
    XYSeriesCollection xydataset = new XYSeriesCollection();
    xydataset.addSeries(seriesLoc);
    JFreeChart chart = ChartFactory.createXYLineChart("", "i", "mV", xydataset, PlotOrientation.VERTICAL, true, true, true);
    JFrame frame = new JFrame("MinimalStaticChart");
    frame.getContentPane().add(addPanelOneToPane());
    frame.getContentPane().add(addPanelTwoToPane(chart));
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(WindowUtils.getDimensionFromPercent(50, 70));
    frame.setResizable(false);
    WindowUtils.centerOnScreenAndSetVisible(frame);
  }

}
