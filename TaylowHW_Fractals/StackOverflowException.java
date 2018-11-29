package hw2_taylor;

public class StackOverflowException extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public StackOverflowException()
  {
    super();
  }

  public StackOverflowException(String message)
  {
    super(message);
  }
}