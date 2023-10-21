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
//Idea1: DFS - recursive <-- 매 노드마다 subTree의 root 와 같은지 체크하고 값이 같으면 sameTree인지 수행. 즉 N x M 의 수행이 이루어지게 됨.  
//Time Complexity:O(N x M) , N is root의 node 수 , M is subRoot 의 node 수
//Space Complexity: O(M + N) , 이유는 아래에 
/*
There will be at most N recursive call to dfs ( or isSubtree). Now, each of these calls will have M recursive calls to isIdentical. Before calling isIdentical, our call stack has at most 
O(N) elements and might increase to O(N+M) during the call. After calling isIdentical, it will be back to at most O(N) since all elements made by isIdentical are popped out. Hence, the maximum number of elements in the call stack will be M+N.
*/
/*
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null){
            return true;
        }else if(root == null || subRoot == null){
            return false;
        }
        
        if(root.val == subRoot.val){
            if(isSame(root, subRoot)){
                return true;
            }
        }        
               
       if(isSubtree(root.left, subRoot)){
           return true;
       }

       return isSubtree(root.right,subRoot);
        
        
    }
    
    boolean isSame(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null){
            return true;
        }else if(root == null || subRoot == null){
            return false;
        }
        
        if(root.val != subRoot.val){
            return false;
        }        
        
        return isSame(root.left, subRoot.left) && isSame(root.right, subRoot.right);
    }
}
*/

//Idea1: BFS - QUEUE <-- 매 노드마다 체크를 해야할듯 
//Time Complexity:O(N x M) , N is root의 node 수 , M is subRoot 의 node 수
//

class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null || subRoot == null ){
            return false;
        }
        
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        while(!bfs.isEmpty()){
            TreeNode leave = bfs.remove();
            
            if(leave.val == subRoot.val){
                if(isSameTree(leave, subRoot)){
                    return true;
                }
            }
            if(leave.left != null){
                bfs.add(leave.left);
            }
            if(leave.right != null){
                bfs.add(leave.right);
            }           
        }
        
        return false;
    }
    
    //Queue

    boolean isSameTree(TreeNode root, TreeNode subRoot){
        
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        bfs.add(subRoot);
        while(!bfs.isEmpty()){
            TreeNode rootNode = bfs.remove();
            TreeNode subRootNode = bfs.remove();
            
            if(rootNode == null && subRootNode == null){
                continue;
            }else if(rootNode == null || subRootNode == null){
                return false;
            }
            
            if(rootNode.val != subRootNode.val){
                return false;
            }
            bfs.add(rootNode.left);
            bfs.add(subRootNode.left);
            bfs.add(rootNode.right);
            bfs.add(subRootNode.right);
        }
        return true;
    }

    //recursive
    /*
    boolean isSameTree(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null){
            return true;
        }else if(root == null || subRoot == null){
            return false;
        }
        
        if(root.val != subRoot.val){
            return false;
        }
        
        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }
    */
}


 //2022-11-23 - iteration
 //Time Complexity: O(NxM) <-- N 은 root tree 의 노드수, M 은 subRoot tree 의 노드수 
 //Space Complexity: O(N+M) 이라고 하네 
 /*
 There will be at most N recursive call to dfs ( or isSubtree). Now, each of these calls will have M recursive calls to isIdentical. Before calling isIdentical, our call stack has at most O(N) elements and might increase to O(N+M) during the call. After calling isIdentical, it will be back to at most O(N)since all elements made by isIdentical are popped out. Hence, the maximum number of elements in the call stack will be M+N
 */
/*
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null){
            return true;
        }else if(root == null ){
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            if(node.val == subRoot.val){
                if(isSametree(node, subRoot)){// the subRoot tree is included.
                    return true;
                }
            }
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }

        return false; 
    }

    private boolean isSametree(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null){
            return true;
        }else if( root == null || subRoot == null){
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);//add orginal node
        queue.add(subRoot);//add compared node

        while(!queue.isEmpty()){
            TreeNode orgNode = queue.remove();
            TreeNode comparedNode = queue.remove();

            if(orgNode == null && comparedNode == null){
                continue; //same
            }else if(orgNode == null || comparedNode == null){
                return false; // not same
            }

            if(orgNode.val != comparedNode.val){
                return false; //not same
            }

            queue.add(orgNode.left);
            queue.add(comparedNode.left);
            queue.add(orgNode.right);
            queue.add(comparedNode.right);
        }

        return true; //same
    }
}
*/