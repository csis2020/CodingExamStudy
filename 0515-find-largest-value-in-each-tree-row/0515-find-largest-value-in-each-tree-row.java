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

//2023-10-25
//Idea: using BFS with Queue
//Time Complexity: O(N)
//Space Complexity: O(N) <-- the worst case is the full binary tree's bottom width = (N+1)/2 => O(N)
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        
        if(root == null){
            return list;
        }
        
        Queue<TreeNode> bfs = new ArrayDeque<>();
        bfs.add(root);
        while(!bfs.isEmpty()){
            int size = bfs.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++){
                TreeNode node = bfs.remove();
                max = Math.max(max, node.val);
                
                if(node.left != null){
                    bfs.add(node.left);
                }
                if(node.right != null){
                    bfs.add(node.right);
                }
            }
            list.add(max);
        }
        
        return list;
        
    }
}