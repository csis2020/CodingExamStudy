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

//limitation : Is it full binary tree?? No.

//Idea: bfs search using queue

//Time Complexity: O(N)
//Space Complexity: O(N)

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if(root == null){
            return result;
        }
        
        Queue<TreeNode> bfs = new LinkedList<>();
        
        bfs.add(root);
        
        while(!bfs.isEmpty()){
            int size = bfs.size();
            
            TreeNode leave = null;
            for(int i = 0; i < size; i++){
                leave = bfs.remove();
                if(leave.left != null){
                    bfs.add(leave.left);
                }
                if(leave.right != null){
                    bfs.add(leave.right);
                }
            }
            if(leave != null){
                result.add(leave.val);
            }
        }
        
        return result;
    }
}