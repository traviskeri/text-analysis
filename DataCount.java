
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
    /**
     * Create a new data count.
     * 
     * @param data the data element whose count we are recording.
     * @param count the count for the data element.
     */
    DataCount(E data, int count) {
        this.data = data;
        this.count = count;
        this.totalWordCount=0;
    }
}
