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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0 || lists==null){
            return null;
        }

        int n=lists.length;

        //Merge 0,1 ->0 ;2,3->2 ; 
        //For next iteration double the interval

        //Outer loop to expand the intervals everytime
        for(int i=1;i<n;i*=2){
            //Inner loop to merge two lists
            // for(int j=0;j<n;j+=i*2){ This will fail in the case of odd length array
            for(int j=0;j+i<n;j+=i*2){
                lists[j]=mergeTwoLists(lists[j],lists[j+i]);
            }
        }

        return lists[0];

    }
    public ListNode mergeTwoLists(ListNode list1,ListNode list2){
        

        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;

        while(list1!=null && list2!=null){
            if(list1.val<list2.val){
                curr.next=list1;
                list1=list1.next;
                curr=curr.next;
            }
            else{
                curr.next=list2;
                list2=list2.next;
                curr=curr.next;
            }
        }

        if(list1==null){
            curr.next= list2;
        }

        if(list2==null){
            curr.next= list1;
        }

        return dummy.next;
    }

}






// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {

//         //Split the list and pass both the list to the helper for merging
//         //Control the splitting in the helper method
//         return mergeKListHelper(lists,0,lists.length-1);
        
//     }

//     private ListNode mergeKListHelper(ListNode[] lists,int start,int end){
//         if(start==end){
//             return lists[start];
//         }

//         //Don't miss the case where start could be greater than the end
//         if(start>end){
//             return null;
//         }

//         //From both sides I'll get one list and I need to merge that list.
//         //Find the middle split into two and pass to the helper 
//         int mid=start+(end-start)/2;
//         return mergeTwoLists(mergeKListHelper(lists,start,mid),mergeKListHelper(lists,mid+1,end));
        
//     }

//     private ListNode mergeTwoLists(ListNode list1,ListNode list2){

//         ListNode dummy=new ListNode(-1);
//         ListNode current=dummy;
//         while(list1!=null && list2!=null){
//             if(list1.val<list2.val){
//                 current.next=list1;
//                 list1=list1.next;
//                 current=current.next;
//             }
//             else{
//                 current.next=list2;
//                 list2=list2.next;
//                 current=current.next;
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
    

// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {

//         //Create PQ
//         //Store heads of all the lists 
        
//         //Add mode to the pq
//         PriorityQueue<ListNode> pq=new PriorityQueue<>((a,b)->Integer.compare(a.val,b.val));
        
//         //To initialize the list add all the head to the pq
//         for(ListNode list:lists){
//             if(list!=null){
//                 pq.offer(list);
//             }
//         }

//         ListNode dummyNode=new ListNode(-1);
//         ListNode currNode=dummyNode;

//         //Pop the head of the list and keep on making the new list
//         while(!pq.isEmpty() ){
//             currNode.next=pq.poll();
//             currNode=currNode.next;
//             if(currNode.next!=null){
//                 pq.offer(currNode.next);
//             }
//         }
//         //When we pop the head we need to pass the next element of the list to the queue

//         return dummyNode.next;
        
//     }
// }
