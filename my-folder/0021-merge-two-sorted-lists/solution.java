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
        //I want to do a recursive solution for this 
        //Compare the current two node and return whichever is the smaller
        if(list1==null){
            return list2;
        }
        if(list2==null){
            return list1;
        }

        ListNode mergedNode;

        if(list1.val<list2.val){
            list1.next=mergeTwoLists(list1.next,list2);
            mergedNode=list1;
        }
        else{
            list2.next=mergeTwoLists(list1,list2.next);
            mergedNode=list2;

        }

        return mergedNode;
    }
}


// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }
//  */
// class Solution {
//     public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

//         //Compare the heads of both the list and make a new list
//         //Dummy node to track the head of the merged list easily
//         ListNode dummy=new ListNode(-1);

//         ListNode current =dummy;

//         while(list1!=null && list2!=null){
//             if(list1.val<list2.val){
//                 current.next=list1;
//                 current=current.next;
//                 list1=list1.next;
//             }
//             else{
//                 current.next=list2;
//                 current=current.next;
//                 list2=list2.next;
//             }             
//         }

//         if(list1==null){
//             current.next=list2;
//         }
//         else{
//             current.next=list1;
//         }

//         return dummy.next;

//     }
// }




