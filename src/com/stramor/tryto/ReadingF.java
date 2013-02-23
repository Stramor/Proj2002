package com.stramor.tryto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.common.io.Closeables;

public class ReadingF {
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
}
