package hw3_taylor;

public class BSTNode<T>
{
  private T info;                // The node info
  private String desc;
  private BSTNode<T> left;       // A link to the left child node
  private BSTNode<T> right;      // A link to the right child node

  public BSTNode(T info, String element2)
  {
    this.info = info; left = null; right = null; this.desc = element2;
  }
 
  public void setInfo(T info){this.info = info;}
  public T getInfo(){return info;}
  
  public void setDesc(T desc){this.info = desc;}
  public String getDesc(){return desc;}
  
  public void setLeft(BSTNode<T> link){left = link;}
  public void setRight(BSTNode<T> link){right = link;}
  
  public BSTNode<T> getLeft(){return left;}
  public BSTNode<T> getRight(){return right;}
}
 
 