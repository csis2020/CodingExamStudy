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

//2022.09.18
// Limitation: if root is null, return false
// Input: root = [2,1,3] --> Output: true
// Input: root = [5,1,4,null,null,3,6] --> Output: false

//Approach#1 - Top-down DFS
// Time Complexity: O(n), Space Complexity: O(n)
/*
class Solution {
    public boolean isValidBST(TreeNode root) {
        
        if(root == null){
            return false;
        }
        
        // initial minVal is null, initial maxVal is null <--- treenode 가 가질수 있는 value 의 범위가 Integer.MIN_VALUE, Integer.MAX_VALUE 를 포함하기때문에 이렇게 null 값을 이용해 양극단을 체크 하도록 함. 
        return validateTree(root, null, null);
        
    }
    
    boolean validateTree(TreeNode root, Integer minVal, Integer maxVal){
        
        if(root == null){
            return true;
        }
        
        if((minVal != null && minVal >= root.val) || (maxVal != null) && root.val >= maxVal){
            return false;
        } 
        
        if(!validateTree(root.left, minVal, root.val)){
            return false;
        }
        
        if(!validateTree(root.right, root.val, maxVal)){
            return false;
        }
        
        return true;
    }
}
*/

//Approach#2 - Inorder Traversal (left -> root -> right)
//Time Complexity: O(n), Space Complexity: O(n)
class Solution {
    
    Integer prevVal = null;
    
    public boolean isValidBST(TreeNode root) {
        
        if(root == null){
            return false;
        }
        
        return inorderTrav(root);
        
    }
    
    boolean inorderTrav(TreeNode root){
        if(root == null){
            return true;
        }
        
        if(!inorderTrav(root.left)){
            return false;
        }        
        
        if((prevVal != null ) && (root.val <= prevVal)){
            return false;
        }
        
        prevVal = root.val;
        
        if(!inorderTrav(root.right)){
            return false;
        }
        
        return true;    
    }
}


//아래 풀이는 다음경우에 error 발생 
// Input : [2147483647] -> Output : true 이여야 하는데 false 가 나옴. 
// [2147483647] <-- 이 값이 Integer.MAX_VALUE 이기 때문. 

//Approach#1 - Top-down DFS
/*
class Solution {
    public boolean isValidBST(TreeNode root) {
        
        if(root == null){
            return false;
        }
        
        int leftValue = Integer.MIN_VALUE;
        int rightValue = Integer.MAX_VALUE;
        
        return validate(root, leftValue, rightValue);
        
    }
    
    boolean validate(TreeNode root, int leftValue, int rightValue){
        
        if(root == null){ // there is no node
            return true;
        }
        
        if((leftValue >= root.val) || (root.val >= rightValue)){
            return false;
        }
        
        if(!validate(root.left, leftValue, root.val)){
            return false;
        }
        
        if(!validate(root.right, root.val, rightValue)){
            return false;
        }
        
        return true;
    }
}
*/
