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
 //2022-11-27
 //Time Complexity: O(N)
 //Space Complexity: O(logN) <-- left 와 right 쪽 각각 깊게 들어간 depth 가 logN , logN + logN 은 2LogN 으로 결국 O(logN)
 // Leetcode 설명 : The recursion stack requires O(log⁡N) space because the tree is height-balanced. 
 // Note that the O(N)space used to store the output does not count as auxiliary space,
 // so it is not included in the space complexity.


class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }

        return makeBinaryTree(nums, 0, nums.length -1);
    }

    private TreeNode makeBinaryTree(int[] nums, int start, int end){

        if(start > end){
            return null;
        }

        int rootPos = (end + start) /2;
        TreeNode root = new TreeNode(nums[rootPos]);
        root.left = makeBinaryTree(nums, start, rootPos-1);
        root.right = makeBinaryTree(nums, rootPos+1, end);
        return root;
    }
}