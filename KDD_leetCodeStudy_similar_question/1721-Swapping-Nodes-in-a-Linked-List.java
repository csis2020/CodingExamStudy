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

//2022.10.29
//Time Complexity: O(n)
//Space Complexity: O(1)
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode leftNode = dummyNode;
        ListNode rightNode = dummyNode; 
        ListNode checkNode = dummyNode;
            
        int count = 0;
        
        //check node is for finding the (k)th node from the end.
        while(checkNode != null && count < k){
            checkNode = checkNode.next; 
            count++;
        }
        leftNode = checkNode; // (k)th node
        
        //find the (k)th node from the end
        while(checkNode != null){
            checkNode = checkNode.next;
            rightNode = rightNode.next;
        }
        
        int val = leftNode.val;
        leftNode.val = rightNode.val;
        rightNode.val = val;
        
        return dummyNode.next;
        
    }
}