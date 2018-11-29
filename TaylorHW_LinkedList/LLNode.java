/* PURPOSE: Imported LLNode from ch02.stacks. LLNode uses methods
 * in order to set info, get info, set link, and get link for nodes.
 * 
 * Name: Kaivan Taylor
 * CSC 202 - Computer Science II
 * Professor Weng 
 */
package hw1_taylor;

public class LLNode<T> {
  protected LLNode<MyCourse> link;
  protected MyCourse info;

  public LLNode(MyCourse info) {
    this.info = info;
    link = null;
  }

  public void setInfo(MyCourse info) {
    this.info = info;
  }

  public MyCourse getInfo() {
    return info;
  }

  public void setLink(LLNode<MyCourse> link) {
    this.link = link;
  }

  public LLNode<MyCourse> getLink() {
    return link;
  }
}