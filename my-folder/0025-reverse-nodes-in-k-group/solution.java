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
 //First arrempt without any help and dry run : 90 min
// class Solution {
//     public ListNode reverseKGroup(ListNode head, int k) {

//         //First grp I need to reverse seperately as I need it's tail as my main head
//         //Better I can use a dummy node
//         if(head==null || head.next==null){
//             return head;
//         }

//         ListNode dummy=new ListNode(-1,head);
//         ListNode current=dummy;

//             /**
//             * L=1 -> 2 -> 3 -> 4-> 5
//             * dummy->L
//             */

//         //Reverse the 1st grp
//         while(current!= null){
//             /** current     rev
//                     -1      (-1,2)
//                     1       (1,2)
//                     3       (3,2)
//             */
//             current=reverseKNodes(current ,k);
//         }

//         return dummy.next;
//     }

//     private ListNode reverseKNodes(ListNode start ,int k){

//         //Before reversing I need to check if the k elements are present after start
//         /**
//             start   k   lst
//              -1     2   -1>2>1>3>4>5
//              1      2
//              3      2   -1>2>1>4>3>5
//          */
//         ListNode traverse=start.next;
//         for(int i=0;i<k;i++){

//             /**
//                 traverse    i
//                 1           0
//                 2           1

//                 3           0
//                 4           1

//                 5           0
//                 null

//              */
//             if(traverse==null){
//                 return null;
//             }
//             traverse=traverse.next;                            
//         }
//         ListNode startNode=start.next;
//         ListNode currentNode=start.next;
//         ListNode prevNode=null;


//         for(int i=0;i<k;i++){

//             /**
//                 curNod   prevNod    nextNod     i   lst
//                 1        null       2           0   1>null
//                 2         1         3           1   2>1>null
//                 3         2                     2

//                 3        null       4           0   null
//                 4         3         5           1  3>null 
//                 5         4                     2  4>3>null
//              */
    
//             ListNode nextNode=currentNode.next;
//             currentNode.next=prevNode;

//             prevNode=currentNode;
//             currentNode=nextNode;
//         }

//         /**
//              startNode  lst
//                 1       -1>2>1>3>4>5
//                 3       -1>2>1>4>3>5
//          */

//         startNode.next=currentNode;
//         start.next=prevNode;

//         return startNode;
        
//     }
// }


//Gemini Solution

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
    
    public ListNode reverseKGroup(ListNode head, int k) {
        // Defensive check: If k is 1, reversal is a no-op.
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        // Dummy node simplifies head-of-list operations, acting as the tail of the "0th" group.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // 1. Look ahead to ensure we have exactly k nodes to reverse.
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break; // Less than k nodes remaining, invariant says leave them as is.
            }
            
            ListNode groupNext = kth.next;
            
            // 2. Reverse the sublist [groupPrev.next, kth]
            // By initializing prev to groupNext, the new tail automatically points to the remainder.
            ListNode prev = groupNext; 
            ListNode curr = groupPrev.next;
            
            while (curr != groupNext) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }
            
            // 3. Connect the previous group's tail to the new head of this reversed group.
            ListNode originalGroupHead = groupPrev.next;
            groupPrev.next = kth; // kth is the new head of this group
            
            // 4. Advance groupPrev to the tail of the current reversed group for the next iteration.
            groupPrev = originalGroupHead; 
        }

        return dummy.next;
    }

    /**
     * Helper method to find the k-th node from a given starting point.
     * Keeps the main logic declarative and easy to read.
     */
    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
