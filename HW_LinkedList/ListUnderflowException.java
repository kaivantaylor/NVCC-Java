package hw1_taylor;

public class ListUnderflowException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  public ListUnderflowException()
  {
    super();
  }

  public ListUnderflowException(String message)
  {
    super(message);
  }
}