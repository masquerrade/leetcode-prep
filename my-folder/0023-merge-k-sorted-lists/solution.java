/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        //Create PQ
        //Store heads of all the lists 
        
        //Add mode to the pq
        PriorityQueue<ListNode> pq=new PriorityQueue<>((a,b)->Integer.compare(a.val,b.val));
        
        //To initialize the list add all the head to the pq
        for(ListNode list:lists){
            if(list!=null){
                pq.offer(list);
            }
        }

        ListNode dummyNode=new ListNode(-1);
        ListNode currNode=dummyNode;

        //Pop the head of the list and keep on making the new list
        while(!pq.isEmpty() ){
            currNode.next=pq.poll();
            currNode=currNode.next;
            if(currNode.next!=null){
                pq.offer(currNode.next);
            }
        }
        //When we pop the head we need to pass the next element of the list to the queue

        return dummyNode.next;
        
    }
}
