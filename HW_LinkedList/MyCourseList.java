/* PURPOSE: Create a specific linked list of type MyCourseList which
 * takes in object of <MyCourse>. Uses LLNode as basis for updating
 * info and links within the linked list.
 * 
 * Name: Kaivan Taylor
 * CSC 202 - Computer Science II
 * Professor Weng
 */
package hw1_taylor;

import hw2_taylor.LLNode;

public class MyCourseList<T> implements LinkedCourseInterface<MyCourse> {
  protected LLNode<MyCourse> top; // reference to the top of this stack

  public MyCourseList() {
    top = null;
  }

  public void printList()
  // Print out all contents from linked list to the console.
  {
    if (isEmpty())
      throw new ListUnderflowException("Pop attempted on an empty stack.");
    else
      System.out.println("--------------------");
      while (isEmpty() != true) {
        System.out.println(checkCourse());
        removeCourse();
        System.out.println("--------------------");
      }
  }

  public void insertCourse(MyCourse element)
  // Places element at the top of this stack.
  {
    LLNode<MyCourse> newNode = new LLNode<MyCourse>(element);
    newNode.setLink(top);
    top = newNode;
  }

  public void removeCourse()
  // Throws ListUnderflowException if this stack is empty,
  // otherwise removes top element from this stack.
  {
    if (isEmpty())
      throw new ListUnderflowException("Pop attempted on an empty stack.");
    else
      top = top.getLink();
  }

  public MyCourse checkCourse()
  // Throws ListUnderflowException if this stack is empty,
  // otherwise returns top element of this stack.
  {
    if (isEmpty())
      throw new ListUnderflowException("Top attempted on an empty stack.");
    else
      return top.getInfo();
  }

  public boolean isEmpty()
  // Returns true if this stack is empty, otherwise returns false.
  {
    return (top == null);
  }

  public boolean isFull()
  // Returns false - a linked stack is never full
  {
    return false;
  }
}
