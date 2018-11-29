/* PURPOSE: Create a class with specific formatting. Using the toString() method,
 * the user is able to create an object of type <MyCourse> with a Course Name and
 * Course Grade with its respective name and grade.
 * 
 * Name: Kaivan Taylor
 * CSC 202 - Computer Science II
 * Professor Weng 
 */
package hw1_taylor;

public class MyCourse {
  String name;
  String grade;

  public MyCourse(String n, String g) {
    name = n;
    grade = g;
  }

  public String toString() {
    String mystr = "Course Name: " + name + "\n" + "Course Grade: " + grade;
    return mystr;
  }
}