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

//2023-09-20
//간단하게는 link 를 끝까지 돌아서 개수 N를 구하고, 그런다음 다시 link를 처음부터 시작해서 N/2+1 까지 move 한뒤 해당 node 를 리턴하면됨. 
//더 나은 방법은 아래.  
//Idea : There are 2 pointers : fast node (move 2steps at a time), slow node(move 1 step at a time) => if fast node reachs to the end, it means that slow node reaches to the mid poisition
//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution {
    public ListNode middleNode(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        //1->2->3->4->5,        1->2->3->4->5->6
        //f: 1->3->5        f: 1->3->5->null
        //s: 1->2->3        s: 1->2->3->4

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}