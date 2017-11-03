
public class DataCount <E> {
    /**
     * The data element whose count we are recording.
     */
    E data;

    /**
     * The count for the data element.
     */
    int count;

    //total amount of words
    public double totalWordCount;
    
    //name of the file
    
    public String nameOfFile;
    /**
     * Create a new data count.
     * 
     * @param data the data element whose count we are recording.
     * @param count the count for the data element.
     */
    DataCount(Object data, int count) {
        this.data =(E) data;
        this.count = count;
        this.totalWordCount=0;
    }
    
}
