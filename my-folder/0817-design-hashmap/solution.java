class MyHashMap {

    // I need a list  and list node
    // Array of lists 
    private static class Node{
        //Node will have the key value also
        int key;
        int value;
        Node next;
        
        Node(int key,int val,Node next){
            this.key=key;
            this.value=val;
            //If I use this then I need to Provide the next node when creating node
            this.next=next;
        }
    }

    private final Node [] bucket;
    private final int SIZE=1000;

    public MyHashMap() {
        // The capacity cannot incerease because I'm using the hash function to find the index
        bucket=new Node[SIZE];
        
    }

    //In put what I need to do is check if the key is already present . If it is present update the value or add the new value    

    public void put(int key, int value) {

        //First I need to calculate the hash using the hash function
        int index=hash(key);
        //Get the node at this key

         Node node=findNode(index,key);

         if(node!=null){
            node.value=value;
         }
         else{
            //I need to create new node with the next node as bucket[index] and then make the bucket[index] as the current node
            Node newNode=new Node(key,value,bucket[index]);
            bucket[index]=newNode;

         }

        // Node node=bucket[index];

        //This whole logic can be done in the find node function
        // //If it is null add a node if not null 
        
        // while(node!=null){
        //     if(node.key==key){
        //         node.value=value;
        //         return 
        //     }
        //     node=node.next;
        // }

    }
    
    public int get(int key) {

        //I'll just call the find node function
        //If it returns null then node is not present

        int index=hash(key);
        Node n=findNode(index,key);
        if(n==null){
            return -1;
        }
        else{
            return n.value;
        }


        
    }
    
    public void remove(int key) {

        //I need to remove node from both array as well as linked list
        //For SLL I need the previous node as well as the current node to remove the current node so I need to traverse the Linked List
        //Using key I need to get the index then traverse the LL

        int index=hash(key);
        Node node = bucket[index];

        if(node==null){
            return ;
        }

        if(node.key==key){
            bucket[index]=node.next;
        }

        while(node.next!=null){
            if(node.next.key==key){
                //This creates a self loop
                // node.next.next=node.next;
                node.next=node.next.next;
                //Here I need to return because node.next.next can be null
                return;
            }
            //If key is not found check the next node
            node=node.next;
        }

        
    }


    //Helper functions

    public int hash(int key){
        return key%SIZE;
    }

    public Node findNode(int index,int key){
        //If find node return it or return null
        Node node=bucket[index];

        while(node!=null){
            if(key==node.key){
                return node;
            }

            node=node.next;
        }

        return null;

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


