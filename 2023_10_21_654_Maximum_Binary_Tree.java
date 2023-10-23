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

//idea : using recursive 
//Time Complexity: O(N^2) -> check each element * search biggnest number
//  At each level of the recursive tree, we traverse over all the n elements to find the maximum element. In the average case, there will be a logN levels leading to a complexity of O(NlogN). In the worse case, the depth of the recursive tree can grow upto n, which happens in the case of a sorted nums array, giving a complexity of O(n^2)
//Space Complexity: O(N) -> recursive call stack is tree height. Everage case is O(logN) worse case is O(N)
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        
        if(nums == null || nums.length <= 0){
            return null;
        }
        
        return makeBinaryTree(nums, 0, nums.length -1);
        
    }
    
    //[3,2,1,6,0,5]
    //maxNumIndex: 3, 0, 1, 2, 4, 5 , 4
    //maxNum: 6, 3, 2,1, 0, 5, 0
    //(s,e): (0, 5), l(0,2-l(0,-1)r(1,2 - l(1,0)r(2,2)) )  r(4,5 -l(4,4)r(6,5)) 
    //Tree: 6-(3-(null,2-(null,1)), 5-(0,null))
    TreeNode makeBinaryTree(int[] nums, int start, int end){
        
        if(start > end){
            return null;
        }
        
        int maxNumIndex = start;
        int maxNum = nums[start];
        for(int i = start+1; i <= end; i++){
            if(maxNum < nums[i]){
                maxNumIndex = i;
                maxNum = nums[i];
            }
        }
        
        TreeNode node = new TreeNode(maxNum);
        node.left = makeBinaryTree(nums, start, maxNumIndex -1);
        node.right = makeBinaryTree(nums, maxNumIndex +1 , end);
        return node;
    }
}