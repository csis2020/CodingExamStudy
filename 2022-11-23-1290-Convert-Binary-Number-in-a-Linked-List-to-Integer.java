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
 //2022-11-23 
 //Time Complexity : O(N)
 //Space Complexity: O(1)
class Solution {
    public int getDecimalValue(ListNode head) {
        int res = 0;

        while(head != null){
            res = res << 1;
            res += head.val;
            head = head.next;
        }
        return res;
    }
}