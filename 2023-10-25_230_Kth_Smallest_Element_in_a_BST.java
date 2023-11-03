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

//2023-10-24
//중요! 그냥 Binary tree 가 아니라 BST임! (Binary Search Tree) 는 Left child 가 root 보다 작고, Right child 가 root 보다 크다. Left child 내에서도 이게 반복됨. 중요한 점은, left sub tree 의 모든 node 값은 전체 트리의 root 보다 작음. right sub tree 의 모든 node 는 전체트리 root 보다 큼. 

//idea 1: iterative inorder traversal - Stack
//내가 만든 Stack 솔루션
//Time Complexity: O(H + K),  This complexity is defined by the stack, which contains at least H+k elements, since before starting to pop out one has to go down to a leaf. This results in O(logN+k) for the balanced tree and O(N+k) for completely unbalanced tree with all the nodes in the left subtree.
//Space Complexity: O(H)
/*
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if( root == null || k <= 0){
            return -1;
        }
        
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
        
            if(root != null && root.left != null){
                stack.push(root.left);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                count++;
                if(count == k){
                    return node.val;
                }
                if(node.right != null){
                    stack.push(node.right);
                }      
                root = node.right;
            }
        }
        return -1;
    }
}
*/
//leetcode 보고 참고해서 update한 stack solution 
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if( root == null || k <= 0){
            return -1;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        int count  = 0;
        stack.push(root);
        
        while(!stack.isEmpty()){
            
            while(root != null){
                if(root.left != null){
                    stack.push(root.left);
                }
                root = root.left;
            }
            
            TreeNode node = stack.pop();
            count++;
            if(count == k){
                return node.val;
            }
            root = node.right;
        }
        
        return -1;
    }
}


//idea 2: recursive call inorder traversal (left -root - right 순으로 값이 커짐)
//Time Complexity: O(H + K)
//Space Complexity: O(H) 
/*
class Solution {
    
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null || k <= 0){
            return -1;
        }
        
       return dfs(root, k);
    }
    
    int dfs(TreeNode root, int k){
        if(root == null){
            return -1;
        }
        
        int result = dfs(root.left, k);
        if(result < 0){
            count++;
            if(count == k){
               return root.val;
            }
            result = dfs(root.right, k);
        }

        return result;
    }
}
*/
 //2022-11-29
 //Time Complexity: O(N + K)
 //Space Complexity: O(N)
/*
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if(root == null || k < 1){
            return -1;
        }

        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                count++;
                if(count == k){
                    return node.val;
                }
                root = node.right;
            }
        }
        return -1;
    }
}
*/