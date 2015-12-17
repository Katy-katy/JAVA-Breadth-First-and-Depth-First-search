public interface Stack
{
  public boolean empty();
  public Object peek() throws StackEmptyException;
  public void push ( Object theObject) throws StackFullException;
  public Object pop() throws StackEmptyException;
}