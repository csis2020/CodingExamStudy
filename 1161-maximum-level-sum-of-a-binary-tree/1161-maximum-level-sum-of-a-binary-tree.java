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
/*
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
*/

//idea2 - DFS - recursive with List<> 
class Solution {
    
    public int maxLevelSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        List<List<Integer>> sumList = new ArrayList<>();
        makeSumList(root, sumList, 0);
        
        int maxSum = -100001;
        int maxSumLevel = 0;
        for(int i = 0; i < sumList.size(); i++){
            List<Integer> list = sumList.get(i);
            int sum = 0;
            for(int j = 0; j < list.size(); j++){
                sum += list.get(j);
            }
            if(sum > maxSum){
                maxSum = sum;
                maxSumLevel = i + 1;
            }
        }
        
        return maxSumLevel;
    }
    
    void makeSumList(TreeNode root, List<List<Integer>> sumList, int depth){
        if(root == null){
            return;
        }
        
        if(sumList.size() == depth){
            List<Integer> list = new ArrayList<>();
            sumList.add(list);
        }
        sumList.get(depth).add(root.val);
        makeSumList(root.left, sumList, depth+1);
        makeSumList(root.right, sumList, depth+1);        
    }
}
