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
 //Limitation: if there is 1 node, remove the node? --> yes.
 //Idea : fast/slow pointers -> find mid point 
 //Time Complexity: O(N)
 //Space Complexity: O(1)

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null){
            return null; // if there is only 1 node, just remove the node 
        }

        //To find the (middle-1)th node, initialize two pointers, 'slow' and 'fast'.
        //ListNode beforeMiddle = head;
        //ListNode fast = head;
        ListNode fast = head.next.next; //middle 의 앞 node 를 구하기 위해서 fast 가 한텀 일찍 출발해야한다. 아니면, 위처럼 fast = head 를 할거면 , slow 보다 1개 앞의 node 를 저장하기위한 추가 node (= beforeMiddle) 가 필요
        ListNode slow = head;

        //head = [1,3,4,7,1,2,6]     head = [1,2,3,4]    head=[2,1]
        //f:4->1->6                  f:3->null           f:null
        //s:1->3->4                  s:1->2              s:2
        while(fast != null && fast.next != null){
            //beforeMiddle = slow;//
            fast = fast.next.next;
            slow = slow.next;
        }

        //beforeMiddle.next = beforeMiddle.next.next;
        slow.next = slow.next.next;
        return head;
    }
}


//fast/slow 방법이 생각이 나지 않을경우, 간단하게 기본방식으로 푸는 경우 
//  linked list 의 size 를 먼저 while 문돌아서 구하고, 그다음 절반위치 까지 다시 가서 delete
//Time Complexity: O(N)
//Space Complexity:O(N)
/*
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }

        ListNode currNode = head;
        int length = 0;
        while(currNode != null){
            length++;
            currNode = currNode.next;
        }
        int mid = length /2; // 1/2= 0, 2/2= 1, 3/2 =1, 4/2 = 2
        int count = 0; 
        currNode =  head;
        //Input: head = [2,1]   head =[1,3,4,7,1,2,6]
        //mid-1:0               mid-1 =2;
        //count: 0;             count:0 ->1->2
        //currNode:2->null      currNode:1 ->3->4
        while(count < mid -1){
            currNode = currNode.next;
            count++;
        }

        currNode.next = currNode.next.next;
        return head;
    }
}
*/