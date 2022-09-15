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

//2022-09-22
//limitation: if head is null , return null
//Input: head = [1,2,3,4,5] -> Output: [5,4,3,2,1]
//Input: head = [1,2] -> Output: [2,1]
//Input: head = [] -> Output: []

// Time Complexity: O(n), Space Complexity: O(1)

class Solution {
    public ListNode reverseList(ListNode head) {
        
        if(head == null){
            return null;
        }
        
        ListNode preNode = null;
        ListNode reverseHead = null;
        
        while(head != null){
            reverseHead = head;
            head = head.next;
            
            reverseHead.next = preNode;
            preNode = reverseHead;
        }
        
        return reverseHead;
    }
}

//Leetcode solution
/*
class Solution {
    public ListNode reverseList(ListNode head) {
        
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
*/