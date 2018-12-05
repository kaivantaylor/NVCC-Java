package HW4_Taylor_Final;

public class QueueOverflowException extends RuntimeException
{
  public QueueOverflowException()
  {
    super();
  }

  public QueueOverflowException(String message)
  {
    super(message);
  }
}