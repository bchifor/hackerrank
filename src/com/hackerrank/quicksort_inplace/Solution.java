package com.hackerrank.quicksort_inplace;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner in = new Scanner(System.in);
    int size = in.nextInt();
    int[] values = new int[size];
    for(int i=0; i<size; i++){
      values[i] = in.nextInt();
    }
    quicksort( 0, size-1, values);
  }

  private static void quicksort( int start, int end, int[] values){
    if ( start < end){
      int pivot = partition( start, end, values);

      print(values);
      quicksort( start, pivot - 1, values);
      quicksort( pivot + 1, end, values);
    }
  }

  private static void print(int[] values){
    for( int value: values){
      System.out.print( value + " ");
    }
    System.out.println();
  }

  private static int partition( int start, int end, int[] values){

    int pivotIndex = choosePivot( start, end, values);
    int pivotVal = values[pivotIndex];

    //put pivot at the end of the array
    if ( pivotIndex != end){
      swap(pivotIndex, end, values);
    }

    int finalPivotIndex = start;
    for (int i=start; i<end; i++){
      if ( values[i]<pivotVal){
        swap( i, finalPivotIndex, values);
        finalPivotIndex++;
      }
    }
    swap( finalPivotIndex, end, values);
    return finalPivotIndex;
  }

  private static void swap( int pos1, int pos2, int[] values){
    int swap = values[pos1];
    values[pos1]=values[pos2];
    values[pos2]=swap;
  }
  private static int choosePivot(int start, int end, int[] values){
    return end;
  }

}