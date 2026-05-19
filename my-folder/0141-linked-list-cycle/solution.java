/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {

        //So I'm going with fast and slow pointer approach
        //Any object type is a reference
        ListNode slow=head;
        ListNode fast=head;

        //What if there is self cycle in single node,the same code will work

        // while(fast.next.next!=null){ I need to check both fast.next and fast.next.next
        // while(fast.next!=null && fast.next.next!=null) I'm doing next on fast and fast.next so check if these two are not null
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;

            if(fast==slow){
                return true;
            }
        }

        return false;        
        
    }
}
