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
 //2023.09.20
 //첫번째 방법 
// fast/slow 방법 을 mid point 찾고, 그다음 second half list 를 reverse order 로 만든뒤 
// first half list 와 secodn half list 를 merge
//Time Complexity: O(N)
//Space Complexity: O(1)

 class Solution{
     public void reorderList(ListNode head){
        if(head == null || head.next == null || head.next.next == null){
            return;
        }
         
         //find mid position
         ListNode fast = head;//move 2 steps at a time
         ListNode slow = head; //move 1 step at a time
         //Input: head = [1,2,3,4,5]    head = [1,2,3,4]
         //f:1->3->5                    f:1->3->null
         //s:1->2->3                    s:1->2->3
         while(fast != null && fast.next != null){
             fast = fast.next.next;
             slow = slow.next;
         }
         
         ListNode secondHead =slow;
         //Make reversed linked list for second half
         ListNode prev = null;
         ListNode next = null;
         //Input: secondhead = [3,4,5] 
         //next:null, 4, 5 , null
         //secondHead.next:null<-3<-4<-5
         //Prev:null, 3, 4, 5
         //secondHead:3, 4, 5, null
         while(secondHead !=null){
             next = secondHead.next;
             secondHead.next = prev;
             prev = secondHead;
             secondHead = next;
         }
         
         secondHead = prev;
         ListNode firstHead = head;
         ListNode mergedHead = new ListNode();
         //head = [1,2,3,4,5]
         //firstHead = [1,2,3] => 1,2, 3
         //secondHed = [5,4,3] => 5,4, 3, null
         //f-next:2, 3, null
         //s-next:4, 3, null
         //merged: []->1->5->2->4->3
         while(secondHead != null){
            ListNode firstNext = firstHead.next;
            ListNode secondNext = secondHead.next;
             if(secondHead == firstHead){
                 mergedHead.next = firstHead;
                 secondHead = secondNext;
             }else{
                 mergedHead.next = firstHead;
                 mergedHead.next.next = secondHead;
                 mergedHead = mergedHead.next.next;
                 firstHead = firstNext;
                 secondHead = secondNext;
             }
         }
         

     }
 }


//두번째 방법 
// List<ListNode> 를 선언하고 여기에 node 들을 다 넣은후 palindrome 과 같은 방식으로 비교하면서 merge
//Time Complexity: O(N)
//Space Complexity: O(N)
//아래방법 cycle 생긴다는 error 가 남.... 확인해봐야한다. 
/*
class Solution{
    public void reorderList(ListNode head){
        if(head.next == null || head.next.next == null){
            return;
        }

        List<ListNode> nodes = new ArrayList<>();
        ListNode currNode = head;
        while(currNode != null){
            nodes.add(currNode);
            currNode = currNode.next;
        }

        int start = 0;
        int end = nodes.size() -1;
        ListNode reorderedHead = new ListNode(); //dummy node
        //head = [1,2,3,4,5]                head = [1,2,3,4]
        //start node: 1 ->2 -> 3            1->2->
        //end node  : 5 ->4 ->3             4->3
        //merged    :1->5->2->4->3->null,   1->4->2->3->null
        while(start <= end){ 
            ListNode startNode = nodes.get(start); //이렇게 copy 해서 써야 한다. 
            ListNode endNode = nodes.get(end);      // 직접 nodes.get(i) 를 사용하면 ListNode 에 cycle 에 생긴다는 에러가 남. head:Error - Found cycle in the ListNode
            if(start == end){ //Size is odd number, it is mid position
                //reorderedHead.next = nodes.get(start);
                reorderedHead.next = startNode;
                reorderedHead = reorderedHead.next;
            }else{
                //reorderedHead.next = nodes.get(start);
                //reorderedHead.next.next = nodes.get(end);
                reorderedHead.next = startNode;
                reorderedHead.next.next = endNode;
                reorderedHead = reorderedHead.next.next;
                start++;
                end--;
            }
        }
        reorderedHead.next = null; 
    }
}
*/

 //2022.11.04
 //Time Complexity: O(n)
 //Space Complexity: O(1)
/*
class Solution{
    public void reorderList(ListNode head){
        
        ListNode slow = head; //move one step
        ListNode fast = head; //move two step

        //find middle node
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;  
        }

        //reverse the second list (from middle node to end node)
        ListNode second = reverseList(slow);
        ListNode first = head;      
        
        //merge two lists
        ListNode temp;
        while(second.next != null){ // if second list reach the end, stop 
            temp = first.next;
            first.next = second;
            first = temp;

            temp = second.next; 
            second.next = first;
            second = temp;
        }
    }

    private ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        
        return pre;
    }
}
*/
 //Time Complexity: O(n)
 //Space Complexity: O(n)
 /*
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        ListNode mNode = head;
        int count = 0;
        HashMap<Integer, ListNode> map = new HashMap<>();
        while(mNode != null){
            map.put(count, mNode);
            count++;
            mNode = mNode.next;
        }

        int i = 0;
        ListNode dummyNode = new ListNode(0);

        while(i <= count/2){
            ListNode first = map.get(i);
            ListNode second = map.get(count -1 -i);
            if(first == second){ // middle node in case of odd number,
                dummyNode.next = first;
                dummyNode = dummyNode.next;
            }else{
                dummyNode.next = first;
                dummyNode.next.next = second;
                dummyNode = dummyNode.next.next;
            }

            i++;
        }
        dummyNode.next = null;

    }
}
*/