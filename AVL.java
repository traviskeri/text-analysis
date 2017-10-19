//#1: So should I store nodes that are a string and that hold the number how many times that string has appeared?

//#2: If I am storing the nodes by string do I need to organized all the string node in alphabetical order?
import java.lang.Object;  
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
	
public class AVL extends BinarySearchTree implements DataCounter{
	
	

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
		
		public void insert(int data) {
			if(overallRoot==null)
				overallRoot=new BSTNode(data, null);
			//new node greater
			else if(true){//TODO NEED METHOD TO ORDER STRINGS
				if(overallRoot.right==null) {
					overallRoot.right = new BSTNode(data, overallRoot);
					rebalance(overallRoot.right);}
				else
					insert(data, overallRoot.right);
			}
			//new node lesser
			else if(true) {//TODO NEED METHOD TO ORDER STRINGS
				if(overallRoot.left==null) {
					overallRoot.left = new BSTNode(data, overallRoot);
					rebalance(overallRoot.left);}
				else
					insert(data, overallRoot.left);
			}
		}
		
		public void insert(int data, BSTNode focusNode) {
			//new node greater
			if(true){//TODO NEED METHOD TO ORDER STRINGS
				if(focusNode.right==null) {
					focusNode.right = new BSTNode(data, focusNode);
					rebalance(focusNode.right);
				}
				else {
					insert(data, focusNode.right);
				}
			}
			else if(true) {//TODO NEED METHOD TO ORDER STRINGS
				if(focusNode.left==null) {
					focusNode.left = new BSTNode(data, focusNode);
					rebalance(focusNode.left);
				}
				else {
					insert(data, focusNode.left);
				}
			}
			
		}	
		

		 private BSTNode leftRotation(BSTNode x) {
		 	BSTNode y = x.right;
	        y.parent = x.parent;
	        x.right = y.left;
	        y.left = x;
	        if (x.right != null)x.right.parent = x;
	        x.parent = y;
	        reattach(x,y);
	        balance(x, y);
	        System.out.print("Left ");
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
	        System.out.print("Right ");
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

	
}
