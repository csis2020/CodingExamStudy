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

//2023-10-29
//idea : BFS using queue - 단, right node , left node 순으로 queue 에 넣기
//Time Complexity: O(N)
//Space Complexity: O(N) - tree's max width - the worst case: (N+1)/2

class Solution {
    public int findBottomLeftValue(TreeNode root) {
        if(root == null){
            return Integer.MIN_VALUE;
        }
        
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        int btmLeftValue = Integer.MIN_VALUE;
        while(!bfs.isEmpty()){
            int size = bfs.size();
            TreeNode node = null;
            for(int i = 0; i < size; i++){
                node = bfs.remove();
                if(node.right != null){
                    bfs.add(node.right);
                }
                if(node.left != null){
                    bfs.add(node.left);
                }
            }
            btmLeftValue = node.val;
        }
        
        return btmLeftValue;
    }
}


//idea2: DFS using recursive call - postorder traversal (left-right-root 순으로 )
//Time Complexity: O(N)
//Space Complexity: O(N) : tree's height - the average case is O(logN), the worst case is O(N)
/*
class Solution {
    int maxLevel = 0;
    int bottomLeftValue = Integer.MIN_VALUE;
    public int findBottomLeftValue(TreeNode root) {
        if(root == null){
            return Integer.MIN_VALUE;
        }
        
        dfs(root, 0);
        return bottomLeftValue;
    }
    
    void dfs(TreeNode root, int level){
        if(root == null){
            return;
        }
        
        if(maxLevel <= level){
            maxLevel = level;
            bottomLeftValue = root.val;
        }
        
        dfs(root.right, level+1);
        dfs(root.left, level+1);
    }
}
*/