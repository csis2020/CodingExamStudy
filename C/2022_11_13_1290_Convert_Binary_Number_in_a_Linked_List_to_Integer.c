/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 //C 로 작성 
//Time Complexity: O(N)
//Space Complexity: O(1)
int getDecimalValue(struct ListNode* head){
    
    int res = 0;
    while(head){
        res = res << 1;
        res += head->val;
        head = head->next;
    }
    return res;
}