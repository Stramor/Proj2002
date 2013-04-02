package com.stramor.fishinterface;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.io.Closeables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFiles {
  static String readFile(String fileName) throws IOException {
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
    finally {
      Closeables.close(reader, true);
    }
    return stringBuilder.toString();
  }

  public static Double[] getSignal(String content) {
    Double[] sigMassive = new Double[10000];
    int i = 0;
    for (String s : Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().trimResults().split(content)) {
      double numb = Double.parseDouble(s);
      sigMassive[i]=numb;
      i++;
    }
    return sigMassive;
  }
}
