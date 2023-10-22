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

//2023-10-21
//idea: BFS using Queue
//Time Complexity: O(N)
//Space Complexity:O(D)  , D is tree's diameter. the worst case is O((N+1)/2) = O(N)
class Solution {
    public int maxLevelSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        int level = 1;
        int maxSum = -100001;
        int maxSumLevel = 0;
        while(!bfs.isEmpty()){
            int size = bfs.size();
            int levelSum = 0;
            for(int i = 0; i < size; i++){
                TreeNode node = bfs.remove();
                levelSum += node.val;
                if(node.left != null){
                    bfs.add(node.left);
                }
                if(node.right != null){
                    bfs.add(node.right);
                }
            }
            if(levelSum > maxSum){
                maxSum = levelSum;
                maxSumLevel = level;
            }
            level++;
        }
        
        return maxSumLevel;
    }
}