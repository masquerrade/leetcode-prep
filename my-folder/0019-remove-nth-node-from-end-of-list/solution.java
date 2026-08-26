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
//Attempt 1- 5 hrs
// class Solution {
//     public ListNode removeNthFromEnd(ListNode head, int n) {

//         //Intial check of inputs are valid
//         //n is 1 based index
//         // if(head==null || n<0){
//         if(head==null || n<=0){
//             return head;
//         }

//         //Set up sentinal node why? So that I can return even if head is null
//         ListNode sentinel=new ListNode(-1,head);
//         ListNode fast=sentinel;
//         ListNode slow=sentinel;

//         //Set up fast pointer n steps ahead
//         //Defensive check if fast becomes null ,return the head immediately as the element to be removed is not present
//         //At the end of this loop fast can be null . In this case I need to remove the first node
//         // for(int i=1;i<=n;i++){
//         //Here I need to advance n+1 steps and not n step
//         //If I don't keep the fast node n+1 node forward then when my fast becomes null I'll be at the node I need to remove
//         for(int i=1;i<=n+1;i++){
//             if(fast==null){
//                 return head;
//             }
//             fast=fast.next;
//         }


        

//         //Start traversing both the pointers

//         while(fast!=null){
//             slow=slow.next;
//             fast=fast.next;
//         }
//         //In this state I need to remove the node next to the slow
//         //When I've reached the node I need to remove , remove it
//         ListNode delNode=slow.next;
//         slow.next=slow.next.next;


//         //Disconnect the removed node
//         delNode.next=null;

//         //Return sentinel.next
//         return sentinel.next;
        
        
//     }
// }

//Gemini solution
class Solution {
    
    /**
     * Removes the nth node from the end of the list and returns the new head.
     *
     * @param head The head of the linked list.
     * @param n The 1-based index from the end of the list to remove.
     * @return The head of the modified linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Defensive check against invalid input constraints
        if (head == null || n <= 0) {
            return head;
        }

        // Sentinel node to handle edge cases gracefully (e.g., removing the head itself)
        ListNode sentinel = new ListNode(0, head);
        
        ListNode fast = sentinel;
        ListNode slow = sentinel;

        // Phase 1: Establish the invariant. 
        // Advance 'fast' exactly n + 1 steps to create the sliding window.
        for (int i = 0; i <= n; i++) {
            // Defensive check: if n is greater than list length, no-op or throw exception.
            // LeetCode guarantees n <= sz, but production code must be safe.
            if (fast == null) {
                return head; 
            }
            fast = fast.next;
        }

        // Phase 2: Slide the window.
        // Maintain the n+1 gap until 'fast' reaches the end.
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Phase 3: Pointer mutation.
        // 'slow' is now exactly one node before the target node to delete.
        ListNode nodeToRemove = slow.next;
        
        // Remove the target node from the logical chain.
        slow.next = slow.next.next;
        
        // Explicitly sever the removed node's pointer to assist the Garbage Collector
        // and prevent memory leaks in long-lived applications.
        nodeToRemove.next = null;

        return sentinel.next;
    }
}

