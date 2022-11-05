package lib;


public class TreeNode 
{
  @Override
	public String toString() {
	  if(getItem().getCharacter() != ' ') {
		return  getItem().getCharacter() + "\t" + getItem().getFrequency() + "\t" + getItem().getBitcode();
	  }
	  return "no";
	}

private Char_Set item;
  private TreeNode leftChild;
  private TreeNode rightChild;

  public TreeNode(Char_Set newItem)
  {
  // Initializes tree node with item and no children.
    item = newItem;
    leftChild  = null;
    rightChild = null;
  }  // end constructor

  public TreeNode(Char_Set newItem,
                  TreeNode left, TreeNode right)
                  {
  // Initializes tree node with item and
  // the left and right children references.
    item = newItem;
    leftChild  = left;
    rightChild = right;
  }  // end constructor

  public Char_Set getItem()
  {
  // Returns the item field.
    return item;
  }  // end getItem

  public void setItem(Char_Set newItem)
  {
  // Sets the item field to the new value newItem.
  item  = newItem;
  }  // end setItem

  public TreeNode getLeft()
  {
  // Returns the reference to the left child.
    return leftChild;
  }  // end getLeft

  public void setLeft(TreeNode left)
  {
  // Sets the left child reference to left.
    leftChild  = left;
  }  // end setLeft

  public TreeNode getRight()
  {
  // Returns the reference to the right child.
    return rightChild;
  }  // end getRight

  public void setRight(TreeNode right)
  {
  // Sets the right child reference to right.
    rightChild  = right;
  }  // end setRight


}  // end TreeNode