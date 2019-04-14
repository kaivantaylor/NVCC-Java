package hw3_taylor;

import java.util.List;

public interface BTInterface<T> extends CollectionInterface<T> {
  
  public void printBT();
  // Take the Binary Tree and print it using breadth-first approach.
  
  public void postordersort(List<T> exlist, BinarySearchTree<T> tree);
  // Balances the Binary tree based on a postordersort
  
  public void preordersort(List<T> list1, BinarySearchTree<T> tree);
  // Balances the Binary tree based on a preorder sort
  
  public void inordersort(List<T> list1, BinarySearchTree<T> tree);
  // Balances the Binary tree based on an inorder sort
}