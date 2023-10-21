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

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
           return l2;
        }
        if(l2 == null){
            return l1;
        }
        
        ListNode reverseL1 = reversedLinkedList(l1);
        ListNode reverseL2 = reversedLinkedList(l2);
        
        ListNode sumNode = new ListNode(0);
            
        while(reverseL1 != null || reverseL2 != null){
            int value1 = (reverseL1 != null) ? reverseL1.val : 0;
            int value2 = (reverseL2 != null) ? reverseL2.val : 0;
            
            int totalSum = sumNode.val + value1 + value2;
            int carry = totalSum / 10;
            sumNode.val = totalSum % 10;
            
            ListNode head = new ListNode(carry);
            head.next = sumNode;
            sumNode = head;
            
            reverseL1 = (reverseL1 != null) ? reverseL1.next : null;
            reverseL2 = (reverseL2 != null) ? reverseL2.next : null;
        }
        
        if(sumNode.val > 0){
            return sumNode;
        }
        
        return sumNode.next;
        
    }
    
    ListNode reversedLinkedList(ListNode head){
        
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}



//idea2 : make reverse linked list -> ( recursive 로 해도 되고, iterator 로 해도 되고)
//Time Complexity: O(N + M)
//Space Complexity: O(1)
/*
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
*/

//idea3 : using stack -leetcode solution 
//Time Complexity: O(N + M), n is list1's size, m is list2's size
//Space COmplexity : O(N + M)
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int totalSum = 0, carry = 0;
        ListNode ans = new ListNode();
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) {
                totalSum += s1.pop();
            }
            if (!s2.empty()) {
                totalSum += s2.pop();
            }
            
            ans.val = totalSum % 10;
            carry = totalSum / 10;
            ListNode head = new ListNode(carry);
            head.next = ans;
            ans = head;
            totalSum = carry;
        }

        return carry == 0 ? ans.next: ans;        
    }
}
*/