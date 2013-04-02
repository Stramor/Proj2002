package com.stramor.fishinterface;

public class MaxOfSignal {
  public static double findMax(Double[] matrix){
    int i=0;
    double max = 0;
    while (matrix[i] != null){
      if (matrix[i]>max) {max = matrix[i];}
      i++;
    }

    return max;
  }
  public static int[] findRpeaks(Double[] matrix){
    int[] rpeaks = new int[matrix.length];
    int i = 0;

    return rpeaks;
  }

}
