import java.util.*;
//import HashTable.HashNode;

public class AHashTable<E extends Comparable<? super E>> implements DataCounter<E>{

	AHashNode[] wordArray=new AHashNode[8];	//each index in the list contains a AHashNode array
	int size = wordArray.length;
	static double dec=.7;
	
	//this is turned into a data counter
	@Override
	public void incCount(E data) {
		checkSize();
		System.out.println(this.wordArray.length);
		insert(data);
	}

	//get the amount of cells in the hash table
	@Override
	public int getSize() {
		return size;
	}
	
	//this is the final method called 
	//return that array of data counts
	@Override
	public DataCount<E>[] getCounts() {
		DataCount<E>[] counts  = new DataCount[this.size];
		if(this.wordArray!=null)
			counts = linearCount(0, this.size-1, counts); 
		return counts;
	}
	
	//we know there is something in this table	
	public DataCount<E>[] linearCount(int front, int back, DataCount<E>[] counts) {
		int arrayIndex = 0;
		int countIndex = 0;
		while(arrayIndex<=back) {
			//check if the index for word array has a valid hashNode
			if(wordArray[arrayIndex]!=null) {
				counts[countIndex] = new DataCount<E>((E) wordArray[arrayIndex].data, wordArray[arrayIndex].count);
				countIndex++;
				//Search through the linked list add each value
				if(wordArray[arrayIndex].next!=null) searchDownLists(countIndex, counts, wordArray[arrayIndex].next);
			}
			arrayIndex++;
		}
		return counts;
	}
		
	//add the rest of linked list to the counts (More lifting done here than linearCount)
	public void searchDownLists(int countIndex, DataCount<E>[] counts, AHashNode currentNode) {
		if(currentNode!=null) {
			counts[countIndex] = new DataCount<E>((E) currentNode.data, currentNode.count);
			countIndex++;
			searchDownLists(countIndex, counts, currentNode.next);
		}
	}

    //Goes through each character of the string "data" and adds the value to a running sum
    //this way each word from the text file will have its own unique value a=1, b=2, c=3, ect
	//what ever number comes out here is where the "word" will be put in the arrays index
    public int hash(Object data) {//hmm...
    		String sData = (String) data;
    		int hash = 0;
    		for(int i = 0; i < sData.length(); i++)
    			hash = hash*31 + Character.getNumericValue(sData.charAt(i));
    		if(hash<0)
    			hash*=2;
    		System.out.println(Math.abs(hash%size));
    		return Math.abs(hash%size);
    }
    
    //takes the current HashTable and checks if it needs to be checkSized
    //if it does then double the size and fill in the table appropriately
    public AHashTable<E> checkSize(){
    	double filledCells=0;
    	AHashTable<E> oldTable = this;
		for(AHashNode a: oldTable.wordArray)
			filledCells += (a==null)? 0 : 1;
		//after this we have needed size but possible empty
		this.wordArray = (oldTable.size*dec<filledCells)? new AHashNode[oldTable.size*2]: wordArray;
		if(this.wordArray.length!=oldTable.wordArray.length) return fillTable(oldTable, this);//fill it in properl
		return oldTable;
    }
    
    //go through all values of the old hash table and fill in the new one
    public AHashTable<E> fillTable(AHashTable<E> oldTable ,  AHashTable<E> newTable) {
    		int index;
    		this.size*=2;
    		for(AHashNode n: oldTable.wordArray) {
    			index = hash(n.data);
    			newTable.insert((E)n.data);//TODO put data in the table and treat it like a linked list
    		}
    		return this;
    }
    
    public void insert(E data) {
    		AHashNode newNode = new AHashNode(data);
    		int index = hash(data);
    		if (this.wordArray[index]==null) {//best case just fill in the table
    			this.wordArray[index]=newNode;
    			//System.out.println(newNode.data+" : is now taking a new cell");
    		}
    		else
    			insert(newNode, this.wordArray[index]);
    }
    
    public void insert(AHashNode newNode, AHashNode focusNode) {
    		//check if the nodes hold the same data (word)
    		if( focusNode.data.compareTo(newNode.data)==0) {
    			focusNode.count++;
    			//System.out.println(newNode.data+ " : was a match and its count is now: " + focusNode.count);
    			return;
    		}
    		//find the head node 
    		if(focusNode.next==null) {
    			focusNode.next=newNode;
    			//System.out.println(focusNode.next.data + " : is put in the linked list");
    		}
    		else
    			insert(newNode, focusNode.next);
    }
    
    public static void main (String[] args) {
    		AHashTable ht = new AHashTable();
    		DataCount[] dc = new DataCount[20];
    		//THIS SECTION ONLY DEAL WITH A 8 WIDE TABLE
/*    		System.out.println(ht.hash("ab"));//(10+11)%8=5
    		System.out.println("hi" + ht.hash("hi"));
    		System.out.println("hey" + ht.hash("hey"));
    		System.out.println("heyy" + ht.hash("heyy"));
    		System.out.println("hello" + ht.hash("hello"));
 */   		
    		//try out insert
    		for(int i = 0; i<5; i++) {
	    		ht.insert("hi");
	    		ht.insert("hey");
	    		ht.insert("heyy");
  			ht.insert("hello");
    		}
    		//notice a linked list has been made here
//    		System.out.println(ht.wordArray[3].data);//hi
//    		System.out.println(ht.wordArray[3].next.data);//heyy
    		dc = ht.getCounts();
 
    		
    		
    		//checkSize() testing
    		String[] checkArray = {"a","b","c","d","e","f","g","h"};
    		for(int i = 0; i<checkArray.length; i++)
    			ht.insert(checkArray[i]);
    		
    		ht.checkSize();
    		System.out.println(ht.wordArray.length + " this should be larger than 8");
    }
}
