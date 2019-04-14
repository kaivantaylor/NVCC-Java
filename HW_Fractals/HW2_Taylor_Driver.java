//*********************************************************************
//  HW2_Taylor_Driver.java           By Kaivan Taylor                Chapter 3
//
//  Creates a jpg file containing a path to (x,y) coordinates given.
//  Removes recursion from TSquare.java and uses stack to store information.
//  
//********************************************************************
package hw2_taylor;

import java.awt.image.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.imageio.*;

public class HW2_Taylor_Driver {

  static final int SIDE = 1000; // image is SIDE X SIDE
  static BufferedImage image = new BufferedImage(SIDE, SIDE, BufferedImage.TYPE_INT_RGB);

  // static variables for colors
  static final int WHITE = Color.WHITE.getRGB();
  static final int BLUE = Color.BLUE.getRGB();
  static final int BLACK = Color.BLACK.getRGB();
  static final int PINK = Color.PINK.getRGB();
  static final int YELLOW = Color.YELLOW.getRGB();
  static final int ORANGE = Color.ORANGE.getRGB();
  static final int RED = Color.RED.getRGB();

  static boolean isFound = false;

  public static void drawSquare(int x, int y, int s, int coordx, int coordy) // center of square is
                                                                             // x,y length of side
                                                                             // is s
  {
    LinkedStack<List<Integer>> stack = new LinkedStack<>(); // for left top
    LinkedStack<List<Integer>> stack_2 = new LinkedStack<>(); // for left bottom
    LinkedStack<List<Integer>> stack_3 = new LinkedStack<>(); // for right top
    LinkedStack<List<Integer>> stack_4 = new LinkedStack<>(); // for right bottom

    // determine corners
    int left = x - s / 2;
    int top = y - s / 2;
    int right = x + s / 2;
    int bottom = y + s / 2;

    int usercoordx = coordx;
    int usercoordy = coordy;

    // System.out.println("left: " + left + "top: " + top + "right: " + right + "bottom: " +
    // bottom);

    List<Integer> left_top = new ArrayList<Integer>();
    left_top.add(left);
    left_top.add(top);
    left_top.add(s / 2);

    List<Integer> left_bottom = new ArrayList<Integer>();
    left_bottom.add(left);
    left_bottom.add(bottom);
    left_bottom.add(s / 2);

    List<Integer> right_top = new ArrayList<Integer>();
    right_top.add(right);
    right_top.add(top);
    right_top.add(s / 2);

    List<Integer> right_bottom = new ArrayList<Integer>();
    right_bottom.add(right);
    right_bottom.add(bottom);
    right_bottom.add(s / 2);

    stack.push(left_top);
    stack_2.push(left_bottom);
    stack_3.push(right_top);
    stack_4.push(right_bottom);

    // paint the blue square
    for (int i = 250; i < 750; i++)
      for (int j = 250; j < 750; j++) {
        image.setRGB(i, j, BLACK);
      }

    while (!stack.isEmpty()) {
      ArrayList<Integer> list = new ArrayList<>(stack.top());
      int x_1 = list.get(0);
      int y_1 = list.get(1);
      int s_1 = list.get(2);

      if (s_1 >= 5) {
        // determine corners
        int left_1 = x_1 - s_1 / 2;
        int top_1 = y_1 - s_1 / 2;
        int right_1 = x_1 + s_1 / 2;
        int bottom_1 = y_1 + s_1 / 2;

        // paint the blue square
        for (int a = left_1; a < right_1; a++)
          for (int b = top_1; b < bottom_1; b++) {
            /* image.setRGB(a, b, BLUE); */
            if (a == usercoordx && b == usercoordy) {
              image.setRGB(usercoordx, usercoordy, RED);
              image.setRGB(usercoordx + 1, usercoordy, RED);
              image.setRGB(usercoordx + 2, usercoordy, RED);
              image.setRGB(usercoordx + 3, usercoordy, RED);
              image.setRGB(usercoordx + 4, usercoordy, RED);
              image.setRGB(usercoordx, usercoordy + 1, RED);
              image.setRGB(usercoordx, usercoordy + 2, RED);
              image.setRGB(usercoordx, usercoordy + 3, RED);
              image.setRGB(usercoordx, usercoordy + 4, RED);
              image.setRGB(usercoordx - 1, usercoordy, RED);
              image.setRGB(usercoordx - 2, usercoordy, RED);
              image.setRGB(usercoordx - 3, usercoordy, RED);
              image.setRGB(usercoordx - 4, usercoordy, RED);
              image.setRGB(usercoordx, usercoordy - 1, RED);
              image.setRGB(usercoordx, usercoordy - 2, RED);
              image.setRGB(usercoordx, usercoordy - 3, RED);
              image.setRGB(usercoordx, usercoordy - 4, RED);
              isFound = true;
            }
            if (isFound == true) {
              image.setRGB(a, b, BLUE);
              
            } 
            else {
            }
            continue;
          }
        stack.pop();

        List<Integer> left_top_1 = new ArrayList<Integer>();
        left_top_1.add(left_1);
        left_top_1.add(top_1);
        left_top_1.add(s_1 / 2);

        List<Integer> left_bottom_1 = new ArrayList<Integer>();
        left_bottom_1.add(left_1);
        left_bottom_1.add(bottom_1);
        left_bottom_1.add(s_1 / 2);

        List<Integer> right_top_1 = new ArrayList<Integer>();
        right_top_1.add(right_1);
        right_top_1.add(top_1);
        right_top_1.add(s_1 / 2);

        List<Integer> right_bottom_1 = new ArrayList<Integer>();
        right_bottom_1.add(right_1);
        right_bottom_1.add(bottom_1);
        right_bottom_1.add(s_1 / 2);

        stack.push(right_bottom_1);
        stack.push(right_top_1);
        stack.push(left_bottom_1);
        stack.push(left_top_1);

      } else {
        stack.pop();
      }
    }

    while (!stack_2.isEmpty()) {
      ArrayList<Integer> list_2 = new ArrayList<>(stack_2.top());
      int x_2 = list_2.get(0);
      int y_2 = list_2.get(1);
      int s_2 = list_2.get(2);

      if (s_2 >= 5) {
        // determine corners
        int left_2 = x_2 - s_2 / 2;
        int top_2 = y_2 - s_2 / 2;
        int right_2 = x_2 + s_2 / 2;
        int bottom_2 = y_2 + s_2 / 2;

        // paint the blue square
        for (int c = left_2; c < right_2; c++)
          for (int d = top_2; d < bottom_2; d++) {
            image.setRGB(c, d, PINK);
            if (c == usercoordx && d == usercoordy) {
              image.setRGB(usercoordx, usercoordy, RED);
              image.setRGB(usercoordx + 1, usercoordy, RED);
              image.setRGB(usercoordx + 2, usercoordy, RED);
              image.setRGB(usercoordx + 3, usercoordy, RED);
              image.setRGB(usercoordx + 4, usercoordy, RED);
              image.setRGB(usercoordx, usercoordy + 1, RED);
              image.setRGB(usercoordx, usercoordy + 2, RED);
              image.setRGB(usercoordx, usercoordy + 3, RED);
              image.setRGB(usercoordx, usercoordy + 4, RED);
              image.setRGB(usercoordx - 1, usercoordy, RED);
              image.setRGB(usercoordx - 2, usercoordy, RED);
              image.setRGB(usercoordx - 3, usercoordy, RED);
              image.setRGB(usercoordx - 4, usercoordy, RED);
              image.setRGB(usercoordx, usercoordy - 1, RED);
              image.setRGB(usercoordx, usercoordy - 2, RED);
              image.setRGB(usercoordx, usercoordy - 3, RED);
              image.setRGB(usercoordx, usercoordy - 4, RED);
              isFound = true;
              return;
            }
          }
        stack_2.pop();

        List<Integer> left_top_2 = new ArrayList<Integer>();
        left_top_2.add(left_2);
        left_top_2.add(top_2);
        left_top_2.add(s_2 / 2);

        List<Integer> left_bottom_2 = new ArrayList<Integer>();
        left_bottom_2.add(left_2);
        left_bottom_2.add(bottom_2);
        left_bottom_2.add(s_2 / 2);

        List<Integer> right_top_2 = new ArrayList<Integer>();
        right_top_2.add(right_2);
        right_top_2.add(top_2);
        right_top_2.add(s_2 / 2);

        List<Integer> right_bottom_2 = new ArrayList<Integer>();
        right_bottom_2.add(right_2);
        right_bottom_2.add(bottom_2);
        right_bottom_2.add(s_2 / 2);

        stack_2.push(right_bottom_2);
        stack_2.push(right_top_2);
        stack_2.push(left_bottom_2);
        stack_2.push(left_top_2);
      } else {
        stack_2.pop();
      }

      while (!stack_3.isEmpty()) {
        ArrayList<Integer> list_3 = new ArrayList<>(stack_3.top());
        int x_3 = list_3.get(0);
        int y_3 = list_3.get(1);
        int s_3 = list_3.get(2);

        if (s_3 >= 5) {
          // determine corners
          int left_3 = x_3 - s_3 / 2;
          int top_3 = y_3 - s_3 / 2;
          int right_3 = x_3 + s_3 / 2;
          int bottom_3 = y_3 + s_3 / 2;

          // paint the blue square
          for (int z = left_3; z < right_3; z++)
            for (int y1 = top_3; y1 < bottom_3; y1++) {
              image.setRGB(z, y1, YELLOW);
              if (z == usercoordx && y1 == usercoordy) {
                image.setRGB(usercoordx, usercoordy, RED);
                image.setRGB(usercoordx + 1, usercoordy, RED);
                image.setRGB(usercoordx + 2, usercoordy, RED);
                image.setRGB(usercoordx + 3, usercoordy, RED);
                image.setRGB(usercoordx + 4, usercoordy, RED);
                image.setRGB(usercoordx, usercoordy + 1, RED);
                image.setRGB(usercoordx, usercoordy + 2, RED);
                image.setRGB(usercoordx, usercoordy + 3, RED);
                image.setRGB(usercoordx, usercoordy + 4, RED);
                image.setRGB(usercoordx - 1, usercoordy, RED);
                image.setRGB(usercoordx - 2, usercoordy, RED);
                image.setRGB(usercoordx - 3, usercoordy, RED);
                image.setRGB(usercoordx - 4, usercoordy, RED);
                image.setRGB(usercoordx, usercoordy - 1, RED);
                image.setRGB(usercoordx, usercoordy - 2, RED);
                image.setRGB(usercoordx, usercoordy - 3, RED);
                image.setRGB(usercoordx, usercoordy - 4, RED);
                isFound = true;
                return;
              }
            }
          stack_3.pop();

          List<Integer> left_top_3 = new ArrayList<Integer>();
          left_top_3.add(left_3);
          left_top_3.add(top_3);
          left_top_3.add(s_3 / 2);

          List<Integer> left_bottom_3 = new ArrayList<Integer>();
          left_bottom_3.add(left_3);
          left_bottom_3.add(bottom_3);
          left_bottom_3.add(s_3 / 2);

          List<Integer> right_top_3 = new ArrayList<Integer>();
          right_top_3.add(right_3);
          right_top_3.add(top_3);
          right_top_3.add(s_3 / 2);

          List<Integer> right_bottom_3 = new ArrayList<Integer>();
          right_bottom_3.add(right_3);
          right_bottom_3.add(bottom_3);
          right_bottom_3.add(s_3 / 2);

          stack_3.push(right_bottom_3);
          stack_3.push(right_top_3);
          stack_3.push(left_bottom_3);
          stack_3.push(left_top_3);

        } else {
          stack_3.pop();
        }
      }
      while (!stack_4.isEmpty()) {
        ArrayList<Integer> list_4 = new ArrayList<>(stack_4.top());
        int x_4 = list_4.get(0);
        int y_4 = list_4.get(1);
        int s_4 = list_4.get(2);

        if (s_4 >= 5) {
          // determine corners
          int left_4 = x_4 - s_4 / 2;
          int top_4 = y_4 - s_4 / 2;
          int right_4 = x_4 + s_4 / 2;
          int bottom_4 = y_4 + s_4 / 2;

          // paint the blue square
          for (int g = left_4; g < right_4; g++)
            for (int h = top_4; h < bottom_4; h++) {
              image.setRGB(g, h, ORANGE);
              if (g == usercoordx && h == usercoordy) {
                image.setRGB(usercoordx, usercoordy, RED);
                image.setRGB(usercoordx + 1, usercoordy, RED);
                image.setRGB(usercoordx + 2, usercoordy, RED);
                image.setRGB(usercoordx + 3, usercoordy, RED);
                image.setRGB(usercoordx + 4, usercoordy, RED);
                image.setRGB(usercoordx, usercoordy + 1, RED);
                image.setRGB(usercoordx, usercoordy + 2, RED);
                image.setRGB(usercoordx, usercoordy + 3, RED);
                image.setRGB(usercoordx, usercoordy + 4, RED);
                image.setRGB(usercoordx - 1, usercoordy, RED);
                image.setRGB(usercoordx - 2, usercoordy, RED);
                image.setRGB(usercoordx - 3, usercoordy, RED);
                image.setRGB(usercoordx - 4, usercoordy, RED);
                image.setRGB(usercoordx, usercoordy - 1, RED);
                image.setRGB(usercoordx, usercoordy - 2, RED);
                image.setRGB(usercoordx, usercoordy - 3, RED);
                image.setRGB(usercoordx, usercoordy - 4, RED);
                isFound = true;
                return;
              }
            }

          stack_4.pop();

          List<Integer> left_top_4 = new ArrayList<Integer>();
          left_top_4.add(left_4);
          left_top_4.add(top_4);
          left_top_4.add(s_4 / 2);

          List<Integer> left_bottom_4 = new ArrayList<Integer>();
          left_bottom_4.add(left_4);
          left_bottom_4.add(bottom_4);
          left_bottom_4.add(s_4 / 2);

          List<Integer> right_top_4 = new ArrayList<Integer>();
          right_top_4.add(right_4);
          right_top_4.add(top_4);
          right_top_4.add(s_4 / 2);

          List<Integer> right_bottom_4 = new ArrayList<Integer>();
          right_bottom_4.add(right_4);
          right_bottom_4.add(bottom_4);
          right_bottom_4.add(s_4 / 2);

          stack_4.push(right_bottom_4);
          stack_4.push(right_top_4);
          stack_4.push(left_bottom_4);
          stack_4.push(left_top_4);
        } else {
          stack_4.pop();
        }
      }
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    Scanner inScan = new Scanner(System.in);
    System.out.println("Enter the file name:");
    String filename = inScan.nextLine() + ".jpg";
    String fileOut = filename;

    // make image WHITE
    for (int i = 0; i < SIDE; i++)
      for (int j = 0; j < SIDE; j++) {
        image.setRGB(i, j, WHITE);
      }

    // Ask user for coordinates
    System.out.println("Please enter coordinate for x:");
    int input_x = inScan.nextInt();
    System.out.println("\nPlease enter coordinate for y:");
    int input_y = inScan.nextInt();
    inScan.close();

    System.out.println("\nProcessing code...");

    // first square
    drawSquare(SIDE / 2, SIDE / 2, SIDE / 2, input_x, input_y);

    TimeUnit.SECONDS.sleep(3);

    if (isFound == true) {
      System.out.println("The path has been generated in the image named " + filename);
      // save image
      File outputfile = new File(fileOut);
      ImageIO.write(image, "jpg", outputfile);
    } else {
      System.out.println("The path could not be reached. :( ");
    }
  }
}