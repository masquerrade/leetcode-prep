// class MyHashMap {

//     //Mwmber arrays to store keys and values
//     private int[] keys;
//     private int[] values;

//     //Class level constants
//     private static final int EMPTY=-1;
//     private static final int TOMBSTONE=-2;
//     private static final float LOAD_FACTOR=0.66f;
//     private static final int INITIAL_CAPACITY=16;
    


//     public MyHashMap() {
        
//     }
    
//     public void put(int key, int value) {
        
//     }
    
//     public int get(int key) {
        
//     }
    
//     public void remove(int key) {
        
//     }
// }


// //Gemini separate chaining solution
// class MyHashMap {

//     /**
//      * Internal node representing a key-value pair in a singly-linked bucket chain.
//      */
//     private static final class Node {
//         final int key;
//         int val;
//         Node next;

//         Node(int key, int val, Node next) {
//             this.key = key;
//             this.val = val;
//             this.next = next;
//         }
//     }

//     // Prime modulus reduces collision clustering caused by common key strides.
//     // 2069 provides ~480 keys/bucket at max 10^6 entries (LC constraints), 
//     // maintaining low chain length for interview constraints without rehashing bloat.
//     private static final int BUCKET_COUNT = 2069;

//     // Fixed array of bucket heads. Each bucket holds a dummy sentinel node.
//     private final Node[] buckets;

//     public MyHashMap() {
//         this.buckets = new Node[BUCKET_COUNT];
//         // Pre-allocate sentinel dummy nodes to remove null-checks during runtime operations.
//         for (int i = 0; i < BUCKET_COUNT; i++) {
//             this.buckets[i] = new Node(-1, -1, null);
//         }
//     }

//     /**
//      * Maps an integer key to its deterministic bucket index.
//      * Uses bitwise masking to guarantee a non-negative index even if keys are negative.
//      */
//     private int getBucketIndex(int key) {
//         //return (key & 0x7fffffff) % BUCKET_COUNT;
// 	   return Math.abs(key) % BUCKET_COUNT;
// }


//     /**
//      * Traverses a bucket chain to find the node PRECEDING the target key.
//      * 
//      * Invariant maintained:
//      * - Returns the node 'prev' such that either prev.next.key == targetKey,
//      *   or prev.next == null (targetKey does not exist in chain).
//      */
//     private Node findPredecessor(Node dummyHead, int key) {
//         Node prev = dummyHead;
//         Node curr = dummyHead.next;

//         // Loop Invariant: All examined nodes up to and including 'prev' do not match 'key'.
//         while (curr != null && curr.key != key) {
//             prev = curr;
//             curr = curr.next;
//         }
//         return prev;
//     }

//     public void put(int key, int value) {
//         int index = getBucketIndex(key);
//         Node prev = findPredecessor(buckets[index], key);

//         if (prev.next != null) {
//             // Case 1: Key already exists. Update value in-place.
//             prev.next.val = value;
//         } else {
//             // Case 2: Key does not exist. Append new node at tail of chain.
//             // Linking via prev.next correctly appends without needing head-null checks.
//             prev.next = new Node(key, value, null);
//         }
//     }

//     public int get(int key) {
//         int index = getBucketIndex(key);
//         Node prev = findPredecessor(buckets[index], key);

//         // If prev.next exists, target node is found. Otherwise, return -1.
//         return (prev.next != null) ? prev.next.val : -1;
//     }

//     public void remove(int key) {
//         int index = getBucketIndex(key);
//         Node prev = findPredecessor(buckets[index], key);

//         if (prev.next != null) {
//             // Unlink target node: garbage collector reclaims prev.next.
//             // Sentinel guarantees 'prev' is never null, making this safe even for the first real node.
//             prev.next = prev.next.next;
//         }
//     }
// }

//My solution separate chaining
class MyHashMap {

    //Inner class to define the node / content of the bucket
    private static final class Node{

        //Storing a key is important as it will be used in case of chaining to identify the value
        final int key;
        int value;
        Node next;

        //Constructor to initialize my node
        Node(int key,int value){
            this.key = key;
            this.value = value;
        }

    }

    //Member variables 
    //Array of buckets of a fixed size for seperate chaining
    private static final int BUCKET_COUNT=2069;
    private final Node[] buckets;


    public MyHashMap() {

        //Initialise the hasmap as array of Node
        buckets = new Node[BUCKET_COUNT];
        //Initialize each node with a dummy node
        for(int i=0;i<BUCKET_COUNT;i++){
            buckets[i]=new Node(-1,-1);
        }

        
    }

    
    public void put(int key, int value) {
        
        //Get the previous node with the key
        Node prev=getPreviousNode(key);
        
        //Updeate if existing or add if new
        if(prev.next == null){
            prev.next=new Node(key,value);
        }
        else{
            prev.next.value=value;
        }
                
    }
    
    public int get(int key) {

        //Find the prev node as it is safe
        Node prev=getPreviousNode(key);

        // val if exists . -1 if not
        return (prev.next==null)?-1:prev.next.value;
    }
    
    public void remove(int key) {

        //Find the prev node as it is safe
        Node prev=getPreviousNode(key);

        //Remove the next node
        if(prev.next != null){
            prev.next=prev.next.next;
        }
        
    }

    private int getBucketIndex(int key){
        return (key & 0x7fffffff) % BUCKET_COUNT; 
    }

    private Node getPreviousNode(int key){

        int index = getBucketIndex(key);

        Node dummynode = buckets[index];

        Node curr=dummynode;
        Node prev=dummynode;

        while(curr != null){
            if(curr.key == key){
                return prev;
            }
            prev=curr;
            curr=curr.next;
        }

        return prev;

    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */


//  class MyHashMap {

//     // I need a list  and list node
//     // Array of lists 
//     private static class Node{
//         //Node will have the key value also
//         int key;
//         int value;
//         Node next;
        
//         Node(int key,int val,Node next){
//             this.key=key;
//             this.value=val;
//             //If I use this then I need to Provide the next node when creating node
//             this.next=next;
//         }
//     }

//     private final Node [] bucket;
//     private final int SIZE=1000;

//     public MyHashMap() {
//         // The capacity cannot incerease because I'm using the hash function to find the index
//         bucket=new Node[SIZE];
        
//     }

//     //In put what I need to do is check if the key is already present . If it is present update the value or add the new value    

//     public void put(int key, int value) {

//         //First I need to calculate the hash using the hash function
//         int index=hash(key);
//         //Get the node at this key

//          Node node=findNode(index,key);

//          if(node!=null){
//             node.value=value;
//          }
//          else{
//             //I need to create new node with the next node as bucket[index] and then make the bucket[index] as the current node
//             Node newNode=new Node(key,value,bucket[index]);
//             bucket[index]=newNode;

//          }

//         // Node node=bucket[index];

//         //This whole logic can be done in the find node function
//         // //If it is null add a node if not null 
        
//         // while(node!=null){
//         //     if(node.key==key){
//         //         node.value=value;
//         //         return 
//         //     }
//         //     node=node.next;
//         // }

//     }
    
//     public int get(int key) {

//         //I'll just call the find node function
//         //If it returns null then node is not present

//         int index=hash(key);
//         Node n=findNode(index,key);
//         if(n==null){
//             return -1;
//         }
//         else{
//             return n.value;
//         }


        
//     }
    
//     public void remove(int key) {

//         //I need to remove node from both array as well as linked list
//         //For SLL I need the previous node as well as the current node to remove the current node so I need to traverse the Linked List
//         //Using key I need to get the index then traverse the LL

//         int index=hash(key);
//         Node node = bucket[index];

//         if(node==null){
//             return ;
//         }

//         if(node.key==key){
//             bucket[index]=node.next;
//         }

//         while(node.next!=null){
//             if(node.next.key==key){
//                 //This creates a self loop
//                 // node.next.next=node.next;
//                 node.next=node.next.next;
//                 //Here I need to return because node.next.next can be null
//                 return;
//             }
//             //If key is not found check the next node
//             node=node.next;
//         }

        
//     }


//     //Helper functions

//     public int hash(int key){
//         return key%SIZE;
//     }

//     public Node findNode(int index,int key){
//         //If find node return it or return null
//         Node node=bucket[index];

//         while(node!=null){
//             if(key==node.key){
//                 return node;
//             }

//             node=node.next;
//         }

//         return null;

//     }
// }


//  class MyHashMap {

//     /**
//      * Inner class to represent the nodes in the linked list for chaining.
//      * Each node stores a key-value pair and a pointer to the next node.
//      */
//     class ListNode {
//         int key;
//         int value;
//         ListNode next;

//         public ListNode(int key, int value, ListNode next) {
//             this.key = key;
//             this.value = value;
//             this.next = next;
//         }
//     }

//     // A prime number is often chosen for the size to help distribute keys more evenly.
//     private static final int SIZE = 10000; 
//     private ListNode[] buckets;

//     /** Initialize your data structure here. */
//     public MyHashMap() {
//         buckets = new ListNode[SIZE];
//     }
    
//     /** A simple hash function to map a key to a bucket index. */
//     private int hash(int key) {
//         return key % SIZE;
//     }

//     /** Helper function to find the node for a given key in a specific bucket. */
//     private ListNode findNode(int index, int key) {
//         ListNode node = buckets[index];
//         while (node != null) {
//             if (node.key == key) {
//                 return node;
//             }
//             node = node.next;
//         }
//         return null;
//     }

//     /** value will always be non-negative. */
//     public void put(int key, int value) {
//         int index = hash(key);
        
//         // Find if the key already exists in the bucket's list.
//         ListNode existingNode = findNode(index, key);
        
//         if (existingNode != null) {
//             // If the key exists, update its value.
//             existingNode.value = value;
//         } else {
//             // If the key doesn't exist, add a new node to the front of the list.
//             ListNode newNode = new ListNode(key, value, buckets[index]);
//             buckets[index] = newNode;
//         }
//     }
    
//     /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
//     public int get(int key) {
//         int index = hash(key);
        
//         ListNode node = findNode(index, key);
        
//         return (node != null) ? node.value : -1;
//     }
    
//     /** Removes the mapping of the specified value key if this map contains a mapping for the key */
//     public void remove(int key) {
//         int index = hash(key);
//         ListNode head = buckets[index];
        
//         if (head == null) {
//             return; // Nothing to remove
//         }
        
//         // If the node to be removed is the head of the list
//         if (head.key == key) {
//             buckets[index] = head.next;
//             return;
//         }

//         // Search for the node to remove
//         ListNode prev = head;
//         ListNode curr = head.next;
//         while (curr != null) {
//             if (curr.key == key) {
//                 // Remove the node by linking the previous node to the next one
//                 prev.next = curr.next;
//                 return;
//             }
//             prev = curr;
//             curr = curr.next;
//         }
//     }
// }


