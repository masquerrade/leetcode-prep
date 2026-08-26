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
//  //Took 5hrs for 1st attempt
// class Solution {
//     public void reorderList(ListNode head) {

//         //Initial base case check
//         //If size of list <= 2 return head
//         if(head==null || head.next==null || head.next.next==null){
//             return ;
//         }
        
//         //Need to find mid as array needs to be split in two half
//         ListNode mid=findMid(head);
        
//         ListNode secondHalf=mid.next;
//         //Disconnect the second half
//         mid.next=null;

//         //Revere the second Half and get the reversed head
//         ListNode reverseSecond=reverseList(secondHalf);

//         //Now I need to interleave both the lists
//         // ListNode interleaved=mergeList(firstHalf,reverseSecond);
//         mergeList(head,reverseSecond);
        
//     }
    
//     //Here it is very important to know that for odd length list first half will be the bigger half
//     //For even length it will be same length
//     private ListNode findMid(ListNode head){
//         //Base case
//         //For even length list first half is returned
//         ListNode slow=head;
//         ListNode fast=head;

//         while(fast!=null && fast.next!=null &&fast.next.next!=null){
//             slow=slow.next;
//             fast=fast.next.next;
//             //Here we need to check fast.next.next is also not null so that we can be confirm that fast can move two steps(Even without this it won't create a null pointer exception ) but we need to confirm that fast and slow are properly synced.
//         }

//         return slow;
//     }

//     private ListNode reverseList(ListNode head){
//         if(head==null || head.next==null){
//             return head;
//         }

//         //Three pointer approach
//         ListNode prevNode=null;
//         ListNode currNode=head;

//         while(currNode!=null){
//             ListNode tempNode=currNode.next;

//             //Reverse
//             currNode.next=prevNode;

//             //Update states
//             prevNode=currNode;
//             currNode=tempNode;
//         }

//         return prevNode;
//     }

//     //This is the trickiest part
//     //Since I'm modifying the actual connections I don't need to return the list
//     //This will work only if the length of first list is greater than or equal too the second list 
//     private void mergeList(ListNode head1,ListNode head2){
        
//         ListNode finalHead=head1;
//         while(head1!=null && head2!=null){
//             ListNode next1=head1.next;
//             ListNode next2=head2.next;

//             head1.next=head2;
//             head2.next=next1;

//             head1=next1;
//             head2=next2;

//         }
//     }

// }

//Gemini solution
class Solution {
    
    public void reorderList(ListNode head) {
        // Defensive check: 0, 1, or 2 nodes require no reordering.
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // Step 1: Find the middle of the list to partition it.
        ListNode mid = findMiddle(head);
        
        // Step 2: Reverse the second half of the list.
        // We capture the head of the reversed second half.
        ListNode secondHalf = reverseList(mid.next);
        
        // Sever the first half from the second half to prevent cycles.
        mid.next = null;
        
        // Step 3: Weave the two halves together.
        mergeLists(head, secondHalf);
    }

    /**
     * Uses the Tortoise and Hare algorithm to find the middle node.
     * For even-length lists, returns the first middle node.
     */
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        // fast.next != null ensures we don't throw NPE on even lengths
        // fast.next.next != null ensures fast can jump two steps
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Reverses a singly linked list in place.
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next; // Cache next node
            current.next = prev;              // Reverse pointer
            prev = current;                   // Advance prev
            current = nextTemp;               // Advance current
        }
        return prev;
    }

    /**
     * Weaves two linked lists alternately.
     */
    private void mergeLists(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            // Cache next pointers to prevent losing the rest of the lists
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;
            
            // Wire l1 to l2
            l1.next = l2;
            
            // Wire l2 to the next node in l1
            l2.next = l1Next;
            
            // Advance pointers for the next iteration
            l1 = l1Next;
            l2 = l2Next;
        }
    }
}

//Gemini correction
// class Solution {
//     public void reorderList(ListNode head) {
//         if (head == null || head.next == null) return;

//         ListNode current = head;
//         ArrayDeque<ListNode> stack = new ArrayDeque<>();

//         // 1. Populate the stack
//         while (current != null) {
//             stack.offerFirst(current);
//             current = current.next;
//         }

//         current = head;

//         // 2. We only need to interleave exactly half the elements
//         int limit = stack.size() / 2;

//         for (int i = 0; i < limit; i++) {
//             ListNode nextNode = current.next;    // Cache next node in the original sequence
//             ListNode tailNode = stack.pollFirst(); // Get the node from the end

//             // Interleave
//             current.next = tailNode;
//             tailNode.next = nextNode;

//             // Move pointer forward
//             current = nextNode;
//         }

//         // 3. Terminate the list to prevent cycles
//         current.next = null;
//     }
// }


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
