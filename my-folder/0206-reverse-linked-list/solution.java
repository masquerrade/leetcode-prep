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
 //Iterative
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }

        //Three pointer solution iterative soln
        ListNode currNode=head; // This will hold the unreversed section
        ListNode prevNode=null; // This will hold the reversed section
        
        while(currNode != null){
            //This will store the connections of the current node
            ListNode nextNode=currNode.next;

            //Reverse the pointer
            currNode.next=prevNode;

            //Update the states
            prevNode=currNode; //New reversed head
            currNode=nextNode; //Unreversed head
        }

        return prevNode;
    }
}

// //Recursive
// class Solution {
//     public ListNode reverseList(ListNode head) {
//         if(head==null || head.next==nll){
//             return head;
//         }

        
//     }
// }


// class Solution {
//     public ListNode reverseList(ListNode head) {
//          //I've got the head 
//         //I've to take a prev as null which will be the tail of the new list
//         //In currNode I will store the head
//         //currNode=head
//         //currNode.next=prev
//         //By this time I should have the current node .next saved
//         //I need to go till I reach the end and my currNode.next == null
//         ListNode prev=null;
//         ListNode curr=head;

//         while(curr!=null){
//             //Store the next node as tmp
//             ListNode temp=curr.next;
//             curr.next=prev;
//             prev=curr;
//             curr=temp;
//         }

//         return prev;


       
        
//     }
// }
