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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        //Intial check of inputs are valid
        //n is 1 based index
        // if(head==null || n<0){
        if(head==null || n<=0){
            return head;
        }

        //Set up sentinal node why? So that I can return even if head is null
        ListNode sentinel=new ListNode(-1,head);
        ListNode fast=sentinel;
        ListNode slow=sentinel;

        //Set up fast pointer n steps ahead
        //Defensive check if fast becomes null ,return the head immediately as the element to be removed is not present
        //At the end of this loop fast can be null . In this case I need to remove the first node
        // for(int i=1;i<=n;i++){
        //Here I need to advance n+1 steps and not n step
        //If I don't keep the fast node n+1 node forward then when my fast becomes null I'll be at the node I need to remove
        for(int i=1;i<=n+1;i++){
            if(fast==null){
                return head;
            }
            fast=fast.next;
        }


        

        //Start traversing both the pointers

        while(fast!=null){
            slow=slow.next;
            fast=fast.next;
        }
        //In this state I need to remove the node next to the slow
        //When I've reached the node I need to remove , remove it
        ListNode delNode=slow.next;
        slow.next=slow.next.next;


        //Disconnect the removed node
        delNode.next=null;

        //Return sentinel.next
        return sentinel.next;
        
        
    }
}
