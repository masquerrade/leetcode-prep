
public final class LRUCache {

    private static final class Node {
        private final int key;
        private int value;
        private Node prev;
        private Node next;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> lookup;
    private final Node head;
    private final Node tail;

    /**
     * Initializes the LRU cache with a fixed positive capacity.
     *
     * @param capacity maximum number of distinct keys the cache can hold.
     * @throws IllegalArgumentException if capacity is less than or equal to 0.
     */
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be strictly positive. Provided: " + capacity);
        }
        this.capacity = capacity;
        /* Pre-allocate map capacity with standard load factor (0.75) to prevent rehashing overhead

	This line is a performance optimization technique used to ensure that the HashMap maintains predictable O(1) performance without encountering latency spikes caused by rehashing.The Technical Rationale1
The Resizing Problem: By default, a HashMap starts with a small initial capacity (typically 16) and a load factor of 0.75. When the number of elements in the map exceeds capacity * loadFactor, the HashMap triggers a "resize" operation: it creates a new, larger internal array and rehashes (re-maps) every existing element. This resize is an O(N) operation that creates significant latency spikes.
The Formula: To avoid this, we want to set an "initial capacity" large enough that the HashMap will never need to resize as long as the number of elements does not exceed your defined capacity.
Since threshold = capacity * loadFactor (where the default load factor is 0.75), we derive the required capacity by solving for capacity: initialCapacity = capacity / 0.75.
The "+ 1" Buffer: The addition of + 1 acts as a safety margin. Floating-point arithmetic during the division (e.g., capacity / 0.75) can sometimes result in values that hit the resizing threshold exactly when the cache is full. Adding 1 ensures the initial array is slightly larger than the theoretical threshold, guaranteeing that the map never triggers a resize operation under normal operating conditions.1
In summary: This initialization ensures the cache performs exclusively at O(1) time complexity for insertions, preventing the performance overhead of internal array reallocation as the cache fills to its limit. 
		*/
        this.lookup = new HashMap<>((int) Math.ceil(capacity / 0.75) + 1);

        // Sentinel pseudo-nodes eliminate boundary null-checks during node splicing
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Retrieves the value of the key if present, elevating the node to MRU position.
     *
     * @param key target identifier.
     * @return stored value, or -1 if the key does not exist.
     */
    public int get(int key) {
        Node node = lookup.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * Inserts or updates the value for a key. If the cache exceeds capacity,
     * evicts the least recently used element.
     *
     * @param key   key identifier.
     * @param value payload to associate with the key.
     */
    public void put(int key, int value) {
        Node existingNode = lookup.get(key);

        if (existingNode != null) {
            existingNode.value = value;
            moveToHead(existingNode);
            return;
        }

        if (lookup.size() >= capacity) {
            evictLRU();
        }

        Node newNode = new Node(key, value);
        lookup.put(key, newNode);
        addNodeToHead(newNode);
    }

    private void addNodeToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        Node predecessor = node.prev;
        Node successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        node.prev = null;
        node.next = null;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNodeToHead(node);
    }

    private void evictLRU() {
        Node lruNode = tail.prev;
        if (lruNode == head) {
            return;
        }
        removeNode(lruNode);
        lookup.remove(lruNode.key);
    }
}


//  class LRUCache {


//         private static final class Node{
//             //Key of the node will never change
//             //Inner classes can have private member variables
//             private final Integer key;
//             private  int val;
//             private Node next;
//             private Node prev;

//             //Private constructor
//             private Node(Integer key,int val){
//                 //Use of this variable to differentiate the variable 
//                 this.key=key;
//                 this.val=val;
//             }

//         }


//         //There are only three elements of this LRU cache 
//         //I need to initialize with capacity size
//         private final Map<Integer,Node> lookUp;
//         private final Node head;
//         private final Node tail;
//         //capacity is an important part of the LRU Node
//         private final int capacity;


//     public LRUCache(int capacity) {

//         //What should happen when I intitialise LRU cache
//         //A data structure where I can add the capacity number of nodes
//         //The DS should be able to store KV pair -> Map easily it can get
//         //It should maintain the order of which is least frequently used and which is most frequently used
//         //So that when the size increases I can simply remove the least recently used element
//         //So this rearranging should happen when a get is called
//         /**
//         * When a get is called I need to bring that element to the top of some data structure
//          */

//          //We need put and get to be O(1)
//          //Removing element from the end and adding to the top should be O(1)
//          //So I think I can use linked list -> doubly
//          //We can't use get I need to move that element to the top of the LL but searching that element in the deque will take O(N)


//         //Initialization should happen inside the constructor
//             //Final elements can be initialized oncce in constructor
//             this.capacity=capacity;
//             // this.lookUp=new HashMap<>(Math.ceil(capacity/0.75)+1);
//             //int casting is needed as Math.ceil returns double
//             this.lookUp=new HashMap<>((int)Math.ceil(capacity/0.75)+1);
//             this.head=new Node(-1,-1 );
//             this.tail=new Node(-1,-1);

//             //Head and tail needs to be connected
//             this.head.next=this.tail;
//             this.tail.prev=this.head;

//         }
    
//     public int get(int key) {

//         Node searchedNode=lookUp.get(key);

//         //If node is present bring the node to the head
//         if(searchedNode!=null){
//             moveNodeToHead(searchedNode);
//             return searchedNode.val;
//         }
//         return -1;
//     }
    
//     public void put(int key, int value) {
//         //If key exists update it and move it to the head
//         Node updateNode=lookUp.get(key);
//         if(updateNode!=null){
//             updateNode.val=value;
//             moveNodeToHead(updateNode);
//             return ;
//         }
//         //If it doesn't exist check the capacity . If the capacity if full evict the tail
//         while(lookUp.size()>=capacity){
//             evictNode();
//         }

//         //Add node at the head
//         updateNode=new Node(key,value);
//         addNodeToHead(updateNode);
//         lookUp.put(key,updateNode);
//         //Here I forgot to add node in the map
        
//     }
// //Remove node can be taken out
//     private void moveNodeToHead(Node searchedNode){
//         //What if the searched node is the head itself
//         if(head.next==searchedNode){
//             return ;
//         }
//         Node headNext=head.next;
//         searchedNode.prev.next=searchedNode.next;
//         searchedNode.next.prev=searchedNode.prev;
//         head.next=searchedNode;
//         searchedNode.prev=head;
//         searchedNode.next=headNext;
//         headNext.prev=searchedNode;

//     }

//     private void evictNode(){
//         Node lru=tail.prev;
//         Integer key=lru.key;
//         lru.prev.next=tail;
//         tail.prev=lru.prev;
//         lru.prev=null;
//         lru.next=null;
//         lookUp.remove(key);
//     }

//     private void addNodeToHead(Node newNode){
//         Node headNext=head.next;
//         head.next=newNode;
//         newNode.prev=head;
//         newNode.next=headNext;
//         headNext.prev=newNode;
//     }
// }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


//  class LRUCache {

//     //What are the immediate members of LRUCache class
//     //I want a capacity 
//     //I want a hashmap to hold all my elements fot quick retrieval
//     //I want a DLL for removing the LRU node when capacity is more 
//     //For DLL I will have head and tail node

//     //Inner class Linked List node
//     //private static class Node so that no outer class can access it

//     private static class Node{
//          //Why we need this key is because while evicting I'll need to get the key from the node I'm evicting .
//         int key;
//         int value;
//         Node next;
//         Node prev;

//         public Node(int key,int value){
//             this.key=key;
//             this.value=value;
//         }
//     }

//     //Members of LRU Cache class
//     private final int capacity;
//     //Is user going to provide the key
//     private final Map<Integer,Node> cache;
//     private final Node head;
//     private final Node tail;

//     public LRUCache(int capacity) {
//         this.capacity=capacity;
//         this.cache=new HashMap<>();
//         this.head=new Node(-1,-1);
//         this.tail=new Node(-1,-1);
//         head.next=tail;
//         tail.prev=head;
        
//     }
    
//     //Whenever we get a key . If a key is present we return the value . And then we move to the beginning .If the key is not present we return -1 . Why we are moving it to the beinning , so that this is not at the end.
//     public int get(int key) {
//         //Get the value from map
//         Node nd=cache.get(key);

//         //If the key is present the return the value and move the node to the beginning
//         if(nd!=null){
//             moveNodeToFront(nd);
//             return nd.value;
//         }
        
//         return -1;
//     }


//     // We need to add the entry in the hashmap and at the beginning of the DLL 
    

//     public void put(int key, int value) {

//         //If key is present then update the value 
//         if(cache.containsKey(key)){
//             Node nd=cache.get(key);
//             nd.value=value;
//             moveNodeToFront(nd);
//         }
//         else{
//             Node nd=new Node(key,value);
//             cache.put(key,nd);
//             addNodeToFront(nd);

//             if(cache.size()>capacity){
//                 Node lastNode=tail.prev;
//                 removeNode(lastNode);
//                 cache.remove(lastNode.key);
//             }


//         }

//         //If not present then insert it and check if the current size is greter than the capacity.If it greater than capacity then remove the least recently used entry meaning the last entry in the DLL


        
//     }



//     //Helper methods
//     public void moveNodeToFront(Node nd){
//         //Delete node from current place and add node in the front
//         //First I need to delete the node as I need it's connections intact
//         removeNode(nd);
//         addNodeToFront(nd);
        
//     }

//     public void addNodeToFront(Node nd){
//         nd.next=head.next;
//         nd.next.prev=nd;
//         head.next=nd;
//         nd.prev=head;

//     }

//     public void removeNode(Node nd){
//         nd.prev.next=nd.next;
//         nd.next.prev=nd.prev;
//     }

// }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


//  public LRUCache(int capacity) {
//     this.capacity = capacity;
//     this.cache = new HashMap<>();
//     this.head = new Node(-1, -1);
//     this.tail = new Node(-1, -1);
    
//     // Fix: Link head and tail to form the initial empty list
//     head.next = tail;
//     tail.prev = head;
// }
