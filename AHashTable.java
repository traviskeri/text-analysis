import java.util.*;
//import HashTable.HashNode;

public class AHashTable<E extends Comparable<? super E>> implements DataCounter<E>{

	AHashNode[] wordArray=new AHashNode[8];	//each index in the list contains a AHashNode array
	int size = wordArray.length;
	static double dec=.875;
	
	//this is turned into a data counter
	@Override
	public void incCount(E data) {
		this.resize();
		this.insert(data);
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
	public DataCount<E>[] binarySearch(int front, int back, DataCount<E>[] counts) {
		//lets go through front first
		counts[front] = new DataCount<E>((E) this.wordArray[front].data, this.wordArray[front].count);
		
		
		return counts;
	}
	
	//we know there is something in this table	
	public DataCount<E>[] linearCount(int front, int back, DataCount<E>[] counts) {
		counts[front] = new DataCount<E>((E) this.wordArray[front].data, this.wordArray[front].count);
		
		
		return counts;
	}
		
	public void searchDownLists() {
		
	}

    //Goes through each character of the string "data" and adds the value to a running sum
    //this way each word from the text file will have its own unique value a=1, b=2, c=3, ect
	//what ever number comes out here is where the "word" will be put in the arrays index
    public int hash(Object data) {//hmm...
    		String sData = (String) data;
    		int sum = 0;
    		for(int i = 0; i < sData.length(); i++)
    			sum += Character.getNumericValue(sData.charAt(i));
    		return sum%this.size;
    }
    
    //takes the current HashTable and checks if it needs to be resized
    //if it does then double the size and fill in the table appropriately
    public AHashTable<E> resize(){
    	int filledCells=0;
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
    			System.out.println(newNode.data);
    		}
    		else
    			insert(newNode, this.wordArray[index]);
    }
    
    public void insert(AHashNode newNode, AHashNode focusNode) {
    		//check if the nodes hold the same data (word)
    		if( focusNode.data.compareTo(newNode.data)==0) {
    			focusNode.count++;
    			return;
    		}
    		//find the head node 
    		if(focusNode.next==null)
    			focusNode.next=newNode;
    		else
    			insert(newNode, focusNode.next);
    }
    
    public static void main (String[] args) {
    		AHashTable ht = new AHashTable();
    		//THIS SECTION ONLY DEAL WITH A 8 WIDE TABLE
    		//System.out.println(ht.hash("ab"));//(10+11)%8=5
    		System.out.println("hi" + ht.hash("hi"));
    		System.out.println("hey" + ht.hash("hey"));
    		System.out.println("heyy" + ht.hash("heyy"));
    		//try out insert
    		ht.insert("hi");
    		ht.insert("hi");
    		ht.insert("hey");
    		ht.insert("heyy");
    		//notice a linked list has been made here
    		System.out.println(ht.wordArray[3].data);//hi
    		System.out.println(ht.wordArray[3].next.data);//heyy
    		
    }
}
