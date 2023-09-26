/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

//2023-09-25
//1) HashSet : first list -> hashset ,  compare this with second list. if there is contained node, it is intersect node
//2) 2-pointers
//  - distance between headA and intersected node = ditsA,
//  - distance between headB and intersected node = distB
//  - distance between interseccted node and tail =distC, 
// From listA: move (distA + distC) steps-> if node is null, null = headB, and then move again
// From listB: move (distB + distC) steps-> if node is null, null = headA, and then move again
// ==>  distA + distC + distB = distB + distC + distA 
// ==>  after move above steps, they meets the intersected node
//Time Complexity : O(N + M) , N is size of listA, M is size of listB (두 노드가 맨끝노드에서 만날경우 2바퀴 돌고 나서 만남. 2N + 2M => O(N+M))
//Space Complexity: O(1)

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null){
            return headB;
        }else if( headB == null){
            return headA;
        }
        
        ListNode currA = headA;
        ListNode currB = headB;
        
    //Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
    //currA: 4->1->8->4->5->null->5   ->6->1->8
    //currB: 5->6->1->8->4->5   ->null->4->1->8
        while(currA != currB){
            currA = (currA == null)? headB : currA.next;
            currB = (currB == null)? headA : currB.next;            
        }
        return currA;
    }
}

