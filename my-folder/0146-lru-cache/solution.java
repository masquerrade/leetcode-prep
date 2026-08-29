class LRUCache {


        private static final class Node{
            //Key of the node will never change
            //Inner classes can have private member variables
            private final Integer key;
            private  int val;
            private Node next;
            private Node prev;

            //Private constructor
            private Node(Integer key,int val){
                //Use of this variable to differentiate the variable 
                this.key=key;
                this.val=val;
            }

        }


        //There are only three elements of this LRU cache 
        //I need to initialize with capacity size
        private final Map<Integer,Node> lookUp;
        private final Node head;
        private final Node tail;
        //capacity is an important part of the LRU Node
        private final int capacity;


    public LRUCache(int capacity) {

        //What should happen when I intitialise LRU cache
        //A data structure where I can add the capacity number of nodes
        //The DS should be able to store KV pair -> Map easily it can get
        //It should maintain the order of which is least frequently used and which is most frequently used
        //So that when the size increases I can simply remove the least recently used element
        //So this rearranging should happen when a get is called
        /**
        * When a get is called I need to bring that element to the top of some data structure
         */

         //We need put and get to be O(1)
         //Removing element from the end and adding to the top should be O(1)
         //So I think I can use linked list -> doubly
         //We can't use get I need to move that element to the top of the LL but searching that element in the deque will take O(N)


        //Initialization should happen inside the constructor
            //Final elements can be initialized oncce in constructor
            this.capacity=capacity;
            // this.lookUp=new HashMap<>(Math.ceil(capacity/0.75)+1);
            //int casting is needed as Math.ceil returns double
            this.lookUp=new HashMap<>((int)Math.ceil(capacity/0.75)+1);
            this.head=new Node(-1,-1 );
            this.tail=new Node(-1,-1);

            //Head and tail needs to be connected
            this.head.next=this.tail;
            this.tail.prev=this.head;

        }
    
    public int get(int key) {

        Node searchedNode=lookUp.get(key);

        //If node is present bring the node to the head
        if(searchedNode!=null){
            moveNodeToHead(searchedNode);
            return searchedNode.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        //If key exists update it and move it to the head
        Node updateNode=lookUp.get(key);
        if(updateNode!=null){
            updateNode.val=value;
            moveNodeToHead(updateNode);
            return ;
        }
        //If it doesn't exist check the capacity . If the capacity if full evict the tail
        while(lookUp.size()>=capacity){
            evictNode();
        }

        //Add node at the head
        updateNode=new Node(key,value);
        addNodeToHead(updateNode);
        lookUp.put(key,updateNode);
        //Here I forgot to add node in the map
        
    }

    private void moveNodeToHead(Node searchedNode){
        //What if the searched node is the head itself
        if(head.next==searchedNode){
            return ;
        }
        Node headNext=head.next;
        searchedNode.prev.next=searchedNode.next;
        searchedNode.next.prev=searchedNode.prev;
        head.next=searchedNode;
        searchedNode.prev=head;
        searchedNode.next=headNext;
        headNext.prev=searchedNode;

    }

    private void evictNode(){
        Node lru=tail.prev;
        Integer key=lru.key;
        lru.prev.next=tail;
        tail.prev=lru.prev;
        lru.prev=null;
        lru.next=null;
        lookUp.remove(key);
    }

    private void addNodeToHead(Node newNode){
        Node headNext=head.next;
        head.next=newNode;
        newNode.prev=head;
        newNode.next=headNext;
        headNext.prev=newNode;
    }
}

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
