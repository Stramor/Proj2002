package com.stramor.tryto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadingF {
  static String readFile(String fileName) {
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
