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

//idea1 : recursive call - using postorder(왼쪽노드부터 시작) 
//Time Complexity: O(N)
//Space Complexity: O(H) - H is tree's height, recursive call stack size, the worse case is H = N, O(N)

class Solution {
    
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        
        recursiveFlatten(root);
    }
    
    TreeNode recursiveFlatten(TreeNode root){
        if(root == null){
            return null;
        }
        if(root.left == null && root.right == null){
            return root;
        }
        
        TreeNode leftEnd = recursiveFlatten(root.left);
        TreeNode rightEnd = recursiveFlatten(root.right);
        
        if(root.left != null){
            leftEnd.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        
        return rightEnd != null ? rightEnd : leftEnd;
    }
}

//Idea2 : recursive call - using postorder (오른쪽노드부터시작) search with global variable
//  leetcode Discuss 에 있던 best 답을 참조함. 
//Time Complexity: O(N)
//Space Complexity: O(H) - H is tree's height, recursive call stack size, the worse case is H = N, O(N)
/*
class Solution {
    
    TreeNode preNode = null;
    public void flatten(TreeNode root) {

        if(root == null){
            return;
        } 
        
        flatten(root.right);
        flatten(root.left);
        
        root.right = preNode;
        root.left = null;
        preNode = root;       
    }    
    
}
*/

//아래는 leetcode sulution 에서 T.C 가 O(N), S.C 가 O(1) 인 솔루션이다. 
//이해는 잘 못했음. 나중에 다시 봐야함. 
/* 
//알고리즘
1.So basically, this is going to be a super short algorithm and a short-er implementation :)

2.We use a pointer for traversing the nodes of our tree starting from the root. We have a loop that keeps going until the node pointer becomes null which is when we would be done processing the entire tree.

3.For every node we check if it has a left child or not. If it doesn't we simply move on to the right hand side i.e.

 node = node.right
 
4.If the node does have a left child, we find the first node on the rightmost branch of the left subtree which doesn't have a right child i.e. the almost rightmost node.

 rightmost = node.left
 while rightmost != null:
 rightmost = rightmost.right

5.Once we find this rightmost node, we rewire the connections as explained in the intuition section.

 rightmost.right = node.right
 node.right = node.left
 node.left = null
 
6. And we move on to the right node to continue processing of our tree.
*/
/*
class Solution {
   
    public void flatten(TreeNode root) {
        
        // Handle the null scenario
        if (root == null) {
            return;
        }
        
        TreeNode node = root;
        
        while (node != null) {
            
            // If the node has a left child
            if (node.left != null) {
                
                // Find the rightmost node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                
                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            
            // move on to the right side of the tree
            node = node.right;
        }
    }
}
*/
