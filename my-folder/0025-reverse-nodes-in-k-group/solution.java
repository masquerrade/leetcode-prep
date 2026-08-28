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

        //First grp I need to reverse seperately as I need it's tail as my main head
        //Better I can use a dummy node
        if(head==null || head.next==null){
            return head;
        }

        ListNode dummy=new ListNode(-1,head);
        ListNode current=dummy;

            /**
            * L=1 -> 2 -> 3 -> 4-> 5
            * dummy->L
            */

        //Reverse the 1st grp
        while(current!= null){
            /** current     rev
                    -1      (-1,2)
                    1       (1,2)
                    3       (3,2)
            */
            current=reverseKNodes(current ,k);
        }

        return dummy.next;
    }

    private ListNode reverseKNodes(ListNode start ,int k){

        //Before reversing I need to check if the k elements are present after start
        /**
            start   k   lst
             -1     2   -1>2>1>3>4>5
             1      2
             3      2   -1>2>1>4>3>5
         */
        ListNode traverse=start.next;
        for(int i=0;i<k;i++){

            /**
                traverse    i
                1           0
                2           1

                3           0
                4           1

                5           0
                null

             */
            if(traverse==null){
                return null;
            }
            traverse=traverse.next;                            
        }
        ListNode startNode=start.next;
        ListNode currentNode=start.next;
        ListNode prevNode=null;


        for(int i=0;i<k;i++){

            /**
                curNod   prevNod    nextNod     i   lst
                1        null       2           0   1>null
                2         1         3           1   2>1>null
                3         2                     2

                3        null       4           0   null
                4         3         5           1  3>null 
                5         4                     2  4>3>null
             */
    
            ListNode nextNode=currentNode.next;
            currentNode.next=prevNode;

            prevNode=currentNode;
            currentNode=nextNode;
        }

        /**
             startNode  lst
                1       -1>2>1>3>4>5
                3       -1>2>1>4>3>5
         */

        startNode.next=currentNode;
        start.next=prevNode;

        return startNode;
        
    }
}
