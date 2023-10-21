/**
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

//2023-10-20
//idea: Change left <-->right  with iterator
//TimeComplexity: O(N)
//Space Complexity: O(D) -> D is tree's diameter (Worse case is N/2 = O(N))
/*
 class Solution{
     public TreeNode invertTree(TreeNode root){
         if(root == null){
             return null;
         }
         
         Queue<TreeNode> bfs = new LinkedList<>();
         bfs.add(root);
         while(!bfs.isEmpty()){
             TreeNode leave = bfs.remove();
             
            TreeNode temp = leave.left;
            leave.left = leave.right;
            leave.right = temp;
            if(leave.left != null){
                bfs.add(leave.left);
            }
            if(leave.right != null){
                bfs.add(leave.right);
            }
         }
         return root;
     }
 }
*/

//idea: Change left <-->right  with recursive function
//TimeComplexity: O(N)
//Space Complexity: O(H) , H is recursive stack size = tree height

 class Solution{
     public TreeNode invertTree(TreeNode root){
         if(root == null){
             return null;
         }
         
         dfs(root);
         return root;
     }
     
     void dfs(TreeNode root){
         if(root == null){
             return;
         }
         
         TreeNode temp = root.left;
         root.left = root.right;
         root.right = temp;
         dfs(root.left);
         dfs(root.right);
     }
 }



 //2022-11-22
 //Recursive 
 //Time Complexity: O(N)
 //Space Complexity: O(N)
/*
 class Solution{
     public TreeNode invertTree(TreeNode root){

         recursiveInvert(root);

         return root;
     }

     public void recursiveInvert(TreeNode root){
         if(root == null){
             return;
         }

         TreeNode temp = root.left;
         root.left = root.right;
         root.right = temp;
        recursiveInvert(root.left);
        recursiveInvert(root.right);
     } 
 }
 */
 //Iteration case 
 //Time Complexity: O(N)
 //Space Complexity: O(N) <-- Queue 에 들어갈수 있는 max 값이 tree node 수 
 /*
class Solution{
    public TreeNode invertTree(TreeNode root){
        if(root == null){
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }

        return root;
    }
}
*/
//Leetcode solution - iteration
/*
class Solution {
    public TreeNode invertTree(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList();
        
        if(root == null){
            return root;
        }
        
        queue.offer(root);
        
        while(!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            TreeNode tempNode = curNode.left;
            curNode.left = curNode.right;
            curNode.right = tempNode;
            
            if(curNode.left != null){
                queue.offer(curNode.left);
            }
            if(curNode.right != null){
                queue.offer(curNode.right);
            }
        }
        
        return root;
        
    }
}
*/
//Leetcode solution - recursive
/*
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;
        
        return root;
        
    }
}
*/

//04.07 My Solution ---- recursive 이용
/*
class Solution {
    public TreeNode invertTree(TreeNode root) {
        
        if(root == null){
            return root;
        }
        
        invertNode(root);
          
        return root;
    }
    
    private void invertNode(TreeNode root){
        
        if(root == null){
            return;
        }
        
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        
        if(root.left != null){
            invertNode(root.left);
        }
        if(root.right != null){
            invertNode(root.right);
        }
    }
}

*/