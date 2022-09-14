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

//2022-09-14
//input data can be zero 
// Time Complexity : O(max(l1 size, l2 size)), Space Complexity: O(1) (doesn't count the return value space/  if it is counted, O( max(l1 size, l2 size)))

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        if(l1 == null || l2 == null){
            return null;
        }
        
        ListNode addedNumbers = new ListNode();;
        ListNode headNode = addedNumbers;

        int carry = 0; 
        
        while(l1 != null || l2 != null){
            
            int firstNum = (l1 != null) ? l1.val : 0;
            int secondNum = (l2 != null) ? l2.val : 0;            
           
            int val = (firstNum + secondNum + carry) % 10 ;
            carry = (firstNum + secondNum + carry) /10; 
            
            addedNumbers.next = new ListNode(val);
            
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }

            addedNumbers = addedNumbers.next;
        }
        
        if(carry > 0){
            addedNumbers.next = new ListNode(carry);
        }
        return headNode.next;
    }
}
