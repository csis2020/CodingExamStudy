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
/*
//leetcode Discuss 에 있는 best 답 - recursive 방법 이용
Recursively call removeNodes to handle the tail first.
Then head.next node should have the biggest value.
Compare head.val and head.next.val,
if head.val < head.next.val,
should remove the current node,
return head.next,
otherwise we return head.
*/
//Time Complexity : O(N) <-- check every nodes 
//Space Complexity: O(N) <-- stack memory by recursive function 
class Solution {
    public ListNode removeNodes(ListNode head) {
        if(head == null){
            return null;
        }
        
        head.next = removeNodes(head.next);
        
        if(head.next != null && (head.val < head.next.val)){
            return head.next;
        }
        return head;
    }
}
//brute force ------------ 아래 코드 동작 안함. .... wrong answer 나옴 
//idea : Linked list 에 있는 value 를 가지고 sorted list 를 만듬 (descending order)
//      제일 큰수부터 시작해서 linked list 가 해당 value 보다 작으면 delete. 해당 value 를 만나면 멈춤.
//      두번째로 큰수를 가지고 다시 linkedlist 처음부터 시작해서 해당 value 보다 작으면 delete. 해당 value 만나면멈춤
//      이런 과정을 list 에 있는 숫자 마지막까지 수행.
/*
class Solution {
    public ListNode removeNodes(ListNode head) {
        
        if(head == null){
            return head;
        }
        
        List<Integer> values = new ArrayList<>();
        ListNode dummy = head;
        while(dummy != null){
            values.add(dummy.val);
            dummy = dummy.next;
        }
        
        dummy = new ListNode(0, head);

        for(int i = 0; i < values.size(); i++){
            ListNode curr = dummy;
            
            while(curr.next != null){
                if(curr.next.val < values.get(i)){
                    curr.next = curr.next.next; //remove curr.next
                }else {
                    curr = curr.next;
                }
                
            }
        }
        
        return dummy.next;
    }
}
*/