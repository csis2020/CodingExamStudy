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

//2023-10-13
//idea1 : using List<Integer>
//Time Complexity: O(N + M), n is list1's size, m is list2's size
//Space COmplexity : O(N + M)
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
    }
}
*/


//idea2 : make reverse linked list -> ( recursive 로 해도 되고, iterator 로 해도 되고)
//Time Complexity: O(N + M)
//Space Complexity: O(1)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        
        ListNode reversedL1 = reverseLinkedList(l1);
        ListNode reversedL2 = reverseLinkedList(l2);
        
        ListNode sumNode = new ListNode();

        while(reversedL1 != null || reversedL2 != null){
            int l1value = (reversedL1 != null)? reversedL1.val : 0;
            int l2value = (reversedL2 != null)? reversedL2.val : 0;
            int sum = sumNode.val + l1value + l2value;
            int carry = sum / 10;
               
            sumNode.val = sum % 10;
            ListNode headNode = new ListNode(carry);
            headNode.next = sumNode;
            sumNode = headNode;
            
            reversedL1 = (reversedL1 != null)? reversedL1.next : null;
            reversedL2 = (reversedL2 != null)? reversedL2.next : null;
        }
        
        return (sumNode.val == 1) ? sumNode : sumNode.next;
    }
    
    ListNode reverseLinkedList(ListNode head){
        if(head.next == null){
            return head;
        }
        
        ListNode next = head.next;
        ListNode reversedHead = reverseLinkedList(next);
        next.next = head;
        head.next = null;
        
        return reversedHead;
    }
}


//idea3 : using stack
//Time Complexity: O(N + M), n is list1's size, m is list2's size
//Space COmplexity : O(N + M)
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
    }
}
*/