package com.hackerrank.ACM_ICPC_Team;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

  /*
   * Complete the function below.
   */
  public static void main(String[] args) throws Exception {

    Scanner in = new Scanner(System.in);
    String[] firstLineTokens = in.nextLine().split(" ");
    //assert there are 2 tokens
    int peopleCount = Integer.parseInt(firstLineTokens[0]);
    int topicsCount = Integer.parseInt(firstLineTokens[1]);

    int[][] topicsByPeople = new int[peopleCount][topicsCount];
    for (int i = 0; i < peopleCount; i++) {
      String topicsLine = in.nextLine();
      //assert there are enough topicsTokens as topicsCount
      for (int j = 0; j < topicsLine.length(); j++) {
        topicsByPeople[i][j] = Character.getNumericValue(topicsLine.charAt(j));
      }
    }

    Map<Integer,Integer> valueToPairs = new HashMap<>();
    int maxTopics = 0;
    for (int i = 0; i < peopleCount; i++) {
      for (int j = i; j < peopleCount; j++) {
        int totalTopics = processPeople( topicsByPeople[i], topicsByPeople[j]);
        maxTopics = totalTopics > maxTopics ? totalTopics : maxTopics;
        Integer pairsCount = valueToPairs.get(totalTopics);
        if (null == pairsCount){
          valueToPairs.put( totalTopics, 1);
        } else {
          valueToPairs.put( totalTopics, pairsCount + 1);
        }
      }
    }

    System.out.println(maxTopics);
    System.out.println(valueToPairs.get(maxTopics));
  }

  private static int processPeople( int[] topicsA, int[] topicsB){

    int topicsCount = 0;
    for(int i=0; i<topicsA.length; i++){
      topicsCount = topicsCount + (topicsA[i]==1 || topicsB[i]==1 ? 1 : 0);
    }

    return topicsCount;
  }
}
