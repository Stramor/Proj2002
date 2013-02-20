package com.stramor.tryto;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class ReadTxtMe {
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
