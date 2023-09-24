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

//limitation : two lists have same size? No

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        
        ListNode head = new ListNode();
        ListNode currNode = head;
        int carry = 0;
        int sum = 0;
        //Input: l1 = [2,4,3], l2 = [5,6,4] , Input: l1 = [9->9->9->9->9->9->9], l2 = [9->9->9->9]
        //carry:0, 1, 0                         carry:1, 1, 1, 1, 1, 1, 1
        //sum:7,10, 0, 8                        sum: 18, 8,19,9, 19,9,19,9,10, 0, 10,0, 10, 0
        //added :[]->7 ->0->8                   added:[]->8->9->9->9->0->0->0->1
        while(l1 != null || l2 != null){
            int firstNum = (l1 != null)? l1.val : 0;
            int secondNum = (l2 != null)? l2.val : 0;
            
            sum = (firstNum + secondNum + carry)%10;
            carry = (firstNum + secondNum + carry)/10;
            
            currNode.next = new ListNode(sum);
            currNode = currNode.next;
            
            if(l1 != null){                
                l1 = l1.next;                
            }
            if(l2 != null){
                l2 = l2.next;
            }

        }
        
        if(carry == 1){
            currNode.next = new ListNode(1);
        }
        return head.next;
    }
}


//2022-09-14
//input data can be zero 
// Time Complexity : O(max(l1 size, l2 size)), Space Complexity: O(1) (doesn't count the return value space/  if it is counted, O( max(l1 size, l2 size)))
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        if(l1 == null || l2 == null){
            return null;
        }
        
        ListNode addedNumbers = new ListNode();;
        ListNode headNode = addedNumbers;

        int carry = 0; 
        
        while(l1 != null || l2 != null){
            
            int firstNum = (l1 != null) ? l1.val : 0;
            int secondNum = (l2 != null) ? l2.val : 0;            
           
            int val = (firstNum + secondNum + carry) % 10 ;
            carry = (firstNum + secondNum + carry) /10; 
            
            addedNumbers.next = new ListNode(val);
            
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }

            addedNumbers = addedNumbers.next;
        }
        
        if(carry > 0){
            addedNumbers.next = new ListNode(carry);
        }
        return headNode.next;
    }
}
*/


//Input: l1 = [2,4,3], l2 = [5,6,4]  ==> Output: [7,0,8]
//Input: l1 = [0], l2 = [0]  ==>  Output: [0]
//Time Complexity: O(max(n,m)), Space Complexity: O(max(m,n))
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode headNode = new ListNode(); 
        int carry = 0;
        int val = 0;
        ListNode node = headNode;
        
        
        while(l1 != null || l2 != null){
            if(l1 == null){
                val = l2.val;
            }else if(l2 == null){
                val = l1.val;
            }else{
                val = l1.val + l2.val;
            }
            
            ListNode tempNode = new ListNode();
            tempNode.val = (val + carry) % 10;
            carry = (val + carry) / 10;
            
            node.next = tempNode;
            node = node.next;
          
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        
        if(carry > 0){
            node.next = new ListNode(carry, null);
        }
        // meaning data from second node 
        return headNode.next;
    }
}
*/