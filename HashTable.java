/**
 * TODO Replace this comment with your own.
 * 
 * Stub code for an implementation of a DataCounter that uses a hash table as
 * its backing data structure. We included this stub so that it's very clear
 * that HashTable works only with Strings, whereas the DataCounter interface is
 * generic.  You need the String contents to write your hashcode code.
 */
import java.util.ArrayList;

public class HashTable implements DataCounter<String> {

    public class HashNode<k, v>{
        k key;
        v value;

        HashNode<k, v> next;

        //constructor for HashNode
        public HashNode(k key, v value){
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<HashNode<k, v>> bucketArray;

    private int numBuckets;

    private int size;

    public map(){
        bucketArray = new ArrayList<>():
        numBuckets = 10;
        size = 0;

        for(int i; i < numBuckets; i++){
            bucketArray.insert(null);
        }   
    }

    public int size(){return size;}

    public boolean isEmpty(){return size == 0;}

    private in findBucket(k key){
        int hashcode = key.hashcode();
        int index = hashcode % numBuckets;
        return index;
    }

    public V get(k key){

        int bucketIndex = findBucket(key);
        HashNode<k, v> head = bucketArray.get(bucketIndex);

        while(head != null){
            if(head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public void insert(k key, v value){
        
        int bucketIndex = findBucket(key);
        HashNode<k, v> head = bucketArray.get(bucketIndex);

        while(head != null){
            if (head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<k, v> newNode = new HashNode<k, v>(key, value);
        newNode.next = head;

        if((1.0*size)/numBuckets >= 0.7){
            ArrayList<HashNode<k, v> temp = bucketArray;
            bucketArray = new ArrayList<>(numBuckets * 2);
            numBuckets = 2 * numBuckets;
            size = 0;
            for(int i = 0; i < numBuckets; i++){
                bucketArray.insert(null);
            }

            for(HashNode<k, v> head : temp){
                while(head != null){
                    insert(head.key, head.value);
                    head = head.next;
                }
            }

        }

    }

    public v remove(k key){

        int bucketIndex = findBucket(key);

        HashNode<k, v> head = bucketArray.get(bucketIndex);
        HashNode<k, v> prev = null;

        while(head != null){
            if(head.key.equals(key))
                break;
            prev = head;
            head = head.next;
        }

        if(head == null)
            return null;

        size--;

        if(prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);

        return head.value;

    }

    /** {@inheritDoc} */
    public DataCount<String>[] getCounts() {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    /** {@inheritDoc} */
    public void incCount(String data) {
        // TODO Auto-generated method stub

    }

}
