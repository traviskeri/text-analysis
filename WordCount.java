import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {

    private static void countWords(String[] args) {
    	DataCounter<String> counter; 
    		if(args[0]=="-b")
    			counter = new BinarySearchTree<String>();
    		else if(args[0]=="-a")
    			counter = new AVL<String>();
    		else
    			counter = new BinarySearchTree<String>();

        try {
            FileWordReader reader = new FileWordReader(args[2]);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + args[2] + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        for (DataCount<String> c : counts)
            System.out.println(c.count + " \t" + c.data);
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
        if (args.length != 3) {
            System.err.println("Usage: filename of document to analyze");
            System.out.println("Incorret amount of arguments");
            System.exit(1);
        }
        //
        
        countWords(args);

    }
}
