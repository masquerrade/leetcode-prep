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
    public ListNode detectCycle(ListNode head) {

        //Phase 1 find the cycle 
        ListNode fast=head;
        ListNode slow=head;

        while(fast!=null && fast.next!=null){
            //IN the first iteration this will always be same
            // if(fast==slow){
            //     //Cycle found
            //     break;
            // }

            slow=slow.next;
            fast=fast.next.next;

            if(fast==slow){
                //Cycle found
                break;
            }
        }

        //Not always the fast pointer will be null when the pevious loop terminates .
        // if(fast==null){
        if(fast==null || fast.next==null){
            return null;
        }


        //Phase 2 find the starting node
        fast=head;

        //Definitely cycle is there
        while(fast!=slow){
            slow=slow.next;
            fast=fast.next;
        }

        return fast;
        
    }
}

// //Hashset method
// public class Solution {
//     public ListNode detectCycle(ListNode head) {

//         //I can think that first node which is visited second time
        
//         if(head==null){
//             return head;
//         }

//         HashSet<ListNode> set=new HashSet<>();

//         //Traverse the whole linked list
//         while(head!=null){
//             if(!set.add(head)){
//                 return head;
//             }

//             head=head.next;
//         }

//         return head;        
        
//     }
// }
