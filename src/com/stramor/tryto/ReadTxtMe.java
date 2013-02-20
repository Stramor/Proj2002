package com.stramor.tryto;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class ReadTxtMe {
  /*File filetxt = new File("C:\Other\chf05.txt");
  int i=0;
  try {
    Scanner sc1 = new Scanner(filetxt);

    while (i<3) {
      String currentLine = sc1.nextLine();
      System.out.println(currentLine);
    }
  }
  catch (FileNotFoundException e) {
    e.printStackTrace();
  }*/
   ReadTxtMe() {
    BufferedReader inbuf;
    int i=0;
    try {
      inbuf = new BufferedReader(new InputStreamReader(new FileInputStream("C://Other/chf05.txt")));
      while (i<100) {
        String s = inbuf.readLine();
        System.out.println(s);
      }

    } catch (IOException e) {}

  }
}
