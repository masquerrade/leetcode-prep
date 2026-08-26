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
    public void reorderList(ListNode head) {

        //Initial base case check
        //If size of list <= 2 return head
        if(head==null || head.next==null || head.next.next==null){
            return ;
        }
        
        //Need to find mid as array needs to be split in two half
        ListNode mid=findMid(head);
        
        ListNode secondHalf=mid.next;
        //Disconnect the second half
        mid.next=null;

        //Revere the second Half and get the reversed head
        ListNode reverseSecond=reverseList(secondHalf);

        //Now I need to interleave both the lists
        // ListNode interleaved=mergeList(firstHalf,reverseSecond);
        mergeList(head,reverseSecond);
        
    }
    
    //Here it is very important to know that for odd length list first half will be the bigger half
    //For even length it will be same length
    private ListNode findMid(ListNode head){
        //Base case
        //For even length list first half is returned
        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null && fast.next!=null &&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            //Here we need to check fast.next.next is also not null so that we can be confirm that fast can move two steps(Even without this it won't create a null pointer exception ) but we need to confirm that fast and slow are properly synced.
        }

        return slow;
    }

    private ListNode reverseList(ListNode head){
        if(head==null || head.next==null){
            return head;
        }

        //Three pointer approach
        ListNode prevNode=null;
        ListNode currNode=head;

        while(currNode!=null){
            ListNode tempNode=currNode.next;

            //Reverse
            currNode.next=prevNode;

            //Update states
            prevNode=currNode;
            currNode=tempNode;
        }

        return prevNode;
    }

    //This is the trickiest part
    //Since I'm modifying the actual connections I don't need to return the list
    //This will work only if the length of first list is greater than or equal too the second list 
    private void mergeList(ListNode head1,ListNode head2){
        
        ListNode finalHead=head1;
        while(head1!=null && head2!=null){
            ListNode next1=head1.next;
            ListNode next2=head2.next;

            head1.next=head2;
            head2.next=next1;

            head1=next1;
            head2=next2;

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
