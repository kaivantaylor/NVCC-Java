package hw3_taylor;

import java.util.Iterator;

public interface BSTInterface<T> extends BTInterface<T>
{
  // Used to specify traversal order.
  public enum Traversal {Inorder, Preorder, Postorder};

  T min();
  // If this BST is empty, returns null;
  // otherwise returns the smallest element of the tree.

  T max();
  // If this BST is empty, returns null;
  // otherwise returns the largest element of the tree.
  
  public Iterator<T> getIterator(Traversal orderType);
  // Creates and returns an Iterator providing a traversal of a "snapshot" 
  // of the current tree in the order indicated by the argument.

}