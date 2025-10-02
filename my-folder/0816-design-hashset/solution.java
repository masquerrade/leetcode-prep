class MyHashSet {

    //Here I'm going to use a linked list collection because I need to store only one value
    //I will keep 

    private final int SIZE=1000;

    private final LinkedList<Integer> [] bucket ;

    public MyHashSet() {

        // bucket= new LinkedList<Integer> [SIZE];
        //Correct way to declare array of collection
        bucket= new LinkedList[SIZE];

        //This won't work because the enhanced for loop gets the copy of the variable instead of getting the actual variable
        // for(LinkedList l:bucket){
        //     l=new LinkedList<Integer>();
        // }

        //We need to use the traditional while loop
        for(int i=0;i<SIZE;i++){
            bucket[i]=new LinkedList<Integer>();
        }
        
    }
    
    public void add(int key) {

        //For adding I need to calculate the hash
        int index=hash(key);
        LinkedList<Integer> ll=bucket[index];

        if(!ll.contains(key)){
            ll.add(key);
        }
        
    }
    
    public void remove(int key) {
        //For removing also I need to check whether it is present
         //For adding I need to calculate the hash
        int index=hash(key);
        LinkedList<Integer> ll=bucket[index];

        //No need to check the contains and I need to pass the integer and not the int
        // if(ll.contains(key)){
        //     ll.remove(key);
        // }

        ll.remove(Integer.valueOf(key));
        

        
    }
    
    public boolean contains(int key) {
        int index=hash(key);
        LinkedList<Integer> ll=bucket[index];
        return ll.contains(key);
        
    }

    public int hash(int key){
        return key%SIZE;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */

//  import java.util.LinkedList;

// class MyHashSet {

//     // The array of buckets. Each bucket is a LinkedList to handle collisions.
//     private LinkedList<Integer>[] buckets;
//     // A prime number is often chosen for the size to help distribute keys more evenly.
//     private static final int KEY_SPACE = 769;

//     /** Initialize your data structure here. */
//     public MyHashSet() {
//         buckets = new LinkedList[KEY_SPACE];
//         for (int i = 0; i < KEY_SPACE; i++) {
//             buckets[i] = new LinkedList<>();
//         }
//     }
    
//     /** Adds a value to the HashSet. */
//     public void add(int key) {
//         int hashKey = key % KEY_SPACE;
//         LinkedList<Integer> bucket = buckets[hashKey];
//         // Add the key only if it's not already present to ensure uniqueness.
//         if (!bucket.contains(key)) {
//             bucket.add(key);
//         }
//     }
    
//     /** Removes a value from the HashSet. Returns nothing. */
//     public void remove(int key) {
//         int hashKey = key % KEY_SPACE;
//         LinkedList<Integer> bucket = buckets[hashKey];
//         // Use Integer.valueOf(key) to remove the object, not the element at an index.
//         bucket.remove(Integer.valueOf(key));
//     }
    
//     /** Returns true if this set contains the specified element */
//     public boolean contains(int key) {
//         int hashKey = key % KEY_SPACE;
//         LinkedList<Integer> bucket = buckets[hashKey];
//         return bucket.contains(key);
//     }
// }

// /**
//  * Your MyHashSet object will be instantiated and called as such:
//  * MyHashSet obj = new MyHashSet();
//  * obj.add(key);
//  * obj.remove(key);
//  * boolean param_3 = obj.contains(key);
//  */
