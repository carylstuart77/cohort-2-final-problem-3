package edu.cnm.deepdive.finalexam;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Developed to generate a Keno game complete with payout.  Will read standard input for basic
 * checking input for duplications.
 *
 * @authors: Caryl, Edge and Paula.
 */
public class Keno {

  /**
   * Used to save input string.
   */
  private static String[] strs;
  /**
   * Used to keep track of wins.
   */
  private static int indexWin;
  /** Used to parse user selections. */
  static int[] userSelection;
  /** Used to determine if selection is a winner. */
  static List<Integer> computerSelection = new ArrayList<>();

  /**
   * Read input with buffer reader.  Provide user with help option
   * if questions symbol or help is entered.
   * Check for duplicates.
   * @param args
   */
  public static void main(String[] args) {

    try {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String line = br.readLine();
      strs = line.trim().split(" ");
      userSelection = new int[15];

      if (line.equals("-?") || line.equals("-help") || line.equals("-HELP") || line
          .equals("-Help")) {
        System.out.println(
            "1. Enter a number between 1 and 80.\n" +
            "2. There is a max of 15 numbers. \n" +
            "3. Account starts with $100. \n");
        System.exit(0);
      }

      if (line.equals("")) {
        System.out.println("Please enter a number.");
      } else {
        if (strs.length > 15) {
          System.out.println("Please choose less than 15 numbers.");
        } else {
          for (int i = 0; i < strs.length; i++) {

              userSelection[i] = Integer.parseInt(strs[i]);

              if (userSelection[i] > 80 || userSelection[i] < 1) {
                System.out.println("Invalid Number: " + userSelection[i]);

                for (int j = i + 1; j < strs.length; j++) {
                  if (strs[i].equals(strs[j])) {
                    System.out.print("There are duplicate numbers.  Please re-enter.");
                  }
                }

//            for (int x = 0; x < strs.length; x++) {
//              if (strs[x] > 80 || strs[x] == 0) {
//                System.out.println("Selection has to between 0 and 80.");
              }
            }
          }
        }

      if (isInvalid()) {
        System.out.println("invalid number!");
      } else {
        generateAndRandomize();
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Check if number is invalid.
   * @return true or false.
   */

  public static boolean isInvalid() {
    for (int i = 0; i < strs.length; i++) {
      for (int j = i + 1; j < strs.length; j++) {
        if (strs[i].equals(strs[j])) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Randomize Array List and validate number picks.
   * Mix up the collection with shuffle command.
   * Check if number is a winner and display count of wins.
   */
  public static void generateAndRandomize() {
    List<Integer> randomEighty = new ArrayList<>();
    for (int i = 1; i <= 80; i++) {
      randomEighty.add(i);
    }

    Collections.shuffle(randomEighty);
    for (int i = 0; i < 20; i++) {
      computerSelection.add(randomEighty.get(i));
    }

    for (int i = 0; i < userSelection.length; i++) {
      if (computerSelection.contains(userSelection[i])) {
        indexWin++;
      }
    }
    System.out.println("Number of wins : " + indexWin);
  }
}