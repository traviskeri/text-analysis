/**
 * TODO Replace this comment with your own.
 * 
 * Stub code for an implementation of a DataCounter that uses a hash table as
 * its backing data structure. We included this stub so that it's very clear
 * that HashTable works only with Strings, whereas the DataCounter interface is
 * generic.  You need the String contents to write your hashcode code.
 */
import java.util.ArrayList;
import java.lang.String;





public class HashTable<E> implements DataCounter<E>{

    //Inner class for the hashnode
      public class HashNode{
        E key;
        int value;

        HashNode next;

        //constructor for HashNode
        public HashNode(E key, int value){
            this.key = key;
            this.value = value;
        }
    }
  
    private ArrayList<HashNode> bucketArray;

    //Number of buckets in the hashtable
    private int numBuckets;

    //Number of unique entry in hashtable
    private int size;

    //Creating the bucketArry setting it to the default of 10
    public void map(){
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;

        for(int i; i < numBuckets; i++){
            bucketArray.insert(null);
        }   
    }

    //Returns a boolean for if the hashtable is empty
    public boolean isEmpty(){return size == 0;}

    //Takes the key hashes it to a unigue value then mods it to the size of the hashtable
    private int findBucket(E key){
        int hash;
        String temp = key.toString();
        for(int i = 0; i < temp.length(); i++){
            hash = hash * 7 + temp.charAt(i);
        }
        int index = hash % numBuckets;
        return index;
    }

    //Returns the value of a HashNode for a key
    public int get(E key){

        int bucketIndex = findBucket(key);
        HashNode head = bucketArray.get(bucketIndex);

        while(head != null){
            if(head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    //Inserts a HashNode into the hasetable
    public void insert(E key){
        
        int bucketIndex = findBucket(key);
        HashNode head = bucketArray.get(bucketIndex);

        /*while(head != null){
            if (head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }*/

        size++;
        head = bucketArray.get(bucketIndex);
        HashNode newNode = new HashNode(key, 1);
        newNode.next = head;

        if((1.0*size)/numBuckets >= 0.7){
            ArrayList<HashNode> temp = bucketArray;
            bucketArray = new ArrayList<>(numBuckets * 2);
            numBuckets = 2 * numBuckets;
            size = 0;
            for(int i = 0; i < numBuckets; i++){
                bucketArray.insert(null);
            }

            for(HashNode head : temp){
                while(head != null){
                    insert(head.key, head.value);
                    head = head.next;
                }
            }

        }

    }

    //Removes a HashNode from the hashtable
    public int remove(E key){

        int bucketIndex = findBucket(key);

        HashNode head = bucketArray.get(bucketIndex);
        HashNode prev = null;

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
    public DataCount<E>[] getCounts() {
        @SuppressWarnings("unchecked")
        DataCount<E>[] counts = new DataCount[size];
        HashNode current;
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
    public void incCount(E key) {
        int bucketIndex = findBucket(key);
        HashNode current = bucketArray.get(bucketIndex);

        while(current != null){
            if(current.key.equals(key)){
                current.value= current.value + 1;
                return;
            }
            current = current.next;
        }

        insert(key);

    }

     public static void main(String[] args)
    {
        HashTable<String>ht = new HashTable<>();
        ht.incCount("this");
        ht.incCount("coder");
        ht.incCount("this");
        ht.incCount("hi");
        System.out.println(ht.getSize());
        System.out.println(ht.remove("this"));
        System.out.println(ht.remove("this"));
        System.out.println(ht.getSize());
        System.out.println(ht.isEmpty());
    }

}
