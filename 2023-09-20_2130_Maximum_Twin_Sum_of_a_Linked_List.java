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
 //Basic Idea : palindrome linked list 와 문제가 비슷해 보인다. 
 //<간단한 버전> List<Integer>에 node 들을 다 넣고 palindrome 비교할때처럼 start, end 순으로 twin sum 을 구해서 maximum 을 return 
 //Time Complexity: O(N)
 //Space Complexity: O(N)
 // 더 좋은 아이디어는 아래 2번째 fast/slow 를 이용하는 방식으로 Space Complexity 가 O(1)이다. 
 /*
class Solution {
    public int pairSum(ListNode head) {
        if(head == null || head.next == null){
            return 0;
        }

        List<Integer> values = new ArrayList<>();
        ListNode currNode = head;
        while(currNode != null){
            values.add(currNode.val);
            currNode = currNode.next;
        }

        int start = 0;
        int end = values.size() -1; 
        int maximum = 0;
        //[4,2,2,3]         [1,100000]
        //s:4->2            s:1
        //e:3->2            e:100000
        //m:7               m:100001
        while(start < end){
            int twinSum = values.get(start)+values.get(end);
            maximum = Math.max(maximum, twinSum);
            start++;
            end--;
        }
        return maximum;
    }
}
*/
//Idea: 
//1. find middle of the linked list using fast/slow pointers
//  - fast pointer : move 2 steps at a time
//  - slow pointer : move 1 step at a time. 
//    => When fast pointer reaches to the end, slow pointer reaches to the middle
//2. from second half of linked list, make reverse linked list 
//3. Compare first half of linked list and second half of linked list 
//  -> Get twin sum from first half of linked list's nodes and second half of linked list's nodes
//  -> find maximum twin sum.
class Solution {
    public int pairSum(ListNode head) {
        if(head == null || head.next == null){
            return 0;
        }

        ListNode fast = head; //move 2 steps at a time
        ListNode slow = head; //move 1 step at a time
        //find first half of linked list's tail
        //head = [5,4,2,1]
        //f:5 -> 2
        //s:5 -> 4
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalfHead = slow.next;
        //Make reverse linked list for second half
        ListNode prev = null;
        ListNode next = null;        
        while(secondHalfHead != null){
            next = secondHalfHead.next;
            secondHalfHead.next = prev;
            prev = secondHalfHead;
            secondHalfHead = next;
        }

        secondHalfHead = prev;

        int twinSum = 0;
        int maximumSum = 0;
        ListNode firstHalfHead = head;
        while(secondHalfHead != null){
            twinSum = firstHalfHead.val + secondHalfHead.val;
            maximumSum = Math.max(maximumSum, twinSum);
            firstHalfHead = firstHalfHead.next;
            secondHalfHead = secondHalfHead.next;
        }

        return maximumSum;
    }
}