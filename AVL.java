
//#1: So should I store nodes that are a string and that hold the number how many times that string has appeared?

//#2: If I am storing the nodes by string do I need to organized all the string node in alphabetical order?
import java.lang.Object;  
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.lang.String;

public class AVL<E> extends BinarySearchTree implements DataCounter{
	
	protected int lastComparedValue=0;
	protected E lastWord = (E) "";

		private BSTNode findMin(BSTNode t){
			if(t == null){
				return null;
			}
			else if(t.left == null){
				return t;
			}
			else{
				return findMin(t.left);
			}
		}

	    /** {@inheritDoc} */
		//copied from BinarySearchTree class
	    public void incCount(String data) {
	        if (overallRoot == null) {
	            overallRoot = new BSTNode(data);
	        } else {
	            // traverse the tree
	            BSTNode currentNode = overallRoot;
	            while (true) {

	                // compare the data to be inserted with the data at the current
	                // node
	                int cmp = data.compareTo((String)currentNode.data);

	                if (cmp == 0) {
	                    // current node is a match
	                    currentNode.count++;
	                    return;
	                } else if (cmp < 0) {
	                    // new data goes to the left of the current node
	                    if (currentNode.left == null) {
	                        currentNode.left = new BSTNode(data);
	                        currentNode.left.parent=currentNode;
	                        rebalance(currentNode.left);
	                        return;
	                    }
	                    currentNode = currentNode.left;
	                } else {
	                    // new data goes to the right of the current node
	                    if (currentNode.right == null) {
	                        currentNode.right = new BSTNode(data);
	                        currentNode.right.parent=currentNode;
	                        rebalance(currentNode.right);
	                        return;
	                    }
	                   currentNode = currentNode.right;
	                }
	            }
	        }
	    }
		
		
//		public void insert(String data) {
//			BSTNode t = new BSTNode((Comparable) data);
//			BSTNode currentNode= overallRoot;
//			System.out.println(t.data);
//			int cmp = t.data.compareTo(lastWord);
//			if(overallRoot==null) {
//				overallRoot=new BSTNode(t.data, null);
//				lastWord = (E) t.data;
//				}
//			else if(cmp==0)
//				
//			//new node greater for roots only
//			else if(cmp>0){//TODO NEED METHOD TO ORDER STRINGS
//				if(overallRoot.right==null) {
//					overallRoot.right = new BSTNode(t.data, overallRoot);
//					lastWord = (E) t.data;
//					overallRoot.right.count++;
//					rebalance(overallRoot.right);	
//				}
//				else
//					insert(t, overallRoot.right);
//			}
//			//new node lesser
//			else if(cmp>0) {//TODO NEED METHOD TO ORDER STRINGS overallRoot.left.data
//				if(overallRoot.left==null) {
//					overallRoot.left = new BSTNode(t.data, overallRoot);
//					lastWord = (E) t.data;
//					rebalance(overallRoot.left);
//					overallRoot.left.count++;
//				}
//				else
//					insert(t, overallRoot.left);
//			}
//		}
//		
//		public void insert(BSTNode t, BSTNode focusNode) {
//			//new node greater
//			int cmp = t.data.compareTo(lastWord);
//			if(cmp==0) {
//				t.count++;
//			}
//			else if(cmp>0){//TODO NEED METHOD TO ORDER STRINGS
//				if(focusNode.right==null) {
//					focusNode.right = new BSTNode(t.data, focusNode);
//					rebalance(focusNode.right);
//					lastWord = (E) t.data;
//				}
//				else {
//					insert(t, focusNode.right);
//				}
//			}
//			else if(cmp<0) {//TODO NEED METHOD TO ORDER STRINGS
//				if(focusNode.left==null) {
//					focusNode.left = new BSTNode(t.data, focusNode);
//					rebalance(focusNode.left);
//					lastWord = (E) t.data;
//				}
//				else {
//					insert(t, focusNode.left);
//				}
//			}
//		}	
		

		 private BSTNode leftRotation(BSTNode x) {
		 	BSTNode y = x.right;
	        y.parent = x.parent;
	        x.right = y.left;
	        y.left = x;
	        if (x.right != null)x.right.parent = x;
	        x.parent = y;
	        reattach(x,y);
	        balance(x, y);
	        //System.out.print("Left ");
	        return y;
	    }

		private BSTNode rightRotation(BSTNode x) {
			BSTNode y = x.left;
	        y.parent = x.parent;
	        x.left = y.right;
	        y.right = x;
	        if (x.left != null)x.left.parent = x;
	        x.parent = y;
	        reattach(x,y);
	        balance(x, y);
	        //int data = BSTNode.data;
	        //System.out.print("Right ");
	        return y;
		}
		
		private BSTNode leftRight(BSTNode focusNode) {
			focusNode.left = leftRotation(focusNode.left);
	        return rightRotation(focusNode);
	    }
	 
	    private BSTNode rightLeft(BSTNode focusNode) {
	    	focusNode.right = rightRotation(focusNode.right);
	        return leftRotation(focusNode);
	    }
	    
	    private void reattach(BSTNode x, BSTNode y) {
	        if (y.parent != null) {
	            if (y.parent.right == x) {
	                y.parent.right = y;
	            } else {
	                y.parent.left = y;
	            }
	       }
	    }
	    

	    private void deleteoverallRoot(BSTNode BN) {	
	    	     if (BN.left == null && BN.right == null) {
	            if (BN.parent == null) {
	                overallRoot = null;
	            } 
	            else {
	                BSTNode parent = BN.parent;
	                if (parent.left == BN) {
	                    parent.left = null;
	                } else {
	                    parent.right = null;
	                }
	                rebalance(parent);
	            }
	            return;
	        }
	    	     else if (BN.left != null) {
	        		BSTNode child  = BN.left;
	            while (child.right != null) child = child.right;
	            BN.data = child.data;
	            deleteoverallRoot(child);
	        }
	    	     else {
	        		this.height=5;
	            BSTNode child = BN.right;
	            while (child.left != null) child = child.left;
	            BN.data = child.data;
	            deleteoverallRoot(child);
	        }
	    }
		private void rebalance(BSTNode n) {
	        balance(n);
	        if (n.balance < -1) 
	        		n = (height(n.left.left) >= height(n.left.right))? rightRotation(n): leftRight(n);
	        else if (n.balance>1) 
	        		n=(height(n.right.right) >= height(n.right.left))? leftRotation(n): rightLeft(n);
	    		if (n.parent == null) overallRoot = n;
	        else rebalance(n.parent);
	    }

	    private void balance(BSTNode a, BSTNode b) {
				a.height = height(a);
	            a.balance = height(a.right) - height(a.left);
	            b.height = height(b);
	            b.balance = height(b.right) - height(b.left);
	    }

	    private void balance(BSTNode a) {
	    		a.height = height(a);
	        a.balance = height(a.right) - height(a.left);
	    }
	    
	
	private int height(BSTNode focusNode) {
		if(focusNode==null)
			return 0;		
		int leftHeight = height(focusNode.left);
		int rightHeight = height(focusNode.right);
		if(leftHeight>rightHeight) {
			this.height = leftHeight+1;
			return (leftHeight+1);
		}
		else {
			this.height = leftHeight+1;
			return (rightHeight+1);
		}
	}

	public int height() {
		return this.height;
	}
	
	private int getBalance(BSTNode focusNode) {
		return (focusNode==null)?  0: height(focusNode.left) - height(focusNode.right);
	    }

	public BSTNode getoverallRoot(){
		return overallRoot;
	}

	public static void main (String[] args) {
		AVL avl = new AVL<>();
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		for(String words: list) {			
			avl.incCount(words);
		}
		avl.getCounts();
		System.out.println(avl.overallRoot.data+" should be d");
		System.out.println(avl.overallRoot.left.data+" should be b");
		System.out.println(avl.overallRoot.right.data+" should be f");
		System.out.println(avl.overallRoot.left.left.data+" should be a");
		System.out.println(avl.overallRoot.left.right.data+" should be c");
		System.out.println(avl.overallRoot.right.left.data+" should be e");
		System.out.println(avl.overallRoot.right.right.data+" should be g");
		
	}
}
