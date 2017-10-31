/**
 * TODO Replace this comment with your own.
 * 
 * Stub code for an implementation of a DataCounter that uses a hash table as
 * its backing data structure. We included this stub so that it's very clear
 * that HashTable works only with Strings, whereas the DataCounter interface is
 * generic.  You need the String contents to write your hashcode code.
 */
import java.util.ArrayList;

public class HashTable<E> implements DataCounter<String> {

    //Inner class for the hashnode
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

    //Number of buckets in the hashtable
    private int numBuckets;

    //Number of unique entry in hashtable
    private int size;

    //Creating the bucketArry setting it to the default of 10
    public map(){
        bucketArray = new ArrayList<>():
        numBuckets = 10;
        size = 0;

        for(int i; i < numBuckets; i++){
            bucketArray.insert(null);
        }   
    }

    //Returns a boolean for if the hashtable is empty
    public boolean isEmpty(){return size == 0;}

    //Takes the key hashes it to a unigue value then mods it to the size of the hashtable
    private in findBucket(k key){
        int hash;
        for(int i = 0; i < k.length; i++){
            hash = hash * 7 + k.chatAt(i);
        }
        int index = hash % numBuckets;
        return index;
    }

    //Returns the value of a HashNode for a key
    public v get(k key){

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

    //Inserts a HashNode into the hasetable
    public void insert(k key, v value){
        
        int bucketIndex = findBucket(key);
        HashNode<k, v> head = bucketArray.get(bucketIndex);

        /*while(head != null){
            if (head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }*/

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

    //Removes a HashNode from the hashtable
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
        @SuppressWarnings("unchecked")
        DataCount<E>[] counts = new DataCount[size];
        HashNode<k, v> current;
        int j = 0;
        
        if(!bucketArry.isEmpty()){
        
            for(int i = 0; i < numBuckets; i++){
                current = bucketArray.get(i);
        
                while(current != null){
                    counts[j++] = new DataCount<E>(current.key, current.value);
                    current = current.next;
                }
            }
        }
        return counts;
    }

    /** {@inheritDoc} Return the number of unique elements in the hashtable*/
    public int getSize() {
        return size;
    }

    /** {@inheritDoc} */
    public void incCount(k key) {
        int bucketIndex = findBucket(key);
        HashNode<k, v> current = bucketArray.get(bucketIndex);

        while(current != null){
            if(current.key.equals(key)){
                value++;
                return;
            }
            current = current.next;
        }

        insert(key);

    }

}
