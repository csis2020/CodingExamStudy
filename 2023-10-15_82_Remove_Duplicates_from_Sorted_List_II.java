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
//2023-10-15
//idea : create dummy node and dummy.next is head. 
//      make prev node and curr node. prev = dummy, curr = dummy.next
//      if curr.val == curr.next.val -> keep moving using while(curr.val == curr.next.val)
//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = dummy.next;
        
        while(curr != null){
            if(curr.next != null && curr.val == curr.next.val){
                while(curr.next != null && curr.val == curr.next.val){
                    curr = curr.next;
                }
                
                prev.next = curr.next;
            }else{
                prev = prev.next;
            }
            curr = curr.next;
        }
        
        return dummy.next;
    }
}