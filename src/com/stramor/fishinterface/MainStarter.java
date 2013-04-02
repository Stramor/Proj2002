package com.stramor.fishinterface;

import com.stramor.tryto.ReadingF;
import org.jfree.data.xy.XYSeries;

import java.io.IOException;

public class MainStarter {

  public static void main(String[] args) throws IOException {
    int maximumOfSignal = 0;
    Double[] inputSignal = new Double[10000];
    String nameSig = "WeAreTheChampions";
    XYSeries series = new XYSeries(nameSig);

    String content = ReadingFiles.readFile("01.txt");
    for (int i=0;ReadingFiles.getSignal(content)[i]!=null; i++) {
      inputSignal[i] = ReadingFiles.getSignal(content)[i];
      maximumOfSignal=i;
    }
    series = BuildWindow.bindSeries(inputSignal,nameSig, maximumOfSignal);
    BuildWindow.Drawing(series);
    System.out.println(MaxOfSignal.findMax(inputSignal));
  }

}
