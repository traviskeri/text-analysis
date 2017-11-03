//import HashTable.HashNode;

public class AHashNode <E>{
    E data;
    AHashNode next;
    int count;
    	Integer index;

    public AHashNode(E data) {
    		this.data = data;
    		this.count =1;
    		this.next = null;
    }
    
    //constructor for HashNode
    public AHashNode(E data, Integer count){
        this.data = data;//the word from the text
        this.count = (int)count;//this is for the word count
    }


}
