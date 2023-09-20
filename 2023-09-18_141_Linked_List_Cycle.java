//Similar question
//  142. Linked List Cycle II (premium ??)

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
//Floyd's cycle finding algorithm 이 생각나지 않는다면 HashSet 을 이용하도록 
////Time Complexity: O(n)
//Space Complexity: O(n)  <--- Floyd's cycle Finding algorithm 을 이용하면 O(1) 로 만들수있음 
public class Solution{
    public boolean hasCycle(ListNode head){

        ListNode checkNode = head;
        Set<ListNode> visited = new HashSet<>();

        while(checkNode != null){
            if(visited.contains(checkNode)){
                return true;
            }
            visited.add(checkNode);
            checkNode = checkNode.next;
        }
        return false;
    }
}

//Floyd's Cycle Finding Algorithm
//Time Complexity: O(n)
//Space Complexity: O(1)
/*
public class Solution{
    public boolean hasCycle(ListNode head){

        //Start positon of slow and fast 
        ListNode slow = head;
        ListNode fast = head; 
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        
        return false;
    }
}
*/
/*
public class Solution{
    public boolean hasCycle(ListNode head){
        
        ListNode slow = head; //each step - one move
        ListNode fast = head; //each step - two move
        
        while(fast!= null && fast.next != null){ //only need to check fast.
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
            
        }
        
        return false;
    }
}
*/
//Time Complexity: O(n)
//Space Complexity: O(n)
/*
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        if(head == null || head.next == null ){
            return false;
        }
        
        HashSet<ListNode> nodeSet = new HashSet<>();
        
        while(head !=null){
            if(nodeSet.contains(head)){
                return true;
            }
            nodeSet.add(head);
            head = head.next;
        }
        
        return false;
    }
}
*/