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
 //Time Complexity: O(n)
 //Space Complexity: O(1)
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0){
            return null;
        }
        return buildBinaryTree(inorder, 0, inorder.length -1, postorder, 0, postorder.length -1);
    }

    private TreeNode buildBinaryTree(int[] inorder, int inStart, int inEnd, int[] postorder, int poStart, int poEnd){
        if(inStart > inEnd || poStart > poEnd){
            return null;
        }

        int mid = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(postorder[poEnd] == inorder[i]){
                mid = i;
                break;
            }
        }
        int leftSize = mid - inStart;
        int rightSize = inEnd - mid;

        TreeNode root = new TreeNode(postorder[poEnd]);
        root.left = buildBinaryTree(inorder, inStart, mid-1, postorder, poStart, poStart+leftSize-1);
        root.right = buildBinaryTree(inorder, mid+1, inEnd, postorder, poStart+leftSize, poEnd-1);

        return root;
    }
}