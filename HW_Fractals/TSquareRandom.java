//*********************************************************************
//  TSquare.java           By Dale/Joyce/Weems                Chapter 3
//
//  Creates a jpg file containing a recursive TSquare.
//  Run argument 1: full name of target jpg file
//  
//********************************************************************
package hw2_taylor;

import java.awt.image.*;
import java.awt.Color;
import java.io.*;
import java.util.Random;

import javax.imageio.*;

public class TSquareRandom {
  static final int SIDE = 1000; // image is SIDE X SIDE
  static BufferedImage image = new BufferedImage(SIDE, SIDE, BufferedImage.TYPE_INT_RGB);
  static int WHITE = Color.WHITE.getRGB();
  static int BLACK = Color.BLACK.getRGB();

  private static void drawSquare(int x, int y, int s, int myColor)
  // center of square is x,y length of side is s
  {
    if (s <= 5) // base case
      return;
    else {
      // determine corners
      int left = x - (s / 2);
      int top = y - (s / 2);
      int right = x + (s / 2);
      int bottom = y + (s / 2);

      if ((left < 0 || top < 0 || right < 0 || bottom < 0)
          || (left > SIDE || top > SIDE || right > SIDE || bottom > SIDE))
        return;

      // recursively paint squares at the corners
      int myNextColor = myColor - 100000;
      drawSquare(left, top, s / 2, myNextColor);
      drawSquare(left, bottom, s / 2, myNextColor);
      drawSquare(right, top, s / 2, myNextColor);
      drawSquare(right, bottom, s / 2, myNextColor);

      for (int i = left; i < right; i++)
        for (int j = top; j < bottom; j++) {
          image.setRGB(i, j, myColor);
        }
    }
  }

  public static void main(String[] args) throws IOException {
    String fileOut = args[0];

    // make image black
    for (int i = 0; i < SIDE; i++)
      for (int j = 0; j < SIDE; j++) {
        image.setRGB(i, j, BLACK);
      }

    // first square
    int myColor = WHITE;
    drawSquare(500, 500, SIDE / 2, myColor);

    // save image
    File outputfile = new File(fileOut);
    ImageIO.write(image, "jpg", outputfile);
  }
}