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


//2023-10-20

//idea1 : DFS - recursive
//Time Complexity: O(N)
//Space Complexity: O(H) , H is tree height, worst case is H = N, O(N) <=  node 가 left 로만 있거나 right 로만 있는경우
/*
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        
        if(p == null || q == null){
            return false;
        }
        
        if(p.val != q.val){
            return false;
        }
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }    
    
}
*/

//idea2: BFS - queue
// Time Complexity : O(N)
// Space Complexity: O(D) , D is tree's diameter (트리의 가장 넓은 너비)
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(p);
        bfs.add(q);
        while(!bfs.isEmpty()){
            TreeNode pNode = bfs.remove();
            TreeNode qNode = bfs.remove();
            
            //Null 인경우 처리 필요
            if(pNode == null && qNode == null){
                continue;
            }else if(pNode == null || qNode == null){
                return false;
            }
            
            if(pNode.val != qNode.val){
                return false;
            }
            
            bfs.add(pNode.left);
            bfs.add(qNode.left);
            bfs.add(pNode.right);
            bfs.add(qNode.right);
        }
        
        return true;
    }
}

 //DFS
//Time Complexity: O(N)
//Space Complexity: O(트리높이) <-- node 가 left 로만 있거나 right 로만 있는경우가 worst 로 O(N)
/*
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }else if(p == null || q == null){
            return false;
        }

        if(p.val != q.val){
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
*/

//BFS
//Time Complexity:O(N)
//Space Complexity:O(트리 너비중 가장 넓을때) ???
//leetcode 설명은 아래와 같다. 
////Space complexity : O(N) in the worst case, where the tree is a perfect fully 
//balanced binary tree, since BFS will have to store at least an entire level of the tree 
//in the queue, and the last level has O(N) nodes.
/*
class Solution{
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }else if(p == null || q == null){
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // add p and q at the same time
        queue.add(p); 
        queue.add(q);
        while(!queue.isEmpty()){
            TreeNode first = queue.remove();
            TreeNode second = queue.remove();
            if(first == null && second == null){
                continue; //same
            }else if(first == null || second == null){
                return false; // is not same
            }else{
                if(first.val != second.val){
                    return false; // is not same
                }
                queue.add(first.left);
                queue.add(second.left);
                queue.add(first.right);
                queue.add(second.right);
            }
        }

        return true;
    }
}
*/