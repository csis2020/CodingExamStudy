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
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        
        ListNode dummy = new ListNode(0, head);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = dummy.next;
        while(curr != null){
            if(curr.val == val){
                prev.next = curr.next;
            }else{
                prev = curr;
            }
            curr = curr.next;
        }
        
        return dummy.next;
    }
}
//2023-09-23
//Time Complexity: O(N)
//Space Complexity: O(1)
/*
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
*/
//Leetcode 솔루션  - 위와 동일한데 , ListNode curr 외에 ListNode prev 를 하나 더 두어서 사용했다.
//  이렇게 하니 코드가 더 깔끔해지네. 
//Time Complexity: O(N)
//Space Complexity: O(1)
/*
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prevNode = dummyHead;
        ListNode currNode = dummyHead.next;
        
        while(currNode != null){
            if(currNode.val == val){
                prevNode.next = currNode.next;
            }else{
                prevNode = currNode;
            }
            currNode = currNode.next;
        }
        
        return dummyHead.next;
    }
}
*/