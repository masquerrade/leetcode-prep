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
    public ListNode detectCycle(ListNode head) 
    {

        //That mathematics I need to put here
        //L + K + nC=2(L+K)
        //L+K =nC
        //L=nC-K
        //L=( n-1)C+C-K

        //Overall steps
        //Base case
        if(head==null || head.next==null){
            //Don't return head as we need to return null in case of no cycle
            return null;
        }
        

        //First step is to find the intersection of slow and fast pointer if the cycle is present 
        ListNode inter=findIntersection(head);
        if(inter==null){
            return inter;
        }

        //Second step is to implement the trick to find the starting point
        ListNode start=head;

        //Untill the inter and start meet
        while(start!=inter){
            start=start.next;
            inter=inter.next;
        }

        return start;
        
    }

    private ListNode findIntersection(ListNode head){

        ListNode fast=head;
        ListNode slow=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;

            if(fast==slow){
                return fast;
            }
        }

        //Don't return fast as in the case the while doesn't run it should return null
        // return fast;
        return null;

    } 
}


// public class Solution {
//     public ListNode detectCycle(ListNode head) {

//         //Phase 1 find the cycle 
//         ListNode fast=head;
//         ListNode slow=head;

//         while(fast!=null && fast.next!=null){
//             //IN the first iteration this will always be same
//             // if(fast==slow){
//             //     //Cycle found
//             //     break;
//             // }

//             slow=slow.next;
//             fast=fast.next.next;

//             if(fast==slow){
//                 //Cycle found
//                 break;
//             }
//         }

//         //Not always the fast pointer will be null when the pevious loop terminates .
//         // if(fast==null){
//         if(fast==null || fast.next==null){
//             return null;
//         }


//         //Phase 2 find the starting node
//         fast=head;

//         //Definitely cycle is there
//         while(fast!=slow){
//             slow=slow.next;
//             fast=fast.next;
//         }

//         return fast;
        
//     }
// }

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
