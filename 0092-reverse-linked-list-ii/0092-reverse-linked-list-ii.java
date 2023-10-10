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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode leftLeftNode = null;
        ListNode currNode = head;
        int count = 1;
        while(count < left){
            leftLeftNode = currNode;
            currNode = currNode.next;
            count++;
        }
        
        ListNode endNode = currNode;
        ListNode prevNode = null;
        while(count <= right){
            ListNode nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
            count++;
        }
        
        endNode.next = currNode;
        ListNode startNode = prevNode;
        
        if(leftLeftNode == null){
            return startNode;
        }
        
        leftLeftNode.next = startNode;
        return head;
        
    }
}