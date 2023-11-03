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

//2023-10-23
//idea : recursive call 로 left tree depth + right depth = curr diameter 
//          이걸 반복해서 max 값을 save 
//Time Complexity: O(N)
//Space Complexity: O(N) - recursive call stack -  In the worst case, the tree is skewed so the height of the tree is O(N). If the tree is balanced, it'd be O(logN).

class Solution {
    
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        depthOfTree(root);
        return maxDiameter;
    }
    
    //input = [1,2,3,4,5] 
    //(left, right): 1(2(4(n-0,n-0)-1 5(n-0,n-0)-1)-2, 3(n,n)-1)-3
    //diameter: 0, 2,3
    //maxDiameter:0,2,3
    int depthOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        
        int leftDepth = depthOfTree(root.left);
        int rightDepth = depthOfTree(root.right);
        
        int diameter = leftDepth + rightDepth;
        
        maxDiameter = Math.max(diameter, maxDiameter);
        
        return Math.max(leftDepth, rightDepth)+1;
    }
}

//2022-06-16
//Limitation : root is null --> return 0;
//Leetcode solution 

//Time complexity: O(N),  Space complexity : O(N)
/*
class Solution {
    
    private int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        
        visitChildNodes(root);
            
        return maxDiameter;
        
    }
    
    private int visitChildNodes(TreeNode root){
        if(root == null){
            return 0;
        }
        
        int leftDepth = 0;
        int rightDepth = 0;
        
        if(root.left != null){
            leftDepth = visitChildNodes(root.left) + 1;
            
        }
        
        if(root.right != null){
            rightDepth = visitChildNodes(root.right) + 1;
            
        }     
        
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        
        return (leftDepth >= rightDepth) ? leftDepth : rightDepth; 
        
    }
}
*/


/*
class Solution {
    
    private int maxDiameter;
    public int diameterOfBinaryTree(TreeNode root) {
        
        maxDiameter = 0;
       
        visitChildNode(root);        
        
        return maxDiameter;
    }
    
    private int visitChildNode(TreeNode node){
        if(node == null){
            return 0;
        }
        
        int leftDepth = visitChildNode(node.left);
        int rightDepth = visitChildNode(node.right);
        
        //아래 방식 대신에 maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);  라고 한줄로 써도 된다. 
        int currentDiameter = leftDepth + rightDepth;
        if(maxDiameter < currentDiameter){
            maxDiameter = currentDiameter;
        }
        
        //아래 방식 대신에  return Math.max(leftDepth, rightDepth) + 1; 이라고 한줄에 써도 된다.
        int biggerDepth = (leftDepth > rightDepth) ? leftDepth : rightDepth;
        
        return (biggerDepth +1);
    }
}
*/