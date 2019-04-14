package hw3_taylor;

import java.util.*; // Iterator, Comparator

public class BinarySearchTree<T> implements BSTInterface<T> {
  protected BSTNode<T> root; // reference to the root of this BST
  protected Comparator<T> comp; // used for all comparisons

  protected boolean found; // used by remove

  public BinarySearchTree()
  // Precondition: T implements Comparable
  // Creates an empty BST object - uses the natural order of elements.
  {
    root = null;
    comp = new Comparator<T>() {
      @SuppressWarnings("unchecked")
      public int compare(T element1, T element2) {
        return ((Comparable<T>) element1).compareTo(element2);
      }
    };
  }

  public BinarySearchTree(Comparator<T> comp)
  // Creates an empty BST object - uses Comparator comp for order
  // of elements.
  {
    root = null;
    this.comp = comp;
  }

  public T getNode() {
    return root.getInfo();
  }

  public boolean isFull()
  // Returns false; this link-based BST is never full.
  {
    return false;
  }

  public boolean isEmpty()
  // Returns true if this BST is empty; otherwise, returns false.
  {
    return (root == null);
  }

  public T min()
  // If this BST is empty, returns null;
  // otherwise returns the smallest element of the tree.
  {
    if (isEmpty())
      return null;
    else {
      BSTNode<T> node = root;
      while (node.getLeft() != null)
        node = node.getLeft();
      return node.getInfo();
    }
  }

  public String getDesc() {
    return root.getDesc();
  }

  public T max()
  // If this BST is empty, returns null;
  // otherwise returns the largest element of the tree.
  {
    if (isEmpty())
      return null;
    else {
      BSTNode<T> node = root;
      while (node.getRight() != null)
        node = node.getRight();
      return node.getInfo();
    }
  }

  private int recSize(BSTNode<T> node)
  // Returns the number of elements in subtree rooted at node.
  {
    if (node == null)
      return 0;
    else
      return 1 + recSize(node.getLeft()) + recSize(node.getRight());
  }

  public int size()
  // Returns the number of elements in this BST.
  {
    return recSize(root);
  }

  public int size2()
  // Returns the number of elements in this BST.
  {
    int count = 0;
    if (root != null) {
      LinkedStack<BSTNode<T>> nodeStack = new LinkedStack<BSTNode<T>>();
      BSTNode<T> currNode;
      nodeStack.push(root);
      while (!nodeStack.isEmpty()) {
        currNode = nodeStack.top();
        nodeStack.pop();
        count++;
        if (currNode.getLeft() != null)
          nodeStack.push(currNode.getLeft());
        if (currNode.getRight() != null)
          nodeStack.push(currNode.getRight());
      }
    }
    return count;
  }

  private boolean recContains(T target, BSTNode<T> node)
  // Returns true if the subtree rooted at node contains info i such that
  // comp.compare(target, i) == 0; otherwise, returns false.
  {
    if (node == null)
      return false; // target is not found
    else if (comp.compare(target, node.getInfo()) < 0)
      return recContains(target, node.getLeft()); // Search left subtree
    else if (comp.compare(target, node.getInfo()) > 0)
      return recContains(target, node.getRight()); // Search right subtree
    else
      return true; // target is found
  }

  public boolean contains(T target)
  // Returns true if this BST contains a node with info i such that
  // comp.compare(target, i) == 0; otherwise, returns false.
  {
    return recContains(target, root);
  }

  private T recGet(T target, BSTNode<T> node)
  // Returns info i from the subtree rooted at node such that
  // comp.compare(target, i) == 0; if no such info exists, returns null.
  {
    if (node == null)
      return null; // target is not found
    else if (comp.compare(target, node.getInfo()) < 0)
      return recGet(target, node.getLeft()); // get from left subtree
    else if (comp.compare(target, node.getInfo()) > 0)
      return recGet(target, node.getRight()); // get from right subtree
    else
      return node.getInfo(); // target is found
  }

  public T get(T target)
  // Returns info i from node of this BST where comp.compare(target, i) == 0;
  // if no such node exists, returns null.
  {
    return recGet(target, root);
  }

  private BSTNode<T> recAdd(T element, String element2, BSTNode<T> node)
  // Adds element to tree rooted at node; tree retains its BST property.
  {
    if (node == null)
      // Addition place found
      node = new BSTNode<T>(element, element2);
    else if (comp.compare(element, node.getInfo()) <= 0)
      node.setLeft(recAdd(element, element2, node.getLeft())); // Add in left subtree
    else
      node.setRight(recAdd(element, element2, node.getRight())); // Add in right subtree
    return node;
  }

  public boolean add(T element, String element2)
  // Adds element to this BST. The tree retains its BST property.
  {
    root = recAdd(element, element2, root);
    return true;
  }

  private T getPredecessor(BSTNode<T> subtree)
  // Returns the information held in the rightmost node of subtree
  {
    BSTNode<T> temp = subtree;
    while (temp.getRight() != null)
      temp = temp.getRight();
    return temp.getInfo();
  }

  private BSTNode<T> removeNode(BSTNode<T> node)
  // Removes the information at node from the tree.
  {
    T data;
    if (node.getLeft() == null)
      return node.getRight();
    else if (node.getRight() == null)
      return node.getLeft();
    else {
      data = getPredecessor(node.getLeft());
      node.setInfo(data);
      node.setLeft(recRemove(data, node.getLeft()));
      return node;
    }
  }

  private BSTNode<T> recRemove(T target, BSTNode<T> node)
  // Removes element with info i from tree rooted at node such that
  // comp.compare(target, i) == 0 and returns true;
  // if no such node exists, returns false.
  {
    if (node == null)
      found = false;
    else if (comp.compare(target, node.getInfo()) < 0)
      node.setLeft(recRemove(target, node.getLeft()));
    else if (comp.compare(target, node.getInfo()) > 0)
      node.setRight(recRemove(target, node.getRight()));
    else {
      node = removeNode(node);
      found = true;
    }
    return node;
  }

  public boolean remove(T target)
  // Removes a node with info i from tree such that comp.compare(target,i) == 0
  // and returns true; if no such node exists, returns false.
  {
    root = recRemove(target, root);
    return found;
  }

  public void preOrder(BSTNode<T> node, LinkedQueue<T> q)
  // Enqueues the elements from the subtree rooted at node into q in preOrder.
  {
    if (node != null) {
      q.enqueue(node.getInfo());
      preOrder(node.getLeft(), q);
      preOrder(node.getRight(), q);
    }
  }

  public void inOrder(BSTNode<T> node, LinkedQueue<T> q)
  // Enqueues the elements from the subtree rooted at node into q in inOrder.
  {
    if (node != null) {
      inOrder(node.getLeft(), q);
      q.enqueue(node.getInfo());
      inOrder(node.getRight(), q);
    }
  }

  public void postOrder(BSTNode<T> node, LinkedQueue<T> q)
  // Enqueues the elements from the subtree rooted at node into q in postOrder.
  {
    if (node != null) {
      postOrder(node.getLeft(), q);
      postOrder(node.getRight(), q);
      q.enqueue(node.getInfo());
    }
  }

  public Iterator<T> getIterator(BSTInterface.Traversal orderType)
  // Creates and returns an Iterator providing a traversal of a "snapshot"
  // of the current tree in the order indicated by the argument.
  // Supports Preorder, Postorder, and Inorder traversal.
  {
    final LinkedQueue<T> infoQueue = new LinkedQueue<T>();
    if (orderType == BSTInterface.Traversal.Preorder)
      preOrder(root, infoQueue);
    else if (orderType == BSTInterface.Traversal.Inorder)
      inOrder(root, infoQueue);
    else if (orderType == BSTInterface.Traversal.Postorder)
      postOrder(root, infoQueue);

    return new Iterator<T>() {
      public boolean hasNext()
      // Returns true if the iteration has more elements; otherwise returns false.
      {
        return !infoQueue.isEmpty();
      }

      public T next()
      // Returns the next element in the iteration.
      // Throws NoSuchElementException - if the iteration has no more elements
      {
        if (!hasNext())
          throw new IndexOutOfBoundsException(
              "illegal invocation of next " + " in BinarySearchTree iterator.\n");
        return infoQueue.dequeue();
      }

      public void remove()
      // Throws UnsupportedOperationException.
      // Not supported. Removal from snapshot iteration is meaningless.
      {
        throw new UnsupportedOperationException(
            "Unsupported remove attempted on " + "BinarySearchTree iterator.\n");
      }
    };
  }

  public Iterator<T> iterator()
  // InOrder is the default, "natural" order.
  {
    return getIterator(BSTInterface.Traversal.Inorder);
  }

  public void printBT() {
    LinkedStack<BSTNode<T>> stack = new LinkedStack<BSTNode<T>>();
    LinkedStack<BSTNode<T>> stack2 = new LinkedStack<BSTNode<T>>();
    LinkedStack<BSTNode<T>> stack3 = new LinkedStack<BSTNode<T>>();

    if (root != null) {
      stack2.push(root);
    }
    while (!stack2.isEmpty()) {
      while (!stack2.isEmpty()) {
        BSTNode<T> node;
        node = stack2.top();
        //System.out.println("This enters stack2: " + node.getInfo());

        if (node.getRight() != null) {
          stack.push(node.getRight());
          //System.out.println("Right child is pushed into stack 1: " + node.getRight().getInfo());
        }
        if (node.getLeft() != null) {
          stack.push(node.getLeft());
          //System.out.println("Left child is pushed into stack 1: " + node.getLeft().getInfo());
        }

        stack3.push(node);
        stack2.pop();
      }

      while (!stack3.isEmpty()) {
        System.out.print(stack3.top().getInfo() + "    ");
        stack3.pop();
      }
      System.out.println();
      if (!stack.isEmpty()) {
        while (!stack.isEmpty()) {
          //System.out.println();
          //System.out.print("This enters stack1: " + stack.top().getInfo());
          BSTNode<T> node1;
          node1 = stack.top();
          stack2.push(node1);
          //System.out.println(" stack1 pushes out " + node1.getInfo());
          stack.pop();
        }
      }
    }
  }
  
  public void postordersort(List<T> exlist, BinarySearchTree<T> tree) {
    inordersort(exlist, tree);
  }
  
  public void preordersort(List<T> exlist, BinarySearchTree<T> tree) {
    inordersort(exlist, tree);
  }

  public void inordersort(List<T> list1, BinarySearchTree<T> tree) {

    try {
      while (true) {
        recSort(list1, tree);
      }
    } catch (Exception e) {
      System.out.println();
      System.out.println("Using the above traversal to build a BST, the BST is:" );
      System.out.println("\nSorted BST (Balanced)");
    }
  }

  public List<T> recSort(List<T> exlist, BinarySearchTree<T> tree) {

    int half = ((exlist.size() / 2));
    // System.out.println("TEST: HALF " + exlist.get(half));
    tree.add(exlist.get(half), "Inorder");
    exlist.remove(half);

    int list_size = exlist.size();

    int lefthalf = half - 1;
    int righthalf = half + 1;

    int lefthalfmiddle = (lefthalf / 2);
    int righthalfmiddle = ((list_size - righthalf) / 2 + righthalf) - 1;
    // System.out.println("TEST: RIGHT " + exlist.get(righthalfmiddle));
    // System.out.println("TEST: LEFT " + exlist.get(lefthalfmiddle));
    tree.add(exlist.get(lefthalfmiddle), "Inorder");
    tree.add(exlist.get(righthalfmiddle), "Inorder");

    exlist.remove(righthalfmiddle);
    exlist.remove(lefthalfmiddle);

    // create new list for left and right
    List<T> leftlist = new ArrayList<T>();
    List<T> rightlist = new ArrayList<T>();

    leftlist.addAll(exlist.subList(0, half - 1));
    rightlist.addAll(exlist.subList(half - 1, list_size - 2));

    lolSort(leftlist, rightlist, tree);

    return exlist;
  }

  public List<T> lolSort(List<T> exlist, List<T> exlist2, BinarySearchTree<T> tree) {

    if (exlist2.size() == 2) {
      tree.add(exlist2.get(0), "Inorder");
      tree.add(exlist2.get(1), "Inorder");
    }

    int start = 1;
    int end = exlist.size() - 2;
    int end2 = exlist2.size() - 2;
    /*
     * System.out.println("TEST:   " + exlist + "\nSTART #: " + exlist.get(start) + "\nEnd #:  " +
     * exlist.get(end)); System.out.println("START INDEX:   " + start + "\nEND INDEX:   " + end);
     * System.out.println();
     */

    /*
     * System.out.println("TEST:   " + exlist2 + "\nSTART #: " + exlist2.get(start) + "\nEnd #:  " +
     * exlist2.get(end)); System.out.println("START INDEX:   " + start + "\nEND INDEX:   " + end2);
     * System.out.println();
     */

    tree.add(exlist.get(end), "Inorder");
    tree.add(exlist.get(start), "Inorder");

    exlist.remove(end);
    exlist.remove(start);

    tree.add(exlist2.get(end2), "Inorder");
    tree.add(exlist2.get(start), "Inorder");

    exlist2.remove(end2);
    exlist2.remove(start);

    // System.out.println("END OF EXLIST: " +exlist);
    lolSort(exlist, exlist2, tree);
    return exlist;

  }
}