/* PURPOSE: Create an Exception to throw if list overflows/ goes
 * past specific size limit.
 * 
 * Name: Kaivan Taylor
 * CSC 202 - Computer Science II
 * Professor Weng 
 */
package hw1_taylor;

public class ListOverflowException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ListOverflowException() {
    super();
  }

  public ListOverflowException(String message) {
    super(message);
  }
}