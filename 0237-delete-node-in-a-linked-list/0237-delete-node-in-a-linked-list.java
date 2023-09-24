/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//2023-09-23
//Idea : I don't have any info about previous node. Actually, I cannot delete the node itself 
//       Instead, I can delete the node's next node and make the input node replace of the next node. 
//        I think that's why it is guaranteed that the given node 'node' is not the last node in the linked list. 
//Time Complexity : O(1)
//Space Complexity: O(1)
class Solution {
    public void deleteNode(ListNode node) {
        
        if(node == null){
            return;
        } 
        
        ListNode nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
        nextNode.next = null;
        
    }
}