
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

//2023.09.24
//Time Complexity: O(n+m) , n is size of list1, m is size of list2
//Space Complexity: O(1)
class Solution{
    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        
        ListNode mergedList = new ListNode(0);
        ListNode head = mergedList;
        
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                head.next = list1;
                list1 = list1.next;
            }else{
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        
        if(list1 != null){
            head.next = list1;
        }else if(list2 != null){
            head.next = list2;
        }
        
        return mergedList.next;
    }
}


//Time Complexity: O(n+m) <- n: size of list1, m : size of list2
//Space Complexity: O(1)
/*
class Solution{
    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        
        ListNode head = new ListNode();
        ListNode merged = head;
        while(list1 != null || list2 != null){
            if(list1 == null){
                merged.next = list2;
                break;
            }else if(list2 == null){
                merged.next = list1;
                break;
            }else{
                if(list1.val <= list2.val){
                    merged.next = list1;
                    list1 = list1.next;
                }else{
                    merged.next = list2;
                    list2 = list2.next;
                }
                merged = merged.next;
            }            
        }
        
        return head.next;
    }
}
*/
//2022-09-14
//limitation : if list1 and list2 is null, return null, if one of them is null, reutrn the other

//Time Complexity: O(l1 size + l2 size), Space Complexity: O(1) (new 를 하지 않은 경우 )
/*
class Solution {
    
     public ListNode mergeTwoLists(ListNode list1, ListNode list2){
         
         if(list1 == null && list2 == null){
             return null;
         }
         if(list1 == null){
             return list2;
         }else if(list2 == null){
             return list1;
         }
         
         ListNode mergedLists = new ListNode(); 
         ListNode headNode = mergedLists;
         
         while((list1 != null) && (list2 != null)){
             
             //int val = 0;
             if(list1.val <= list2.val){
                 //val = list1.val;
                 mergedLists.next = list1;
                 list1 = list1.next;
             }else{
                 //val = list2.val;
                 mergedLists.next = list2;
                 list2 = list2.next;
             }
             
             //mergedLists.next = new ListNode(val); // Don't need to assign memory. 
             mergedLists = mergedLists.next;
         }
         
         if(list1 != null){
             mergedLists.next = list1;
         }else if(list2 != null){
             mergedLists.next = list2;
         }
         
         return headNode.next;
     }
}
*/
//limitation :  list1 or list2 null => 
//Time complexity: O(N +M ) : N is length of list1, M is length of list2
//Space complexity: O(1)  : 별도의 space 를 잡지 않았음. 
/*
class Solution {
    
     public ListNode mergeTwoLists(ListNode list1, ListNode list2){
         if(list1 == null){
             return list2;
         }else if( list2 == null){
             return list1;
         }
         
         ListNode mergedList, headList;
         headList = new ListNode();
         mergedList = headList;
         
         while(list1 != null && list2  != null){
             if(list1.val < list2.val){
                 headList.next = list1;
                 list1 = list1.next;
             }else{
                 headList.next = list2;
                 list2=list2.next;
             }
             headList = headList.next;
         }
         
         if(list1 != null){
             headList.next = list1;          
         }else{
             headList.next = list2;
         }
         
         return mergedList.next;
     }
}
*/

/*
class Solution {
    
     public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }else{
              
            ListNode preNode = new ListNode(-1);
            ListNode headNode = preNode;
            
            while((list1 != null) && (list2 != null)){
                if(list1.val <= list2.val){
                    preNode.next = list1;
                    list1 = list1.next;
                    
                }else{
                    preNode.next = list2;
                    list2 = list2.next;
                }
                preNode = preNode.next;
            }
            
            preNode.next = (list1 == null) ? list2 : list1;
            
            return headNode.next;
        }
     }
}
 */   
    //recursive
/*
class Solution {
    
      public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
          
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }else if (list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else{
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
      }
      */
    /*
    //2022.03.21 솔루션
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        
        ListNode mergedNodeHead;
        ListNode mergedNodeList;
        ListNode tempNode1, tempNode2;
        
        if(list1.val < list2.val){
            mergedNodeList = list1;
            tempNode1 = list1.next;
            tempNode2 = list2;
        }else{
            mergedNodeList = list2;
            tempNode1 = list1;
            tempNode2 = list2.next;
        }
        mergedNodeList.next = null;
        mergedNodeHead = mergedNodeList;
        
        while((tempNode1 != null) || (tempNode2!= null)){
           
            if(tempNode1 == null){
                mergedNodeList.next = tempNode2;
                return mergedNodeHead;
            }
            if(tempNode2 == null){
                mergedNodeList.next = tempNode1;
                return mergedNodeHead;
            }
            if(tempNode1.val < tempNode2.val){
                mergedNodeList.next = tempNode1;
                tempNode1 = tempNode1.next;                
            }else{
                mergedNodeList.next = tempNode2;
                tempNode2 = tempNode2.next;
            }
            mergedNodeList = mergedNodeList.next;
        }
        return mergedNodeHead;
    }

}
*/