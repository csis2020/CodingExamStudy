private int maxLength = 0;
public int longestConsecutive(TreeNode root) {
    dfs(root, null, 0);
    return maxLength;
}

private void dfs(TreeNode p, TreeNode parent, int length) {
    if (p == null) return;
    length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
    maxLength = Math.max(maxLength, length);
    dfs(p.left, p, length);
    dfs(p.right, p, length);
}/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

  //2023-01-18---[4]
  //Bottom-up 방식
  //Time Complexity: O(N)
  //Space Complexity: O(N)
  class Solution{
      private int longestLength = 0;
      public int longestConsecutive(TreeNode root){
          checkNode(root);
          return longestLength;
      }

      private int checkNode(TreeNode root){
          if(root == null){
              return 0;
          }

          int rightLength = checkNode(root.right);
          int leftLength = checkNode(root.left);


          if(root.right != null && (root.val + 1) == root.right.val){
              rightLength++;
          }else{
              rightLength = 1;
          }
          if(root.left != null && (root.val + 1) == root.left.val){
              leftLength++;
          }else{
              leftLength = 1;
          }

        int curLength = Math.max(rightLength, leftLength);
        longestLength = Math.max(longestLength, curLength);

        return curLength;

      }
  }
  //2023-01-18---[3]
  //[idea] : recursive -check every node, if number is consecutive sequence, count the number 
  //[2] 번과 동일하나 global variable 을 없에고 parameter 로 처리  
  //Time Complexity: O(N)
  //Space Complexity: O(N)
  //바로 아래 solution 
  /*
    class Solution{
      //private int longestLength = 0;

        public int longestConsecutive(TreeNode root){
            return checkNode(root, null, 0, 0);
        }

        private int checkNode(TreeNode root, TreeNode parent, int count, int longestLength){
                
            if(root == null){
                return longestLength;
            }

            if(parent != null && (parent.val+1) == root.val){
                count++;
                //longestLength = Math.max(longestLength, count); //여기서만 계산하면 node가 1개인 경우 누락됨 
            }else{
                count = 1;
            }
            longestLength = Math.max(longestLength, count);// parent !=null 일때만 계산하면 
                                                    // TreeNode 가 1개인 경우가 계산에서 누락됨. 

            int rightLength = checkNode(root.right, root, count, longestLength);
            int leftLength = checkNode(root.left, root, count, longestLength); 

            return (rightLength > leftLength) ? rightLength : leftLength;
        }
    }
    */
//2023-01-18---[2]
      //[idea] : recursive -check every node, if number is consecutive sequence, count the number 
  //아래 [1]번 푼것과 동일아이디어, leetcode solution 참고하여 코드를 정리함 
  //Time Complexity: O(N)
  //Space Complexity: O(N)
  /*
  class Solution{
      private int longestLength = 0;

        public int longestConsecutive(TreeNode root){
            checkNode(root, null, 0);
            return longestLength;
        }

        private void checkNode(TreeNode root, TreeNode parent, int count){
                
            if(root == null){
                return;
            }

            if(parent != null && (parent.val+1) == root.val){
                count++;
                //longestLength = Math.max(longestLength, count); //여기서만 계산하면 node가 1개인 경우 누락됨 
            }else{
                count = 1;
            }
            longestLength = Math.max(longestLength, count);// parent !=null 일때만 계산하면 
                                                    // TreeNode 가 1개인 경우가 계산에서 누락됨. 

            checkNode(root.right, root, count);
            checkNode(root.left, root, count);       
        }
    }
    */
    //2023-01-18---[1]
 //[idea] : recursive -check every node, if number is consecutive sequence, count the number 
   //Time Complexity: O(N)
  //Space Complexity: O(N)
 /*
class Solution {
    private int longestLength = 0; 

    public int longestConsecutive(TreeNode root) {
        if(root == null){
            return 0;
        }
        checkNode(root, 1);
        return longestLength;
    }

    private void checkNode(TreeNode root, int count){
        
        if(root.right != null){
            if((root.val + 1) == root.right.val){//consecutive sequence
                checkNode(root.right, count+1);
            }else{
                longestLength = Math.max(longestLength, count);
                checkNode(root.right, 1);
            }
        }else{
            longestLength = Math.max(longestLength, count); // no more right node, need to check the longest length
        }
        if(root.left != null){
            if((root.val + 1) == root.left.val){//consecutive sequence
                checkNode(root.left, count+1);
            }else{
                longestLength = Math.max(longestLength, count);
                checkNode(root.left, 1);
            }
        }else{
            longestLength = Math.max(longestLength, count); // no more right node, need to check the longest length
        }
    }
}
*/