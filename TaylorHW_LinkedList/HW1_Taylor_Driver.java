/* PURPOSE: Driver.java class that scans from Courses.txt
 * and instantiates objects of data type MyCourse. Then creates a new
 * linked list using the MyCourse objects.
 * 
 * Name: Kaivan Taylor
 * CSC 202 - Computer Science II
 * Professor Weng
 */
package hw1_taylor;

import java.util.Scanner; // For I/O reading.
import java.io.File; // Used to read file from system.
import java.io.FileNotFoundException; // Throws an exception when file cannot be read.

public class HW1_Taylor_Driver {
  public static void main(String[] args) throws FileNotFoundException {

    File file = new File("src/hw1_taylor/Courses.txt");
    Scanner ioscanner = new Scanner(file); // Creates a new Scanner
    MyCourseList<MyCourse> linked_list = new MyCourseList<MyCourse>();

    while (ioscanner.hasNextLine()) { // Reads from .txt file until there is nothing left.

      String line_1 = ioscanner.nextLine();
      String[] result = line_1.split(" "); // Splits course name and grade for each line.
      // System.out.println(i);
      // System.out.println(result[0] + " " + result[1]);

      MyCourse mycourse_obj = new MyCourse(result[0], result[1]);
      // System.out.println(mycourse_obj.getClass().getName());
      linked_list.insertCourse(mycourse_obj);
    }
        linked_list.printList(); // Method from MyCourseList that prints out LinkedList
        ioscanner.close(); // Closes the Scanner
  }
}