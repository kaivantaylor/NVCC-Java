package hw3_taylor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BTDriver {
  public static void main(String[] args) {

    BinarySearchTree<Integer> example = new BinarySearchTree<Integer>();
    BinarySearchTree<Integer> pre = new BinarySearchTree<Integer>();
    BinarySearchTree<Integer> in = new BinarySearchTree<Integer>();
    BinarySearchTree<Integer> post = new BinarySearchTree<Integer>();
    
    Iterator<Integer> iter;
    
    /*
     * Example from Blackboard: 
     * 1 Do you spend 8 hours or more on the course? 
     * 2 Are you employed
     * to work for 20 hours or more? 
     * 3 Do you miss any HW? 
     * 4 Do you come to class regularly? 
     * 5 Are
     * you resourceful? 
     * 6 Did you earn an A from CSC201? 
     * 7 Did you earn an A from CSC201? 
     * 8 F 
     * 9 C 
     * 10 D 
     * 11 C 
     * 12 B 
     * 13 A 
     * 14 C 
     * 15 B
     * 
     * Structure Below 
     * 4 8 9 
     * 5 10 11 
     * 6 12 13 
     * 7 14 15 
     * 2 4 5 
     * 3 6 7 
     * 1 2 3
     */

    String ex1 = "Do you spend 8 hours or more on the course?";
    String ex2 = "Are you employed to work for 20 hours or more?";
    String ex3 = "Do you miss any HW?";
    String ex4 = "Do you come to class regularly?";
    String ex5 = "Are you resourceful? ";
    String ex6 = "Did you earn an A from CSC201?";
    String ex7 = "A";
    String ex8 = "B";
    String ex9 = "C";
    String ex10 = "D";
    String ex11 = "F";
    
    example.add(4, ex4); example.add(8, ex11); example.add(9, ex9); // 4 8 9
    example.add(5, ex5); example.add(10, ex10); example.add(11, ex9); // 5 10 11
    example.add(6, ex6); example.add(12, ex8); example.add(13, ex7); // 6 12 13
    example.add(7, ex6); example.add(14, ex6); example.add(15, ex8); // 7 14 15
    example.add(2, ex2);  // 2 4 5 (Duplicate 4,5 )
    example.add(3, ex3);  // 3 6 7 (Duplicate 6,7)
    example.add(1, ex1);  // 1 2 3 (Duplicated 2,3)
    

    // Part 2 for HW
    System.out.println("Print out the BT in the following format:");
    System.out.println();
    System.out.println("BT Tree (Unbalanced)");
    example.printBT();

    // Part 3 for HW
    List<Integer> list = new ArrayList<Integer>();
    Scanner reader = new Scanner(System.in);
    

    System.out.println("\nNow I will turn the BT into a BST. How do you want to list the BT?");
    System.out.println("1)Preorder 2)Inorder 3)Postorder");
    System.out.println();
    System.out.println("Please choose a number: ");
    
    int a = reader.nextInt();
    reader.close();
    
    if (a == 1) {
      System.out.println();
      System.out.println("Preorder Traversal:");

      iter = example.getIterator(BSTInterface.Traversal.Preorder); // Print out the Pre order
      while (iter.hasNext()) {
        System.out.print(iter.next() + " ");
      }

      System.out.println("\n");
      iter = example.getIterator(BSTInterface.Traversal.Preorder); // Add to a list
      while (iter.hasNext()) {
        list.add(iter.next());
      }
      for(Integer temp: list) {
        pre.add(temp, "Pre");
      }
      Collections.sort(list);
      //pre.preordersort(list, pre);
      pre.printBT();
            
    }

      else if (a == 2) {
        System.out.println();
        System.out.println("Inorder Traversal: ");
        iter = example.getIterator(BSTInterface.Traversal.Inorder); // Print out the Pre order
        while (iter.hasNext()) {
          System.out.print(iter.next() + " ");
        }
        System.out.println();
        iter = example.getIterator(BSTInterface.Traversal.Inorder); // Add to a list
        while (iter.hasNext()) {
          list.add(iter.next());
        }
        in.inordersort(list, in);
        in.printBT();
      }

      else if(a == 3) {
        System.out.println();
        System.out.println("Postorder Traversal: ");

        iter = example.getIterator(BSTInterface.Traversal.Postorder); // Print out the Pre order
        while (iter.hasNext()) {
          System.out.print(iter.next() + " ");
        }

        System.out.println();
        iter = example.getIterator(BSTInterface.Traversal.Postorder); // Add to a list
        while (iter.hasNext()) {
          list.add(iter.next());
        }
        System.out.println();
        for(Integer temp: list) {
          post.add(temp, "Post");
      }
        post.printBT();
    }
  }
}