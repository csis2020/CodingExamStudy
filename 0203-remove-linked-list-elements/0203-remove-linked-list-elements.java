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
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }
        
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode currNode = dummyHead;
        //Input: head = [1,2,6,3,4,5,6], val = 6
        //currNode: []->1->2->3->4->5->null
        while(currNode.next != null){
            
            if(currNode.next.val == val){
                currNode.next = currNode.next.next;
            }else{
                currNode = currNode.next;
            }
            
        }
        return dummyHead.next;
    }
}