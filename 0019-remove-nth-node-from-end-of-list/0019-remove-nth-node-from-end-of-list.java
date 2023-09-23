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

// 2023-09-17
 //limitation: n is bigger than zero? yes
 // I can solve this basically
 //     first. check the size of linkedlist
 //     second.  remove Nth node
 // Better solution: is to use 2 dummy nodes that points the head.  ( 2 pass algorithm)
 //     first. move first dummy node to n'th node from the head
 //     second. from n+1 to the end, move first dummy node and second dummy node together. => if first dummy node reaches to the end, it means second dummy node points (n-1)th node. because gap between first node and second node is 'n'.
/*
class Solution{
    public ListNode removeNthFromEnd(ListNode head, int n){
 
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode first = dummyNode;
        ListNode second = dummyNode;

        for(int i = 0; i < n; i++){
            first  = first.next;
        }

        while(first.next != null){ //if first.next is null, it means 'last node'
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dummyNode.next;
    }
}
*/

//
//1-pointer : n -stop/ start move on Nth node from the beginning -> reaches the end
//2-pointer :          start move from head. (distance between 1-pointer and 2-pointer is N)
class Solution{
    public ListNode removeNthFromEnd(ListNode head, int n){
        if(head == null || n <= 0){
            return null;
        }

        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode first = dummyNode;
        ListNode second = dummyNode;
        
        int count = 0;
        while(count < n){
            first = first.next;
            count++;
        }
        
        //head = [1,2,3,4,5], n = 2  /      head=[1], n=1
        //first:   2->3->4->5               first:  1
        //second: []->1->2->3               second: 
        while(first.next != null){
            first = first.next;
            second = second.next;
        }
        
        second.next = second.next.next;
        
        return dummyNode.next;
    }
}
//one pass algorithm
//Time Complexity: O(n)
//Space Complexity: O(1)
//ListNode dummy와 first, second copynode 를 이용 
/*
class Solution{
    public ListNode removeNthFromEnd(ListNode head, int n){
        
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode first = dummyNode;
        ListNode second = dummyNode;
        
        int count = 0;
        while(first != null && count <= n){
            first = first.next;
            count++;
        }
        
        while(first != null){
            first = first.next;
            second = second.next;
        }
        
        second.next = second.next.next;
        
        return dummyNode.next;
    }
}
*/

/*
class Solution{
    public ListNode removeNthFromEnd(ListNode head, int n){
        
        ListNode tempNode = new ListNode();
        tempNode.next = head;
        ListNode first = tempNode;
        ListNode second = tempNode;
        
        int count = 0; 
        //make the n node gap between first and second
        while(first != null && count <= n){ 
            first = first.next;
            count++;
        }
        
        //move to the position the (n-1)th node from the end of the list
        while(first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        
        return tempNode.next;
    }
}
*/
    
//Two pass algorithm
//Time Complexity: O(n) <-- O(2n) 이여서 O(n) 
//Space Complexity: O(1)
/*
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode removedList = head;

        int listSize = 0;
        
        while(removedList != null){
            listSize++;
            removedList = removedList.next;
        }
        
        removedList = head;
        
        int count = listSize - n;
        
        if(count < 0){
            return head; // n is worng number
        }else if(count == 0){
            return head.next; // remove first node. 
        }
        count--; // need to stop before the node to remove
        while(count > 0){
            removedList = removedList.next;
            count--; 
        }
        
        removedList.next = removedList.next.next;
        
        return head;
    }
}
*/
//Time Complexity: O(n)
//Space Complexity: O(n)
/*
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode removedList = head;
        
        HashMap<Integer, ListNode> nodeMap = new HashMap<>();
        int listSize = 0;
        
        //get the size 
        while(removedList != null){
            listSize++;
            
            nodeMap.put(listSize, removedList);
            removedList = removedList.next;
        }
        
        int prePosition = listSize - n;
        
        if(prePosition < 0){
            return head; // wrong position. 
        }else if (prePosition == 0){
            return head.next; // remove first Node. -> start second node.
        }
        
        ListNode preNode = nodeMap.get(prePosition);
        preNode.next = preNode.next.next;
        
        return head;
    }
}
*/