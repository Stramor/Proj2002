package com.stramor.tryto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.common.io.Closer;

public class ReadingF {
  static String readFile(String fileName) throws IOException {
    File file = new File(fileName);
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader reader;

    Closer closer = Closer.create();
    try {
      reader = new BufferedReader(new FileReader(file));
      closer.register(reader);
      String text;
      while ((text = reader.readLine()) != null) {
        stringBuilder.append(text).append(System.getProperty("line.separator"));
      }
    }
    catch (Throwable e) {
      throw closer.rethrow(e);
    }
    finally {
      closer.close();
    }
    return stringBuilder.toString();
  }
}
