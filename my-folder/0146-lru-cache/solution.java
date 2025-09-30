class LRUCache {

    //What are the immediate members of LRUCache class
    //I want a capacity 
    //I want a hashmap to hold all my elements fot quick retrieval
    //I want a DLL for removing the LRU node when capacity is more 
    //For DLL I will have head and tail node

    //Inner class Linked List node
    //private static class Node so that no outer class can access it

    private static class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    //Members of LRU Cache class
    private final int capacity;
    //Is user going to provide the key
    private final Map<Integer,Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.cache=new HashMap<>();
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        
    }
    
    //Whenever we get a key . If a key is present we return the value . And then we move to the beginning .If the key is not present we return -1 . Why we are moving it to the beinning , so that this is not at the end.
    public int get(int key) {
        //Get the value from map
        Node nd=cache.get(key);

        //If the key is present the return the value and move the node to the beginning
        if(nd!=null){
            moveNodeToFront(nd);
            return nd.value;
        }
        
        return -1;
    }


    // We need to add the entry in the hashmap and at the beginning of the DLL 
    

    public void put(int key, int value) {

        //If key is present then update the value 
        if(cache.containsKey(key)){
            Node nd=cache.get(key);
            nd.value=value;
            moveNodeToFront(nd);
        }
        else{
            Node nd=new Node(key,value);
            cache.put(key,nd);
            addNodeToFront(nd);

            if(cache.size()>capacity){
                Node lastNode=tail.prev;
                removeNode(lastNode);
                cache.remove(lastNode.key);
            }


        }

        //If not present then insert it and check if the current size is greter than the capacity.If it greater than capacity then remove the least recently used entry meaning the last entry in the DLL


        
    }



    //Helper methods
    public void moveNodeToFront(Node nd){
        //Delete node from current place and add node in the front
        //First I need to delete the node as I need it's connections intact
        removeNode(nd);
        addNodeToFront(nd);
        
    }

    public void addNodeToFront(Node nd){
        nd.next=head.next;
        nd.next.prev=nd;
        head.next=nd;
        nd.prev=head;

    }

    public void removeNode(Node nd){
        nd.prev.next=nd.next;
        nd.next.prev=nd.prev;
    }

}

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
