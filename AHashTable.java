import java.util.*;
//import HashTable.HashNode;

public class AHashTable <E> implements DataCounter<E>{

	AHashNode[] wordArray=new AHashNode[8];	//each index in the list contains a AHashNode array
	int size = wordArray.length;
	static double dec=.875;
	
	//this is the final method called
	@Override
	public void incCount(E data) {
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public DataCount<E>[] getCounts() {
		// TODO Auto-generated method stub
		return null;
	}
	

    //Goes through each character of the string "data" and adds the value to a running sum
    //this way each word from the text file will have its own unique value a=1, b=2, c=3, ect
	//what ever number comes out here is where the "word" will be put in the arrays index
    public int hash(Object data) {//hmm...
    		String sData = (String) data;
    		System.out.println(sData);
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
    			newTable.insert(n.data, newTable.wordArray[index]);//TODO put data in the table and treat it like a linked list
    		}
    		return this;
    }
    
    public void insert(E data) {
    		AHashNode node = new AHashNode(data);
    		
    }
    
    public static void main (String[] args) {
    		AHashTable ht = new AHashTable();
    		System.out.println(ht.hash("ab"));//10+11	
    }
}
