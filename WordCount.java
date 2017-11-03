import java.io.IOException;
import java.util.ArrayList;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount<T> {
	String name;
	T dataStruct;
	ArrayList<String> frequency;
	int numUnique;
	
	public WordCount(String name, T dataStruct){
		this.name=name;
		this.dataStruct=dataStruct;
	}
	public WordCount(String name){
		this.name=name;
	}
	
    public String getName() {	return name;}
	public void setName(String name) {this.name = name;}
	public ArrayList<String> getFrequency() {return frequency;}
	public void setFrequency(ArrayList<String> frequency) {		this.frequency = frequency;}
	public int getNumUnique() {	return numUnique;}
	public void setNumUnique(int num_unique) {	this.numUnique = num_unique;}

	public static DataCount<String>[] countWords(String[] theArgs, boolean print) {
    		DataCounter<String> counter; 
		switch(theArgs[0]) {
			case "-a":	counter = new  AVL<String>();
				break;
			case "-h":	counter = new AHashTable<String>();
				break;
			default:		counter = new BinarySearchTree<String>();
				break;
		}
		try {
            FileWordReader reader = new FileWordReader("texts/"+theArgs[2]);//added to be able to keep text folder.
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + theArgs[2] + e);
            System.exit(1);
        }
		DataCount<String>[] counts = counter.getCounts();//put all the words in these counts

		    	switch(theArgs[1]) {
			    	case "-frequency": 
			            sortByDescendingCount(counts);
			            for(int i=0; i<counts.length;i++) {
			            		if(print)
			            			System.out.println(counts[i].count + " \t" + counts[i].data );
			                counts[0].totalWordCount+=counts[i].count;
			                //System.out.println(counts[1].totalWordCount+ ": total words");
			            }
			    		break;
			    	case "-num_unique":
			    		if(print)
			    			System.out.println(counter.getSize());//for hamlet we are getting 9701
			    		break;
		    	}  
	    	return counts;
    }
	
	

    /**
     * TODO Replace this comment with your own.
     * 
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     * 
     * This code uses insertion sort. You should modify it to use a different
     * sorting algorithm. NOTE: the current code assumes the array starts in
     * alphabetical order! You'll need to make your code deal with unsorted
     * arrays.
     * 
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     * 
     * @param counts array to be sorted.
     */
    private static <E extends Comparable<? super E>> void sortByDescendingCount(
            DataCount<E>[] counts) {
        for (int i = 1; i < counts.length; i++) {
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (counts[j].count >= x.count) {
                    break;
                }
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }


    /**
    *TK
    *Modified this to recongnize if we want to use bst, avl, or hashtabel. We
    *still need to decide if we want to modify the countWords() method to do all 
    *three or if we want to do a seperate method for each.
    *
    *Also still need to add in the if statements for the third argument
    */
    public static void main(String[] args) {
    	System.out.println("the args are: "+args[0]+ " " + args[1]+ " "+ args[2]);
        if (args.length != 3) {
            System.err.println("Usage: filename of document to analyze");
            System.out.println("Incorret amount of arguments");
            System.exit(1);
        }
        boolean print = true;//this is so count words will print things
        String[] theArgs = args;
        countWords(theArgs,true);
        System.out.println("hey");

    }
}
