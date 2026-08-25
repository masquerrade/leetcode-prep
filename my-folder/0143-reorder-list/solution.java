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

//Gemini correction
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode current = head;
        ArrayDeque<ListNode> stack = new ArrayDeque<>();

        // 1. Populate the stack
        while (current != null) {
            stack.offerFirst(current);
            current = current.next;
        }

        current = head;

        // 2. We only need to interleave exactly half the elements
        int limit = stack.size() / 2;

        for (int i = 0; i < limit; i++) {
            ListNode nextNode = current.next;    // Cache next node in the original sequence
            ListNode tailNode = stack.pollFirst(); // Get the node from the end

            // Interleave
            current.next = tailNode;
            tailNode.next = nextNode;

            // Move pointer forward
            current = nextNode;
        }

        // 3. Terminate the list to prevent cycles
        current.next = null;
    }
}


//Wrong while condition
//37 min in thinking 
// class Solution {
//     public void reorderList(ListNode head) {



//         //I need to reverse the list 
//         ListNode current=head;
//         ArrayDeque<ListNode> reverse=new ArrayDeque<>();

//         while(current!=null){

//             //Reverse
//             reverse.offerFirst(current);
//             //Update
//             current=current.next;
//         }
                                        
//         current=head;
//         while(current!=reverse.peek()){
//             ListNode nextNode=current.next;

//             //Interleave
//             current.next=reverse.poll();

//             //Update
//             current=current.next;
//             current.next=nextNode;
//             current=current.next;
//         }

//         current.next=null;
        
//     }
// }
