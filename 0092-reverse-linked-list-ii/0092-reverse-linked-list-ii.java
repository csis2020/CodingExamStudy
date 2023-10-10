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

//2023-10-09
//idea : left 직전의 node 를 저장해 놓고, left 부터 right 까지 revrse order 만든뒤 
//        reversed link의 tail 이 right 다음의 node 를 가르키고, left 전의 node 가  reversed link 의 node 를 가르키도록 만듬.
//Time Complexity: O(N)
//Space Complexity: O(1)
//위의 idea 로 내가 만든 solution 
/*
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
        
        ListNode endNode = currNode; //save the end of reversed linked list
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
*/
//위의 idea 로 leetcode solution이 만든 조금더 깔끔하게 정리된 코드 
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode dummy = new ListNode(0, head);
        ListNode before = dummy;
        
        int count = 1;
        while(count < left){
            before = before.next;            
            count++;
        }
        
        ListNode prev = null;
        ListNode curr = before.next;
        while(count <= right){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        
        before.next.next = curr; //before.next: tail of reversed list / curr: after of reversed list
        before.next = prev; //prev : head of reversed list
        
        return dummy.next;
    }
}