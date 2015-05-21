package com.hackerrank.snakesandladders;


import java.util.*;

public class Solution {

  /*
   * Complete the function below.
   */
  public static void main(String[] args) throws Exception {

    //Scanner in = new Scanner(System.in);
    Scanner in = new Scanner(Solution.class.getResourceAsStream("in2.txt"));

    // read number of test cases
    int testCasesCount = Integer.parseInt(in.nextLine().trim());

    // read and process each test case
    int[] results = new int[testCasesCount];
    for (int i = 0; i < testCasesCount; i++) {
      results[i] = processInput(in, i);
    }

    // output results for each test case
    for (int i = 0; i < testCasesCount; i++) {
      System.out.println(results[i]);
    }
  }

  //assumes the input is of the format "x y"
  public static int[] parseString(String input) {
    String[] tokens = input.split(" ");
    return new int[]{Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])};
  }

  private static int processInput(Scanner in, int caseIndex) {

    // read ladders
    // TODO: validate, no point is greater than END_POINT-1 and x < y
    int laddersCount = Integer.parseInt(in.nextLine().trim());
    Map<Integer, Integer> snakesLadders = new HashMap<>();
    for (int i = 0; i < laddersCount; i++) {
      int[] line = parseString(in.nextLine());
      snakesLadders.put(line[0], line[1]);
    }

    // read snakes
    // TODO: validate, no point is greater than END_POINT-1 and x > y
    int snakesCount = Integer.parseInt(in.nextLine().trim());
    for (int i = 0; i < snakesCount; i++) {
      int[] line = parseString(in.nextLine());
      snakesLadders.put(line[0], line[1]);
    }

    int value = playGame(snakesLadders);

    //no solution, (no min), is represented as -1
    return value == Integer.MAX_VALUE ? -1 : value;
  }

  private static int playGame(Map<Integer, Integer> snakesLadders) {

    return playGame(1, snakesLadders, new HashSet<Integer>());
  }

  private static final int END_POINT = 100;
  private static final int MAX_ROLL = 6;

  private static int playGame(int startingPoint, Map<Integer, Integer> snakesLadders, Set<Integer> visitedSquares) {

    //keep track of visited squares to detect cycles
    visitedSquares.add(startingPoint);

    // can we end the game ?
    if (END_POINT - startingPoint <= MAX_ROLL) {
      return END_POINT == startingPoint ? 0 : 1;
    }

    // depth first into the best available move using the new starting point
    int min = Integer.MAX_VALUE;
    for (int i = 1; i <= MAX_ROLL; i++) {

      int currentPoint = startingPoint + i;
      int newStartingPoint = compStartingPoint(currentPoint, snakesLadders);

      // try to get a ladder, snake, or just your bigest roll
      if (newStartingPoint == currentPoint && i < MAX_ROLL) {
        continue;
      }
      
      // skip this path if cycle is detected
      if ( visitedSquares.contains( newStartingPoint)){
        continue;
      }

      // ladders
      int rollMin = playGame(newStartingPoint, snakesLadders, new HashSet<Integer>(visitedSquares));
      min = Math.min(min, rollMin);
    }

    // we don't want to +1 Integer.MAX_VALUE
    return min==Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + min;
  }

  private static int compStartingPoint(int startingPoint, Map<Integer, Integer> snakesLadders) {

    Integer endPoint = snakesLadders.get(startingPoint);
    return (null != endPoint) ? endPoint : startingPoint;
  }

}
