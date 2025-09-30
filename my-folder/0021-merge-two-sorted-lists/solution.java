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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        //
        ListNode head1=list1;
        ListNode head2=list2;
        ListNode newList=new ListNode(-1);
        ListNode currNode=newList;

        while(head1!=null && head2!=null){
            //Comaparre value of curr node
            if(head1.val<head2.val){
                ListNode newNode=new ListNode(head1.val);
                currNode.next=newNode;
                head1=head1.next;
            }
            else{
                ListNode newNode=new ListNode(head2.val);
                currNode.next=newNode;
                head2=head2.next;
            }
            currNode=currNode.next;

        }
        if(head1==null){
            currNode.next=head2;
        }
        else{
            currNode.next=head1;
        }

        return newList.next;
        
    }
}
