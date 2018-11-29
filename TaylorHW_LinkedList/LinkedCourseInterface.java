/* PURPOSE: Create an interface that describes the methods and their purpose.
 * The body of the methods corresponds to the MyCourseList.java file.
 * 
 * Name: Kaivan Taylor
 * CSC 202 - Computer Science II
 * Professor Weng
 */
package hw1_taylor;

public interface LinkedCourseInterface<T> {

  void insertCourse(MyCourse element) throws ListOverflowException;
  // Throws ListOverflowException if this stack is full,
  // otherwise places element at the top of this stack.

  void printList() throws ListUnderflowException;
  // Throws ListUnderflowException if this stack is empty,
  // otherwise prints out all contents from linked list to
  // the console.
  
  void removeCourse() throws ListUnderflowException;
  // Throws ListUnderflowException if this stack is empty,
  // otherwise removes top element from this stack.

  MyCourse checkCourse() throws ListUnderflowException;
  // Throws ListUnderflowException if this stack is empty,
  // otherwise returns top element of this stack.

  boolean isEmpty();
  // Returns true if this stack is empty, otherwise returns false.

  boolean isFull();
  // Returns true if this stack is full, otherwise returns false.
}